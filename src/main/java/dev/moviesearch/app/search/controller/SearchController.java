package dev.moviesearch.app.search.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.moviesearch.app.search.domain.SearchLogDto;
import dev.moviesearch.app.search.service.SearchService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/search")
@Controller
public class SearchController {
	
	private final SearchService searchService;
	
	@PostMapping("/deleteRecentSearch")
	@ResponseBody
	public void deleteRecentSearch(HttpSession session, SearchLogDto searchLogDto) {
		searchLogDto.setUserId(session.getAttribute("user").toString());
		searchService.deleteRecentSearch(searchLogDto);
	}

}
