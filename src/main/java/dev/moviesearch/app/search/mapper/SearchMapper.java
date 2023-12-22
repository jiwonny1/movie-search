package dev.moviesearch.app.search.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import dev.moviesearch.app.search.domain.SearchLogDto;

@Mapper
public interface SearchMapper {
	List<SearchLogDto> selectRecentSearch(String userId);
}
