package dev.moviesearch.app.movieapi.domain;

import lombok.Getter;

@Getter
public enum MovieOrderBy {
	TITLE_ASC("제목 오름차순", "TA"),
	TITLE_DESC("제목 내림차순", "TD"),
	RELEASE_DATE_ASC("개봉일 빠른순", "RA"),
	RELEASE_DATE_DESC("개봉일 느린순", "RD"),
	COUNT_ASC("평가 적은순", "CA"),
	COUNT_DESC("평가 많은순", "CD"),
	VOTE_ASC("평가 낮은순", "VA"),
	VOTE_DESC("평가 높은순", "VD"),
	POPULARITY_ASC("인지도 낮은순", "PA"),
	POPULARITY_DESC("인지도 높은순", "PD");
	
	private String orderName;
	private String code;
	
	private MovieOrderBy(String orderName, String code) {
		this.orderName = orderName;
		this.code = code;
	}
}
