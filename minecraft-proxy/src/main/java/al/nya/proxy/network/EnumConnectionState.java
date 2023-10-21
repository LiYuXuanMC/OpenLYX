package al.nya.proxy.network;

public enum EnumConnectionState {
    Handshaking(0),
    Status(1),
    Login(2),
    Play(3);

    public int i;

    EnumConnectionState(int i) {
        this.i = i;
    }
}
