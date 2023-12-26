package dev.moviesearch.app.movieapi.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MovieDto {
	private boolean adult; //
	private String backdropPath; //
	private List<Integer> genreIds; // 생각 좀 더 해야함
	private int id; // CONTENT_ID
	private String originalLanguage; //
	private String originalTitle; //
	private String overview; //
	private double popularity; //
	private String posterPath; //
	private Date releaseDate; //
	private String title; //
	private boolean video; //
	private double voteAverage; //
	private int voteCount; //
}
