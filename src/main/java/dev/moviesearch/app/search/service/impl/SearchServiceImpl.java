package dev.moviesearch.app.search.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

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

	
	// 자바로 검색
	@Override
	public List<MovieDto> searchUsingJava(SearchConditionDto param, String type) {
		List<MovieDto> result = null;
		
		// TODO : 모든 영화 데이터 검색
		List<MovieDto> contentList = searchMapper.selectAllContent();
		List<Map<String, Integer>> genreIdList = searchMapper.selectGenreIds(); // KEY : id, genreId
		Map<Integer, List<String>> keywordMap = new HashMap<>(); // <컨텐츠아이디, 키워드아이디리스트>
		
		// TODO : List -> Stream 변환
		Stream<MovieDto> contentStream = contentList.stream();
		Stream<Map<String, Integer>> genreIdStream = genreIdList.stream();
		
		// TODO : 검색 타입에 따라 Stream 이용해서 원하는 값 검색 (페이징 까지) 
		Predicate<MovieDto> check1 = null; // 첫 번째 filter 작업
		
		// 키워드 검색일 때 키워드 설정
		if(type.equals("k") || type.equals("ks")) {
			searchMapper.selectKeywords().forEach( map -> {
				List<String> tempKeywordList = keywordMap.getOrDefault(map.get("id"), new ArrayList<String>());
				tempKeywordList.add(map.get("keyword").toString());
				keywordMap.put((Integer) map.get("id"), tempKeywordList);
			});
		}
		
		int searchKeywordSize = param.getKeywords().size(); // 입력된 검색어의 개수
			
		switch (type) {
		case "t": // 제목으로 모두 일치하는 항목
			check1 = content -> {
				String title = content.getTitle();
				
				for(String searchKeyword : param.getKeywords()) 
					if(!title.contains(searchKeyword)) return false;
				
				return true;
			};
			break;
		case "ts": // 제목으로 일부 일치하는 항목
			check1 = content -> {
				int count = 0;
				String title = content.getTitle();
				
				for(String searchKeyword : param.getKeywords()) 
					if(title.contains(searchKeyword)) count++;
				
				if(count == 0 || count == searchKeywordSize) return false;
				else return true;
			};
			break;
		case "k": // 키워드로 모두 일치하는 항목
			check1 = content -> {
				List<String> thisKeywordList = keywordMap.get(content.getId());
				
				if(thisKeywordList == null) return false;
				
				for(String searchKeyword : param.getKeywords()) {
					if(!thisKeywordList.contains(searchKeyword)) return false;
				}
				
				return true;
			};
			break;
		case "ks": // 키워드로 일부 일치하는 항목
			check1 = content -> {
				int count = 0;
				List<String> thisKeywordList = keywordMap.get(content.getId());
				
				if(thisKeywordList == null) return false;
				
				for(String searchKeyword : param.getKeywords()) {
					if(thisKeywordList.contains(searchKeyword)) count++;
				}
				
				if(count == 0 || count == searchKeywordSize) return false;
				else return true;
			};
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
		
		// TODO : 필터링 실행, 리스트로 변환
		result = contentStream.filter(check1).toList();
		
		return result;
	}

}
