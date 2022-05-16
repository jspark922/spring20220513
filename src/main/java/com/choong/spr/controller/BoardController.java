package com.choong.spr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choong.spr.domain.BoardDto;
import com.choong.spr.service.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping("list")
	public void BoardList(BoardDto board, Model model) {
		List<BoardDto> list = service.getBoardList();
		
		model.addAttribute("boardList", list);
	}
	
	@GetMapping("{id}")
	public String getBoard(@PathVariable("id") int id, Model model) {
		BoardDto board = service.getBoard(id);
		
		model.addAttribute("board", board);
		
		return "/board/get";
	}
	
	@GetMapping("write")
	public void writeBoard() {
		
	}
	
	@PostMapping("write")
	public String wirteBoardProcess(BoardDto board, RedirectAttributes rttr) {
		boolean success = service.addBoard(board);
		
		if (success) {
			rttr.addFlashAttribute("message", "게시물이 추가 되었습니다.");
		} else {
			rttr.addFlashAttribute("message", "게시물 추가 중 문제가 발생하였습니다.");
		}
		
		return "redirect:/board/" + board.getId();
	}
	
	@PostMapping("modify")
	public String modifyBoard(BoardDto board, RedirectAttributes rttr) {
		boolean success = service.updateBoard(board);
		
		if (success) {
			rttr.addFlashAttribute("message", "게시물이 수정되었습니다.");
		} else {
			rttr.addFlashAttribute("message", "게시물 수정 중 문제가 발생하였습니다.");
		}
		
		return "redirect:/board/" + board.getId();
	}
	
	@PostMapping("remove")
	public String removeBoard(int id, RedirectAttributes rttr) {
		boolean success = service.removeBoardByID(id);
		
		if(success) {
			rttr.addFlashAttribute("message", "게시물이 삭제되었습니다.");
		} else {
			rttr.addFlashAttribute("message", "게시물 삭제 중 문제가 발생하였습니다.");
		}
		
		return "redirect:/board/list";
	}
	
}
