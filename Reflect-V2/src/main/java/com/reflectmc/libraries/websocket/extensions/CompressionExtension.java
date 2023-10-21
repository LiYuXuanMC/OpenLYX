package com.reflectmc.libraries.websocket.extensions;

import com.reflectmc.libraries.websocket.exceptions.InvalidDataException;
import com.reflectmc.libraries.websocket.exceptions.InvalidFrameException;
import com.reflectmc.libraries.websocket.framing.ControlFrame;
import com.reflectmc.libraries.websocket.framing.DataFrame;
import com.reflectmc.libraries.websocket.framing.Framedata;


public abstract class CompressionExtension extends DefaultExtension {

  @Override
  public void isFrameValid(Framedata inputFrame) throws InvalidDataException {
    if ((inputFrame instanceof DataFrame) && (inputFrame.isRSV2() || inputFrame.isRSV3())) {
      throw new InvalidFrameException(
          "bad rsv RSV1: " + inputFrame.isRSV1() + " RSV2: " + inputFrame.isRSV2() + " RSV3: "
              + inputFrame.isRSV3());
    }
    if ((inputFrame instanceof ControlFrame) && (inputFrame.isRSV1() || inputFrame.isRSV2()
        || inputFrame.isRSV3())) {
      throw new InvalidFrameException(
          "bad rsv RSV1: " + inputFrame.isRSV1() + " RSV2: " + inputFrame.isRSV2() + " RSV3: "
              + inputFrame.isRSV3());
    }
  }
}
