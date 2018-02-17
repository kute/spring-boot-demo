package com.kute.demo.rpc.thrift.swift.constant;

import com.facebook.swift.codec.*;
import com.google.common.collect.*;
import java.util.*;

public final class Constants
{
    private Constants() {
    }

    public static final Map<String, String> MAP_CONST = ImmutableMap.<String, String>builder()
        .put("hello", "world")
        .put("goodnight", "moon")
        .build();

    public static final double VERSION = 1.0;
}
