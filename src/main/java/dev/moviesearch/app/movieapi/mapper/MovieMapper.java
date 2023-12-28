package dev.moviesearch.app.movieapi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import dev.moviesearch.app.movieapi.domain.MovieDto;

@Mapper
public interface MovieMapper {
	void insertMovieList(List<MovieDto> data);
}
