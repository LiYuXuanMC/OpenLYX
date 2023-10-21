package com.reflectmc.libraries.websocket.extensions.permessage_deflate;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import com.reflectmc.libraries.websocket.enums.Opcode;
import com.reflectmc.libraries.websocket.exceptions.InvalidDataException;
import com.reflectmc.libraries.websocket.exceptions.InvalidFrameException;
import com.reflectmc.libraries.websocket.extensions.CompressionExtension;
import com.reflectmc.libraries.websocket.extensions.ExtensionRequestData;
import com.reflectmc.libraries.websocket.extensions.IExtension;
import com.reflectmc.libraries.websocket.framing.BinaryFrame;
import com.reflectmc.libraries.websocket.framing.CloseFrame;
import com.reflectmc.libraries.websocket.framing.ContinuousFrame;
import com.reflectmc.libraries.websocket.framing.DataFrame;
import com.reflectmc.libraries.websocket.framing.Framedata;
import com.reflectmc.libraries.websocket.framing.FramedataImpl1;
import com.reflectmc.libraries.websocket.framing.TextFrame;


public class PerMessageDeflateExtension extends CompressionExtension {

  // Name of the extension as registered by IETF https://tools.ietf.org/html/rfc7692#section-9.
  private static final String EXTENSION_REGISTERED_NAME = "permessage-deflate";
  // Below values are defined for convenience. They are not used in the compression/decompression phase.
  // They may be needed during the extension-negotiation offer in the future.
  private static final String SERVER_NO_CONTEXT_TAKEOVER = "server_no_context_takeover";
  private static final String CLIENT_NO_CONTEXT_TAKEOVER = "client_no_context_takeover";
  private static final String SERVER_MAX_WINDOW_BITS = "server_max_window_bits";
  private static final String CLIENT_MAX_WINDOW_BITS = "client_max_window_bits";
  private static final int serverMaxWindowBits = 1 << 15;
  private static final int clientMaxWindowBits = 1 << 15;
  private static final byte[] TAIL_BYTES = {(byte) 0x00, (byte) 0x00, (byte) 0xFF, (byte) 0xFF};
  private static final int BUFFER_SIZE = 1 << 10;

  private int threshold = 1024;

  private boolean serverNoContextTakeover = true;
  private boolean clientNoContextTakeover = false;

  // For WebSocketServers, this variable holds the extension parameters that the peer client has requested.
  // For WebSocketClients, this variable holds the extension parameters that client himself has requested.
  private Map<String, String> requestedParameters = new LinkedHashMap<>();

  private Inflater inflater = new Inflater(true);
  private Deflater deflater = new Deflater(Deflater.DEFAULT_COMPRESSION, true);

  public Inflater getInflater() {
    return inflater;
  }

  public void setInflater(Inflater inflater) {
    this.inflater = inflater;
  }

  public Deflater getDeflater() {
    return deflater;
  }

  public void setDeflater(Deflater deflater) {
    this.deflater = deflater;
  }

  
  public int getThreshold() {
    return threshold;
  }

  
  public void setThreshold(int threshold) {
    this.threshold = threshold;
  }

  
  public boolean isServerNoContextTakeover() {
    return serverNoContextTakeover;
  }

  
  public void setServerNoContextTakeover(boolean serverNoContextTakeover) {
    this.serverNoContextTakeover = serverNoContextTakeover;
  }

  
  public boolean isClientNoContextTakeover() {
    return clientNoContextTakeover;
  }

  
  public void setClientNoContextTakeover(boolean clientNoContextTakeover) {
    this.clientNoContextTakeover = clientNoContextTakeover;
  }

  
  @Override
  public void decodeFrame(Framedata inputFrame) throws InvalidDataException {
    // Only DataFrames can be decompressed.
    if (!(inputFrame instanceof DataFrame)) {
      return;
    }

    if (!inputFrame.isRSV1() && inputFrame.getOpcode() != Opcode.CONTINUOUS) {
      return;
    }

    // RSV1 bit must be set only for the first frame.
    if (inputFrame.getOpcode() == Opcode.CONTINUOUS && inputFrame.isRSV1()) {
      throw new InvalidDataException(CloseFrame.POLICY_VALIDATION,
          "RSV1 bit can only be set for the first frame.");
    }

    // Decompressed output buffer.
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    try {
      decompress(inputFrame.getPayloadData().array(), output);

      
      if (inflater.getRemaining() > 0) {
        inflater = new Inflater(true);
        decompress(inputFrame.getPayloadData().array(), output);
      }

      if (inputFrame.isFin()) {
        decompress(TAIL_BYTES, output);
        // If context takeover is disabled, inflater can be reset.
        if (clientNoContextTakeover) {
          inflater = new Inflater(true);
        }
      }
    } catch (DataFormatException e) {
      throw new InvalidDataException(CloseFrame.POLICY_VALIDATION, e.getMessage());
    }

    // Set frames payload to the new decompressed data.
    ((FramedataImpl1) inputFrame)
        .setPayload(ByteBuffer.wrap(output.toByteArray(), 0, output.size()));
  }

  
  private void decompress(byte[] data, ByteArrayOutputStream outputBuffer)
      throws DataFormatException {
    inflater.setInput(data);
    byte[] buffer = new byte[BUFFER_SIZE];

    int bytesInflated;
    while ((bytesInflated = inflater.inflate(buffer)) > 0) {
      outputBuffer.write(buffer, 0, bytesInflated);
    }
  }

  @Override
  public void encodeFrame(Framedata inputFrame) {
    // Only DataFrames can be decompressed.
    if (!(inputFrame instanceof DataFrame)) {
      return;
    }

    byte[] payloadData = inputFrame.getPayloadData().array();
    if (payloadData.length < threshold) {
      return;
    }
    // Only the first frame's RSV1 must be set.
    if (!(inputFrame instanceof ContinuousFrame)) {
      ((DataFrame) inputFrame).setRSV1(true);
    }

    deflater.setInput(payloadData);
    // Compressed output buffer.
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    // Temporary buffer to hold compressed output.
    byte[] buffer = new byte[1024];
    int bytesCompressed;
    while ((bytesCompressed = deflater.deflate(buffer, 0, buffer.length, Deflater.SYNC_FLUSH))
        > 0) {
      output.write(buffer, 0, bytesCompressed);
    }

    byte[] outputBytes = output.toByteArray();
    int outputLength = outputBytes.length;

    
    if (inputFrame.isFin()) {
      if (endsWithTail(outputBytes)) {
        outputLength -= TAIL_BYTES.length;
      }

      if (serverNoContextTakeover) {
        deflater.end();
        deflater = new Deflater(Deflater.DEFAULT_COMPRESSION, true);
      }
    }

    // Set frames payload to the new compressed data.
    ((FramedataImpl1) inputFrame).setPayload(ByteBuffer.wrap(outputBytes, 0, outputLength));
  }

  
  private static boolean endsWithTail(byte[] data) {
    if (data.length < 4) {
      return false;
    }

    int length = data.length;
    for (int i = 0; i < TAIL_BYTES.length; i++) {
      if (TAIL_BYTES[i] != data[length - TAIL_BYTES.length + i]) {
        return false;
      }
    }

    return true;
  }

  @Override
  public boolean acceptProvidedExtensionAsServer(String inputExtension) {
    String[] requestedExtensions = inputExtension.split(",");
    for (String extension : requestedExtensions) {
      ExtensionRequestData extensionData = ExtensionRequestData.parseExtensionRequest(extension);
      if (!EXTENSION_REGISTERED_NAME.equalsIgnoreCase(extensionData.getExtensionName())) {
        continue;
      }

      // Holds parameters that peer client has sent.
      Map<String, String> headers = extensionData.getExtensionParameters();
      requestedParameters.putAll(headers);
      if (requestedParameters.containsKey(CLIENT_NO_CONTEXT_TAKEOVER)) {
        clientNoContextTakeover = true;
      }

      return true;
    }

    return false;
  }

  @Override
  public boolean acceptProvidedExtensionAsClient(String inputExtension) {
    String[] requestedExtensions = inputExtension.split(",");
    for (String extension : requestedExtensions) {
      ExtensionRequestData extensionData = ExtensionRequestData.parseExtensionRequest(extension);
      if (!EXTENSION_REGISTERED_NAME.equalsIgnoreCase(extensionData.getExtensionName())) {
        continue;
      }

      // Holds parameters that are sent by the server, as a response to our initial extension request.
      Map<String, String> headers = extensionData.getExtensionParameters();
      // After this point, parameters that the server sent back can be configured, but we don't use them for now.
      return true;
    }

    return false;
  }

  @Override
  public String getProvidedExtensionAsClient() {
    requestedParameters.put(CLIENT_NO_CONTEXT_TAKEOVER, ExtensionRequestData.EMPTY_VALUE);
    requestedParameters.put(SERVER_NO_CONTEXT_TAKEOVER, ExtensionRequestData.EMPTY_VALUE);

    return EXTENSION_REGISTERED_NAME + "; " + SERVER_NO_CONTEXT_TAKEOVER + "; "
        + CLIENT_NO_CONTEXT_TAKEOVER;
  }

  @Override
  public String getProvidedExtensionAsServer() {
    return EXTENSION_REGISTERED_NAME
        + "; " + SERVER_NO_CONTEXT_TAKEOVER
        + (clientNoContextTakeover ? "; " + CLIENT_NO_CONTEXT_TAKEOVER : "");
  }

  @Override
  public IExtension copyInstance() {
    return new PerMessageDeflateExtension();
  }

  
  @Override
  public void isFrameValid(Framedata inputFrame) throws InvalidDataException {
    if ((inputFrame instanceof ContinuousFrame) && (inputFrame.isRSV1() || inputFrame.isRSV2()
        || inputFrame.isRSV3())) {
      throw new InvalidFrameException(
          "bad rsv RSV1: " + inputFrame.isRSV1() + " RSV2: " + inputFrame.isRSV2() + " RSV3: "
              + inputFrame.isRSV3());
    }
    super.isFrameValid(inputFrame);
  }

  @Override
  public String toString() {
    return "PerMessageDeflateExtension";
  }


}
