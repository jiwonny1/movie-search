package dev.moviesearch.app.movieapi.domain;

import lombok.Getter;

@Getter
public enum MovieLanguages {
	NOSELECT("선택안함", "no"),
	KOREAN("한국어", "ko"),
	ENGLISH("영어", "en"),
	CHINESE("중국어", "cn"),
	JAPANESE("일본어", "ja"),
	SPANISH("스페인어", "es");
	
	private String languageName;
	private String code;
	
	private MovieLanguages(String languageName, String code) {
		this.languageName = languageName;
		this.code = code;
	}
}
