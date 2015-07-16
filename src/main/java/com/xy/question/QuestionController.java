package com.xy.question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bouncycastle.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.iactive.db.DataGridModel;

import com.xy.dic.IDicService;
import com.xy.util.ParamUtils;
import com.xy.vo.RetVO;
import com.xy.vo.Tree;
@Controller
@RequestMapping("/xy/question")
public class QuestionController {
	@Autowired
	IQuestionService questionService;
	@Autowired
	IDicService dicService;
	@RequestMapping(value="list")
	public String list(HttpServletRequest request,int COURSE_ID)
	{
		request.setAttribute("COURSE_ID", COURSE_ID);
		return "question/list";
	}
	@RequestMapping(value="getTree")
	@ResponseBody
	public List<Tree> getTree(HttpServletRequest request,int COURSE_ID)
	{
		HashMap<String, Object> params=new HashMap<String, Object>();
		params.put("ID", COURSE_ID);
		//List<Tree> tree=chapterService.getChapterTree(params);
		//List<Tree> tree=subjectService.getBaseTree();
		return null;
	}
	@RequestMapping(value="getQuestionDataJson")
	@ResponseBody
	public HashMap<String, Object> getQuestionDataJson(HttpServletRequest request,DataGridModel dm)
	{
		HashMap<String, Object> params=ParamUtils.getFilterParams(request);
		 HashMap<String, Object> list=questionService.getQuestionByCHAPTER_UNIT_ID(dm, params);
		 HashMap<String, HashMap<String, Object>> dic=dicService.getAllDic();
		 Object obj=((List<HashMap<String, Object>>) list.get("rows"));
		 ArrayList<Object> map=(ArrayList<Object>) obj;
		for(Object obj_new:map)
		{
			HashMap<String, Object> map_new=(HashMap<String, Object>) obj_new;
			map_new.put("QUE_TYPE",((HashMap<String, Object>)(dic.get("QUE_TYPE")).get(map_new.get("QUE_TYPE").toString())).get("NAME"));
			map_new.put("QUE_DIFFCULT",((HashMap<String, Object>)(dic.get("QUE_DIFFCULT")).get(map_new.get("QUE_DIFFCULT").toString())).get("NAME"));
			map_new.put("QUE_SCORE",((HashMap<String, Object>)(dic.get("QUE_SCORE")).get(map_new.get("QUE_SCORE").toString())).get("NAME"));
		}
		return list;
	}
	@RequestMapping(value="addQuestion")
	public String addQuestion(HttpServletRequest request)
	{
		HashMap<String, Object> params=ParamUtils.getFilterParams(request);
		params.put("QUE_TYPE", 10);
		params.put("QUE_DIFFCULT", 1);
		params.put("QUE_SCORE", 1);
		params.put("QUE_CLASS", 1);
		params.put("QUE_CONTENT_TYPE", 1);
		request.setAttribute("question", params);
		return "question/editQuestion";
	}
	@RequestMapping(value="editQuestion")
	public String editQuestion(HttpServletRequest request)
	{
		HashMap<String, Object> params=ParamUtils.getFilterParams(request);
		HashMap<String, Object> question=questionService.getQuestionById(params);
		String text=questionService.getCONTENT_TEXTByURL(question.get("CONTENT_URL").toString(),request);
		question.put("CONTENT_TEXT", text);
		request.setAttribute("question", question);
		return "question/editQuestion";
	}
	@RequestMapping(value="saveQuestion")
	@ResponseBody
	public RetVO saveQuestion(HttpServletRequest request)
	{
		HashMap<String, Object> params=ParamUtils.getFilterParams(request);
		RetVO retVO=new RetVO();
		try {
			if(params.get("ID")==null)
			{
				String[] CHAPTER_UNIT_ID_s=params.get("CHAPTER_UNIT_IDs").toString().split(",");
				 int[] CHAPTER_UNIT_ID_S=new int[CHAPTER_UNIT_ID_s.length];
				 for(int i=0;i<CHAPTER_UNIT_ID_s.length;i++)
				 {
					 CHAPTER_UNIT_ID_S[i]=Integer.parseInt(CHAPTER_UNIT_ID_s[i]);
				 }
				 boolean flag=questionService.saveQuestion(CHAPTER_UNIT_ID_S,params,request);
				 retVO.setSuccess(flag);
			}
			else
			{
				boolean flag= questionService.updateQuestion(params,request);
				 retVO.setSuccess(flag);
			}
			 
		} catch (Exception e) {
			 retVO.setSuccess(false);
		}
		
		return retVO;
	}
	@RequestMapping(value="getAnster")
	public String getAnster(String type,HttpServletRequest request)
	{
		request.setAttribute("type", type);
		return "question/answer";
	}
}
