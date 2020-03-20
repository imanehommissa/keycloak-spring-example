package com.example.keyvalue.model;

import javax.persistence.*;

@Entity(name="movies")
public class KeyValueEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int movieId;

	@Column(nullable=false)
	private String movieName;

	@Column(nullable=false)
	private String favActor;


	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getFavActor() {
		return favActor;
	}
	public void setFavActor(String favActor) {
		this.favActor = favActor;
	}
}
