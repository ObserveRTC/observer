package org.observertc.webrtc.observer.controllers;///*

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import org.observertc.webrtc.observer.ObserverConfig;
import org.observertc.webrtc.observer.configbuilders.ConfigConverter;
import org.observertc.webrtc.observer.configbuilders.ConfigOperations;
import org.observertc.webrtc.observer.repositories.ConfigRepository;
import org.observertc.webrtc.observer.repositories.ObserverConfigDispatcher;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller(value = "/config")
public class ConfigController {

	private final ConfigOperations configOperations;

	@Inject
	ObserverConfigDispatcher observerConfigDispatcher;

	@Inject
	ConfigRepository configRepository;

	public ConfigController(ObserverConfigDispatcher observerConfigDispatcher) throws IOException {
		ObserverConfig config = observerConfigDispatcher.getConfig();
		Map<String, Object> map = ConfigConverter.convertToMap(config);
		this.configOperations = new ConfigOperations(map);
	}

	@Get("/")
	public ObserverConfig read() throws IOException {
		return this.observerConfigDispatcher.getConfig();
	}

	@Put("/")
	public ObserverConfig update(Map<String, Object> config) throws IOException {
		ObserverConfig actual = this.observerConfigDispatcher.getConfig();
		Map<String, Object> actualMap = ConfigConverter.convertToMap(actual);
		Map<String, Object> updatedMap = this.configOperations
				.replace(actualMap)
				.add(config)
				.makeConfig();
		ObserverConfig updatedConfig = ConfigConverter.convert(ObserverConfig.class, updatedMap);
		this.configRepository.updateObserverConfig(updatedConfig);
		return updatedConfig;
	}

	@Delete("/")
	public ObserverConfig remove(String keys) throws IOException {
		ObserverConfig actual = this.observerConfigDispatcher.getConfig();
		Map<String, Object> actualMap = ConfigConverter.convertToMap(actual);
		Map<String, Object> updatedMap = this.configOperations
				.replace(actualMap)
				.remove(Arrays.asList(keys.split(".")))
				.makeConfig();
		ObserverConfig updatedConfig = ConfigConverter.convert(ObserverConfig.class, updatedMap);
		this.configRepository.updateObserverConfig(updatedConfig);
		return updatedConfig;
	}
}