package dev.moviesearch.app.review.controller;

import dev.moviesearch.app.review.domain.ReviewDto;
import dev.moviesearch.app.review.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;



//	@GetMapping("/session")
//	public String session(Model model, HttpSession session) {
//		session.setAttribute("userId", "Goosia");
//		session.setAttribute("isLogin", "true");
//		session.setAttribute("cookieDate", LocalDateTime.now());
//		model.addAttribute("userId", session.getAttribute("userId"));
//		model.addAttribute("isLogin", session.getAttribute("isLogin"));
//		return "session";
//	}

	// 리뷰 목록
	@GetMapping("/list/{contentId}")
	public String showReviewList(@PathVariable int contentId, Model model) {
		List<ReviewDto> reviewList = reviewService.findReviewList(contentId);
		model.addAttribute("reviews", reviewList);
		System.out.println("reviewList = " + reviewList);
		return "postDetail2";
	}


	// 리뷰 등록
	@PostMapping("/create/{contentId}")
	public String createReview(@ModelAttribute ReviewDto req, Principal principal) {
		//임시 아이디
		String userId = "cat";
		req.setUserId(userId);

		reviewService.insertReview(req);
		return "redirect:/post/detail/" + req.getContentId();
	}


	// 리뷰 수정
	@PutMapping("/memo")
	public String updateMemo(@ModelAttribute ReviewDto req, Principal principal) {
		reviewService.updateReview(req);
		return "redirect:/post/postDeteil/" + req.getContentId();
	}
	//리뷰 삭제
	@DeleteMapping("/memo/{idx}")
	public String deleteMemo(@PathVariable int idx) {
		reviewService.deleteReview(idx);
		return "redirect:/";
	}
}
