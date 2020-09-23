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

package org.observertc.webrtc.observer.evaluators.reportdrafts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.UUID;

@JsonTypeName("INITIATED_CALL")
public class InitiatedCallReportDraft extends ReportDraft {
	public static InitiatedCallReportDraft of(UUID serviceUUID, UUID callUUID, Long initiated) {
		InitiatedCallReportDraft result = new InitiatedCallReportDraft();
		result.callUUID = callUUID;
		result.serviceUUID = serviceUUID;
		result.initiated = initiated;
		return result;
	}

	@JsonCreator
	public InitiatedCallReportDraft() {
		super(ReportDraftType.INITIATED_CALL);
	}

	public UUID serviceUUID;
	public UUID callUUID;
	public Long initiated;
}
