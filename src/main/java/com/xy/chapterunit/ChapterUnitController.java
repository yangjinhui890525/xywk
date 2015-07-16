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
import com.xy.vo.LoginInfo;
import com.xy.vo.RetVO;
import com.xy.vo.Tree;

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
		LoginInfo info=(LoginInfo) request.getSession().getAttribute("info");
		if(info==null)
			info=new LoginInfo();
		info.setChapter_unit(chapterUnit);
		request.getSession().setAttribute("info", info);
		request.setAttribute("chapterUnit", chapterUnit);
		 return "chapter/editChapterUnit";
	}
	@RequestMapping(value="saveChapterUnit")
	@ResponseBody
	public RetVO saveChapterUnit(HttpServletRequest request)
	{
		RetVO retVO=new RetVO();
		HashMap<String , Object> chapterUnit=ParamUtils.getFilterParams(request);
		HashMap<String , Object> chapterUnitInsert=ParamUtils.getFilterParams(request);
		chapterUnitInsert.remove("P_URL");
		chapterUnitInsert.remove("COURSE_ID");
		long ID=chapterUnitService.saveChapterUnitID(chapterUnitInsert);
		chapterUnit.put("ID", ID);
		chapterUnitInsert.put("ID", ID);
		String P_URL=chapterUnitService.createChapterUnitHtml(chapterUnit,request);
		chapterUnitInsert.put("P_URL", P_URL);
		boolean flag=chapterUnitService.updateChapterUnit(chapterUnitInsert);
		//boolean flag=chapterUnitService.saveChapterUnit(chapterUnit);
		if(flag)
			retVO.setSuccess(true);
		 return retVO;
	}
	@RequestMapping(value="deleteChapterUnit")
	@ResponseBody
	public RetVO deleteChapterUnit(HttpServletRequest request)
	{
		RetVO ret=new RetVO();
		try{
			HashMap<String, Object> params=ParamUtils.getFilterParams(request);
				  boolean flag=chapterUnitService.deleteChapterUnit(params);
				  ret.setSuccess(flag);
			  }catch (Exception e)
			  {
				  ret.setSuccess(false);
			      ret.setMsg("error："+e.getMessage());
	}
		 return ret;
	}
	@RequestMapping(value="getChapterUnitTree")
	@ResponseBody
	public List<Tree> getChapterUnitTree(int COURSE_ID,HttpServletRequest httpServletRequest)
	{
		HashMap<String, Object> params=new HashMap<String, Object>();
		params.put("ID", COURSE_ID);
		List<Tree> tree=chapterUnitService.getChapterTree(params);
		return tree;
	}
}
