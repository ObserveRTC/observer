package org.observertc.webrtc.service.reportsink.bigquery;

import java.util.HashMap;
import java.util.Map;
import org.observertc.webrtc.common.reports.MediaStreamSampleRecord;


public class MediaStreamSampleEntryRecord {
	public static MediaStreamSampleEntryRecord from(MediaStreamSampleRecord source) {
		return new MediaStreamSampleEntryRecord()
				.withPresented(source.getPresented())
				.withEmpty(source.getEmpty())
				.withMaximum(source.getMaximum())
				.withMinimum(source.getMinimum())
				.withSum(source.getSum());
	}

	private static final String MINIMUM_FIELD_NAME = "minimum";
	private static final String MAXIMUM_FIELD_NAME = "maximum";
	private static final String PRESENTED_FIELD_NAME = "presented";
	private static final String EMPTY_FIELD_NAME = "empty";
	private static final String SUM_FIELD_NAME = "sum";

	private final Map<String, Object> summaryValues = new HashMap<>();


	public MediaStreamSampleEntryRecord withMinimum(Long value) {
		this.summaryValues.put(MINIMUM_FIELD_NAME, value);
		return this;
	}

	public MediaStreamSampleEntryRecord withMaximum(Long value) {
		this.summaryValues.put(MAXIMUM_FIELD_NAME, value);
		return this;
	}

	public MediaStreamSampleEntryRecord withSum(Long value) {
		this.summaryValues.put(SUM_FIELD_NAME, value);
		return this;
	}

	public MediaStreamSampleEntryRecord withPresented(Long value) {
		this.summaryValues.put(PRESENTED_FIELD_NAME, value);
		return this;
	}

	public MediaStreamSampleEntryRecord withEmpty(Long value) {
		this.summaryValues.put(EMPTY_FIELD_NAME, value);
		return this;
	}

	public Map<String, Object> toMap() {
		return this.summaryValues;
	}
}