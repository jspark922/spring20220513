package com.choong.spr.mapper;

import java.util.List;

import com.choong.spr.domain.ReplyDto;

public interface ReplyMapper {

	void deleteReplyByBoard(int boardId);

	int insertReply(ReplyDto reply);

	List<ReplyDto> selectReplyByBoardId(int id);

}
