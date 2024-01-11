package dev.moviesearch.app.search.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import dev.moviesearch.app.movieapi.domain.MovieDto;
import dev.moviesearch.app.search.domain.SearchConditionDto;
import dev.moviesearch.app.search.domain.SearchLogDto;

@Mapper
public interface SearchMapper {
	List<SearchLogDto> selectRecentSearch(String userId);
	void deleteRecentSearch(SearchLogDto searchLogDto);
	void insertRecentSearch(List<SearchLogDto> data);
	
	List<MovieDto> selectByTitle(SearchConditionDto param);
	List<MovieDto> selectByPartOfTitle(SearchConditionDto param);
	List<MovieDto> selectByKeyword(SearchConditionDto param);
	List<MovieDto> selectByHalfKeyword(SearchConditionDto param);
	
}
