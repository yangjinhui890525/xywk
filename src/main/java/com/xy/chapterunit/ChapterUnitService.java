package com.xy.chapterunit;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xy.constants.DBTableConstants;

import cn.com.iactive.db.DataGridModel;
import cn.com.iactive.db.IACDB;


@Service
public class ChapterUnitService implements IChapterUnitService {
	@Autowired
	  private IACDB<HashMap<String,Object>> iacDB;
	@Override
	public HashMap<String, Object> getChapterUnitListByPid(DataGridModel dm,
			HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return iacDB.getDataGrid("getChapterUnitList", dm, params);
	}
	@Override
	public HashMap<String, Object> getChapterUnitById(
			HashMap<String, Object> chapterUnit) {
		// TODO Auto-generated method stub
		return iacDB.get("getChapterUnitById", chapterUnit);
	}
	@Override
	public boolean saveChapterUnit(HashMap<String, Object> chapterUnit) {
		return iacDB.insertDynamic(DBTableConstants.TBL_CHAPTERUNIT_NAME, chapterUnit);
	}

}
