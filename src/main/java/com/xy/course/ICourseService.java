package com.xy.course;

import java.util.HashMap;
import java.util.List;

import cn.com.iactive.db.DataGridModel;

public interface ICourseService {
	public HashMap<String, Object> getCourseDateByCategroyID(DataGridModel dm, HashMap<String, Object> params);
	public HashMap<String, Object> getCourseByID(HashMap<String, Object> params);
	public boolean save(HashMap<String, Object> course);
	public void deleteCourses(String[] pKS);
	public List<HashMap<String, Object>> getCourseDateByCategroyIDNoPage(HashMap<String, Object> params);
}
