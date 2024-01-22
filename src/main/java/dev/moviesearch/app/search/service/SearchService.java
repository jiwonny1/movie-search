package dev.moviesearch.app.search.service;

import java.util.List;

import dev.moviesearch.app.movieapi.domain.MovieDto;
import dev.moviesearch.app.search.domain.SearchConditionDto;
import dev.moviesearch.app.search.domain.SearchLogDto;

public interface SearchService {
	List<SearchLogDto> getRecentSearch(String userId);
	void deleteRecentSearch(SearchLogDto searchLogDto);
	void insertRecentSearch(List<SearchLogDto> data);
	
	List<MovieDto> searchByTitle(SearchConditionDto param);
	List<MovieDto> searchByPartOfTitle(SearchConditionDto param);
	List<MovieDto> searchByKeyword(SearchConditionDto param);
	List<MovieDto> searchByHalfKeyword(SearchConditionDto param);
	
	// 자바로 검색
	List<MovieDto> searchUsingJava(SearchConditionDto param, String type);
}
