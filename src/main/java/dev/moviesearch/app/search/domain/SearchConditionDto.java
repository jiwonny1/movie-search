package dev.moviesearch.app.search.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SearchConditionDto {
	private List<String> keywords; // 키워드 리스트
	private List<Integer> genreIds; // 장르 리스트
	
	private int page; // 페이지
	
	private String orderBy; // 정렬 기준
	
	private String startMonth; // 개봉일자 검색 시작점
	private String endMonth; // 개봉일자 검색 끝점
	
	private String originalLanguage; // 원래 언어
}
