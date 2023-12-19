package dev.moviesearch.app.movieapi.service.impl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.moviesearch.app.movieapi.domain.MovieListDto;
import dev.moviesearch.app.movieapi.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
	private ObjectMapper mapper = new ObjectMapper(); 
	private String accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5OWEzMjg1MTRlNTZiZjBmMDQyNWE4M2U4MTMzZDE1ZiIsInN1YiI6IjY1N2JiMGNlN2EzYzUyMDBjYTdiMzYzZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.UEuBUn0_d32uIRvHVkxY8yF8a84APoHMkF5lCdcAwa0";
	private String language = "ko-KR";

	
	@Override
	public MovieListDto getPopularMovieList(int page) {
		MovieListDto movieList = null;
		String requestURL = "https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=" + language + "&page=" + page + "&sort_by=popularity.desc";
		
		String jsonData = sendRequest(requestURL);
		movieList = (MovieListDto) convertJsonToVo(jsonData, MovieListDto.class);
		
		return movieList;
	}
	
	
//============================================== private methods ================================================
	
	private String sendRequest(String requestURL) {
		String jsonData = null;
		HttpRequest request = HttpRequest.newBuilder()
			    .uri(URI.create(requestURL))
			    .header("accept", "application/json")
			    .header("Authorization", accessToken)
			    .method("GET", HttpRequest.BodyPublishers.noBody())
			    .build();
		
		try {
			HttpResponse<String> response;
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			jsonData = response.body();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		return jsonData;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Object convertJsonToVo(String jsonData, Class c) {
		Object result = null;
		
		try {
			result = mapper.readValue(jsonData, c);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
