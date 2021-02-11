package org.observertc.webrtc.observer.tasks;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.observertc.webrtc.observer.ObserverHazelcast;
import org.observertc.webrtc.observer.entities.OldCallEntity;
import org.observertc.webrtc.observer.repositories.stores.RepositoryProvider;
import org.observertc.webrtc.observer.repositories.stores.SynchronizationSourcesRepository;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@MicronautTest
class CallInitializerTaskTest {

    @Inject
    ObserverHazelcast observerHazelcast;

    @Inject
    RepositoryProvider repositoryProvider;

    @Inject
    Provider<CallInitializerTask> subjectProvider;

    static EasyRandom generator = new EasyRandom();

    @BeforeAll
    static void setup() {
        generator = new EasyRandom();
    }

    @AfterAll
    static void teardown() {

    }

    @Test
    public void shouldNotRegisterCallsWithoutSSRC() {
        Assertions.assertThrows(Exception.class, () -> {
            subjectProvider.get()
                    .forCallEntity(generator.nextObject(OldCallEntity.class))
                    .perform();
        });
    }

    @Test
    public void shouldNotRegisterCallsWithoutEntity() {
        Assertions.assertThrows(Exception.class, () -> {
            subjectProvider.get()
                    .forSSRCs(Set.of(1L))
                    .perform();
        });
    }

    @Test
    public void shouldRegisterCall() {
        // Given
        AtomicReference<UUID> found = new AtomicReference<>(null);
        OldCallEntity callEntity = generator.nextObject(OldCallEntity.class);
        Set<Long> SSRCs = Set.of(1L, 2L);
        CallInitializerTask callInitializerTask = subjectProvider.get();

        // When
        callInitializerTask
                .forCallEntity(callEntity)
                .forSSRCs(SSRCs)
                .execute();

        // Then
        SSRCs.stream().forEach(ssrc -> {
            Assertions.assertTrue(this.repositoryProvider.getSSRCRepository().exists(SynchronizationSourcesRepository.getKey(callEntity.serviceUUID, ssrc)));
        });
        Assertions.assertTrue(this.repositoryProvider.getCallEntitiesRepository().exists(callEntity.callUUID));
        Assertions.assertTrue(this.repositoryProvider.getCallNamesRepository().exists(callEntity.callName));
        Collection<String> keys = this.repositoryProvider.getCallSynchronizationSourcesRepository().find(callEntity.callUUID);
        Assertions.assertTrue(0 < keys.size());
        keys.stream().forEach(key -> {
            Assertions.assertTrue(this.repositoryProvider.getSSRCRepository().exists(key));
        });
    }


}