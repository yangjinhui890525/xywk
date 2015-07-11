package com.xy.chapterunit;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.iactive.db.DataGridModel;

import com.xy.util.ParamUtils;
import com.xy.vo.RetVO;

@Controller
@RequestMapping("/xy/chapterunit")
public class ChapterUnitController {
	@Autowired
	IChapterUnitService chapterUnitService;
	@RequestMapping(value="create")
	@ResponseBody
	public String CreateChapterUnit(HttpServletRequest request)
	{
		HashMap<String , Object> params=ParamUtils.getFilterParams(request);
		return params.toString();
	}
	@RequestMapping(value="getChapterUnitList")
	@ResponseBody()
	public HashMap<String , Object>  getChapterUnitList(HttpServletRequest request,DataGridModel dm)
	{
		HashMap<String , Object> params=ParamUtils.getFilterParams(request);
		HashMap<String, Object> list=chapterUnitService.getChapterUnitListByPid(dm, params);
		return list;
	}
	@RequestMapping(value="editChapterUnit")
	public String editChapterUnit(HttpServletRequest request)
	{
		HashMap<String , Object> chapterUnit=ParamUtils.getFilterParams(request);
/*		if(chapterUnit.get("ID")!=null)
			chapterUnit=chapterUnitService.getChapterUnitById(chapterUnit);*/
		request.setAttribute("chapterUnit", chapterUnit);
		 return "chapter/editChapterUnit";
	}
	@RequestMapping(value="saveChapterUnit")
	public RetVO saveChapterUnit(HttpServletRequest request)
	{
		RetVO retVO=new RetVO();
		HashMap<String , Object> chapterUnit=ParamUtils.getFilterParams(request);
		boolean flag=chapterUnitService.saveChapterUnit(chapterUnit);
		if(flag)
			retVO.setSuccess(true);
		 return retVO;
	}
	
	
}
