package com.choong.spr.mapper;

import java.util.List;

import com.choong.spr.domain.BoardDto;

public interface BoardMapper {

	List<BoardDto> selectBoard();

	BoardDto selectBoardById(int id);

	int insertBoard(BoardDto board);

}
