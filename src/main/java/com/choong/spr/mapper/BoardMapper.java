package com.choong.spr.mapper;

import java.util.List;

import com.choong.spr.domain.BoardDto;

public interface BoardMapper {

	List<BoardDto> selectBoard();

	BoardDto selectBoardById(int id);

	int updateBoard(BoardDto board);

	int insertBoard(BoardDto board);

	int deleteBoard(int id);


}
