/*
 * This file is generated by jOOQ.
 */
package org.observertc.webrtc.service.jooq.enums;


import org.jooq.Catalog;
import org.jooq.EnumType;
import org.jooq.Schema;


/**
 * The role of the user determines of which endpoint it can access to
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public enum UsersRole implements EnumType {

    customer("customer"),

    administrator("administrator");

    private final String literal;

    private UsersRole(String literal) {
        this.literal = literal;
    }

    @Override
    public Catalog getCatalog() {
        return null;
    }

    @Override
    public Schema getSchema() {
        return null;
    }

    @Override
    public String getName() {
        return "Users_role";
    }

    @Override
    public String getLiteral() {
        return literal;
    }
}