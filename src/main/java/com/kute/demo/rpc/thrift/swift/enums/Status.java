package com.kute.demo.rpc.thrift.swift.enums;

import com.facebook.swift.codec.*;

public enum Status
{
    PEED(0), GOOD(1), BAD(2), SO(3), ASD(10);

    private final int value;

    Status(int value)
    {
        this.value = value;
    }

    @ThriftEnumValue
    public int getValue()
    {
        return value;
    }
}
