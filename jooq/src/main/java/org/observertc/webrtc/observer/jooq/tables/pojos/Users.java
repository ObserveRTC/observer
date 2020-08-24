/*
 * This file is generated by jOOQ.
 */
package org.observertc.webrtc.observer.jooq.tables.pojos;


import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.observertc.webrtc.observer.jooq.enums.UsersRole;


/**
 * Users
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Users implements Serializable {

    private static final long serialVersionUID = 1085589347;

    private final Integer   id;
    private final byte[]    uuid;
    private final String    username;
    private final byte[]    passwordDigest;
    private final byte[]    passwordSalt;
    private final UsersRole role;

    public Users(Users value) {
        this.id = value.id;
        this.uuid = value.uuid;
        this.username = value.username;
        this.passwordDigest = value.passwordDigest;
        this.passwordSalt = value.passwordSalt;
        this.role = value.role;
    }

    public Users(
        Integer   id,
        byte[]    uuid,
        String    username,
        byte[]    passwordDigest,
        byte[]    passwordSalt,
        UsersRole role
    ) {
        this.id = id;
        this.uuid = uuid;
        this.username = username;
        this.passwordDigest = passwordDigest;
        this.passwordSalt = passwordSalt;
        this.role = role;
    }

    public Integer getId() {
        return this.id;
    }

    @Size(max = 16)
    public byte[] getUuid() {
        return this.uuid;
    }

    @Size(max = 255)
    public String getUsername() {
        return this.username;
    }

    @Size(max = 64)
    public byte[] getPasswordDigest() {
        return this.passwordDigest;
    }

    @Size(max = 32)
    public byte[] getPasswordSalt() {
        return this.passwordSalt;
    }

    @NotNull
    public UsersRole getRole() {
        return this.role;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Users (");

        sb.append(id);
        sb.append(", ").append("[binary...]");
        sb.append(", ").append(username);
        sb.append(", ").append("[binary...]");
        sb.append(", ").append("[binary...]");
        sb.append(", ").append(role);

        sb.append(")");
        return sb.toString();
    }
}
