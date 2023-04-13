package com.example.movies_manager.entity;

import java.util.List;

public class Actor {
	
	private Long id;
	
	private String name;
	
	private String lastName;
	
	private Integer age;
	
	private String nationality;
	
	private List<Movie> movies;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public Actor() {
	}

	public Actor(Long id, String name, String lastName, Integer age, String nationality) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.age = age;
		this.nationality = nationality;
	}

	public Actor(Long id, String name, String lastName, Integer age, String nationality, List<Movie> movies) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.age = age;
		this.nationality = nationality;
		this.movies = movies;
	}
}
