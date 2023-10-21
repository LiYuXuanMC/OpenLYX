package com.reflectmc.loader.utils;

import java.net.URI;
import java.net.URISyntaxException;

public class UnsafeCast {
    public static URI url2URI(String url){
        try {
            return new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
