package dev.moviesearch.app.movieapi.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MovieListDto {
	private int page;
	private List<MovieDto> results;
	private int totalPages;
	private int totalResults;
	
	private Map<String, Date> dates;
	
	// 검색 조건
	private String[] searchCondition;
}
