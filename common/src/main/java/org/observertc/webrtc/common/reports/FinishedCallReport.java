//package org.observertc.webrtc.common.reports;
//
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonTypeName;
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//@JsonTypeName("FINISHED_CALL")
//public class FinishedCallReport extends Report {
//	public static FinishedCallReport of(UUID observerUUID, UUID callUUID, LocalDateTime finished) {
//		FinishedCallReport result = new FinishedCallReport();
//		result.callUUID = callUUID;
//		result.observerUUID = observerUUID;
//		result.finished = finished;
//		return result;
//	}
//
//	@JsonCreator
//	public FinishedCallReport() {
//		super(ReportType.FINISHED_CALL);
//	}
//
//	public UUID observerUUID;
//	public UUID callUUID;
//
//	@JsonSerialize(using = LocalDateTimeSerializer.class)
//	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
//	public LocalDateTime finished;
//}
