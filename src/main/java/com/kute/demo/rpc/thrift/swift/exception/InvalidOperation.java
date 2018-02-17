package com.kute.demo.rpc.thrift.swift.exception;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

@ThriftStruct("InvalidOperation")
public final class InvalidOperation extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public InvalidOperation() {
    }

    private int what;

    @ThriftField(value=1, name="what", requiredness=Requiredness.NONE)
    public int getWhat() { return what; }

    @ThriftField
    public void setWhat(final int what) { this.what = what; }

    private String why;

    @ThriftField(value=2, name="why", requiredness=Requiredness.NONE)
    public String getWhy() { return why; }

    @ThriftField
    public void setWhy(final String why) { this.why = why; }
}
