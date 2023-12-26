package dev.moviesearch.app.movieapi.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.moviesearch.app.movieapi.domain.MovieDto;
import dev.moviesearch.app.movieapi.service.MovieService;
import lombok.RequiredArgsConstructor;

@RequestMapping("/movie")
@RequiredArgsConstructor
@Controller
public class MovieController {
	
	private final MovieService movieService;
	
	@GetMapping("/insert")
	public String insertMovieList() {
		for(int i = 1; i <= 100; i++) {
			List<MovieDto> data = movieService.getPopularMovieList(i).getResults();
			movieService.insertMovieList(data);
		}
		return "redirect:/";
	}

}
