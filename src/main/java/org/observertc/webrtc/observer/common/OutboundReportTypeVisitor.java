package org.observertc.webrtc.observer.common;

import org.observertc.webrtc.schemas.reports.ReportType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public interface OutboundReportTypeVisitor<TIn, TOut> extends BiFunction<TIn, ReportType, TOut> {
    static Logger logger = LoggerFactory.getLogger(OutboundReportTypeVisitor.class);

    static <RIn, ROut> OutboundReportTypeVisitor<RIn, ROut> createFunctionalVisitor(
            Function<RIn, ROut> observerEventFuncProcess,
            Function<RIn, ROut> callEventFuncProcess,
            Function<RIn, ROut> callMetaDataFuncProcess,
            Function<RIn, ROut> clientExtensionDataFuncProcess,
            Function<RIn, ROut> peerConnectionTransportFuncProcess,
            Function<RIn, ROut> peerConnectionDataChannelFuncProcess,
            Function<RIn, ROut> inboundAudioTrackFuncProcess,
            Function<RIn, ROut> inboundVideoTrackFuncProcess,
            Function<RIn, ROut> outboundAudioTrackFuncProcess,
            Function<RIn, ROut> outboundVideoTrackFuncProcess,
            Function<RIn, ROut> mediaTrackFuncProcess
    ) {
        return new OutboundReportTypeVisitor<RIn, ROut>() {
            @Override
            public ROut visitObserverEventReport(RIn obj) {
                return observerEventFuncProcess.apply(obj);
            }

            @Override
            public ROut visitCallEventReport(RIn obj) {
                return callEventFuncProcess.apply(obj);
            }

            @Override
            public ROut visitCallMetaDataReport(RIn obj) {
                return callMetaDataFuncProcess.apply(obj);
            }

            @Override
            public ROut visitClientExtensionDataReport(RIn obj) {
                return clientExtensionDataFuncProcess.apply(obj);
            }

            @Override
            public ROut visitPeerConnectionTransportReport(RIn obj) {
                return peerConnectionTransportFuncProcess.apply(obj);
            }

            @Override
            public ROut visitPeerConnectionDataChannelReport(RIn obj) {
                return peerConnectionDataChannelFuncProcess.apply(obj);
            }

            @Override
            public ROut visitInboundAudioTrackReport(RIn obj) {
                return inboundAudioTrackFuncProcess.apply(obj);
            }

            @Override
            public ROut visitInboundVideoTrackReport(RIn obj) {
                return inboundVideoTrackFuncProcess.apply(obj);
            }

            @Override
            public ROut visitOutboundAudioTrackReport(RIn obj) {
                return outboundAudioTrackFuncProcess.apply(obj);
            }

            @Override
            public ROut visitOutboundVideoTrackReport(RIn obj) {
                return outboundVideoTrackFuncProcess.apply(obj);
            }

            @Override
            public ROut visitMediaTrackReport(RIn obj) {
                return mediaTrackFuncProcess.apply(obj);
            }
        };
    }


    static <RIn> OutboundReportTypeVisitor<RIn, Void> createConsumerVisitor(
            Consumer<RIn> observerEventConsumer,
            Consumer<RIn> callEventConsumer,
            Consumer<RIn> callMetaDataConsumer,
            Consumer<RIn> clientExtensionDataConsumer,
            Consumer<RIn> peerConnectionTransportConsumer,
            Consumer<RIn> peerConnectionDataChannelConsumer,
            Consumer<RIn> inboundAudioTrackConsumer,
            Consumer<RIn> inboundVideoTrackConsumer,
            Consumer<RIn> outboundAudioTrackConsumer,
            Consumer<RIn> outboundVideoTrackConsumer,
            Consumer<RIn> mediaTrackConsumer
    ) {
        return new OutboundReportTypeVisitor<RIn, Void>() {
            @Override
            public Void visitObserverEventReport(RIn obj) {
                observerEventConsumer.accept(obj);
                return null;
            }

            @Override
            public Void visitCallEventReport(RIn obj) {
                callEventConsumer.accept(obj);
                return null;
            }

            @Override
            public Void visitCallMetaDataReport(RIn obj) {
                callMetaDataConsumer.accept(obj);
                return null;
            }

            @Override
            public Void visitClientExtensionDataReport(RIn obj) {
                clientExtensionDataConsumer.accept(obj);
                return null;
            }

            @Override
            public Void visitPeerConnectionTransportReport(RIn obj) {
                peerConnectionTransportConsumer.accept(obj);
                return null;
            }

            @Override
            public Void visitPeerConnectionDataChannelReport(RIn obj) {
                peerConnectionDataChannelConsumer.accept(obj);
                return null;
            }

            @Override
            public Void visitInboundAudioTrackReport(RIn obj) {
                inboundAudioTrackConsumer.accept(obj);
                return null;
            }

            @Override
            public Void visitInboundVideoTrackReport(RIn obj) {
                inboundVideoTrackConsumer.accept(obj);
                return null;
            }

            @Override
            public Void visitOutboundAudioTrackReport(RIn obj) {
                outboundAudioTrackConsumer.accept(obj);
                return null;
            }

            @Override
            public Void visitOutboundVideoTrackReport(RIn obj) {
                outboundVideoTrackConsumer.accept(obj);
                return null;
            }

            @Override
            public Void visitMediaTrackReport(RIn obj) {
                mediaTrackConsumer.accept(obj);
                return null;
            }
        };
    }

    static OutboundReportTypeVisitor<Void, Void> createRunnableVisitor(
            Runnable observerEventCallback,
            Runnable callEventCallback,
            Runnable callMetaDataCallback,
            Runnable clientExtensionDataCallback,
            Runnable peerConnectionTransportCallback,
            Runnable peerConnectionDataChannelCallback,
            Runnable inboundAudioTrackCallback,
            Runnable inboundVideoTrackCallback,
            Runnable outboundAudioTrackCallback,
            Runnable outboundVideoTrackCallback,
            Runnable mediaTrackCallback
    ) {
        return new OutboundReportTypeVisitor<Void, Void>() {
            @Override
            public Void visitObserverEventReport(Void obj) {
                observerEventCallback.run();
                return null;
            }

            @Override
            public Void visitCallEventReport(Void obj) {
                callEventCallback.run();
                return null;
            }

            @Override
            public Void visitCallMetaDataReport(Void obj) {
                callMetaDataCallback.run();
                return null;
            }

            @Override
            public Void visitClientExtensionDataReport(Void obj) {
                clientExtensionDataCallback.run();
                return null;
            }

            @Override
            public Void visitPeerConnectionTransportReport(Void obj) {
                peerConnectionTransportCallback.run();
                return null;
            }

            @Override
            public Void visitPeerConnectionDataChannelReport(Void obj) {
                peerConnectionDataChannelCallback.run();
                return null;
            }

            @Override
            public Void visitInboundAudioTrackReport(Void obj) {
                inboundAudioTrackCallback.run();
                return null;
            }

            @Override
            public Void visitInboundVideoTrackReport(Void obj) {
                inboundVideoTrackCallback.run();
                return null;
            }

            @Override
            public Void visitOutboundAudioTrackReport(Void obj) {
                outboundAudioTrackCallback.run();
                return null;
            }

            @Override
            public Void visitOutboundVideoTrackReport(Void obj) {
                outboundVideoTrackCallback.run();
                return null;
            }

            @Override
            public Void visitMediaTrackReport(Void obj) {
                mediaTrackCallback.run();
                return null;
            }
        };
    }

    @Override
    default TOut apply(TIn obj, ReportType reportType) {
        switch (reportType) {
            case OBSERVER_EVENT:
                return this.visitObserverEventReport(obj);
            case CALL_EVENT:
                return this.visitCallEventReport(obj);
            case CALL_META_DATA:
                return this.visitCallMetaDataReport(obj);
            case CLIENT_EXTENSION_DATA:
                return this.visitClientExtensionDataReport(obj);
            case PEER_CONNECTION_TRANPORT:
                return this.visitPeerConnectionTransportReport(obj);
            case PEER_CONNECTION_DATA_CHANNEL:
                return this.visitPeerConnectionDataChannelReport(obj);
            case INBOUND_AUDIO_TRACK:
                return this.visitInboundAudioTrackReport(obj);
            case INBOUND_VIDEO_TRACK:
                return this.visitInboundVideoTrackReport(obj);
            case OUTBOUND_AUDIO_TRACK:
                return this.visitOutboundAudioTrackReport(obj);
            case OUTBOUND_VIDEO_TRACK:
                return this.visitOutboundVideoTrackReport(obj);
            case MEDIA_TRACK:
                return this.visitMediaTrackReport(obj);
            default:
                logger.warn("Unrecognized report type {}", reportType);
        }
        return null;
    }

    TOut visitObserverEventReport(TIn obj);
    TOut visitCallEventReport(TIn obj);
    TOut visitCallMetaDataReport(TIn obj);
    TOut visitClientExtensionDataReport(TIn obj);
    TOut visitPeerConnectionTransportReport(TIn obj);
    TOut visitPeerConnectionDataChannelReport(TIn obj);
    TOut visitInboundAudioTrackReport(TIn obj);
    TOut visitInboundVideoTrackReport(TIn obj);
    TOut visitOutboundAudioTrackReport(TIn obj);
    TOut visitOutboundVideoTrackReport(TIn obj);
    TOut visitMediaTrackReport(TIn obj);
}

