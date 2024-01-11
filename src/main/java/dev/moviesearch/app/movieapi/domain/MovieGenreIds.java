package dev.moviesearch.app.movieapi.domain;

import lombok.Getter;

@Getter
public enum MovieGenreIds {
	ACTION("액션", 28),
	ADVENTURE("모험", 12),
	ANIMATION("애니메이션", 16),
	COMEDY("코미디", 35),
	CRIME("범죄", 80),
	DOCUMENTARY("다큐멘터리", 99),
	DRAMA("드라마", 18),
	FAMILY("가족", 10751),
	FANTASY("판타지", 14),
	HISTORY("역사", 36),
	HORROR("공포", 27),
	MUSIC("음악", 10402),
	MYSTERY("미스터리", 9648),
	ROMANCE("로맨스", 10749),
	SCIENCE_FICTION("SF", 878),
	TV_MOVIE("TV 영화", 10770),
	THRILLER("스릴러", 53),
	WAR("전쟁", 10752),
	WESTERN("서부", 37);
	
	private String genreName;
	private int code;
	
	private MovieGenreIds(String genreName, int code) {
		this.genreName = genreName;
		this.code = code;
	}
}
