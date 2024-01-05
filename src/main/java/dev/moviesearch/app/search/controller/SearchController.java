package dev.moviesearch.app.search.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.moviesearch.app.movieapi.domain.MovieDto;
import dev.moviesearch.app.search.domain.SearchConditionDto;
import dev.moviesearch.app.search.domain.SearchLogDto;
import dev.moviesearch.app.search.service.SearchService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/search")
@Controller
public class SearchController {
	
	private final SearchService searchService;
	
	
	@GetMapping("")
	public String search(HttpSession session, @RequestParam("keywords") List<String> keywords, Model model) {
		
		String userId = (String)session.getAttribute("user");
		
//		// 개발용 코드------------------------------------------
//		MovieListDto trend = movieService.getPopularMovieList(1);
//		model.addAttribute("trend", trend);
//		//---------------------------------------------------------------
		
		SearchConditionDto param = SearchConditionDto.builder()
													 .keywords(keywords)
													 .page(1)
													 .build();
		
		// 로그 등록----------------------------------------------
		if(userId != null) {
			List<SearchLogDto> data = new ArrayList<>();
			for(String keyword : keywords) {
				data.add(SearchLogDto.builder()
									 .userId(userId)
									 .SearchWord(keyword)
									 .build());
			}
			searchService.insertRecentSearch(data);
		}
		
		// 제목으로 검색--------------------------------------------
		List<MovieDto> title = searchService.searchByTitle(param);
		model.addAttribute("title", title);
		
		
		
		
		// 키워드로 검색--------------------------------------------
		List<MovieDto> keyword = searchService.searchByKeyword(param);
		model.addAttribute("keyword", keyword);
		
		// 일부 일치하는 항목 검색----------------------------------------
		if(keywords.size() > 1) {
			// 제목 일부만 일치하는 항목 검색--------------------------------------------
			List<MovieDto> titlePart = searchService.searchByPartOfTitle(param);
			model.addAttribute("titlePart", titlePart);
			
			// 키워드 절반만 일치하는 항목 검색--------------------------------------------
			List<MovieDto> keywordHalf = searchService.searchByHalfKeyword(param);
			model.addAttribute("keywordHalf", keywordHalf);
		}
		
		// 링크로 설정할 파마리터 문자열 생성
		StringBuilder sb = new StringBuilder();
		for(String word : keywords) {
			sb.append("keywords=");
			sb.append(word);
			sb.append("&");
		}
		sb.deleteCharAt(sb.length()-1);
		model.addAttribute("keywords", sb.toString());
		
		return "searchList";
	}
	
	// 왜 Dto안에 List로는 파라미터가 자동으로 안들어가는건가....
	@GetMapping("/detail/{type}")
	public String searchDetail( @PathVariable String type, 
								@RequestParam(required = true, name = "keywords") List<String> keywords, 
								@RequestParam(required = false, name = "genreIds") List<Integer> genreIds,
								@RequestParam(required = false, defaultValue = "1") int page,
								@RequestParam(required = false) String orderBy, 
								@RequestParam(required = false) String startMonth,
								@RequestParam(required = false) String endMonth,
								@RequestParam(required = false) String originalLanguage, 
								Model model) {
		List<MovieDto> movies = null;
		
		SearchConditionDto param = new SearchConditionDto(keywords, genreIds, page, orderBy, startMonth, endMonth, originalLanguage);
		
		switch (type) {
		case "t": 
			movies = searchService.searchByTitle(param);
			break;
		case "ts":
			movies = searchService.searchByPartOfTitle(param);
			break;
		case "k":
			movies = searchService.searchByKeyword(param);			
			break;
		case "ks":
			movies = searchService.searchByHalfKeyword(param);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
		
		model.addAttribute("movies", movies);
		
		return "searchDetail";
	}
	
	@PostMapping("/detail/{type}")
	public String searchDetailList( @PathVariable String type, 
									@RequestParam(required = true, name = "keywords") List<String> keywords, 
									@RequestParam(required = false, name = "genreIds") List<Integer> genreIds,
									@RequestParam(required = false, defaultValue = "1") int page,
									@RequestParam(required = false) String orderBy, 
									@RequestParam(required = false) String startMonth,
									@RequestParam(required = false) String endMonth,
									@RequestParam(required = false) String originalLanguage, 
									Model model) {
		List<MovieDto> movies = null;
		
		SearchConditionDto param = new SearchConditionDto(keywords, genreIds, page, orderBy, startMonth, endMonth, originalLanguage);
		
		switch (type) {
		case "t": 
			movies = searchService.searchByTitle(param);
			break;
		case "ts":
			movies = searchService.searchByPartOfTitle(param);
			break;
		case "k":
			movies = searchService.searchByKeyword(param);			
			break;
		case "ks":
			movies = searchService.searchByHalfKeyword(param);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
		
		model.addAttribute("movies", movies);
		
		return "movies";
	}
	
	@PostMapping("/deleteRecentSearch")
	@ResponseBody
	public void deleteRecentSearch(HttpSession session, SearchLogDto searchLogDto) {
		searchLogDto.setUserId(session.getAttribute("user").toString());
		searchService.deleteRecentSearch(searchLogDto);
	}

}
