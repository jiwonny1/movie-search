package dev.moviesearch.app.movieapi.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MovieListDto {
	private int page;
	private List<MovieDto> results;
	private int total_pages;
	private int total_results;
	
	private Map<String, Date> dates;
	
	// 검색 조건
	private String[] searchCondition;
}
