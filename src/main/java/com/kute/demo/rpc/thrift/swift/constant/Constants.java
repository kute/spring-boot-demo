package com.kute.demo.rpc.thrift.swift.constant;

import com.facebook.swift.codec.*;
import com.google.common.collect.*;
import java.util.*;

public final class Constants
{
    private Constants() {
    }

    public static final Map<String, String> MAP_CONST = ImmutableMap.<String, String>builder()
        .put("goodnight", "moon")
        .put("hello", "world")
        .build();

    public static final double VERSION = 1.0;
}
