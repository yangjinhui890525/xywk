package com.xy.category;

import java.util.HashMap;
import java.util.List;

import com.xy.vo.Tree;

import net.sf.json.JSONArray;

public interface ICategoryService {
	  public List<HashMap<String, Object>> getCategoryData(HashMap<String,Object> params);//ID,PID,NAME
	  public boolean insertCategory(HashMap<String,Object> params);
	  public List<Tree> getCategoryTree();
	  public HashMap<String, Object> getCategoryById(int id);//all
	  public boolean deleteCategory(int[] ids);
}
