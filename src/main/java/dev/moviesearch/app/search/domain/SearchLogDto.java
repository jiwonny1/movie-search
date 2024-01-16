package dev.moviesearch.app.search.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class SearchLogDto {
	@Setter
	private String userId;
	private String SearchWord;
	private String SearchDate;
}
