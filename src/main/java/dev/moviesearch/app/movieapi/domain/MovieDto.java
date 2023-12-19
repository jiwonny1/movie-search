package dev.moviesearch.app.movieapi.domain;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MovieDto {
	private boolean adult;
	private String backdrop_path;
	private List<Integer> genre_ids;
	private int id;
	private String original_language;
	private String original_title;
	private String overview;
	private double popularity;
	private String poster_path;
	private Date release_date;
	private String title;
	private boolean video;
	private double vote_average;
	private int vote_count;
}
