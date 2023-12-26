package dev.moviesearch.app.movieapi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import dev.moviesearch.app.movieapi.domain.MovieDto;
import dev.moviesearch.app.search.domain.SearchLogDto;

@Mapper
public interface MovieMapper {
	void insertMovieList(List<MovieDto> data);
}
