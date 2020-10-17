/*
 * Copyright  2020 Balazs Kreith
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * This file is generated by jOOQ.
 */
package org.observertc.webrtc.observer.jooq.tables.pojos;


import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * SentReports
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sentreports implements Serializable {

    private static final long serialVersionUID = -952130457;

    private final byte[] signature;
    private final byte[] peerconnectionuuid;
    private final Long   reported;

    public Sentreports(Sentreports value) {
        this.signature = value.signature;
        this.peerconnectionuuid = value.peerconnectionuuid;
        this.reported = value.reported;
    }

    public Sentreports(
        byte[] signature,
        byte[] peerconnectionuuid,
        Long   reported
    ) {
        this.signature = signature;
        this.peerconnectionuuid = peerconnectionuuid;
        this.reported = reported;
    }

    @NotNull
    @Size(max = 255)
    public byte[] getSignature() {
        return this.signature;
    }

    @Size(max = 16)
    public byte[] getPeerconnectionuuid() {
        return this.peerconnectionuuid;
    }

    public Long getReported() {
        return this.reported;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Sentreports (");

        sb.append("[binary...]");
        sb.append(", ").append("[binary...]");
        sb.append(", ").append(reported);

        sb.append(")");
        return sb.toString();
    }
}