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

package org.observertc.webrtc.observer.configs.stores;

import org.observertc.webrtc.observer.common.ObjectToString;
import org.observertc.webrtc.observer.configs.ObserverConfig;
import org.observertc.webrtc.observer.configs.ObserverConfigDispatcher;
import org.observertc.webrtc.observer.configs.SentinelConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
public class SentinelsStore extends StoreAbstract<String, SentinelConfig> {

	private static final Logger logger = LoggerFactory.getLogger(SentinelsStore.class);

	@Inject
	ObserverConfigDispatcher configDispatcher;

	@PostConstruct
	void setup() {
		ObserverConfig defaultConfig = configDispatcher.getConfig();
		this.process(defaultConfig);
		this.configDispatcher.onSentinelsChanged()
				.map(event -> event.config)
				.subscribe(this::process);
	}

	private void process(ObserverConfig observerConfig) {
		try {
			Map<String, SentinelConfig> sentinels = observerConfig.sentinels
					.stream()
					.collect(Collectors.toMap(
							c -> c.name,
							java.util.function.Function.identity())
					);
			this.setMap(sentinels);
		} catch (Throwable t) {
			logger.warn("Exception occurred while processing {}. ", ObjectToString.toString(observerConfig));
		}
	}
}
