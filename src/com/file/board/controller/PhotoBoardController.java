package com.file.board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.file.board.service.PhotoBoardService;
import com.file.board.vo.PageVO;
import com.file.board.vo.PhotoBoardVO;

@Controller
public class PhotoBoardController {
	
	@Autowired
	private PhotoBoardService pbService;
	
	@RequestMapping(value="/photo/*",method = RequestMethod.GET)
	public String goList(HttpServletRequest request,Model model, @ModelAttribute  PhotoBoardVO pb) {
		String uri = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/"));
		if("/list".equals(uri)) {
			System.out.println(pb);
			if(pb.getPageVO()==null) {
				pb.setPageVO(new PageVO());
				pb.getPageVO().setPageNum(1);
			}
			pbService.selectPhotoBoardList(pb,model);
		}
		return "photo" + uri;
	}
	
	@RequestMapping(value="/photo/write",method = RequestMethod.POST)
	public String doWrite(Model model,@RequestParam("pbfile") MultipartFile file, @ModelAttribute  PhotoBoardVO pb) {
		pbService.insertPhotoBoard(file, pb);
		return "redirect:/photo/list";
	}
	
	@RequestMapping(value="/photo/view",method = RequestMethod.GET)
	public String goview(Model model,@ModelAttribute PhotoBoardVO pb) {
		model.addAttribute("pb",pbService.selectPhotoBoard(pb));
		return "photo/view";
	}
	
	@RequestMapping(value="/photo/delete",method = RequestMethod.POST)
	public String doDelete(@RequestParam("pbNums")int[] pbNums) {
		pbService.deletePhotoBoard(pbNums);
		return "redirect:/photo/list";
	}
	@RequestMapping(value="/photo/update",method = RequestMethod.POST)
	public String doUpdate(@ModelAttribute PhotoBoardVO pb, @RequestParam("pbfile") MultipartFile file) {
		System.out.println(pbService.updatePhotoBoard(file, pb));
		return "redirect:/photo/list";
	}
}
