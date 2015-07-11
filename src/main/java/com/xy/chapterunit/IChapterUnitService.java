package com.xy.chapterunit;

import java.util.HashMap;

import cn.com.iactive.db.DataGridModel;

public interface IChapterUnitService {

	HashMap<String, Object> getChapterUnitListByPid(DataGridModel dm,
			HashMap<String, Object> params);

	HashMap<String, Object> getChapterUnitById(
			HashMap<String, Object> chapterUnit);

	boolean saveChapterUnit(HashMap<String, Object> chapterUnit);

}
