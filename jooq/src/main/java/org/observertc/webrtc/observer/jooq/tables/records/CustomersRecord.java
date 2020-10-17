/*
 * Copyright  2020 Balazs Kreith
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * This file is generated by jOOQ.
 */
package org.observertc.webrtc.observer.jooq.tables.records;


import javax.validation.constraints.Size;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;
import org.observertc.webrtc.observer.jooq.tables.Customers;


/**
 * Customers
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CustomersRecord extends UpdatableRecordImpl<CustomersRecord> implements Record4<Integer, byte[], String, String> {

    private static final long serialVersionUID = 1065271828;

    /**
     * Setter for <code>WebRTCObserver.Customers.id</code>.
     */
    public CustomersRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>WebRTCObserver.Customers.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>WebRTCObserver.Customers.uuid</code>.
     */
    public CustomersRecord setUuid(byte[] value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>WebRTCObserver.Customers.uuid</code>.
     */
    @Size(max = 16)
    public byte[] getUuid() {
        return (byte[]) get(1);
    }

    /**
     * Setter for <code>WebRTCObserver.Customers.name</code>.
     */
    public CustomersRecord setName(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>WebRTCObserver.Customers.name</code>.
     */
    @Size(max = 255)
    public String getName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>WebRTCObserver.Customers.description</code>.
     */
    public CustomersRecord setDescription(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>WebRTCObserver.Customers.description</code>.
     */
    @Size(max = 255)
    public String getDescription() {
        return (String) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, byte[], String, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Integer, byte[], String, String> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Customers.CUSTOMERS.ID;
    }

    @Override
    public Field<byte[]> field2() {
        return Customers.CUSTOMERS.UUID;
    }

    @Override
    public Field<String> field3() {
        return Customers.CUSTOMERS.NAME;
    }

    @Override
    public Field<String> field4() {
        return Customers.CUSTOMERS.DESCRIPTION;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public byte[] component2() {
        return getUuid();
    }

    @Override
    public String component3() {
        return getName();
    }

    @Override
    public String component4() {
        return getDescription();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public byte[] value2() {
        return getUuid();
    }

    @Override
    public String value3() {
        return getName();
    }

    @Override
    public String value4() {
        return getDescription();
    }

    @Override
    public CustomersRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public CustomersRecord value2(byte[] value) {
        setUuid(value);
        return this;
    }

    @Override
    public CustomersRecord value3(String value) {
        setName(value);
        return this;
    }

    @Override
    public CustomersRecord value4(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public CustomersRecord values(Integer value1, byte[] value2, String value3, String value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CustomersRecord
     */
    public CustomersRecord() {
        super(Customers.CUSTOMERS);
    }

    /**
     * Create a detached, initialised CustomersRecord
     */
    public CustomersRecord(Integer id, byte[] uuid, String name, String description) {
        super(Customers.CUSTOMERS);

        set(0, id);
        set(1, uuid);
        set(2, name);
        set(3, description);
    }
}