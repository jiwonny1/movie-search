package dev.moviesearch.app.post.controller;

import dev.moviesearch.app.post.domain.PostDto;
import dev.moviesearch.app.post.service.PostService;
import dev.moviesearch.app.review.domain.ReviewDto;
import dev.moviesearch.app.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService postService;
	private final ReviewService reviewService;
	@Autowired
	public PostController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}


//	/**
//	 * jsp 파일 없이 화면에 json type 으로 값을 뿌리는 방법
//	 */
//	@GetMapping("/sample")
//	public ResponseEntity<List<PostDto>> sample() {
//		List<PostDto> memoList = postService.findPostList();
//		return new ResponseEntity<>(memoList, HttpStatus.OK);
//	}


	//getPost
//	@GetMapping("/postList")
//	@ResponseBody
//	public List<PostDto> GetPostList(HttpSession session, String category, String keyword, Model model) {
//		return postService.findPostList();
//	}

	@Operation(summary = "영화 디테일 페이지", description = "영화 페이지입니다.")
	@GetMapping("/detail/{contentId}")
	public String showMovieDetail(@PathVariable int contentId, Model model) {
		PostDto dto = postService.findPost(contentId);
		List<ReviewDto> reviewDto = reviewService.findReviewList(contentId);

		model.addAttribute("post", dto);
		model.addAttribute("reviews", reviewDto);

		return "postDetail2";
	}

	@Operation(summary = "세션 처리", description = "세션 처리")
	@GetMapping("/session")
	public String session(Model model, HttpSession session) {
		session.setAttribute("userId", "Goosia");
		session.setAttribute("isLogin", "true");
		session.setAttribute("cookieDate", LocalDateTime.now());
		model.addAttribute("userId", session.getAttribute("userId"));
		model.addAttribute("isLogin", session.getAttribute("isLogin"));
		return "session";
	}

	// 메모 목록 2
	@GetMapping("/memo")
	public String listMemo() {
		return "redirect:/";
	}

	// 메모 상세
	@GetMapping("/memo/{idx}")
	public String detailMemo(Model model, @PathVariable int idx) {
		model.addAttribute("memo", postService.findPost(idx));
		return "detail";
	}

	@GetMapping("/error2")
	public ModelAndView error() {
		ModelAndView mav = new ModelAndView("error");
		return mav;
	}
//
//	// 포스트 등록 화면
//	@GetMapping("/insert")
//	public String showInsertPost() {
//		return "insert";
//	}

//	// 포스트 등록
//	@PostMapping("/create")
//	public String CreatePost(@ModelAttribute PostDto req) {
//		postService.insertPost(req);
//		return "redirect:/postList";
//	}

	// 포스트 수정 요청
	@GetMapping("/memo/{idx}/update")
	public String showUpdateMemo(Model model, @PathVariable int idx) {
		model.addAttribute("memo", postService.findPost(idx));
		return "update";
	}

	// 포스트 수정
	@PutMapping("/memo")
	public String updateMemo(@ModelAttribute PostDto req) {
		postService.updatePost(req);
		return "redirect:/";
	}

	@DeleteMapping("/memo/{idx}")
	public String deleteMemo(@PathVariable int idx) {
		postService.deletePost(idx);
		return "redirect:/";
	}
}
