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
	@RequestMapping(value="deleteChapter")
	@ResponseBody
	public RetVO deleteChapter(HttpServletRequest request)
	{
		RetVO ret=new RetVO();
		try {
			  int IDS[]=ParamUtils.getIntParameters(request, "PK", 0);
			  boolean flag=chapterService.deleteChapter(IDS);
			  ret.setSuccess(true);
		  }catch (Exception e)
		  {
			  ret.setSuccess(false);
		      ret.setMsg("error："+e.getMessage());
		  }
		  return ret;
	}
	
}
