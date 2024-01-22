package dev.moviesearch.app.movieapi.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MovieDto {
	private int id; 
	private boolean adult; 
	private String backdropPath; 
	private List<Integer> genreIds; 
	private String originalLanguage; 
	private String originalTitle; 
	private String overview; 
	private double popularity; 
	private String posterPath; 
	private Date releaseDate; 
	private String title; 
	private boolean video; 
	private double voteAverage; 
	private int voteCount; 
}
