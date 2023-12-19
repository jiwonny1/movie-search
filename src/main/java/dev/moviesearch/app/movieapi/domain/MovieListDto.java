package dev.moviesearch.app.movieapi.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MovieListDto {
	private int page;
	private List<MovieDto> results;
	private int total_pages;
	private int total_results;
}
