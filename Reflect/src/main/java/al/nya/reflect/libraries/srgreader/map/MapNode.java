package al.nya.reflect.libraries.srgreader.map;

public class MapNode {
    private NodeType nodeType;
    private String mcp;
    private String srg;
    public MapNode(NodeType nodeType,String mcp,String srg){
        this.nodeType = nodeType;
        this.mcp = mcp;
        this.srg = srg;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public String getMcp() {
        return mcp;
    }

    public String getSrg() {
        return srg;
    }
}
