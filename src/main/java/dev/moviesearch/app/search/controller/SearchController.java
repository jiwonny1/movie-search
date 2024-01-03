package dev.moviesearch.app.search.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.moviesearch.app.movieapi.domain.MovieDto;
import dev.moviesearch.app.movieapi.domain.MovieListDto;
import dev.moviesearch.app.movieapi.service.MovieService;
import dev.moviesearch.app.search.domain.SearchLogDto;
import dev.moviesearch.app.search.service.SearchService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/search")
@Controller
public class SearchController {
	
	private final SearchService searchService;
	private final MovieService movieService;
	
	@GetMapping("")
	public String search(HttpSession session, String[] keywords, Model model) {
		
		String userId = (String)session.getAttribute("user");
		
//		// 개발용 코드------------------------------------------
//		MovieListDto trend = movieService.getPopularMovieList(1);
//		model.addAttribute("trend", trend);
//		//---------------------------------------------------------------
		
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
		List<MovieDto> title = searchService.searchByTitle(keywords, 1);
		model.addAttribute("title", title);
		
		
		
		
		// 키워드로 검색--------------------------------------------
		List<MovieDto> keyword = searchService.searchByKeyword(keywords, 1);
		model.addAttribute("keyword", keyword);
		
		// 일부 일치하는 항목 검색----------------------------------------
		if(keywords.length > 1) {
			// 제목 일부만 일치하는 항목 검색--------------------------------------------
			List<MovieDto> titlePart = searchService.searchByPartOfTitle(keywords, 1);
			model.addAttribute("titlePart", titlePart);
			
			// 키워드 절반만 일치하는 항목 검색--------------------------------------------
			List<MovieDto> keywordHalf = searchService.searchByHalfKeyword(keywords, 1);
			model.addAttribute("keywordHalf", keywordHalf);
		}

		return "searchList";
	}
	
	@PostMapping("/deleteRecentSearch")
	@ResponseBody
	public void deleteRecentSearch(HttpSession session, SearchLogDto searchLogDto) {
		searchLogDto.setUserId(session.getAttribute("user").toString());
		searchService.deleteRecentSearch(searchLogDto);
	}

}
