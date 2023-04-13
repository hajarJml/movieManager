package com.example.movies_manager.entity;

import java.time.LocalDate;
import java.util.List;


public class Movie {
	
	private Long id;
	
	private String title;
	
	private Long duration;
	
	private Float rating;
	
	private String description;

	
	private List<Actor> actors;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public Movie() {
	}

	public Movie(Long id, String title, Long duration, Float rating, String description) {
		this.id = id;
		this.title = title;
		this.duration = duration;
		this.rating = rating;
		this.description = description;
	}

	public Movie(Long id, String title, Long duration, Float rating, String description, List<Actor> actors) {
		this.id = id;
		this.title = title;
		this.duration = duration;
		this.rating = rating;
		this.description = description;
		this.actors = actors;
	}
}
