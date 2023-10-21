package com.reflectmc.libraries.websocket.client;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

public interface DnsResolver {

  InetAddress resolve(URI uri) throws UnknownHostException;

}
