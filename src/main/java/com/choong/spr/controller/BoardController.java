package com.choong.spr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choong.spr.domain.BoardDto;
import com.choong.spr.domain.PageInfoDto;
import com.choong.spr.domain.ReplyDto;
import com.choong.spr.service.BoardService;
import com.choong.spr.service.ReplyService;

@Controller
@RequestMapping("board")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@Autowired
	private ReplyService replyService;
	
	@GetMapping("list")
	public void BoardList(@RequestParam(name="page", defaultValue = "1")int page, 
						  @RequestParam(name = "keyword", defaultValue= "") String keyword, 
						  Model model) {
		int rowPerPage = 10;
		List<BoardDto> list = service.getBoardList(page, rowPerPage, keyword);
		int totalRecords = service.countCustomers();
		
		int end = (totalRecords -1) / rowPerPage + 1;
		
		PageInfoDto pageInfo = new PageInfoDto();
		pageInfo.setCurrent(page);
		pageInfo.setEnd(end);
		
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("boardList", list);
	}
	
	@GetMapping("{id}")
	public String getBoard(@PathVariable("id") int id, Model model) {
		BoardDto board = service.getBoard(id);
		
		List<ReplyDto> replyList = replyService.listReplyByBoardId(id);
		
		model.addAttribute("board", board);
		model.addAttribute("replyList", replyList);
		
		return "/board/get";
	}
	
	@GetMapping("write")
	public void writeBoard() {
		
	}
	
	@PostMapping("write")
	public String wirteBoardProcess(BoardDto board, RedirectAttributes rttr) {
		boolean success = service.addBoard(board);
		
		if (success) {
			rttr.addFlashAttribute("message", "???????????? ?????? ???????????????.");
		} else {
			rttr.addFlashAttribute("message", "????????? ?????? ??? ????????? ?????????????????????.");
		}
		
		return "redirect:/board/" + board.getId();
	}
	
	@PostMapping("modify")
	public String modifyBoard(BoardDto board, RedirectAttributes rttr) {
		boolean success = service.updateBoard(board);
		
		if (success) {
			rttr.addFlashAttribute("message", "???????????? ?????????????????????.");
		} else {
			rttr.addFlashAttribute("message", "????????? ?????? ??? ????????? ?????????????????????.");
		}
		
		return "redirect:/board/" + board.getId();
	}
	
	@PostMapping("remove")
	public String removeBoard(int id, RedirectAttributes rttr) {
		boolean success = service.removeBoardByID(id);
		
		if(success) {
			rttr.addFlashAttribute("message", "???????????? ?????????????????????.");
		} else {
			rttr.addFlashAttribute("message", "????????? ?????? ??? ????????? ?????????????????????.");
		}
		
		return "redirect:/board/list";
	}
	
}
