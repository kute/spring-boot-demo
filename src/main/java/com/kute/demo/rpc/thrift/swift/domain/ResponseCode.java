package com.kute.demo.rpc.thrift.swift.domain;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("ResponseCode")
public final class ResponseCode
{
    public ResponseCode() {
    }

    private int code;

    @ThriftField(value=1, name="code", requiredness=Requiredness.REQUIRED)
    public int getCode() { return code; }

    @ThriftField
    public void setCode(final int code) { this.code = code; }

    private String message;

    @ThriftField(value=2, name="message", requiredness=Requiredness.REQUIRED)
    public String getMessage() { return message; }

    @ThriftField
    public void setMessage(final String message) { this.message = message; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("code", code)
            .add("message", message)
            .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ResponseCode other = (ResponseCode)o;

        return
            Objects.equals(code, other.code) &&
            Objects.equals(message, other.message);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            code,
            message
        });
    }
}
