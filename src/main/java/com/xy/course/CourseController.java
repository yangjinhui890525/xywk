package com.xy.course;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xy.util.ParamUtils;

import cn.com.iactive.db.DataGridModel;

@Controller
@RequestMapping("/xy/course")
public class CourseController {
	@Autowired
	ICourseService courseService;
	@RequestMapping(value="managerCourse")
	public String managerCource()
	{
		return "course/managerCourse";
	}
	@RequestMapping(value="getCourseDataJson")
	@ResponseBody
	public HashMap<String, Object> getCourseDataJson(DataGridModel dm,HttpServletRequest request,HttpServletResponse response,int category_id)
	{
		HashMap<String, Object> params=ParamUtils.getFilterParams(request);
		HashMap<String, Object> map=courseService.getCourseDateByCategroyID(dm, params);
		request.setAttribute("serchParams", params);
		request.setAttribute("a", "aaa");
		return map;
	}
	@RequestMapping(value="editCourse")
	public String editCourse(HttpServletRequest request)
	{
		HashMap<String, Object> course=ParamUtils.getFilterParams(request);
		if(course.get("ID")!=null)
			course=courseService.getCourseByID(course);
		request.setAttribute("course", course);
		return "course/editCourse";
	}
	@RequestMapping(value="editCourse_ok")
	@ResponseBody
	public JSONObject editCourse_ok(HttpServletRequest request)
	{
		HashMap<String, Object> course=ParamUtils.getFilterParams(request);
		boolean flag=courseService.save(course);
		JSONObject jsonObject=new JSONObject();
		if(flag)
			jsonObject.put("success", true);
		return jsonObject;
	}
	@RequestMapping(value="deleteCourse")
	@ResponseBody
	public JSONObject deleteCourse(HttpServletRequest request)
	{
		String[] PKS=ParamUtils.getParameters(request, "PK");
		HashMap<String, Object> course=ParamUtils.getFilterParams(request);
		courseService.deleteCourses(PKS);
		JSONObject jsonObject=new JSONObject();
		if(true)
			jsonObject.put("success", true);
		return jsonObject;
	}

}
