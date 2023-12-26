package dev.moviesearch.app.movieapi.service;

import java.util.List;

import dev.moviesearch.app.movieapi.domain.MovieDto;
import dev.moviesearch.app.movieapi.domain.MovieListDto;

public interface MovieService {
	MovieListDto getPopularMovieList(int page);
	MovieListDto getPlayingMovieList(int page);
	void insertMovieList(List<MovieDto> data);
}
