package com.file.board.service;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.file.board.vo.PhotoBoardVO;

public interface PhotoBoardService {

	int insertPhotoBoard(MultipartFile file,PhotoBoardVO pb);
	int updatePhotoBoard(MultipartFile file, PhotoBoardVO pb);
	PhotoBoardVO selectPhotoBoard(PhotoBoardVO pb);
	List<PhotoBoardVO> selectPhotoBoardList(PhotoBoardVO pb,Model model);
	int deletePhotoBoard(int[] pbNums);
}
