package com.observertc.gatekeeper.webrtcstat.samples;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.micronaut.core.annotation.Introspected;
import java.time.LocalDateTime;
import java.util.UUID;

@Introspected
public class ObserverSSRCPeerConnectionSample {
	public UUID observerUUID;
	public Long SSRC;
	public UUID peerConnectionUUID;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	public LocalDateTime timestamp;
	
	
	
}