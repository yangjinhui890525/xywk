package com.xy.chapter;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.iactive.db.DataGridModel;

import com.xy.constants.AppConstants;
import com.xy.util.ParamUtils;
import com.xy.vo.RetVO;
import com.xy.vo.Tree;

@Controller
@RequestMapping("/xy/chapter")
public class ChapterController {
	@Autowired
	IChapterService chapterService;
	@RequestMapping(value="editChapter")
	public String editChapter(HttpServletRequest request)
	{
		HashMap<String, Object> parmas=ParamUtils.getFilterParams(request);
		return "chapter/editChapter";
	}
	@RequestMapping(value="getChapterList")
	@ResponseBody
	public HashMap<String, Object> getChapterList(HttpServletRequest request,DataGridModel dm)
	{
		HashMap<String, Object> params=ParamUtils.getFilterParams(request);
		HashMap<String, Object> list=chapterService.getChapterListByPid(dm, params);
		return list;
	}
	@RequestMapping(value="openChataterList")
	public String openChataterList(HttpServletRequest request,int COURSE_ID)
	{
		request.setAttribute("COURSE_ID", COURSE_ID);
		return "chapter/managerChapter";
	}
	@RequestMapping(value="editChapterList")
	public String editChapterList(HttpServletRequest request)
	{
		HashMap<String, Object> chapter=ParamUtils.getFilterParams(request);
		if(chapter.get("ID")!=null)
			chapter=chapterService.getChapterByID(chapter);
		request.setAttribute("chapter", chapter);
		return "chapter/editChapter";
	}
	@RequestMapping(value="saveChapter")
	@ResponseBody
	public RetVO saveChapter(HttpServletRequest request)
	{
		RetVO ret=new RetVO();
		 try {
		      HashMap<String,String> chapter = ParamUtils.getParameters(request);
		      boolean flag=chapterService.saveChapter(chapter);
		      ret.setSuccess(flag);
		    } catch (Exception e) {
		      ret.setSuccess(false);
		      ret.setMsg("error");
		    }
		    return ret;
	}
	@RequestMapping(value="deleteChapterList")
	@ResponseBody
	public RetVO deleteChapter(HttpServletRequest request,int ID)
	{
		RetVO ret=new RetVO();
		try{
		HashMap<String, Object> params=ParamUtils.getFilterParams(request);
			  boolean flag=chapterService.deleteChapter(params);
			  ret.setSuccess(flag);
		  }catch (Exception e)
		  {
			  ret.setSuccess(false);
		      ret.setMsg("error："+e.getMessage());
		  }
		  return ret;
	}
	@RequestMapping(value="getChapterTree")
	@ResponseBody
	public List<Tree> getChapterTree(HttpServletRequest request,int COURSE_ID)
	{
		HashMap<String, Object> params=new HashMap<String, Object>();
		params.put("ID", COURSE_ID);
		List<Tree> tree=chapterService.getChapterTree(params);
		return tree;
	}
	
}
