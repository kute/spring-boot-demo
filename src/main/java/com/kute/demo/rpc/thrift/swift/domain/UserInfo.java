package com.kute.demo.rpc.thrift.swift.domain;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("UserInfo")
public final class UserInfo
{
    public UserInfo() {
    }

    private int id;

    @ThriftField(value=1, name="id", requiredness=Requiredness.REQUIRED)
    public int getId() { return id; }

    @ThriftField
    public void setId(final int id) { this.id = id; }

    private String login;

    @ThriftField(value=2, name="login", requiredness=Requiredness.REQUIRED)
    public String getLogin() { return login; }

    @ThriftField
    public void setLogin(final String login) { this.login = login; }

    private String name;

    @ThriftField(value=3, name="name", requiredness=Requiredness.REQUIRED)
    public String getName() { return name; }

    @ThriftField
    public void setName(final String name) { this.name = name; }

    private String mobile;

    @ThriftField(value=4, name="mobile", requiredness=Requiredness.REQUIRED)
    public String getMobile() { return mobile; }

    @ThriftField
    public void setMobile(final String mobile) { this.mobile = mobile; }

    private boolean isAdmin;

    @ThriftField(value=5, name="isAdmin", requiredness=Requiredness.NONE)
    public boolean isIsAdmin() { return isAdmin; }

    @ThriftField
    public void setIsAdmin(final boolean isAdmin) { this.isAdmin = isAdmin; }

    private String location;

    @ThriftField(value=6, name="location", requiredness=Requiredness.OPTIONAL)
    public String getLocation() { return location; }

    @ThriftField
    public void setLocation(final String location) { this.location = location; }

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
