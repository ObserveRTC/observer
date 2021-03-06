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

package org.observertc.webrtc.observer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;
import com.hazelcast.nio.serialization.VersionedPortable;
import org.observertc.webrtc.observer.common.ObjectToString;
import org.observertc.webrtc.observer.common.UUIDAdapter;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

// To avoid exposing hazelcast serialization specific fields
@JsonIgnoreProperties(value = { "classId", "factoryId", "classId" })
public class CallDTO implements VersionedPortable {
	public static final UUID DEFAULT_UUID = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee");
	public static final byte[] DEFAULT_UUID_BYTES = UUIDAdapter.toBytes(DEFAULT_UUID);

	public static final int CLASS_VERSION = 1;
	private static final String CALL_UUID_FIELD_NAME = "callUUID";
	private static final String SERVICE_UUID_FIELD_NAME = "serviceUUID";
	private static final String SERVICE_NAME_FIELD_NAME = "serviceName";
	private static final String INITIATED_FIELD_NAME = "initiated";
	private static final String CALL_NAME_FIELD_NAME = "callName";
	private static final String MARKER_FIELD_NAME = "marker";

	public static CallDTO of(
			UUID callUUID,
			UUID serviceUUID,
			String serviceName,
			Long initiated,
			String callName,
			String marker) {
		CallDTO result = new CallDTO();
		result.callUUID = callUUID;
		result.initiated = initiated;
		result.serviceUUID = serviceUUID;
		result.serviceName = serviceName;
		result.callName = callName;
		result.marker = marker;
		return result;
	}

	public UUID serviceUUID;
	public String serviceName;
	public Long initiated;
	public UUID callUUID;
	public String callName;
	public String marker;

	@Override
	public int getFactoryId() {
		return PortableDTOFactory.FACTORY_ID;
	}

	@Override
	public int getClassId() {
		return PortableDTOFactory.CALL_DTO_CLASS_ID;
	}

	@Override
	public void writePortable(PortableWriter writer) throws IOException {

		writer.writeByteArray(CALL_UUID_FIELD_NAME, UUIDAdapter.toBytesOrDefault(this.callUUID, DEFAULT_UUID_BYTES));
		writer.writeByteArray(SERVICE_UUID_FIELD_NAME, UUIDAdapter.toBytesOrDefault(this.serviceUUID, DEFAULT_UUID_BYTES));
		writer.writeUTF(SERVICE_NAME_FIELD_NAME, this.serviceName);
		writer.writeUTF(CALL_NAME_FIELD_NAME, this.callName);
		writer.writeLong(INITIATED_FIELD_NAME, this.initiated);
		writer.writeUTF(MARKER_FIELD_NAME, this.marker);

	}

	@Override
	public void readPortable(PortableReader reader) throws IOException {
		this.callUUID = UUIDAdapter.toUUIDOrDefault(reader.readByteArray(CALL_UUID_FIELD_NAME), null);
		this.serviceUUID = UUIDAdapter.toUUIDOrDefault(reader.readByteArray(SERVICE_UUID_FIELD_NAME), null);
		this.serviceName = reader.readUTF(SERVICE_NAME_FIELD_NAME);
		this.callName = reader.readUTF(CALL_NAME_FIELD_NAME);
		this.initiated = reader.readLong(INITIATED_FIELD_NAME);
		this.marker = reader.readUTF(MARKER_FIELD_NAME);
	}

	@Override
	public String toString() {
		return ObjectToString.toString(this);
	}

	@Override
	public int getClassVersion() {
		return CLASS_VERSION;
	}

	@Override
	public boolean equals(Object other) {
		if (Objects.isNull(other) || !this.getClass().getName().equals(other.getClass().getName())) {
			return false;
		}
		CallDTO otherDTO = (CallDTO) other;
		if (!Objects.equals(this.callName, otherDTO.callName) ||
			!Objects.equals(this.callUUID, otherDTO.callUUID) ||
			!Objects.equals(this.serviceUUID, otherDTO.serviceUUID) ||
			!Objects.equals(this.marker, otherDTO.marker) ||
			!Objects.equals(this.initiated, otherDTO.initiated) ||
			!Objects.equals(this.serviceName, otherDTO.serviceName)
		) {
			return false;
		}
		return true;
	}
}
