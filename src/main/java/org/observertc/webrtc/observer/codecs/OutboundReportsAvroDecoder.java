package org.observertc.webrtc.observer.codecs;

import io.micronaut.context.annotation.Prototype;
import org.apache.avro.generic.GenericData;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.MessageDecoder;
import org.observertc.webrtc.observer.common.OutboundReport;
import org.observertc.webrtc.observer.evaluators.OutboundReportEncoder;
import org.observertc.webrtc.schemas.reports.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.Objects;

public class OutboundReportsAvroDecoder {
    private static final Logger logger = LoggerFactory.getLogger(OutboundReportEncoder.class);

    private final BinaryMessageDecoder<ObserverEventReport> observerEventDecoder;
    private final BinaryMessageDecoder<CallEventReport> callEventDecoder;
    private final BinaryMessageDecoder<CallMetaReport> callMetaDecoder;
    private final BinaryMessageDecoder<ClientExtensionReport> clientExtensionDecoder;
    private final BinaryMessageDecoder<PcTransportReport> pcTransportDecoder;
    private final BinaryMessageDecoder<PcDataChannelReport> pcDataChannelDecoder;
    private final BinaryMessageDecoder<InboundAudioTrackReport> inboundAudioTrackDecoder;
    private final BinaryMessageDecoder<InboundVideoTrackReport> inboundVideoTrackDecoder;
    private final BinaryMessageDecoder<OutboundAudioTrackReport> outboundAudioTrackDecoder;
    private final BinaryMessageDecoder<OutboundVideoTrackReport> outboundVideoTrackDecoder;
    private final BinaryMessageDecoder<MediaTrackReport> mediaTrackDecoder;

    public OutboundReportsAvroDecoder() {
        this.observerEventDecoder = new BinaryMessageDecoder<ObserverEventReport>(GenericData.get(), ObserverEventReport.getClassSchema());
        this.callEventDecoder = new BinaryMessageDecoder<CallEventReport>(GenericData.get(), CallEventReport.getClassSchema());
        this.callMetaDecoder = new BinaryMessageDecoder<CallMetaReport>(GenericData.get(), CallMetaReport.getClassSchema());
        this.clientExtensionDecoder = new BinaryMessageDecoder<ClientExtensionReport>(GenericData.get(), ClientExtensionReport.getClassSchema());
        this.pcTransportDecoder = new BinaryMessageDecoder<PcTransportReport>(GenericData.get(), PcTransportReport.getClassSchema());
        this.pcDataChannelDecoder = new BinaryMessageDecoder<PcDataChannelReport>(GenericData.get(), PcDataChannelReport.getClassSchema());
        this.inboundAudioTrackDecoder = new BinaryMessageDecoder<InboundAudioTrackReport>(GenericData.get(), InboundAudioTrackReport.getClassSchema());
        this.inboundVideoTrackDecoder = new BinaryMessageDecoder<InboundVideoTrackReport>(GenericData.get(), InboundVideoTrackReport.getClassSchema());
        this.outboundAudioTrackDecoder = new BinaryMessageDecoder<OutboundAudioTrackReport>(GenericData.get(), OutboundAudioTrackReport.getClassSchema());
        this.outboundVideoTrackDecoder = new BinaryMessageDecoder<OutboundVideoTrackReport>(GenericData.get(), OutboundVideoTrackReport.getClassSchema());
        this.mediaTrackDecoder = new BinaryMessageDecoder<MediaTrackReport>(GenericData.get(), MediaTrackReport.getClassSchema());
    }

    public ObserverEventReport decodeObserverEventReports(OutboundReport outboundReport) {
        if (Objects.isNull(outboundReport)) {
            return null;
        }
        return this.decodeOrNull(this.observerEventDecoder, outboundReport);
    }

    public CallEventReport decodeCallEventReports(OutboundReport outboundReport) {
        if (Objects.isNull(outboundReport)) {
            return null;
        }
        return this.decodeOrNull(this.callEventDecoder, outboundReport);
    }

    public CallMetaReport decodeCallMetaReports(OutboundReport outboundReport) {
        if (Objects.isNull(outboundReport)) {
            return null;
        }
        return this.decodeOrNull(this.callMetaDecoder, outboundReport);
    }

    public ClientExtensionReport decodeClientExtensionReport(OutboundReport outboundReport) {
        if (Objects.isNull(outboundReport)) {
            return null;
        }
        return this.decodeOrNull(this.clientExtensionDecoder, outboundReport);
    }

    public PcTransportReport decodePcTransportReport(OutboundReport outboundReport) {
        if (Objects.isNull(outboundReport)) {
            return null;
        }
        return this.decodeOrNull(this.pcTransportDecoder, outboundReport);
    }

    public PcDataChannelReport decodePcDataChannelReport(OutboundReport outboundReport) {
        if (Objects.isNull(outboundReport)) {
            return null;
        }
        return this.decodeOrNull(this.pcDataChannelDecoder, outboundReport);
    }

    public InboundAudioTrackReport decodeInboundAudioTrackReport(OutboundReport outboundReport) {
        if (Objects.isNull(outboundReport)) {
            return null;
        }
        return this.decodeOrNull(this.inboundAudioTrackDecoder, outboundReport);
    }

    public InboundVideoTrackReport decodeInboundVideoTrackReport(OutboundReport outboundReport) {
        if (Objects.isNull(outboundReport)) {
            return null;
        }
        return this.decodeOrNull(this.inboundVideoTrackDecoder, outboundReport);
    }

    public OutboundAudioTrackReport decodeOutboundAudioTrackReport(OutboundReport outboundReport) {
        if (Objects.isNull(outboundReport)) {
            return null;
        }
        return this.decodeOrNull(this.outboundAudioTrackDecoder, outboundReport);
    }

    public OutboundVideoTrackReport decodeOutboundVideoTrackReport(OutboundReport outboundReport) {
        if (Objects.isNull(outboundReport)) {
            return null;
        }
        return this.decodeOrNull(this.outboundVideoTrackDecoder, outboundReport);
    }

    public MediaTrackReport decodeMediaTrackReport(OutboundReport outboundReport) {
        if (Objects.isNull(outboundReport)) {
            return null;
        }
        return this.decodeOrNull(this.mediaTrackDecoder, outboundReport);
    }

    private<T> T decodeOrNull(MessageDecoder<T> messageDecoder, OutboundReport outboundReport) {
        try {
            byte[] bytes = outboundReport.getBytes();
            return messageDecoder.decode(bytes);
        } catch (Throwable thr) {
            // show must go on
            logger.error("Unexpected error occurred during decoding process", thr);
            return null;
        }
    }
}
