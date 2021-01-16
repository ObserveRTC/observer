package org.observertc.webrtc.observer.connector;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import org.observertc.webrtc.observer.connector.sinks.Sink;
import org.observertc.webrtc.observer.connector.transformations.Transformation;
import org.observertc.webrtc.schemas.reports.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Connector implements Observer<Report> {

    private String name;
    private final Subject<Report> input = PublishSubject.create();
    private List<Transformation> transformations = new LinkedList<>();
    private Sink sink;
    private BufferConfig bufferConfig = null;
    private final Logger logger;
    private RestartPolicy restartPolicy = RestartPolicy.Never;


    public Connector(String name) {
        this.logger = LoggerFactory.getLogger(name);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        Observable<Report> observableReport = this.input;

        for (Transformation transformation : this.transformations) {
            observableReport = observableReport.lift(transformation).share();
        }

        Observable<List<Report>> observableReports;
        if (1 < this.bufferConfig.maxItems) {
            if (this.bufferConfig.maxWaitingTimeInS < 1) {
                observableReports = observableReport.buffer(this.bufferConfig.maxItems).share();
            } else {
                observableReports = observableReport.buffer(this.bufferConfig.maxWaitingTimeInS, TimeUnit.SECONDS, this.bufferConfig.maxItems).share();
            }
        } else {
            observableReports = observableReport.map(List::of);
        }


        observableReports.subscribe(this.sink);
    }

    @Override
    public void onNext(@NonNull Report report) {
        try {
            this.input.onNext(report);
        } catch (Throwable t) {
            logger.error("Unexpected error occurred during streaming process", t);
            if (Objects.isNull(this.restartPolicy) || this.restartPolicy.equals(RestartPolicy.Never)) {
                throw new RuntimeException(t);
            }
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        logger.error("Unexpected error occurred.", e);
    }

    @Override
    public void onComplete() {
        logger.info("The pipeline is done");
    }

    public String getName() {
        if (Objects.isNull(this.name)) {
            return "Unkown pipeline";
        }
        return this.name;
    }

    Connector withRestartPolicy(RestartPolicy restartPolicy) {
        this.restartPolicy = restartPolicy;
        return this;
    }

    Connector withBuffer(BufferConfig bufferConfig) {
        this.bufferConfig = bufferConfig;
        return this;
    }

    Connector withTransformation(Transformation transformation) {
        this.transformations.add(transformation);
        return this;
    }

    Connector withSink(Sink sink) {
        if (Objects.nonNull(this.sink)) {
            throw new IllegalStateException(this.getName() + ": cannot set the source for a pipeline twice");
        }
        this.sink = sink
                .withLogger(logger)
                .inPipeline(this);
        return this;
    }

}
