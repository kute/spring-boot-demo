package com.kute.demo.rpc.thrift.swift.domain;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("UserInfo")
public final class UserInfo
{
    @ThriftConstructor
    public UserInfo(
        @ThriftField(value=1, name="id", requiredness=Requiredness.REQUIRED) final int id,
        @ThriftField(value=2, name="login", requiredness=Requiredness.REQUIRED) final String login,
        @ThriftField(value=3, name="name", requiredness=Requiredness.REQUIRED) final String name,
        @ThriftField(value=4, name="mobile", requiredness=Requiredness.REQUIRED) final String mobile,
        @ThriftField(value=5, name="isAdmin", requiredness=Requiredness.NONE) final boolean isAdmin,
        @ThriftField(value=6, name="location", requiredness=Requiredness.OPTIONAL) final String location
    ) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.mobile = mobile;
        this.isAdmin = isAdmin;
        this.location = location;
    }

    public static class Builder {
        private int id;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }
        private String login;

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }
        private String name;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        private String mobile;

        public Builder setMobile(String mobile) {
            this.mobile = mobile;
            return this;
        }
        private boolean isAdmin;

        public Builder setIsAdmin(boolean isAdmin) {
            this.isAdmin = isAdmin;
            return this;
        }
        private String location;

        public Builder setLocation(String location) {
            this.location = location;
            return this;
        }

        public Builder() { }
        public Builder(UserInfo other) {
            this.id = other.id;
            this.login = other.login;
            this.name = other.name;
            this.mobile = other.mobile;
            this.isAdmin = other.isAdmin;
            this.location = other.location;
        }

        public UserInfo build() {
            return new UserInfo (
                this.id,
                this.login,
                this.name,
                this.mobile,
                this.isAdmin,
                this.location
            );
        }
    }

    private final int id;

    @ThriftField(value=1, name="id", requiredness=Requiredness.REQUIRED)
    public int getId() { return id; }

    private final String login;

    @ThriftField(value=2, name="login", requiredness=Requiredness.REQUIRED)
    public String getLogin() { return login; }

    private final String name;

    @ThriftField(value=3, name="name", requiredness=Requiredness.REQUIRED)
    public String getName() { return name; }

    private final String mobile;

    @ThriftField(value=4, name="mobile", requiredness=Requiredness.REQUIRED)
    public String getMobile() { return mobile; }

    private final boolean isAdmin;

    @ThriftField(value=5, name="isAdmin", requiredness=Requiredness.NONE)
    public boolean isIsAdmin() { return isAdmin; }

    private final String location;

    @ThriftField(value=6, name="location", requiredness=Requiredness.OPTIONAL)
    public String getLocation() { return location; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("id", id)
            .add("login", login)
            .add("name", name)
            .add("mobile", mobile)
            .add("isAdmin", isAdmin)
            .add("location", location)
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

        UserInfo other = (UserInfo)o;

        return
            Objects.equals(id, other.id) &&
            Objects.equals(login, other.login) &&
            Objects.equals(name, other.name) &&
            Objects.equals(mobile, other.mobile) &&
            Objects.equals(isAdmin, other.isAdmin) &&
            Objects.equals(location, other.location);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            id,
            login,
            name,
            mobile,
            isAdmin,
            location
        });
    }
}
