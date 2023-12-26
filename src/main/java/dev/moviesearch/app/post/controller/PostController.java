package dev.moviesearch.app.post.controller;

import dev.moviesearch.app.post.domain.PostDto;
import dev.moviesearch.app.post.service.PostService;
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


	/**
	 * jsp 파일 없이 화면에 json type 으로 값을 뿌리는 방법
	 */
	@GetMapping("/sample")
	public ResponseEntity<List<PostDto>> sample() {
		List<PostDto> memoList = postService.findPostList();
		return new ResponseEntity<>(memoList, HttpStatus.OK);
	}

	/**
	 * 위와 같은 결과
	 * @return
	 */

	//getPost
//	@GetMapping("/postList")
//	@ResponseBody
//	public List<PostDto> GetPostList(HttpSession session, String category, String keyword, Model model) {
//		return postService.findPostList();
//	}

	@GetMapping("/detail/{contentId}")
	public String showMovieDetail(@PathVariable int contentId, Model model) {
		// Movie 객체를 모델에 추가
		model.addAttribute("contentId", contentId);

		return "postDetail";
	}
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

	// 메모 등록 요청
	@GetMapping("/insert")
	public String showInsertPost() {
		return "insert";
	}

	// 포스트 등록
	@PostMapping("/create")
	public String CreatePost(@ModelAttribute PostDto req) {
		postService.insertPost(req);
		return "redirect:/postList";
	}

	// 메모 수정 요청
	@GetMapping("/memo/{idx}/update")
	public String showUpdateMemo(Model model, @PathVariable int idx) {
		model.addAttribute("memo", postService.findPost(idx));
		return "update";
	}

	// 메모 수정
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
