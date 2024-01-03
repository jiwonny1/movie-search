package dev.moviesearch.app.search.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import dev.moviesearch.app.movieapi.domain.MovieDto;
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
	public List<MovieDto> searchByTitle(String[] keywords, int page) {
		List<String> keywordList = new ArrayList<String>(Arrays.asList(keywords));
		Map<String, Object> data = new HashMap<>();
		data.put("keywords", keywordList);
		data.put("page", page);
		return searchMapper.selectByTitle(data);
	}
	
	@Override
	public List<MovieDto> searchByPartOfTitle(String[] keywords, int page) {
		List<String> keywordList = new ArrayList<String>(Arrays.asList(keywords));
		Map<String, Object> data = new HashMap<>();
		data.put("keywords", keywordList);
		data.put("page", page);
		return searchMapper.selectByPartOfTitle(data);
	}

	@Override
	public List<MovieDto> searchByKeyword(String[] keywords, int page) {
		List<String> keywordList = new ArrayList<String>(Arrays.asList(keywords));
		Map<String, Object> data = new HashMap<>();
		data.put("keywords", keywordList);
		data.put("page", page);
		return searchMapper.selectByKeyword(data);
	}
	
	@Override
	public List<MovieDto> searchByHalfKeyword(String[] keywords, int page) {
		List<String> keywordList = new ArrayList<String>(Arrays.asList(keywords));
		Map<String, Object> data = new HashMap<>();
		data.put("keywords", keywordList);
		data.put("page", page);
		return searchMapper.selectByHalfKeyword(data);
	}

}
