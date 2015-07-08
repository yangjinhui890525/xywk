package com.xy.chapter;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xy.constants.DBTableConstants;

import cn.com.iactive.db.DataGridModel;
import cn.com.iactive.db.IACDB;

@Service
public class ChapterService implements IChapterService{
	 @Autowired
	  private IACDB<HashMap<String,Object>> iacDB;
	@Override
	public HashMap<String, Object> getChapterListByPid(DataGridModel dm,
			HashMap<String, Object> params) {
		 return iacDB.getDataGrid("getChapterList", dm, params);
	}
	@Override
	public HashMap<String, Object> getChapterByID(
			HashMap<String, Object> chapter) {
		return iacDB.get("ChapterMapper.getChapterByID", chapter);
	}
	@Override
	public boolean saveChapter(HashMap<String, String> chapter) {
		if(chapter.get("ID")==null)
			  return iacDB.insertDynamic(DBTableConstants.TBL_CHAPTER_NAME, chapter);
		  else
		  {
			  return iacDB.updateDynamic(DBTableConstants.TBL_CHAPTER_NAME, DBTableConstants.TBL_CHAPTER_PKE, chapter);
		  }
		
	}
	@Override
	public boolean deleteChapter(int[] iDS) {
		for(int id:iDS)
		{
			HashMap<String, Object> params=new HashMap<String, Object>();
			params.put("ID", id);
			iacDB.deleteDynamic(DBTableConstants.TBL_CHAPTER_NAME, params);
		}
		return true;
	}
	
	

}
