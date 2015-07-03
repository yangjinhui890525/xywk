package com.xy.course;

import java.util.HashMap;
import java.util.List;

import cn.com.iactive.db.DataGridModel;

public interface ICourseService {
	public HashMap<String, Object> getCourseDateByCategroyID(DataGridModel dm, HashMap<String, Object> params);
}
