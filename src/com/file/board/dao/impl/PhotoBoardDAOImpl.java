package com.file.board.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.file.board.dao.PhotoBoardDAO;
import com.file.board.vo.PageVO;
import com.file.board.vo.PhotoBoardVO;

@Repository
public class PhotoBoardDAOImpl implements PhotoBoardDAO{

	@Autowired
	private SqlSessionFactory ssf;
	
	@Override
	public int insertPhotoBoard(PhotoBoardVO pb) {
		try(SqlSession ss = ssf.openSession()){
			return ss.insert("Photo.insertPhotoBoard",pb);
		}
	}

	@Override
	public List<PhotoBoardVO> selectPhotoBoardList(PhotoBoardVO pb) {
		try(SqlSession ss = ssf.openSession()){
			return ss.selectList("Photo.selectPhotoBoardList",pb);
		}
	}

	@Override
	public int selectPhotoBoardCount(PhotoBoardVO pb) {
		try(SqlSession ss = ssf.openSession()){
			return ss.selectOne("Photo.selectPhotoBoardCount",pb);
		}
	}

	@Override
	public int deletePhotoBoard(int[] pbNums) {
		try(SqlSession ss = ssf.openSession()){
			int cnt =0;
			for(int pbNum:pbNums) {
				cnt += ss.delete("Photo.deletePhotoBoard",pbNum);
			}
			if(pbNums.length!=cnt) {
				ss.rollback();
				return 0;
			}else {
				return cnt;
			}
		}
	}

	@Override
	public List<PhotoBoardVO> selectPhotoBoardsForDelete(int[] pbNums) {
		try(SqlSession ss = ssf.openSession()){
			return ss.selectList("Photo.selectPhotoBoardsForDelete",pbNums);
		}
	}

	@Override
	public PhotoBoardVO selectPhotoBoard(PhotoBoardVO pb) {
		try(SqlSession ss = ssf.openSession()){
			return ss.selectOne("Photo.selectPhotoBoard",pb);
		}
	}

	@Override
	public int updatePhotoBoard(PhotoBoardVO pb) {
		try(SqlSession ss = ssf.openSession()){
			System.out.println(pb);
			return ss.update("Photo.updatePhotoBoard",pb);
		}
	}
}
