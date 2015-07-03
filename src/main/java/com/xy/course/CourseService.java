package com.xy.course;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.iactive.db.DataGridModel;
import cn.com.iactive.db.IACDB;
@Service
public class CourseService implements ICourseService{
	  @Autowired
	  private IACDB<HashMap<String,Object>> iacDB;
	public HashMap<String, Object> getCourseDateByCategroyID(DataGridModel dm, HashMap<String, Object> params) {
		
		return iacDB.getDataGrid("getCourseList", dm, params);
	}

}
