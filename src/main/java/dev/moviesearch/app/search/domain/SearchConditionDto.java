package dev.moviesearch.app.search.domain;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SearchConditionDto {
	private List<String> keywords; // 키워드 리스트
	private List<Integer> genreIds; // 장르 리스트
	
	private int page;
	
	private String orderBy; // 정렬 기준
	
	private Date startMonth; // 개봉일자 검색 시작점
	private Date endMonth; // 개봉일자 검색 끝점
	
	private String originalLanguage; // 원래 언어
}
