package dev.moviesearch.app.main.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dev.moviesearch.app.movieapi.domain.MovieListDto;
import dev.moviesearch.app.movieapi.service.MovieService;
import dev.moviesearch.app.search.domain.SearchLogDto;
import dev.moviesearch.app.search.service.SearchService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {
	
	private final MovieService movieService;
	private final SearchService searchService;

	@GetMapping("/")
	public String getIndex(HttpSession session, Model model) {
		
		// 개발용 코드----------------------------
		session.setAttribute("user", "jsw4795");
		//session.invalidate();
		//----------------------------------------
		
		String userId = (String)session.getAttribute("user");
		model.addAttribute("userId", userId);
		
		// 최근 검색어 가져오기------------------------------------------
		if(userId != null) {
			List<SearchLogDto> recentSearch = searchService.getRecentSearch(userId);
			model.addAttribute("recentSearch", recentSearch);
		}
		//---------------------------------------------------------------
		
		// 영화 리스트 가져오기------------------------------------------
		MovieListDto trend = movieService.getPopularMovieList(1);
		MovieListDto recentRelease = movieService.getPlayingMovieList(1);
		
		model.addAttribute("trend", trend);
		model.addAttribute("recentRelease", recentRelease);
		//---------------------------------------------------------------
		
		return "index";
	}
	
}
