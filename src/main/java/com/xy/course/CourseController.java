package com.xy.course;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
		HashMap<String, Object> params=new HashMap<String, Object>();
		params.put("category_id", category_id);
		HashMap<String, Object> map=courseService.getCourseDateByCategroyID(dm, params);
		return map;
	}

}
