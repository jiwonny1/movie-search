package dev.moviesearch.app.search.domain;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum SearchTypes {
	TITLE_ALL("제목이 모두 일치하는 항목", "t"),
	TITLE_PART("제목이 일부 일치하는 항목", "ts"),
	KEYWORD_ALL("키워드가 모두 일치하는 항목", "k"),
	KEYWORD_PART("키워드가 일부 일치하는 항목", "ks");
	
	private String description;
	private String code;
	
	private SearchTypes(String description, String code) {
		this.description = description;
		this.code = code;
	}
	
	public static SearchTypes find(String code) {
		return Arrays.stream(SearchTypes.values())
					 .filter( searchTypes -> searchTypes.code.equals(code))
					 .findAny()
					 .orElseThrow( () -> new IllegalArgumentException("일치하는 값이 없습니다."));
	}
	
}
