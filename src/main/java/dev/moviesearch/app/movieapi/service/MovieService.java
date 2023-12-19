package dev.moviesearch.app.movieapi.service;

import dev.moviesearch.app.movieapi.domain.MovieListDto;

public interface MovieService {
	MovieListDto getPopularMovieList(int page);
}
