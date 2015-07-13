package com.xy.chapterunit;

import java.io.File;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xy.constants.DBTableConstants;
import com.xy.util.FileUtils;

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
	@Override
	public String createChapterUnitHtml(HashMap<String, Object> chapterUnit,HttpServletRequest request) {
		String contextPath=request.getRealPath("/");
		int COURSE_ID=Integer.parseInt(chapterUnit.get("COURSE_ID").toString()) ;
		int CHAPTER_ID=Integer.parseInt(chapterUnit.get("CHAPTER_ID").toString());
		String contextPCPath=contextPath+"upload\\"+COURSE_ID+"\\"+CHAPTER_ID+"\\"+chapterUnit.get("ID")+".html";
		String contextPhonePath=contextPath+"upload\\"+COURSE_ID+"\\"+CHAPTER_ID+"\\"+chapterUnit.get("ID")+"_phone.html";
		this.createPCHtml(contextPCPath, chapterUnit);
		this.createPhoneHtml(contextPhonePath, chapterUnit);
		String strBackUrl = "http://" + request.getServerName()+":"+request.getServerPort()+ request.getContextPath()+"/upload/"+COURSE_ID+"/"+CHAPTER_ID+"/"+chapterUnit.get("ID")+".html";
		return strBackUrl;
	}
	public boolean createPCHtml(String contextPath,HashMap<String, Object> chapterUnit)
	{
		String context=chapterUnit.get("P_URL").toString();
		context="<html><head><meta charset=\"utf-8\"><title>"+chapterUnit.get("NAME").toString()+"</title></head><body>"+context+"</body></html>";
		File file=new File(contextPath);
		try {
			FileUtils.createFile(file);
			FileUtils util=new FileUtils();
			util.WriteFileUTF8(contextPath, context);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	public boolean createPhoneHtml(String contextPath,HashMap<String, Object> chapterUnit)
	{
		String context=chapterUnit.get("P_URL").toString();
		context="<html><head><meta charset=\"utf-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1\"><title>"+chapterUnit.get("NAME").toString()+"</title></head><body>"+context+"</body></html>";
		File file=new File(contextPath);
		try {
			FileUtils.createFile(file);
			FileUtils util=new FileUtils();
			util.WriteFileUTF8(contextPath, context);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	@Override
	public long saveChapterUnitID(HashMap<String, Object> chapterUnit) {
		// TODO Auto-generated method stub
		return iacDB.insertDynamicRInt(DBTableConstants.TBL_CHAPTERUNIT_NAME, chapterUnit);
	}
	@Override
	public boolean updateChapterUnit(HashMap<String, Object> chapterUnitInsert) {
		// TODO Auto-generated method stub
		return iacDB.updateDynamic(DBTableConstants.TBL_CHAPTERUNIT_NAME, DBTableConstants.ID, chapterUnitInsert);
	}
	@Override
	public boolean deleteChapterUnit(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return false;
	}

}
