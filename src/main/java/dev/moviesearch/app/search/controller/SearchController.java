package dev.moviesearch.app.search.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
		
		// 개발용 코드------------------------------------------
		MovieListDto trend = movieService.getPopularMovieList(1);
		model.addAttribute("trend", trend);
		//---------------------------------------------------------------
		
		// 로그 등록----------------------------------------------
		List<SearchLogDto> data = new ArrayList<>();
		for(String keyword : keywords) {
			data.add(SearchLogDto.builder()
								 .userId(userId)
								 .SearchWord(keyword)
								 .build());
		}
		searchService.insertRecentSearch(data);
		
		return "searchList";
	}
	
	@PostMapping("/deleteRecentSearch")
	@ResponseBody
	public void deleteRecentSearch(HttpSession session, SearchLogDto searchLogDto) {
		searchLogDto.setUserId(session.getAttribute("user").toString());
		searchService.deleteRecentSearch(searchLogDto);
	}

}
