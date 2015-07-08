package com.xy.category;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xy.util.ParamUtils;
import com.xy.vo.Tree;

@Controller
@RequestMapping("/xy/category")
public class CategoryController {
	@Autowired
	private ICategoryService categoryService;
	@RequestMapping(value="getCategoryList")
	public String getCategoryList(HttpServletRequest request)
	{
		int pid=ParamUtils.getIntParameter(request, "pid", 0);
		request.setAttribute("pid", pid);
		return "category/adminCategoryList";
	}
	@RequestMapping(value="getCategoryData")
	@ResponseBody
	public List<HashMap<String, Object>> getResourceData(int pid,HttpServletResponse response)
	{
		HashMap<String, Object> params=new HashMap<String, Object>();
		params.put("pid", pid);
		List<HashMap<String, Object>> list=categoryService.getCategoryData(params);
		return list;
	}
	@RequestMapping(value="addCategory")
	public String addCategory(int pid,HttpServletRequest request)
	{
		HashMap<String, Object> category=new HashMap<String, Object>();
		category.put("PID", pid);
		category.put("ORDER_NUM", 0);
		request.setAttribute("category", category);
		return "category/editCategroy";
	}
	@RequestMapping(value="editCategory")
	public String editCategory(int id,HttpServletRequest request)
	{
		HashMap<String, Object> category=categoryService.getCategoryById(id);
		request.setAttribute("category", category);
		return "category/editCategroy";
	}
	@RequestMapping(value="editCategory_ok")
	@ResponseBody
	public JSONObject editCategory_ok(HttpServletRequest request,String name,int pid,int order_num)
	{
		HashMap<String, Object> category=new HashMap<String, Object>();
		category.put("NAME",name);
		category.put("PID",pid);
		category.put("ORDER_NUM",order_num);
		categoryService.insertCategory(category);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("success", true);
		return jsonObject;
	}
	@RequestMapping(value="getCategoryTree")
	@ResponseBody
	public  List<Tree> getCategoryTree()
	{
		List<Tree> list=categoryService.getCategoryTree();
		return list;
	}
	@RequestMapping(value="deleteCategory")
	@ResponseBody
	public JSONObject deleteCategory(String ids)
	{
		String[] id=ids.split(",");
		int[] id_s=new int[id.length];
		JSONObject jsonObject=new JSONObject();
		for(int i=0;i<id.length;i++)
		{
			int ID=Integer.parseInt(id[i]);
			id_s[i]=ID;
		}
		if(categoryService.deleteCategory(id_s))
			jsonObject.put("success", true);
		return jsonObject;
	}
}
