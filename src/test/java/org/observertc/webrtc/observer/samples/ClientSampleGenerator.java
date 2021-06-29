package org.observertc.webrtc.observer.samples;

import io.micronaut.context.annotation.Prototype;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.api.Randomizer;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

@Prototype
public class ClientSampleGenerator implements Supplier<ClientSample> {

    @Inject
    PeerConnectionTransportGenerator pcTransportGenerator;

    @Inject
    InboundAudioTrackGenerator inboundAudioTrackGenerator;

    @Inject
    InboundVideoTrackGenerator inboundVideoTrackGenerator;

    @Inject
    OutboundAudioTrackGenerator outboundAudioTrackGenerator;

    @Inject
    OutboundVideoTrackGenerator outboundVideoTrackGenerator;

    private final AtomicInteger sampleSeqHolder = new AtomicInteger(0);
    private EasyRandom generator;
    private UUID clientId = UUID.randomUUID();

    public ClientSampleGenerator() {

    }

    @PostConstruct
    void setup() {
        this.generator = this.makeGenerator();
    }

    @Override
    public ClientSample get() {
        var result = this.generator.nextObject(ClientSample.class);
        return result;
    }

    public ClientSampleGenerator withClientId(UUID value) {
        this.clientId = value;
        return this;
    }

    public ClientSampleGenerator withPeerConnectionId(UUID value) {
        this.inboundAudioTrackGenerator
                .withPeerConnectionId(value);
        this.inboundVideoTrackGenerator
                .withPeerConnectionId(value);
        this.outboundAudioTrackGenerator
                .withPeerConnectionId(value);
        this.outboundVideoTrackGenerator
                .withPeerConnectionId(value);
        this.pcTransportGenerator
                .withPeerConnectionId(value);
        return this;
    }

    private EasyRandom makeGenerator() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        for (Field field : ClientSample.class.getFields()) {
            String fieldName = field.getName();
            Randomizer randomizer = this.getRandomizerForField(fieldName);
            if (Objects.isNull(randomizer)) {
                continue;
            }
            parameters.randomize(rField -> rField.getName().equals(fieldName), randomizer);
        }
        parameters
                .randomize(ClientSample.PeerConnectionTransport.class, this.pcTransportGenerator::get)
                .randomize(ClientSample.InboundAudioTrack.class, this.inboundAudioTrackGenerator::get)
                .randomize(ClientSample.InboundVideoTrack.class, this.inboundVideoTrackGenerator::get)
                .randomize(ClientSample.OutboundAudioTrack.class, this.outboundAudioTrackGenerator::get)
                .randomize(ClientSample.OutboundVideoTrack.class, this.outboundVideoTrackGenerator::get)
        ;
        var result = new EasyRandom(parameters);
        return result;
    }

    private Randomizer getRandomizerForField(String fieldName) {
        switch (fieldName) {
            case "clientId":
                return this.clientId::toString;
            case "sampleSeq":
                return this.sampleSeqHolder::incrementAndGet;
        }
        return null;
    }
}
