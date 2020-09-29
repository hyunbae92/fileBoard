package com.file.board.vo;

import lombok.Data;

@Data
public class PageVO {
	
	
	private int pageNum;
	private int pageSize;
	private int startNum;
	private int endNum;
	private int totalCnt;
	private int startBlock;
	private int endBlock;
	private int maxBlock;
	
	public void setPageVO(int pageNum,int pageSize) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.startNum = ((pageNum-1)*pageSize) +1;
		this.endNum = ((pageNum-1)*pageSize)+pageSize;
		this.startBlock = ((pageNum-1)/10) *10 +1;
		this.endBlock = ((pageNum-1)/10) *10 + 10;
	}
}
