package org.observertc.webrtc.service.evaluators.valueadapters;

import org.apache.commons.lang3.NotImplementedException;
import org.observertc.webrtc.common.reports.CandidateNetworkType;
import org.observertc.webrtc.service.dto.webextrapp.NetworkType;

public class CandidateNetworkTypeConverter {

	public static CandidateNetworkType fromDTONetworkType(NetworkType networkType) {
		if (networkType == null) {
			return null;
		}
		switch (networkType) {
			case VPN:
				return CandidateNetworkType.VPN;
			case WIFI:
				return CandidateNetworkType.WIFI;
			case WIMAX:
				return CandidateNetworkType.WIMAX;
			case CELLULAR:
				return CandidateNetworkType.CELLULAR;
			case ETHERNET:
				return CandidateNetworkType.ETHERNET;
			case BLUETOOTH:
				return CandidateNetworkType.BLUETOOTH;
			case UNKNOWN:
				return CandidateNetworkType.UNKNOWN;
			default:
				throw new NotImplementedException("NetworkType for " + networkType.name() + " is not implemented");
		}
	}

}
