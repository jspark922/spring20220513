package com.choong.spr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.choong.spr.domain.ReplyDto;
import com.choong.spr.service.ReplyService;

@Controller
@RequestMapping("reply")
public class ReplyController {
	@Autowired
	private ReplyService service;
	
	@PostMapping("add")
	public String addReply(ReplyDto reply) {
		boolean success = service.addReply(reply);
		
		return "redirect:/board/" + reply.getBoardId();
	}
	
	@PostMapping("modify")
	private String modifyReply(ReplyDto reply) {
		boolean success = service.modifyReply(reply);
		
		return "redirect:/board/" + reply.getBoardId();
	}
	
	@PostMapping("remove")
	private String removeReply(ReplyDto reply) {
		boolean success = service.removeReply(reply.getId());
		
		return "redirect:/board/" + reply.getBoardId();
	}
	
}
