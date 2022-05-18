package com.choong.spr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.choong.spr.domain.BoardDto;

public interface BoardMapper {

	List<BoardDto> selectBoard(@Param("from") int from, @Param("row") int row);

	BoardDto selectBoardById(int id);

	int updateBoard(BoardDto board);

	int insertBoard(BoardDto board);

	int deleteBoard(int id);

	int countBoard();


}
