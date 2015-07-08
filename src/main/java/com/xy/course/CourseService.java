package com.xy.course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xy.constants.DBTableConstants;

import cn.com.iactive.db.DataGridModel;
import cn.com.iactive.db.IACDB;
@Service
public class CourseService implements ICourseService{
	  @Autowired
	  private IACDB<HashMap<String,Object>> iacDB;
	public HashMap<String, Object> getCourseDateByCategroyID(DataGridModel dm, HashMap<String, Object> params) {
		
		return iacDB.getDataGrid("getCourseList", dm, params);
	}
	@Override
	public HashMap<String, Object> getCourseByID(HashMap<String, Object> params) {
		List<HashMap<String, Object>> list=iacDB.getList("getCourseByID", params);
		if(list.size()>0)
			return list.get(0);
		return null;
	}
	@Override
	public boolean save(HashMap<String, Object> course) {
		if(course.get("ID")==null)
			return iacDB.insertDynamic(DBTableConstants.TBL_COURSE_NAME, course);
		else
			return iacDB.updateDynamic(DBTableConstants.TBL_COURSE_NAME, DBTableConstants.TBL_COURSE_PKE, course);
	}
	@Override
	public void deleteCourses(String[] pKS) {
		List<Object> list=new  ArrayList<Object>();
		for(String pk:pKS)
		{
			//HashMap<String, Object> map=new HashMap<String, Object>();
			list.add(pk);
		}
		iacDB.deleteBatchDynamic(DBTableConstants.TBL_COURSE_NAME, DBTableConstants.TBL_COURSE_PKE, list);
	}

}
