package com.reflectmc.verify.packet;

public enum PacketType {
    Game,//Game -> Injector
    Client,//Client -> Server
    Injector,//Injector -> Verify
    Server,
    Verify,
}
