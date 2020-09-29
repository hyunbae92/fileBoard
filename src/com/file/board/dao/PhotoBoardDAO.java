package com.file.board.dao;

import java.util.List;

import com.file.board.vo.PageVO;
import com.file.board.vo.PhotoBoardVO;

public interface PhotoBoardDAO {

	int insertPhotoBoard(PhotoBoardVO pb);
	int updatePhotoBoard(PhotoBoardVO pb);
	PhotoBoardVO selectPhotoBoard(PhotoBoardVO pb);
	List<PhotoBoardVO> selectPhotoBoardList(PhotoBoardVO pb);
	int selectPhotoBoardCount(PhotoBoardVO pb);
	int deletePhotoBoard(int[] pbNums);
	List<PhotoBoardVO> selectPhotoBoardsForDelete(int[] pbNums);
}
