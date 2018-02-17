package com.kute.demo.rpc.thrift.swift.exception;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

@ThriftStruct("InvalidOperation")
public final class InvalidOperation extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    @ThriftConstructor
    public InvalidOperation(
        @ThriftField(value=1, name="what", requiredness=Requiredness.NONE) final int what,
        @ThriftField(value=2, name="why", requiredness=Requiredness.NONE) final String why
    ) {
        this.what = what;
        this.why = why;
    }

    public static class Builder {
        private int what;

        public Builder setWhat(int what) {
            this.what = what;
            return this;
        }
        private String why;

        public Builder setWhy(String why) {
            this.why = why;
            return this;
        }

        public Builder() { }
        public Builder(InvalidOperation other) {
            this.what = other.what;
            this.why = other.why;
        }

        public InvalidOperation build() {
            return new InvalidOperation (
                this.what,
                this.why
            );
        }
    }

    private final int what;

    @ThriftField(value=1, name="what", requiredness=Requiredness.NONE)
    public int getWhat() { return what; }

    private final String why;

    @ThriftField(value=2, name="why", requiredness=Requiredness.NONE)
    public String getWhy() { return why; }
}
