package com.reflectmc.verify.resource;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.simple.SimpleLoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ResourceManager {
    private Logger logger = new SimpleLoggerFactory().getLogger("ResourceManager");
    private File resourceDir;
    @Getter
    private List<Resource> resources = new ArrayList<Resource>();
    public ResourceManager(){

    }
    public void initAsMainServer(){
        logger.info("Init ResourceManager as MainServer");
        resourceDir = new File("./resource");
        if (!resourceDir.exists()){
            if (!resourceDir.mkdir()){
                logger.error("Init ResourceManager fail (resource dir cannot be create)");
                return;
            }
        }
        logger.info("Loading resource");
        resources = listFiles(resourceDir);
        for (Resource file : resources) {
            logger.info("Loading {}",file.getName());
            readFile(file);
        }
    }
    private boolean readFile(Resource resource) {
        File file = new File(resourceDir,resource.getName());
        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            fis.close();
            resource.setBytes(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Read file {} fail",resource.getName());
            return false;
        }
        return true;
    }
    private List<Resource> listFiles(File rootDir){
        List<Resource> files = new ArrayList<>();
        for (File file : rootDir.listFiles()) {
            if (file.isFile()){
                files.add(new Resource(simpleName(file.getAbsolutePath())));
            }
            if (file.isDirectory()){
                files.addAll(listFiles(file));
            }
        }
        return files;
    }
    public Resource getResource(String name){
        for (Resource resource : resources) {
            if (resource.getName().equals(name)){
                return resource;
            }
        }
        return null;
    }
    private String simpleName(String name){
        return name.split("resource")[1].replaceFirst("\\\\","");
    }
}
