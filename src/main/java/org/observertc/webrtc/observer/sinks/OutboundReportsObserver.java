package org.observertc.webrtc.observer.sinks;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import org.observertc.webrtc.observer.ObserverConfig;
import org.observertc.webrtc.observer.common.ObjectToString;
import org.observertc.webrtc.observer.common.OutboundReports;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Singleton
public class OutboundReportsObserver implements Observer<OutboundReports> {

    private static Logger logger = LoggerFactory.getLogger(OutboundReportsObserver.class);

    private final Map<String, Sink> sinks = new HashMap<>();
    public OutboundReportsObserver(ObserverConfig config) {
        if (Objects.nonNull(config.sinks)) {
            config.sinks.forEach((sinkId, sinkConfig) -> {
                try {
                    Map<String, Object> sinkConfigValue = (Map<String, Object>) sinkConfig;
                    var sink = this.buildSink(sinkId, sinkConfigValue);
                    this.sinks.put(sinkId, sink);
                } catch (Exception ex) {
                    logger.error("Error occurred while setting up a Sink {} with config {}",
                            sinkId,
                            ObjectToString.toString(sinkConfig),
                            ex
                    );
                }
            });
        }
    }
    private Sink buildSink(String sinkId, Map<String, Object> config) {
        SinkBuilder sinkBuilder = new SinkBuilder();
        sinkBuilder.withConfiguration(config);
        Sink result = sinkBuilder.build();
        String sinkLoggerName = String.format("Sink-%s:", sinkId);
        var logger = LoggerFactory.getLogger(sinkLoggerName);
        result.withLogger(logger);
        return result;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull OutboundReports outboundReports) {

    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
