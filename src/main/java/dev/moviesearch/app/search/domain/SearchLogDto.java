package dev.moviesearch.app.search.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchLogDto {
	private String userId;
	private String SearchWord;
	private String SearchDate;
}
