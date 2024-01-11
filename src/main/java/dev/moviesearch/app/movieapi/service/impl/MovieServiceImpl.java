package dev.moviesearch.app.movieapi.service.impl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.moviesearch.app.movieapi.domain.MovieDto;
import dev.moviesearch.app.movieapi.domain.MovieListDto;
import dev.moviesearch.app.movieapi.mapper.MovieMapper;
import dev.moviesearch.app.movieapi.service.MovieService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {
	private ObjectMapper mapper = new ObjectMapper(); 
	private String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5OWEzMjg1MTRlNTZiZjBmMDQyNWE4M2U4MTMzZDE1ZiIsInN1YiI6IjY1N2JiMGNlN2EzYzUyMDBjYTdiMzYzZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.UEuBUn0_d32uIRvHVkxY8yF8a84APoHMkF5lCdcAwa0";
	private String language = "ko-KR";
	private String region = "KR";

	private final MovieMapper movieMapper;
	
	@Override
	public MovieListDto getPopularMovieList(int page) {
		MovieListDto movieList = null;
		String requestURL = "https://api.themoviedb.org/3/discover/movie?include_video=false&region=" + region + "&language=" + language + "&page=" + page + "&sort_by=popularity.desc";
		
		String jsonData = sendRequest(requestURL);
		movieList = (MovieListDto) convertJsonToVo(jsonData, MovieListDto.class);
		
		return movieList;
	}
	
	@Override
	public MovieListDto getPlayingMovieList(int page) {
		MovieListDto movieList = null;
		
		LocalDate today = LocalDate.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		String requestURL = "https://api.themoviedb.org/3/discover/movie?include_video=false&language=" + language + "&page=" + page + "&region=" + region + "&release_date.gte="+format.format(today.minusMonths(1))+"&release_date.lte="+format.format(today)+"&sort_by=primary_release_date.desc";
		
		String jsonData = sendRequest(requestURL);
		movieList = (MovieListDto) convertJsonToVo(jsonData, MovieListDto.class);
		
		return movieList;
	}
	
	@Override
	public MovieListDto getMovieListForInsert(int page, int year) {
		MovieListDto movieList = null;
		String requestURL = "https://api.themoviedb.org/3/discover/movie?include_video=false&region=" + region + "&language=" + language + "&page=" + page + "&sort_by=popularity.asc&year=" + year;
		
		String jsonData = sendRequest(requestURL);
		movieList = (MovieListDto) convertJsonToVo(jsonData, MovieListDto.class);
		
		return movieList;
	}
	
	@Override
	public void insertMovieList(List<MovieDto> data) {
		movieMapper.insertMovieList(data);
		
	}
	
	
//============================================== private methods ================================================
	
	private String sendRequest(String requestURL) {
		String jsonData = null;
		HttpRequest request = HttpRequest.newBuilder()
			    .uri(URI.create(requestURL))
			    .header("accept", "application/json")
			    .header("Authorization", "Bearer " + accessToken)
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
