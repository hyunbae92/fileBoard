package com.file.board.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.file.board.dao.PhotoBoardDAO;
import com.file.board.service.PhotoBoardService;
import com.file.board.vo.PageVO;
import com.file.board.vo.PhotoBoardVO;

@Service
public class PhotoBoardServiceImpl implements PhotoBoardService {
	private final String uploadPath = "C:\\Users\\Administrator\\git\\fileBoard\\WebContent\\resources\\upload\\";
	@Autowired
	private PhotoBoardDAO pbDAO;
	
	@Override
	public int insertPhotoBoard(MultipartFile file, PhotoBoardVO pb) {
		if(file.getOriginalFilename().trim()=="") {
			return pbDAO.insertPhotoBoard(pb);
		}else {
			String orgFileName = file.getOriginalFilename();
			String extName = orgFileName.substring(orgFileName.lastIndexOf("."));
			String fileName = System.nanoTime()+extName;
			pb.setPbPhotoName(orgFileName);
			pb.setPbPhotoPath(fileName);
			int cnt = pbDAO.insertPhotoBoard(pb);
			if(cnt==1) {
				File f = new File(uploadPath+fileName);
				try {
					file.transferTo(f);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return cnt;
		}
		
	}

	@Override
	public List<PhotoBoardVO> selectPhotoBoardList(PhotoBoardVO pb,Model model) {
		PageVO pVO = pb.getPageVO();
		pVO.setPageVO((pVO.getPageNum()<1?1:pVO.getPageNum()), 10); 
		if(pb.getCredat1()!=null) {
			pb.setCredat1(pb.getCredat1().replace("-", ""));
		}
		if(pb.getCredat2()!=null) {
			pb.setCredat2(pb.getCredat2().replace("-", ""));
		}
		int totalPageSize = pbDAO.selectPhotoBoardCount(pb)/10;
		pVO.setMaxBlock(totalPageSize+1);
		if(pVO.getEndBlock()>totalPageSize) {
			pVO.setEndBlock(totalPageSize+1);
		}
		System.out.println(pVO);
		model.addAttribute("page",pVO);
		model.addAttribute("pbList",pbDAO.selectPhotoBoardList(pb));
		return null;
	}

	@Override
	public int deletePhotoBoard(int[] pbNums) {
		List<PhotoBoardVO> pbList = pbDAO.selectPhotoBoardsForDelete(pbNums);
		int result = pbDAO.deletePhotoBoard(pbNums);
		if(result!=0 && !pbList.isEmpty()) {
			for(PhotoBoardVO pb : pbList) {
				File f = new File(uploadPath+pb.getPbPhotoPath());
				if(f.exists()) {
					f.delete();	
				}
			}
		}
		return result;
	}

	@Override
	public PhotoBoardVO selectPhotoBoard(PhotoBoardVO pb) {
		return pbDAO.selectPhotoBoard(pb);
	}

	@Override
	public int updatePhotoBoard(MultipartFile file, PhotoBoardVO pb) {
		if(!file.isEmpty()) {
			int[] pbNums = {pb.getPbNum()};
			String fileName = pbDAO.selectPhotoBoardsForDelete(pbNums).get(0).getPbPhotoPath();
			File f = new File(uploadPath+fileName); 
			if(f.exists()) {
				f.delete();
			}
			String orgFileName = file.getOriginalFilename();
			String extName = orgFileName.substring(orgFileName.lastIndexOf("."));
			String filePath = System.nanoTime()+extName;
			pb.setPbPhotoName(orgFileName);
			pb.setPbPhotoPath(filePath);
			int cnt = pbDAO.updatePhotoBoard(pb);
			if(cnt == 1) {
				f = new File(uploadPath+filePath);
				try {
					file.transferTo(f);
					return cnt;
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		int result = pbDAO.updatePhotoBoard(pb); 
		System.out.println(result);
		return result;
	}

}
