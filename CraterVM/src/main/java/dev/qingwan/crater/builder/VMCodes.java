package dev.qingwan.crater.builder;

import java.util.function.Consumer;

public enum VMCodes {
    ADD_I,

    LOAD,
    STORE,
    POP,

    RETURN,

    GET,
    GET_STATIC,
    GET_INTERNAL,
    GET_INTERNAL_STATIC,
    GET_INTERNAL_STATIC_INTERNAL,
    CALL_INTERNAL,
    CALL,
    ;
}
