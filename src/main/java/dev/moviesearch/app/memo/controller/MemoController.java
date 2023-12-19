package dev.moviesearch.app.memo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dev.moviesearch.app.memo.domain.MemoDto;
import dev.moviesearch.app.memo.service.MemoService;
import jakarta.servlet.http.HttpSession;

//@Controller
public class MemoController {

	@Autowired
	private MemoService memoService;

	@GetMapping("/hello")
	public String hello(Model model) {
		model.addAttribute("name", "world!");
		return "hello";
	}

	/**
	 * jsp 파일 없이 화면에 json type 으로 값을 뿌리는 방법
	 */
	@GetMapping("/sample")
	public ResponseEntity<List<MemoDto>> sample() {
		List<MemoDto> memoList = memoService.findMemoList();
		return new ResponseEntity<>(memoList, HttpStatus.OK);
	}

	/**
	 * 위와 같은 결과
	 * @return
	 */
	@GetMapping("/sample2")
	@ResponseBody
	public List<MemoDto> sample2() {
		return memoService.findMemoList();
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

	// 메모 목록
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("memos", memoService.findMemoList());
		return "index";
	}

	// 메모 목록 2
	@GetMapping("/memo")
	public String listMemo() {
		return "redirect:/";
	}

	// 메모 상세
	@GetMapping("/memo/{idx}")
	public String detailMemo(Model model, @PathVariable int idx) {
		model.addAttribute("memo", memoService.findMemo(idx));
		return "detail";
	}

	@GetMapping("/error2")
	public ModelAndView error() {
		ModelAndView mav = new ModelAndView("error");
		return mav;
	}

	// 메모 등록 요청
	@GetMapping("/memo/insert")
	public String showInsertMemo() {
		return "insert";
	}

	// 메모 등록
	@PostMapping("/memo")
	public String insertMemo(@ModelAttribute MemoDto req) {
		memoService.insertMemo(req);
		return "redirect:/";
	}

	// 메모 수정 요청
	@GetMapping("/memo/{idx}/update")
	public String showUpdateMemo(Model model, @PathVariable int idx) {
		model.addAttribute("memo", memoService.findMemo(idx));
		return "update";
	}

	// 메모 수정
	@PutMapping("/memo")
	public String updateMemo(@ModelAttribute MemoDto req) {
		memoService.updateMemo(req);
		return "redirect:/";
	}

	@DeleteMapping("/memo/{idx}")
	public String deleteMemo(@PathVariable int idx) {
		memoService.deleteMemo(idx);
		return "redirect:/";
	}
}
