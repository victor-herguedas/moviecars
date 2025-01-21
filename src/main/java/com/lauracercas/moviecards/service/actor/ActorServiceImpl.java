package com.lauracercas.moviecards.service.actor;

import com.lauracercas.moviecards.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Autor: Laura Cercas Ramos Proyecto: TFM Integración Continua con GitHub
 * Actions Fecha: 04/06/2024
 */
@Service
public class ActorServiceImpl implements ActorService {

	@Autowired
	RestTemplate template;

	String url = "https://moviecards-service-herguedas.azurewebsites.net/actors";

	@Override
	public List<Actor> getAllActors() {
		final Actor[] actores = this.template.getForObject(this.url, Actor[].class);
		final List<Actor> actoresList = Arrays.asList(actores);
		return actoresList;
	}

	@Override
	public Actor save(Actor actor) {
		if (actor.getId() != null && actor.getId() > 0) {
			this.template.put(this.url, actor);
		} else {
			actor.setId(0);
			this.template.postForObject(this.url, actor, String.class);
		}
		return actor;
	}

	@Override
	public Actor getActorById(Integer actorId) {
		final Actor actor = this.template.getForObject(this.url + "/" + actorId, Actor.class);
		return actor;
	}
}
