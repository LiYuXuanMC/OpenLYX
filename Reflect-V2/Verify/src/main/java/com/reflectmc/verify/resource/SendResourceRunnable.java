package com.reflectmc.verify.resource;

import com.reflectmc.verify.Main;
import com.reflectmc.verify.Reference;
import com.reflectmc.verify.packet.client.ClientHandler;
import com.reflectmc.verify.packet.client.S04ServerData;
import org.slf4j.Logger;
import org.slf4j.simple.SimpleLoggerFactory;

import java.nio.ByteBuffer;

public class SendResourceRunnable implements Runnable {
    private ClientHandler client;
    private String resourceName;
    private Logger logger = new SimpleLoggerFactory().getLogger("Resource");
    public SendResourceRunnable(ClientHandler client,String resource){
        this.client = client;
        this.resourceName = resource;
    }
    @Override
    public void run() {
        Resource resource = Main.getResourceManager().getResource(resourceName);
        if (resource == null){
            client.sendPacket(new S04ServerData(S04ServerData.Status.INFO,resourceName,"",0));
            client.sendPacket(new S04ServerData(S04ServerData.Status.END,resourceName));
            return;
        }
        client.sendPacket(new S04ServerData(S04ServerData.Status.INFO,resourceName,resource.getMd5(),resource.getBytes().length));
        ByteBuffer buffer = ByteBuffer.wrap(resource.getBytes());
        while (buffer.hasRemaining()){
            byte[] bytes;
            if (buffer.remaining() < Reference.MAXMIUM_DATALENGTH){
                bytes = new byte[buffer.remaining()];
            }else {
                bytes = new byte[Reference.MAXMIUM_DATALENGTH];
            }
            buffer.get(bytes);
            client.sendPacket(new S04ServerData(S04ServerData.Status.DATA,resourceName,bytes));
        }
        client.sendPacket(new S04ServerData(S04ServerData.Status.END,resourceName));
        logger.info("Finish send "+resource.getName()+" MD5:"+resource.getMd5()+" to "+client.getUser().getUsername());
    }
}
