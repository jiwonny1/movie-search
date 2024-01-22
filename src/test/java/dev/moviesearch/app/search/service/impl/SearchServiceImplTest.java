package dev.moviesearch.app.search.service.impl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.assertj.core.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.moviesearch.app.movieapi.domain.MovieDto;
import dev.moviesearch.app.search.domain.SearchConditionDto;
import dev.moviesearch.app.search.mapper.SearchMapper;


@ExtendWith(MockitoExtension.class) // Mapper 없이 테스트
//@SpringBootTest // Mapper 사용 테스트
class SearchServiceImplTest {
	
	@Mock // Mapper 없이 테스트
	SearchMapper searchMapper;
	
	@InjectMocks // Mapper 없이 테스트
	//@Autowired // Mapper 사용 테스트
	SearchServiceImpl searchService;
	
	static List<MovieDto> allData = new ArrayList<>();
	static List<Integer> genreIds = new ArrayList<>();
	static List<Map<String, Object>> keywordList = new ArrayList<>();
	
	@BeforeAll
	static void setMapper() {
		// TODO : mapper에서 뭐를 출력할지
		genreIds.add(13);
		genreIds.add(28);
		genreIds.add(35);
		
		for(int i = 1; i <= 10000; i++) {
			allData.add(MovieDto.builder()
								.id(i)
								.originalLanguage("ko")
								.popularity(Math.round(Math.random() * 1000 * 1000) / 1000.0)
								.title("제목 - " + i)
								.voteAverage(Math.round(Math.random() * 10 * 10) / 10.0)
								.voteCount((int) Math.round(Math.random() * 1000))
								.releaseDate( new GregorianCalendar(20123, (i % 12), 11).getTime() )
								.genreIds(genreIds) // 일단 null, 나중에 필요하면 다시 생각하기
								.build());
			Map<String, Object> keywordSelect = new HashMap<>();
			keywordSelect.put("id", i);
			keywordSelect.put("keyword", String.valueOf(10001 - i));
			keywordList.add(keywordSelect);
			
			Map<String, Object> keywordSelect2 = new HashMap<>();
			keywordSelect2.put("id", i);
			keywordSelect2.put("keyword", "키워드-" + i);
			keywordList.add(keywordSelect2);			
		}
		
		
	}

	@Test
	@DisplayName("[자바 사용] 검색")
	void testSearchUsingJava() {
		// given
		// 이 메소드를 호출하는 코드가 테스트하는 메소드 내부에 없으면 unnecessary stubbings detected 오류
		given(searchMapper.selectAllContent()).willReturn(allData); 
		given(searchMapper.selectKeywords()).willReturn(keywordList);
		
		// 검색 조건 설정
		List<String> keywords = List.of(new String[] {"1", "키워드-1000"}); // 검색 키워드들
		List<Integer> genreIds = List.of(new Integer[] {}); // 검색 장르들 (장르 아이디)
		int page = 1; // 페이지
		String orderBy = null; // 정렬기준 
		String startMonth = null; // 개봉일 검색 시작 월 2023-04
		String endMonth = null; // 개봉일 검색 마지막 월 2023-10
		String originalLanguage = null; // 언어별 검색
		
		// 검색 타입 설정
		String type = "ks";
		
		SearchConditionDto searchCondition = SearchConditionDto.builder()
															   .keywords(keywords)
															   .genreIds(genreIds)
															   .page(page)
															   .orderBy(orderBy)
															   .startMonth(startMonth)
															   .endMonth(endMonth)
															   .originalLanguage(originalLanguage)
															   .build();
		
		// test when
		List<MovieDto> result = searchService.searchUsingJava(searchCondition, type);
		
		result.forEach(content -> System.out.println(content.getTitle()));
		
		// TODO : 모든 영화 데이터 검색 확인
		
		
		// TODO : List -> Stream 변환
		
		// TODO : 변환된거 확인
		
		// TODO : 검색 타입에 따라 Stream 이용해서 원하는 값 검색 (페이징 까지) 
		
		// TODO : 원하는 필터를 다 거쳤는지 확인
		
		// TODO : Stream -> List 변환
		
		// TODO : 변환된거 확인
	}
	
	
	@Disabled
	@Test
	@DisplayName("[SQL 사용] '제목' 일치 검색")
	void testSearchByTitle() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	@DisplayName("[SQL 사용] '제목' 일부 일치 검색")
	void testSearchByPartOfTitle() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	@DisplayName("[SQL 사용] '키워드' 일치 검색")
	void testSearchByKeyword() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	@DisplayName("[SQL 사용] '키워드' 일부 일치 검색")
	void testSearchByHalfKeyword() {
		fail("Not yet implemented");
	}


}
