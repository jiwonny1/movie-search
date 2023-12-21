package dev.moviesearch.app.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dev.moviesearch.app.movieapi.domain.MovieListDto;
import dev.moviesearch.app.movieapi.service.MovieService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {
	
	private final MovieService movieService;

	@GetMapping("/")
	public String getIndex(HttpSession session, Model model) {
		
		MovieListDto top20 = movieService.getPopularMovieList(1);
		
		model.addAttribute("top20", top20);
		
		return "index";
	}
	
}
