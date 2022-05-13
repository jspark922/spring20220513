package com.choong.spr.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.choong.spr.domain.BoardDto;
import com.choong.spr.mapper.BoardMapper;

@Service
public class BoardService {

	@Autowired
	private BoardMapper mapper;

	public List<BoardDto> getBoardList() {
		// TODO Auto-generated method stub
		return mapper.selectBoard();
	}

	public BoardDto getBoard(int id) {
		// TODO Auto-generated method stub
		return mapper.selectBoardById(id);
	}

	public boolean addBoard(BoardDto board) {
		board.setInserted(LocalDateTime.now());
		
		int cnt = mapper.insertBoard(board);
		
		return cnt == 1;
	}

}
