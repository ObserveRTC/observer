/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package org.observertc.webrtc.schemas.reports;
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public enum ReportType {
  OBSERVER_EVENT, CALL_EVENT, CALL_META_DATA, CLIENT_EXTENSION_DATA, PEER_CONNECTION_TRANPORT, PEER_CONNECTION_DATA_CHANNEL, INBOUND_AUDIO_TRACK, INBOUND_VIDEO_TRACK, OUTBOUND_AUDIO_TRACK, OUTBOUND_VIDEO_TRACK, MEDIA_TRACK  ;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"enum\",\"name\":\"ReportType\",\"namespace\":\"org.observertc.webrtc.schemas.reports\",\"symbols\":[\"OBSERVER_EVENT\",\"CALL_EVENT\",\"CALL_META_DATA\",\"CLIENT_EXTENSION_DATA\",\"PEER_CONNECTION_TRANPORT\",\"PEER_CONNECTION_DATA_CHANNEL\",\"INBOUND_AUDIO_TRACK\",\"INBOUND_VIDEO_TRACK\",\"OUTBOUND_AUDIO_TRACK\",\"OUTBOUND_VIDEO_TRACK\",\"MEDIA_TRACK\"]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
}