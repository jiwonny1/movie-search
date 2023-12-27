package dev.moviesearch.app.search.service;

import java.util.List;

import dev.moviesearch.app.search.domain.SearchLogDto;

public interface SearchService {
	List<SearchLogDto> getRecentSearch(String userId);
	void deleteRecentSearch(SearchLogDto searchLogDto);
	void insertRecentSearch(List<SearchLogDto> data);
}
