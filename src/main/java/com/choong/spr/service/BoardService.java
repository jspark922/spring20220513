package com.choong.spr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.choong.spr.domain.BoardDto;
import com.choong.spr.mapper.BoardMapper;
import com.choong.spr.mapper.ReplyMapper;

@Service
public class BoardService {

	@Autowired
	private BoardMapper mapper;
	
	@Autowired
	private ReplyMapper replyMapper;
	
	public List<BoardDto> getBoardList(int page, int rowPerPage) {
		int from = (page-1) * rowPerPage;
		return mapper.selectBoard(from, rowPerPage);
	}

	public BoardDto getBoard(int id) {
		// TODO Auto-generated method stub
		return mapper.selectBoardById(id);
	}

	public boolean updateBoard(BoardDto board) {
		int cnt = mapper.updateBoard(board);
		
		return cnt == 1;
	}

	public boolean addBoard(BoardDto board) {
//		board.setInserted(LocalDateTime.now());
		
		int cnt = mapper.insertBoard(board);
		
		return cnt == 1;
	}

	@Transactional
	public boolean removeBoardByID(int id) {
		replyMapper.deleteReplyByBoard(id);
		
		int cnt = mapper.deleteBoard(id);
		return cnt == 1;
	}

	public int countCustomers() {
		// TODO Auto-generated method stub
		return mapper.countBoard();
	}


}
