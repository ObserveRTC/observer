package org.observertc.webrtc.observer.tasks;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.observertc.webrtc.observer.ObserverHazelcast;
import org.observertc.webrtc.observer.models.CallEntity;
import org.observertc.webrtc.observer.models.SynchronizationSourceEntity;
import org.observertc.webrtc.observer.repositories.hazelcast.RepositoryProvider;
import org.observertc.webrtc.observer.repositories.hazelcast.SynchronizationSourcesRepository;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.Set;
import java.util.UUID;

@MicronautTest
class CallFinderTaskTest {

    @Inject
    ObserverHazelcast observerHazelcast;

    @Inject
    RepositoryProvider repositoryProvider;

    @Inject
    Provider<CallFinderTask> subjectProvider;

    static EasyRandom generator;

    @BeforeAll
    static void setup() {
        generator = new EasyRandom();
    }

    @AfterAll
    static void teardown() {

    }

    @Test
    public void shouldThrowExceptionIfServiceUUIDNotProvided() {
        Assertions.assertThrows(Exception.class, () -> {
            subjectProvider.get()
                    .forSSRCs(Set.of(1L))
                    .perform();
        });
    }

    @Test
    public void shouldThrowExceptionIfSSRCsAreNotProvided() {
        Assertions.assertThrows(Exception.class, () -> {
            subjectProvider.get()
                    .forServiceUUID(UUID.randomUUID())
                    .perform();
        });
    }

    @Test
    public void shouldFindByUUID() {
        // Given
        CallEntity callEntity = generator.nextObject(CallEntity.class);
        SynchronizationSourceEntity SSRCEntity = generator.nextObject(SynchronizationSourceEntity.class);
        SSRCEntity.callUUID = callEntity.callUUID;
        this.repositoryProvider.getCallEntitiesRepository().add(callEntity.callUUID, callEntity);
        this.repositoryProvider.getSSRCRepository().save(SynchronizationSourcesRepository.getKey(callEntity.serviceUUID, SSRCEntity.SSRC), SSRCEntity);
        CallFinderTask task = subjectProvider.get();

        // When

        Set<UUID> foundCallUUIDs = task
                .forSSRCs(Set.of(SSRCEntity.SSRC))
                .forServiceUUID(callEntity.serviceUUID)
                .execute()
                .getResult();

        // Then
        Assertions.assertEquals(1, foundCallUUIDs.size());
        UUID foundCallUUID = foundCallUUIDs.stream().findFirst().get();
        Assertions.assertNotNull(foundCallUUID);
        Assertions.assertEquals(foundCallUUID, callEntity.callUUID);
    }

    @Test
    public void shouldFindByName() {
        // Given
        CallEntity callEntity = generator.nextObject(CallEntity.class);
        this.repositoryProvider.getCallNamesRepository().add(callEntity.callName, callEntity.callUUID);
        this.repositoryProvider.getCallEntitiesRepository().add(callEntity.callUUID, callEntity);
        CallFinderTask task = subjectProvider.get();

        // When

        Set<UUID> foundCallUUIDs = task
                .forSSRCs(Set.of(1L))
                .forServiceUUID(callEntity.serviceUUID)
                .forCallName(callEntity.callName)
                .execute()
                .getResult();

        // Then
        Assertions.assertEquals(1, foundCallUUIDs.size());
        UUID foundCallUUID = foundCallUUIDs.stream().findFirst().get();
        Assertions.assertNotNull(foundCallUUID);
        Assertions.assertEquals(foundCallUUID, callEntity.callUUID);
    }
}