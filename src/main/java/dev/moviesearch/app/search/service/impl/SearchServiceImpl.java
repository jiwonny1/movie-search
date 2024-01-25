package dev.moviesearch.app.search.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
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
		// 결과가 담길 리스트
		List<MovieDto> result = null;
		
// -------------------------------------------- 필요한 데이터 초기화 -----------------------------------------------------
		Stream<MovieDto> contentStream = searchMapper.selectAllContent().stream();
		Map<Integer, List<Integer>> genreMap = new HashMap<>(); // <컨텐츠아이디, 키워드아이디리스트>
		Map<Integer, List<String>> keywordMap = new HashMap<>(); // <컨텐츠아이디, 키워드아이디리스트>
		
		// 키워드 검색일 때 키워드 설정
		if(type.equals("k") || type.equals("ks")) {
			// 불러온 키워드 정보를 Map<컨텐츠아이디, 키워드리스트> 로 변환
			searchMapper.selectKeywords().forEach( map -> {
				List<String> tempKeywordList = keywordMap.getOrDefault(map.get("id"), new ArrayList<String>());
				tempKeywordList.add(map.get("keyword").toString());
				keywordMap.put((Integer) map.get("id"), tempKeywordList);
			});
		}
		
		// 장르를 이용한 검색일 때 장르 설정
		if(param.getGenreIds() != null && param.getGenreIds().size() > 0) {
			// 불러온 장르 정보를 Map<컨텐츠아이디, 장르아이디리스트> 로 변환
			searchMapper.selectGenreIds().forEach( map -> {
				List<Integer> tempGenreList = genreMap.getOrDefault(map.get("id"), new ArrayList<Integer>());
				tempGenreList.add(map.get("genreId"));
				genreMap.put(map.get("id"), tempGenreList);
			});
		}
		
// -------------------------------------------- 작업 설정 -----------------------------------------------------
		Predicate<MovieDto> checkBySearchType = movie -> true; // 검색 타입에 따른 filter 조건
		Predicate<MovieDto> checkOriginalLanguage = movie -> true; // 국가별 (원어) 조건
		Predicate<MovieDto> checkStartMonth = movie -> true; // 개봉일자 시작 조건
		Predicate<MovieDto> checkEndMonth = movie -> true; // 개봉일자 시작 조건
		Predicate<MovieDto> checkGenreIds = movie -> true; // 장르검색 조건
		Comparator<MovieDto> orderBy = (o1, o2) -> 0; // 정렬 조건
		
		// 검색 타입에 따른 검색 조건 설정 (무조건 실행)
		checkBySearchType = setCheckBySearchType(type, param, keywordMap);
		// 정렬 조건 설정 (무조건 실행) (null인경우 PD로 지정)
		orderBy = setOrderBy(param);
		
		// 국가별 검색 파라미터가 들어오고, no가 아닐 때만 설정
		if(param.getOriginalLanguage() != null && !param.getOriginalLanguage().equals("no"))
			checkOriginalLanguage = movie -> movie.getOriginalLanguage().equals(param.getOriginalLanguage());
			
		// 개봉일자 검색 파라미터가 들어왔을 때만 설정
		if(param.getStartMonth() != null) {
			checkStartMonth = movie -> {
				try {
					return (movie.getReleaseDate().equals(new SimpleDateFormat("yyyy-MM").parse(param.getStartMonth())) 
							|| movie.getReleaseDate().after(new SimpleDateFormat("yyyy-MM").parse(param.getStartMonth())));
				} catch (ParseException e) {
					e.printStackTrace();
					return true;
				}
			};
		}
		if(param.getEndMonth() != null) {
			checkEndMonth = movie -> {
				try {
					return (movie.getReleaseDate().equals(new SimpleDateFormat("yyyy-MM").parse(param.getEndMonth())) 
							|| movie.getReleaseDate().before(new SimpleDateFormat("yyyy-MM").parse(param.getEndMonth())));
				} catch (ParseException e) {
					e.printStackTrace();
					return true;
				}
			};
		}
		
		// 장르 검색 파라미터가 들어왔을 때만 설정
		if(param.getGenreIds() != null && param.getGenreIds().size() > 0)
			checkGenreIds = movie -> {
				List<Integer> thisGenreIdList = genreMap.get(movie.getId());
				
				if(thisGenreIdList == null) return false;
				
				for(int genreId : param.getGenreIds()) {
					if(!thisGenreIdList.contains(genreId)) return false;
				}
				
				return true;
			};
		
		
// ------------------------------------------- 필터링 실행, 리스트로 변환 -------------------------------------------
		result = contentStream.filter(checkBySearchType)
							  .filter(checkOriginalLanguage)
							  .filter(checkStartMonth)
							  .filter(checkEndMonth)
							  .filter(checkGenreIds)
							  .sorted(orderBy)
							  .skip( (param.getPage()-1)*30 )
							  .limit(param.getPage() * 30)
							  .toList();
		
		return result;
	}
	
//-------------------------------------------------- Private Methods ------------------------------------------------------	
	
	private Predicate<MovieDto> setCheckBySearchType(String type, SearchConditionDto param, Map<Integer, List<String>> keywordMap) {		
		return switch (type) {
				case "t": { // 제목이 모두 일치하는 항목
					Predicate<MovieDto> checkBySearchType = content -> {
						String title = content.getTitle();
						
						for(String searchKeyword : param.getKeywords()) 
							if(!title.contains(searchKeyword)) return false;
						
						return true;
					};
					yield checkBySearchType;
				}
				case "ts": { // 제목이 일부 일치하는 항목
					Predicate<MovieDto> checkBySearchType = content -> {
						int count = 0;
						String title = content.getTitle();
						
						for(String searchKeyword : param.getKeywords()) 
							if(title.contains(searchKeyword)) count++;
						
						if(count == 0 || count == param.getKeywords().size()) return false;
						else return true;
					};
					yield checkBySearchType;
				}
				case "k": { // 키워드가 모두 일치하는 항목
					Predicate<MovieDto> checkBySearchType = content -> {
						List<String> thisKeywordList = keywordMap.get(content.getId());
						
						if(thisKeywordList == null) return false;
						
						for(String searchKeyword : param.getKeywords()) {
							if(!thisKeywordList.contains(searchKeyword)) return false;
						}
						
						return true;
					};
					yield checkBySearchType;
				}
				case "ks": { // 키워드가 일부 일치하는 항목
					Predicate<MovieDto> checkBySearchType = content -> {
						int count = 0;
						List<String> thisKeywordList = keywordMap.get(content.getId());
						
						if(thisKeywordList == null) return false;
						
						for(String searchKeyword : param.getKeywords()) {
							if(thisKeywordList.contains(searchKeyword)) count++;
						}
						
						if(count == 0 || count == param.getKeywords().size()) return false;
						else return true;
					};
					yield checkBySearchType;
				}
				default:
					throw new IllegalArgumentException("존재하지 않는 검색타입: " + type);
				};
	}
	
	private Comparator<MovieDto> setOrderBy(SearchConditionDto param) {
		if(param.getOrderBy() == null) { // null 일 경우 PD로 지정
			Comparator<MovieDto> orderBy = (o1, o2) -> {
				if(o1.getPopularity() > o2.getPopularity()) return 1;
				else if(o1.getPopularity() == o2.getPopularity()) return 0;
				else return -1;
			};
			return orderBy;
		}
		
		return switch (param.getOrderBy()) {
				case "PA": { // 인지도 오름차순
					Comparator<MovieDto> orderBy = (o1, o2) -> {
						if(o1.getPopularity() > o2.getPopularity()) return 1;
						else if(o1.getPopularity() == o2.getPopularity()) return 0;
						else return -1;
					};
					yield orderBy;
				}
				case "PD": { // 인지도 내림차순
					Comparator<MovieDto> orderBy = (o1, o2) -> {
						if(o2.getPopularity() > o1.getPopularity()) return 1;
						else if(o2.getPopularity() == o1.getPopularity()) return 0;
						else return -1;
					};
					yield orderBy;
				}
				case "RA": { // 개봉일 오름차순
					Comparator<MovieDto> orderBy = (o1, o2) -> {
						return o1.getReleaseDate().compareTo(o2.getReleaseDate());
					};
					yield orderBy;
				}
				case "RD": { // 개봉일 내림차순
					Comparator<MovieDto> orderBy = (o1, o2) -> {
						return o2.getReleaseDate().compareTo(o1.getReleaseDate());
					};
					yield orderBy;
				}
				case "CA": { // 투표수 오름차순
					Comparator<MovieDto> orderBy = (o1, o2) -> {
						if(o1.getVoteCount() > o2.getVoteCount()) return 1;
						else if(o1.getVoteCount() == o2.getVoteCount()) return 0;
						else return -1;
					};
					yield orderBy;
				}
				case "CD": { // 투표수 내림차순
					Comparator<MovieDto> orderBy = (o1, o2) -> {
						if(o2.getVoteCount() > o1.getVoteCount()) return 1;
						else if(o2.getVoteCount() == o1.getVoteCount()) return 0;
						else return -1;
					};
					yield orderBy;
				}
				case "TA": { // 제목 오름차순
					Comparator<MovieDto> orderBy = (o1, o2) -> {
						return o1.getTitle().compareToIgnoreCase(o2.getTitle());
					};
					yield orderBy;
				}
				case "TD": { // 제목 내림차순
					Comparator<MovieDto> orderBy = (o1, o2) -> {
						return o2.getTitle().compareToIgnoreCase(o1.getTitle());
					};
					yield orderBy;
				}
				case "VA": { // 평점 오름차순
					Comparator<MovieDto> orderBy = (o1, o2) -> {
						if(o1.getVoteAverage() > o2.getVoteAverage()) return 1;
						else if(o1.getVoteAverage() == o2.getVoteAverage()) return 0;
						else return -1;
					};
					yield orderBy;
				}
				case "VD": { // 평점 내림차순
					Comparator<MovieDto> orderBy = (o1, o2) -> {
						if(o2.getVoteAverage() > o1.getVoteAverage()) return 1;
						else if(o2.getVoteAverage() == o1.getVoteAverage()) return 0;
						else return -1;
					};
					yield orderBy;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + param.getOrderBy());
				};
	}

}
