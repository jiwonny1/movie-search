package dev.moviesearch.app.movieapi.service.impl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.moviesearch.app.movieapi.domain.MovieListDto;
import dev.moviesearch.app.movieapi.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
	ObjectMapper mapper = new ObjectMapper(); 

	@Override
	public MovieListDto getPopularMovieList(int page) {
		MovieListDto movieList = null;
		HttpRequest request = HttpRequest.newBuilder()
			    .uri(URI.create("https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=ko-KR&page=1&sort_by=popularity.desc"))
			    .header("accept", "application/json")
			    .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5OWEzMjg1MTRlNTZiZjBmMDQyNWE4M2U4MTMzZDE1ZiIsInN1YiI6IjY1N2JiMGNlN2EzYzUyMDBjYTdiMzYzZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.UEuBUn0_d32uIRvHVkxY8yF8a84APoHMkF5lCdcAwa0")
			    .method("GET", HttpRequest.BodyPublishers.noBody())
			    .build();
		
		try {
			HttpResponse<String> response;
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			movieList = mapper.readValue(response.body(), MovieListDto.class);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		//System.out.println(response.body());
		System.out.println(movieList);
		
		return movieList;
	}
	
	

}
