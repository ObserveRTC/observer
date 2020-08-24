/*
 * This file is generated by jOOQ.
 */
package org.observertc.webrtc.observer.jooq;


import org.observertc.webrtc.observer.jooq.tables.Activestreams;
import org.observertc.webrtc.observer.jooq.tables.Observers;
import org.observertc.webrtc.observer.jooq.tables.Peerconnections;
import org.observertc.webrtc.observer.jooq.tables.Sentreports;
import org.observertc.webrtc.observer.jooq.tables.Users;


/**
 * Convenience access to all tables in WebRTCObserver
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * A table to track the active streams
     */
    public static final Activestreams ACTIVESTREAMS = Activestreams.ACTIVESTREAMS;

    /**
     * Observers
     */
    public static final Observers OBSERVERS = Observers.OBSERVERS;

    /**
     * A table to store peer connection reports generated by the service
     */
    public static final Peerconnections PEERCONNECTIONS = Peerconnections.PEERCONNECTIONS;

    /**
     * SentReports
     */
    public static final Sentreports SENTREPORTS = Sentreports.SENTREPORTS;

    /**
     * Users
     */
    public static final Users USERS = Users.USERS;
}
