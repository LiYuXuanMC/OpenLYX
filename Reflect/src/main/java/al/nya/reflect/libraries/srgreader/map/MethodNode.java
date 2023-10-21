package al.nya.reflect.libraries.srgreader.map;


public class MethodNode extends MapNode{
    private Signature sig;
    private String orgSig;
    private String deobfSig;
    public MethodNode(NodeType nodeType, String mcp, String srg, Signature sig,String orgSig,String deobfSig) {
        super(nodeType, mcp, srg);
        this.sig = sig;
        this.orgSig = orgSig;
        this.deobfSig = deobfSig;
    }

    public String getDeobfSig() {
        return deobfSig;
    }

    public Signature getSignature() {
        return sig;
    }

    public String getOrgSig() {
        return orgSig;
    }
}
