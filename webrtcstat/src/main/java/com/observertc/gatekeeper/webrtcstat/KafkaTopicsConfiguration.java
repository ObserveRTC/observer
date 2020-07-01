package com.observertc.gatekeeper.webrtcstat;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("kafkaTopics")
public class KafkaTopicsConfiguration {
	public String observeRTCCIceStatsSample;
	public String observeRTCMediaStreamStatsSamples;
	public String observerSSRCPeerConnectionSamples;
}
