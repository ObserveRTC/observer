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

package org.observertc.webrtc.observer;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.EachProperty;
import org.observertc.webrtc.observer.dto.PeerConnectionFilterDTO;
import org.observertc.webrtc.observer.dto.SentinelDTO;
import org.observertc.webrtc.observer.dto.CallFilterDTO;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.*;

@ConfigurationProperties("observer")
public class ObserverConfig {

	public List<Map<String, Object>> connectors = new LinkedList<>();

	public HazelcastConfig hazelcast;

	public OutboundReportsConfig outboundReports;

	@Min(1)
	@Max(60)
	public int sentinelsCheckingPeriodInMin = 1;

	public List<SentinelDTO> sentinels = new ArrayList<>();

	public List<CallFilterDTO> callFilters = new ArrayList<>();

	public List<PeerConnectionFilterDTO> pcFilters = new ArrayList<>();

	public EvaluatorsConfig evaluators;

	public IPAddressConverterConfig ipAddressConverter;

	public List<ServiceMapConfiguration> servicemappings = new ArrayList<>();

	public SecurityConfig security;

	public InboundRtpMonitorConfig inboundRtpMonitor;

	public OutboundRtpMonitorConfig outboundRtpMonitor;

	public RemoteInboundRtpMonitorConfig remoteInboundRtpMonitor;

	public ReportCounterMonitorConfig reportMonitor;

	@ConfigurationProperties("ipaddress")
	public static class IPAddressConverterConfig {
		public boolean enabled = false;
		public String algorithm = "SHA-256";
		public String salt = "mySalt";
	}

	@ConfigurationProperties("security")
	public static class SecurityConfig {
		public boolean dropUnknownServices = false;
	}

	@ConfigurationProperties("evaluators")
	public static class EvaluatorsConfig {

		@Min(0)
		public int observedPCSBufferMaxTimeInS = 30;

		@Min(1)
		public int observedPCSBufferMaxItemNums = 10000;

		@Min(15)
		public int peerConnectionMaxIdleTimeInS = 300;

		public String impairablePCsCallName = "impairable-peer-connections-default-call-name";
	}

	@ConfigurationProperties("hazelcast")
	public static class HazelcastConfig {
		public String configFile = null;
	}

	@ConfigurationProperties("outboundReports")
	public static class OutboundReportsConfig {
		public boolean enabled = true;
		public String defaultServiceName = "defaultServiceName";
		public String defaultTopicName = "reports";
		public boolean reportOutboundRTPs = true;
		public boolean reportInboundRTPs = true;
		public boolean reportRemoteInboundRTPs = true;
		public boolean reportTracks = true;
		public boolean reportMediaSources = true;
		public boolean reportCandidatePairs = true;
		public boolean reportLocalCandidates = true;
		public boolean reportRemoteCandidates = true;
		public boolean reportUserMediaErrors = true;

		public boolean reportInitiatedCalls = true;
		public boolean reportFinishedCalls = true;
		public boolean reportJoinedPeerConnections = true;
		public boolean reportDetachedPeerConnections = true;
		public boolean reportObserverEvents = true;
		public boolean reportExtensions = true;
		public boolean mediaDevices = true;
		public boolean clientDetails = true;
	}

	@EachProperty("servicemappings")
	public static class ServiceMapConfiguration {
		public String name;
		public List<UUID> uuids = new ArrayList<>();
	}

	public static class RtpMonitorConfig {
		public boolean enabled = false;
		public int retentionTimeInS = 300;
	}

	@ConfigurationProperties("inboundRtpMonitor")
	public static class InboundRtpMonitorConfig extends RtpMonitorConfig{

	}

	@ConfigurationProperties("outboundRtpMonitor")
	public static class OutboundRtpMonitorConfig extends RtpMonitorConfig{

	}

	@ConfigurationProperties("remoteInboundRtpMonitor")
	public static class RemoteInboundRtpMonitorConfig extends RtpMonitorConfig {
		public double weightFactor = 0.5;
	}

	public static class CounterMonitorConfig {
		public boolean enabled = false;
		public boolean tagByType = true;
		public boolean tagByServiceName = false;
		public boolean tagByServiceUUID = false;
	}

	@EachProperty("reportMonitor")
	public static class ReportCounterMonitorConfig extends CounterMonitorConfig {

	}


}

