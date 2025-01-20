package com.lauracercas.moviecards.service.actor;

import com.lauracercas.moviecards.model.Actor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

	private final RestTemplate restTemplate;
	private final String baseUrl = "https://moviecards-service-herguedas.azurewebsites.net/actors";

	public ActorServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<Actor> getAllActors() {
		// Llamada GET para obtener todos los actores
		final Actor[] actors = this.restTemplate.getForObject(this.baseUrl, Actor[].class);
		return Arrays.asList(actors != null ? actors : new Actor[0]);
	}

	@Override
	public Actor save(Actor actor) {
		if (actor.getId() != null) {
			// Llamada PUT para actualizar un actor existente
            this.restTemplate.put(this.baseUrl, actor);
			return actor;
		} else {
			// Llamada POST para crear un nuevo actor
			return this.restTemplate.postForObject(this.baseUrl, actor, Actor.class);
		}
	}

	@Override
	public Actor getActorById(Integer actorId) {
		// Construir la URL para obtener un actor específico
		final String url = UriComponentsBuilder.fromHttpUrl(this.baseUrl).pathSegment(actorId.toString()).toUriString();

		return this.restTemplate.getForObject(url, Actor.class);
	}
}