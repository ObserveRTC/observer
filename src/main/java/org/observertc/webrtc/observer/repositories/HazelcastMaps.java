package org.observertc.webrtc.observer.repositories;

import com.hazelcast.map.IMap;
import com.hazelcast.multimap.MultiMap;
import org.observertc.webrtc.observer.ObserverHazelcast;
import org.observertc.webrtc.observer.dto.*;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class HazelcastMaps {

    private static final String HAZELCAST_CALLS_MAP_NAME = "observertc-calls";
    private static final String HAZELCAST_CALL_TO_CLIENT_IDS_MAP_NAME = "observertc-call-to-clients";
    private static final String HAZELCAST_SERVICE_ROOM_TO_CALL_ID_MAP_NAME = "observertc-service-room-to-call";

    // client related
    // public, because observer configure the map with expiration
    public static final String HAZELCAST_CLIENTS_MAP_NAME = "observertc-clients";
    private static final String HAZELCAST_CLIENT_TO_PEER_CONNECTION_IDS_MAP_NAME = "observertc-client-to-peerconnections";

    // pc related
    private static final String HAZELCAST_PEER_CONNECTIONS_MAP_NAME = "observertc-peerconnections";
    private static final String HAZELCAST_PEER_CONNECTIONS_INBOUND_TRACK_IDS_MAP_NAME = "observertc-peerconnections-to-inbound-tracks";
    private static final String HAZELCAST_PEER_CONNECTIONS_OUTBOUND_TRACK_IDS_MAP_NAME = "observertc-peerconnections-to-outbound-tracks";

    // MediaTrack
    private static final String HAZELCAST_PEER_CONNECTION_MEDIA_TRACKS_MAP_NAME = "observertc-peerconnection-media-tracks";

    public static final String HAZELCAST_WEAKLOCKS_MAP_NAME = "observertc-weaklocks";
    public static final String HAZELCAST_CONFIGURATIONS_MAP_NAME = "observertc-configurations";

    @Inject
    ObserverHazelcast observerHazelcast;

    // calls
    private IMap<UUID, CallDTO> calls;
    private MultiMap<UUID, UUID> callToClientIds;
    private IMap<String, UUID> serviceRoomToCallIds;

    // clients
    private IMap<UUID, ClientDTO> clients;
    private MultiMap<UUID, UUID> clientToPeerConnectionIds;

    // peer connections
    private IMap<UUID, PeerConnectionDTO> peerConnections;
    private MultiMap<UUID, String> peerConnectionToInboundMediaTrackIds;
    private MultiMap<UUID, String> peerConnectionToOutboundMediaTrackIds;

    // media tracks
    private IMap<String, MediaTrackDTO> mediaTracks;

    // other necessary maps
    private IMap<String, WeakLockDTO> weakLocks;
    private IMap<String, ConfigDTO> configurations;

    @PostConstruct
    void setup() {
        this.calls = observerHazelcast.getInstance().getMap(HAZELCAST_CALLS_MAP_NAME);
        this.callToClientIds = observerHazelcast.getInstance().getMultiMap(HAZELCAST_CALL_TO_CLIENT_IDS_MAP_NAME);
        this.serviceRoomToCallIds = observerHazelcast.getInstance().getMap(HAZELCAST_SERVICE_ROOM_TO_CALL_ID_MAP_NAME);

        this.clients = observerHazelcast.getInstance().getMap(HAZELCAST_CLIENTS_MAP_NAME);
        this.clientToPeerConnectionIds = observerHazelcast.getInstance().getMultiMap(HAZELCAST_CLIENT_TO_PEER_CONNECTION_IDS_MAP_NAME);

        this.peerConnections = observerHazelcast.getInstance().getMap(HAZELCAST_PEER_CONNECTIONS_MAP_NAME);
        this.peerConnectionToInboundMediaTrackIds = observerHazelcast.getInstance().getMultiMap(HAZELCAST_PEER_CONNECTIONS_INBOUND_TRACK_IDS_MAP_NAME);
        this.peerConnectionToOutboundMediaTrackIds = observerHazelcast.getInstance().getMultiMap(HAZELCAST_PEER_CONNECTIONS_OUTBOUND_TRACK_IDS_MAP_NAME);

        this.mediaTracks = observerHazelcast.getInstance().getMap(HAZELCAST_PEER_CONNECTION_MEDIA_TRACKS_MAP_NAME);
        this.weakLocks = observerHazelcast.getInstance().getMap(HAZELCAST_WEAKLOCKS_MAP_NAME);

        this.configurations = observerHazelcast.getInstance().getMap(HAZELCAST_CONFIGURATIONS_MAP_NAME);
    }

    public MultiMap<String, UUID> getCallNames(UUID serviceUUID) {
        String mapName = String.format("observertc-callnames-%s", serviceUUID.toString());
        MultiMap<String, UUID> result = observerHazelcast.getInstance().getMultiMap(mapName);
        return result;
    }

    public IMap<UUID, CallDTO> getCalls(){
        return this.calls;
    }
    public MultiMap<UUID, UUID> getCallToClientIds() { return this.callToClientIds; }
    public IMap<String, UUID> getServiceRoomToCallIds() { return this.serviceRoomToCallIds; }

    public IMap<UUID, ClientDTO> getClients() { return this.clients; }
    public MultiMap<UUID, UUID> getClientToPeerConnectionIds() { return this.clientToPeerConnectionIds; }

    public IMap<UUID, PeerConnectionDTO> getPeerConnections() { return this.peerConnections; }
    public MultiMap<UUID, String> getPeerConnectionToInboundTrackIds() { return this.peerConnectionToInboundMediaTrackIds; }
    public MultiMap<UUID, String> getPeerConnectionToOutboundTrackIds() { return this.peerConnectionToOutboundMediaTrackIds; }

    public IMap<String, MediaTrackDTO> getMediaTracks() {
        return this.mediaTracks;
    }

    public IMap<String, WeakLockDTO> getWeakLocks() {return this.weakLocks;}

    public IMap<String, ConfigDTO> getConfigurations() { return this.configurations; }
}
