package dev.moviesearch.app.search.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import dev.moviesearch.app.search.domain.SearchConditionDto;
import dev.moviesearch.app.search.domain.SearchLogDto;
import dev.moviesearch.app.search.service.impl.SearchServiceImpl;

@WebMvcTest(SearchController.class)
public class SearchControllerTest {
	
	private static String userId = "jsw4795";
	private static List<SearchLogDto> searchLogList = new ArrayList<>();
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	SearchServiceImpl searchService;
	
	@BeforeAll
	static void makeLog() {
		for(int i = 1; i <= 20; i++)
			searchLogList.add(SearchLogDto.builder().userId(userId).SearchWord("keyword" + i).SearchDate("2024-01-16").build());
	}
	
	@Test
	@DisplayName("검색 메인화면 테스트")
	void testSearchList() throws Exception {
		//BDDMockito.given(searchService.getRecentSearch(userId)).willReturn(searchLogList);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/search")
											  .param("keywords", "123")
											  .param("keywords", "keyword2")
											  )
				.andExpect(status().isOk())
				.andDo(print());
		
//		verify(searchService).searchByTitle(SearchConditionDto.builder()
//				 .keywords(new ArrayList<String>())
//				 .page(1)
//				 .build());
		
	}
	
	@Test
	@DisplayName("상세 검색 테스트")
	void testSearchDetail() throws Exception {
		String searchType = "t";
		mockMvc.perform(MockMvcRequestBuilders.get("/search/detail/" + searchType)
												  .param("keywords", "keyword1")
												  .param("keywords", "keyword2")
												  )
				.andExpect(status().isOk())
				.andDo(print());
		
		verify(searchService, times(1)).searchByTitle(any());
		// 파라미터에 따라 어떤게 실행되는지를 보고싶었으나, Mapper에서 모든걸 처리해서 컨트롤러 테스트로 확인 불가
	}
	
}
