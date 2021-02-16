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

package org.observertc.webrtc.observer.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;
import com.hazelcast.nio.serialization.VersionedPortable;
import org.observertc.webrtc.observer.common.ObjectToString;
import org.observertc.webrtc.observer.common.UUIDAdapter;
import org.observertc.webrtc.observer.common.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

// To avoid exposing hazelcast serialization specific fields
@JsonIgnoreProperties(value = { "classId", "factoryId", "classId" })
@Deprecated
public class OldPeerConnectionEntity implements VersionedPortable {
	private static final Logger logger = LoggerFactory.getLogger(OldPeerConnectionEntity.class);
	public static final int CLASS_ID = 1001;
	public static final int CLASS_VERSION = 6;

	private static final String SERVICE_UUID_FIELD_NAME = "serviceUUID";
	private static final String SERVICE_NAME_FIELD_NAME = "serviceName";
	private static final String MEDIA_UNIT_ID_FIELD_NAME = "mediaUnitId";
	private static final String CALL_UUID_FIELD_NAME = "callUUID";
	private static final String CALL_NAME_FIELD_NAME = "callName";
	private static final String PEER_CONNECTION_UUID_FIELD_NAME = "peerConnectionUUID";

	private static final String PROVIDED_USER_NAME_FIELD_NAME = "providedUserName";
	private static final String BROWSERID_FIELD_NAME = "browserId";
	private static final String TIMEZONE_FIELD_NAME = "timeZone";
	private static final String JOINED_FIELD_NAME = "joined";
	private static final String MARKER_FIELD_NAME = "marker";

	// since class version 5
	private static final String SSRC_FIELD_NAME = "SSRC";
	private static final String IS_RELAYED_FIELD_NAME = "isRelayed";
	private static final String RELAY_TARGET_FIELD_NAME = "relayTarget";

	// since class version 6
	private static final String LOCAL_ADDRESS_FIELD_NAME = "localAddress";
	private static final String REMOTE_ADDRESS_FIELD_NAME = "remoteAddress";


	public static OldPeerConnectionEntity of(
			UUID serviceUUID,
			String serviceName,
			String mediaUnitId,
			UUID callUUID,
			String callName,
			UUID peerConnectionUUID,
			String providedUserName,
			String browserId,
			String timeZone,
			Long joined,
			String marker
	) {
		OldPeerConnectionEntity result = new OldPeerConnectionEntity();
		result.serviceUUID = serviceUUID;
		result.serviceName = serviceName;
		result.mediaUnitId = mediaUnitId;
		result.callUUID = callUUID;
		result.callName = callName;
		result.peerConnectionUUID = peerConnectionUUID;
		result.providedUserName = providedUserName;
		result.browserId = browserId;
		result.timeZone = timeZone;
		result.joined = joined;
		result.marker = marker;
		return result;
	}

	public UUID serviceUUID;
	public String serviceName;
	public String mediaUnitId;
	public UUID callUUID;
	public String callName;
	public UUID peerConnectionUUID;
	public String providedUserName;
	public String browserId;
	public String timeZone;
	public Long joined;
	public String marker;


	// since class version 5
	public Set<Long> SSRCs = new HashSet<>();
	public boolean isRelayed = false;
	public String relayTarget = "";

	// since class version 6
	public Set<String> localAddresses = new HashSet<>();
	public Set<String> remoteAddresses = new HashSet<>();

	@Override
	public int getFactoryId() {
		return EntityFactory.FACTORY_ID;
	}

	@Override
	public int getClassId() {
		return CLASS_ID;
	}

	@Override
	public void writePortable(PortableWriter writer) throws IOException {
		writer.writeByteArray(PEER_CONNECTION_UUID_FIELD_NAME, UUIDAdapter.toBytesOrDefault(this.peerConnectionUUID, EntityFactory.DEFAULT_UUID_BYTES));
		writer.writeByteArray(CALL_UUID_FIELD_NAME, UUIDAdapter.toBytesOrDefault(this.callUUID, EntityFactory.DEFAULT_UUID_BYTES));
		writer.writeByteArray(SERVICE_UUID_FIELD_NAME, UUIDAdapter.toBytesOrDefault(this.serviceUUID, EntityFactory.DEFAULT_UUID_BYTES));
		writer.writeUTF(SERVICE_NAME_FIELD_NAME, this.serviceName);
		writer.writeUTF(MEDIA_UNIT_ID_FIELD_NAME, this.mediaUnitId);
		writer.writeUTF(CALL_NAME_FIELD_NAME, this.callName);
		writer.writeUTF(PROVIDED_USER_NAME_FIELD_NAME, this.providedUserName);
		writer.writeUTF(BROWSERID_FIELD_NAME, this.browserId);
		writer.writeUTF(TIMEZONE_FIELD_NAME, this.timeZone);
		if (Objects.nonNull(this.joined)) {
			writer.writeLong(JOINED_FIELD_NAME, this.joined);
		}
		writer.writeUTF(MARKER_FIELD_NAME, this.marker);

		// since class version 5
		if (this.getClassVersion() < 5) {
			return;
		}
		try {
			long[] SSRCs;
			if (0 < this.SSRCs.size()) {
				SSRCs = new long[this.SSRCs.size()];
				Iterator<Long> it = this.SSRCs.iterator();
				for (int i = 0; it.hasNext(); ++i) {
					SSRCs[i] = it.next();
				}
			} else {
				SSRCs = new long[]{-1};
			}
			writer.writeLongArray(SSRC_FIELD_NAME, SSRCs);
			writer.writeBoolean(IS_RELAYED_FIELD_NAME, this.isRelayed);
			writer.writeUTF(RELAY_TARGET_FIELD_NAME, Objects.requireNonNull(this.relayTarget, "NULL"));
		} catch (Throwable t) {
			logger.warn("Serialization error for pc {}, class version {} at serialization version 5", this.peerConnectionUUID, this.getClassVersion());
			return;
		}

		// since class version 6
		if (this.getClassVersion() < 6) {
			return;
		}
		try {
			String[] localAddresses;
			if (0 < this.localAddresses.size()) {
				localAddresses = new String[this.localAddresses.size()];
				Iterator<String> it = this.localAddresses.iterator();
				for (int i = 0; it.hasNext(); ++i) {
					localAddresses[i] = it.next();
				}
			} else {
				localAddresses = new String[]{""};
			}
			writer.writeUTFArray(LOCAL_ADDRESS_FIELD_NAME, localAddresses);

			String[] remoteAddresses;
			if (0 < this.remoteAddresses.size()) {
				remoteAddresses = new String[this.remoteAddresses.size()];
				Iterator<String> it = this.remoteAddresses.iterator();
				for (int i = 0; it.hasNext(); ++i) {
					remoteAddresses[i] = it.next();
				}
			} else {
				remoteAddresses = new String[]{""};
			}
			writer.writeUTFArray(REMOTE_ADDRESS_FIELD_NAME, remoteAddresses);
		} catch (Throwable t) {
			logger.warn("Serialization error for pc {}, class version {} at serialization version 5", this.peerConnectionUUID, this.getClassVersion());
			return;
		}

	}

	@Override
	public void readPortable(PortableReader reader) throws IOException {
		this.peerConnectionUUID = UUIDAdapter.toUUIDOrDefault(reader.readByteArray(PEER_CONNECTION_UUID_FIELD_NAME), null);
		this.callUUID = UUIDAdapter.toUUIDOrDefault(reader.readByteArray(CALL_UUID_FIELD_NAME), null);
		this.serviceUUID = UUIDAdapter.toUUIDOrDefault(reader.readByteArray(SERVICE_UUID_FIELD_NAME), null);
		this.serviceName = reader.readUTF(SERVICE_NAME_FIELD_NAME);
		this.mediaUnitId = reader.readUTF(MEDIA_UNIT_ID_FIELD_NAME);
		this.callName = reader.readUTF(CALL_NAME_FIELD_NAME);
		this.providedUserName = reader.readUTF(PROVIDED_USER_NAME_FIELD_NAME);
		this.browserId = reader.readUTF(BROWSERID_FIELD_NAME);
		this.timeZone = reader.readUTF(TIMEZONE_FIELD_NAME);
		if (reader.hasField(JOINED_FIELD_NAME)) {
			this.joined = reader.readLong(JOINED_FIELD_NAME);
		}
		this.marker = reader.readUTF(MARKER_FIELD_NAME);

		// since class version 5
		if (reader.getVersion() < 5) {
			return;
		}
		try {
			long[] SSRCs = reader.readLongArray(SSRC_FIELD_NAME);
			if (SSRCs.length != 1 || SSRCs[0] != -1) {
				Arrays.stream(SSRCs).forEach(this.SSRCs::add);
			}
			this.isRelayed = reader.readBoolean(IS_RELAYED_FIELD_NAME);
			this.relayTarget = Utils.ifExpectedThenAlternative(reader.readUTF(RELAY_TARGET_FIELD_NAME), "NULL", null);
		} catch (Throwable t) {
			logger.warn("Deserialization error for pc {}, class version {} at serialization version 5", this.peerConnectionUUID, this.getClassVersion());
			return;
		}

		// since class version 6
		if (reader.getVersion() < 6) {
			return;
		}
		try {
			String[] localAddresses = reader.readUTFArray(LOCAL_ADDRESS_FIELD_NAME);
			if (localAddresses.length != 1 || localAddresses[0] != "") {
				Arrays.stream(localAddresses).forEach(this.localAddresses::add);
			}
			String[] remoteAddresses = reader.readUTFArray(REMOTE_ADDRESS_FIELD_NAME);
			if (remoteAddresses.length != 1 || remoteAddresses[0] != "") {
				Arrays.stream(remoteAddresses).forEach(this.remoteAddresses::add);
			}
		} catch (Throwable t) {
			logger.warn("Deserialization error for pc {}, class version {} at serialization version 5", this.peerConnectionUUID, this.getClassVersion());
			return;
		}
	}

	@Override
	public String toString() {
		return ObjectToString.toString(this);
	}

	@Override
	public int getClassVersion() {
		return CLASS_VERSION;
	}
}
