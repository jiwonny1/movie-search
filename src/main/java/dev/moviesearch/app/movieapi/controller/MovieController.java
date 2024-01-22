package dev.moviesearch.app.movieapi.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.moviesearch.app.movieapi.domain.MovieDto;
import dev.moviesearch.app.movieapi.service.MovieService;
import lombok.RequiredArgsConstructor;

@RequestMapping("/movie")
@RequiredArgsConstructor
@Controller
public class MovieController {
	
	private final MovieService movieService;
	
	@ResponseBody
	@GetMapping("/insert")
	public String insertMovieList() {
		for(int year = 2023; year <= 2024; year++) {
			for(int i = 1; i <= 500; i++) {
				List<MovieDto> data = movieService.getMovieListForInsert(i, year).getResults();
				if(data == null || data.size() == 0) break;
				
				movieService.insertMovieList(data);
				
				if(data.size() < 20) break;
			}
		}
		return "success";
	}

}
