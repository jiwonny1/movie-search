package dev.moviesearch.app.memo.controller;

import dev.moviesearch.app.memo.domain.MemoDto;
import dev.moviesearch.app.memo.service.MemoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
public class MemoController {

	@Autowired
	private MemoService memoService;

	@Operation(summary = "영화 목록", description = "영화목록 입니다.")
	@GetMapping("/hello")
	public String hello(Model model) {
		model.addAttribute("name", "world!");
		return "hello";
	}


	/**
	 * jsp 파일 없이 화면에 json type 으로 값을 뿌리는 방법
	 */
	@Operation(summary = "메모 목록 Sample Code", description = "ResponseEntity 를 이용하여 메모 목록을 보여주는 코드")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = MemoDto.class))) })
	@GetMapping("/sample")
	public ResponseEntity<List<MemoDto>> sample() {
		List<MemoDto> memoList = memoService.findMemoList();
		return new ResponseEntity<>(memoList, HttpStatus.OK);
	}

	/**
	 * 위와 같은 결과
	 * @return
	 */
	@Operation(summary = "메모 목록 Sample Code", description = "ResponseBody 를 이용하여 메모 목록을 보여주는 코드")
	@GetMapping("/sample2")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = MemoDto.class))) })
	@ResponseBody
	public List<MemoDto> sample2() {
		return memoService.findMemoList();
	}

	@Operation(summary = "세션 처리 Sample Code", description = "세션 처리에 대한 예제를 보여주는 코드")
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
	@Operation(summary = "메모 목록 Sample Code", description = "Model 을 이용하여 메모 목록을 보여주는 코드")
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("memos", memoService.findMemoList());
		return "index";
	}

	// 메모 목록 2
	@Operation(summary = "목록 코드", description = "/memo 접근 시 / 로 리다이렉트 시키는 코드")
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
