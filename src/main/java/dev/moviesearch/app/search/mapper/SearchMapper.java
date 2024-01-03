package dev.moviesearch.app.search.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import dev.moviesearch.app.movieapi.domain.MovieDto;
import dev.moviesearch.app.search.domain.SearchLogDto;

@Mapper
public interface SearchMapper {
	List<SearchLogDto> selectRecentSearch(String userId);
	void deleteRecentSearch(SearchLogDto searchLogDto);
	void insertRecentSearch(List<SearchLogDto> data);
	
	List<MovieDto> selectByTitle(Map<String, Object> data);
	List<MovieDto> selectByPartOfTitle(Map<String, Object> data);
	List<MovieDto> selectByKeyword(Map<String, Object> data);
	List<MovieDto> selectByHalfKeyword(Map<String, Object> data);
	
}
