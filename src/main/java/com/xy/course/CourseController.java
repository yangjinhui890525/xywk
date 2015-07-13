package com.xy.course;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	@RequestMapping(value="uploadFoword")
	public String uploadFoword()
	{
		return "course/uploadFoword";
	}
	@RequestMapping(value="uploadFile")
	 @ResponseBody
	public void uploadFile(HttpServletResponse response, HttpServletRequest request,
		      MultipartHttpServletRequest multipartRequest) throws Exception {

	    response.setCharacterEncoding("utf-8");
	    String result = "";

	    try {
	      for (Iterator<?> it = multipartRequest.getFileNames(); it.hasNext();) {
	        String key = (String) it.next();
	        MultipartFile orderFile = multipartRequest.getFile(key);
	        if (orderFile.getOriginalFilename().length() > 0) {
	        //  logger.info("rderFile.getOriginalFilename()==" + orderFile.getOriginalFilename());

	          String realPath =
	              multipartRequest.getSession().getServletContext().getRealPath("/upload/file");
	          FileUtils.copyInputStreamToFile(orderFile.getInputStream(),
	              new File(realPath, orderFile.getOriginalFilename()));
	        }
	        String strBackUrl = "http://" + request.getServerName() //服务器地址  
	                + ":"   
	                + request.getServerPort()           //端口号  
	                + request.getContextPath()+"/uploadFile/file/"+orderFile.getOriginalFilename();      //项目名称  
	        response.getWriter().println(strBackUrl + "|" + "上传成功");
	       // response.getWriter().println(orderFile.getOriginalFilename() + "|" + "上传成功");
	      }
	      // result=",上传成功";
	    } catch (Exception ex) {
	      result = "上传失败";
	      ex.printStackTrace();
	    }
	    response.getWriter().print(result);
	}
}
