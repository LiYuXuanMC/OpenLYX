package com.reflectmc.reflect.resource;

import com.reflectmc.loader.packet.irc.C04RequestDownloadResource;
import com.reflectmc.loader.packet.irc.S04ServerData;
import com.reflectmc.reflect.Reflect;
import com.reflectmc.reflect.socket.irc.IServerPacketProcessor;
import com.reflectmc.reflect.utils.render.ByteImageLocation;
import com.reflectmc.reflect.wrapper.Wrapper;
import com.reflectmc.reflect.wrapper.mapper.Environment;

import java.util.ArrayList;
import java.util.List;

public class ResourceManager {
    private final List<Resource> resources = new ArrayList<>();
    private volatile boolean downloaded;
    public static ByteImageLocation logo;
    public ResourceManager(){

    }
    public void init(){
        switch (Wrapper.getMapper().getVersion()) {
            case Forge189:
                resources.add(new Resource("189Srg.srg","map"));
                break;
            case Vanilla189:
                resources.add(new Resource("189Notch.srg","map"));
                break;
            case Forge1122:
                resources.add(new Resource("1122Srg.srg","map"));
                break;
        }
        if (Wrapper.getMapper().hasAntiCheat(Environment.NPlusAntiCheat)){
            resources.add(new Resource("DeobfuscationKit.zip"));
        }
        resources.add(new Resource("Baloo.ttf"));
        resources.add(new Resource("Verdana.ttf"));
        resources.add(new Resource("Arial.ttf"));
        resources.add(new Resource("RobotoLight.ttf"));
        resources.add(new Resource("ReflectUIFont.ttf"));
        resources.add(new Resource("logo.png"));
        IServerPacketProcessor processor = (IServerPacketProcessor) Reflect.getINSTANCE().getServerSocket().getPacketProcessor();
        processor.addPacketHook((packet -> onDataPacket((S04ServerData) packet)),S04ServerData.class);

        download();
        while (!downloaded);
        logo = new ByteImageLocation(getResource("logo.png").getBuffer().array(),"logo");
    }
    public Resource getResource(String name){
        for (Resource resource : resources) {
            if (resource.getName().equals(name)){
                return resource;
            }
        }
        return null;
    }
    public void onDataPacket(S04ServerData serverData){
        Resource resource = getResource(serverData.getName());
        switch (serverData.getStatus()){
            case INFO:
                resource.setMd5(serverData.getMd5());
                resource.allocateBuffer(serverData.getLength());
                break;
            case DATA:
                resource.writeBuffer(serverData.getBytes());
                break;
            case END:
                resource.uncompress();
                System.out.println(resource.getName()+ " Downloaded");
                download();
                break;
        }
    }
    public Resource getResourceByAlias(String alias){
        for (Resource resource : resources) {
            if (alias.equals(resource.getAlias()))
                return resource;
        }
        return null;
    }
    public void download(){
        int index = 0;
        for (Resource resource : resources) {
            index += 1;
            Reflect.getINSTANCE().getInjectorSocket().updateProgress("Download Resource",index,resources.size());
            if (resource.getBuffer() != null){
                if (!resource.md5Check()){
                    download(resource);
                    return;
                }
            }else {
                download(resource);
                return;
            }
        }
        downloaded = true;
    }
    public void download(Resource resource){
        Reflect.getINSTANCE().getServerSocket().getPacketProcessor().send(new C04RequestDownloadResource(resource.getName()));
    }
}
