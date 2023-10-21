package al.nya.reflect.libraries.srgreader.map;

import al.nya.reflect.libraries.reflectasm.commons.Method;

import java.util.ArrayList;

public class MapFindList extends ArrayList<MapNode> {
    public MapNode findClass(String mcpName){
        mcpName = mcpName.replace(".","/");
        for (MapNode mapNode : this) {
            if (mapNode.getNodeType() == NodeType.Class){
                if (mapNode.getMcp().equals(mcpName)){
                    return mapNode;
                }
            }
        }
        return null;
    }
    public MapNode findField(String classMcpName,String fieldMcpName){
        classMcpName = classMcpName.replace(".","/");
        for (MapNode mapNode : this) {
            if (mapNode.getNodeType() == NodeType.Field){
                if (mapNode.getMcp().equals(classMcpName+"/"+fieldMcpName)){
                    return mapNode;
                }
            }
        }
        return null;
    }
    public MethodNode findMethod(String classMcpName,String methodMcpName){
        classMcpName = classMcpName.replace(".","/");
        for (MapNode mapNode : this) {
            if (mapNode.getNodeType() == NodeType.Method){
                if (mapNode.getMcp().equals(classMcpName+"/"+methodMcpName)){
                    return (MethodNode)mapNode;
                }
            }
        }
        return null;
    }
    public MethodNode findMethod(String classMcpName,String methodMcpName,String signature){
        classMcpName = classMcpName.replace(".","/");
        for (MapNode mapNode : this) {
            if (mapNode.getNodeType() == NodeType.Method){
                if (mapNode.getMcp().equals(classMcpName+"/"+methodMcpName) && ((MethodNode)mapNode).getDeobfSig().equals(signature)){
                    return (MethodNode)mapNode;
                }
            }
        }
        return null;
    }
}
