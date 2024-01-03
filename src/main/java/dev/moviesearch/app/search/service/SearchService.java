package dev.moviesearch.app.search.service;

import java.util.List;

import dev.moviesearch.app.movieapi.domain.MovieDto;
import dev.moviesearch.app.search.domain.SearchLogDto;

public interface SearchService {
	List<SearchLogDto> getRecentSearch(String userId);
	void deleteRecentSearch(SearchLogDto searchLogDto);
	void insertRecentSearch(List<SearchLogDto> data);
	
	List<MovieDto> searchByTitle(String[] keywords, int page);
	List<MovieDto> searchByPartOfTitle(String[] keywords, int page);
	List<MovieDto> searchByKeyword(String[] keywords, int page);
	List<MovieDto> searchByHalfKeyword(String[] keywords, int page);
}
