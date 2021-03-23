package org.observertc.webrtc.observer.sources;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import org.observertc.webrtc.observer.samples.ObservedPCS;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Objects;

@Singleton
public class Sources extends Observable<ObservedPCS> {

    private final Subject<ObservedPCS> subject = PublishSubject.create();

    // legacy reason

    @Deprecated(since = "0.7.2")
    public void inputUserMediaError(ObservedPCS observedPCS) {
        this.subject.onNext(observedPCS);
    }

    // legacy reason
    @Deprecated(since = "0.7.2")
    public Observer<ObservedPCS> getObservedPCSObserver() {
        return this.subject;
    }

    @Override
    protected void subscribeActual(@NonNull Observer<? super ObservedPCS> observer) {
        subject.subscribe(observer);
    }

    @Inject
    WebsocketPCSamples websocketPCSamples;

    @Inject
    WebsocketPCSampleV20200114 websocketPCSampleV20200114;

    @Inject
    PCSampleConverter pcSampleConverter;

    @PostConstruct
    void setup() {
        this.websocketPCSamples
                .map(this.pcSampleConverter)
                .filter(Objects::nonNull)
                .subscribe(this.subject);

        this.websocketPCSampleV20200114
                .filter(Objects::nonNull)
                .subscribe(this.subject);
    }
}
