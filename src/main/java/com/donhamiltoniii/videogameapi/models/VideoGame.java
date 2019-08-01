package com.donhamiltoniii.videogameapi.models;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class VideoGame {

	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private String studio;
	private Float rating;

	@JsonIgnore
	private ArrayList<Float> ratings = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}

	public ArrayList<Float> getRatings() {
		return ratings;
	}

	public Float getRating() {
		Float total = 0.0f;
		for (float rating : this.ratings) {
			total += rating;
		}

		this.rating = total / ratings.size();

		return this.rating;
	}

	public VideoGame() {
	}

	public VideoGame(String title, String studio, Float rating) {
		this.title = title;
		this.studio = studio;
		this.ratings.add(rating);
		this.rating = this.getRating();
	}

	public void addRating(Float newRating) {
		this.ratings.add(newRating);
		this.getRating();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VideoGame other = (VideoGame) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VideoGame [id=" + id + ", title=" + title + "]";
	}

}
