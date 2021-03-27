package org.observertc.webrtc.observer.controllers;///*

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.views.View;

import java.util.HashMap;
import java.util.Map;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/")
public class IndexController {

	public IndexController() {

	}

	@Get()
	public HttpStatus index() {
		return HttpStatus.OK;
	}

	@Secured(SecurityRule.IS_ANONYMOUS)
	@View("home")
	@Get("/home")
	public Map<String, Object> getHome() {
		return new HashMap<>();
	}

	@Secured(SecurityRule.IS_AUTHENTICATED)
	@Get("/whoiam")
	public HttpResponse whoiam() {
		return HttpResponse.ok("something");
	}

	@Secured(SecurityRule.IS_ANONYMOUS)
	@Get("/about")
	public Map<String, Object> about() {
		// TODO: for versioning: https://www.baeldung.com/java-comparing-versions
		return Map.of(
//				"version", Runtime.Version.parse("0.7.0");
		);
	}

}
