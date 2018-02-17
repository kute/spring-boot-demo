package com.kute.demo.rpc.thrift.swift.domain;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("ResponseCode")
public final class ResponseCode
{
    @ThriftConstructor
    public ResponseCode(
        @ThriftField(value=1, name="code", requiredness=Requiredness.REQUIRED) final int code,
        @ThriftField(value=2, name="message", requiredness=Requiredness.REQUIRED) final String message
    ) {
        this.code = code;
        this.message = message;
    }

    public static class Builder {
        private int code;

        public Builder setCode(int code) {
            this.code = code;
            return this;
        }
        private String message;

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder() { }
        public Builder(ResponseCode other) {
            this.code = other.code;
            this.message = other.message;
        }

        public ResponseCode build() {
            return new ResponseCode (
                this.code,
                this.message
            );
        }
    }

    private final int code;

    @ThriftField(value=1, name="code", requiredness=Requiredness.REQUIRED)
    public int getCode() { return code; }

    private final String message;

    @ThriftField(value=2, name="message", requiredness=Requiredness.REQUIRED)
    public String getMessage() { return message; }

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
