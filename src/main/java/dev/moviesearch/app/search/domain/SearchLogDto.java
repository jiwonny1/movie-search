package dev.moviesearch.app.search.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class SearchLogDto {
	private String userId;
	private String SearchWord;
	private String SearchDate;
}
