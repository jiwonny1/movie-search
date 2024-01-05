package dev.moviesearch.app.search.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.moviesearch.app.movieapi.domain.MovieDto;
import dev.moviesearch.app.search.domain.SearchConditionDto;
import dev.moviesearch.app.search.domain.SearchLogDto;
import dev.moviesearch.app.search.mapper.SearchMapper;
import dev.moviesearch.app.search.service.SearchService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SearchServiceImpl implements SearchService {
	
	private final SearchMapper searchMapper;

	@Override
	public List<SearchLogDto> getRecentSearch(String userId) {
		return searchMapper.selectRecentSearch(userId);
	}

	@Override
	public void deleteRecentSearch(SearchLogDto searchLogDto) {
		searchMapper.deleteRecentSearch(searchLogDto);
		
	}

	@Override
	public void insertRecentSearch(List<SearchLogDto> data) {
		searchMapper.insertRecentSearch(data);
		
	}

	@Override
	public List<MovieDto> searchByTitle(SearchConditionDto param) {
		return searchMapper.selectByTitle(param);
	}
	
	@Override
	public List<MovieDto> searchByPartOfTitle(SearchConditionDto param) {
		return searchMapper.selectByPartOfTitle(param);
	}

	@Override
	public List<MovieDto> searchByKeyword(SearchConditionDto param) {
		return searchMapper.selectByKeyword(param);
	}
	
	@Override
	public List<MovieDto> searchByHalfKeyword(SearchConditionDto param) {
		return searchMapper.selectByHalfKeyword(param);
	}

}
