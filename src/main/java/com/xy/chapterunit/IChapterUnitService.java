package com.xy.chapterunit;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.xy.vo.Tree;

import cn.com.iactive.db.DataGridModel;

public interface IChapterUnitService {

	HashMap<String, Object> getChapterUnitListByPid(DataGridModel dm,
			HashMap<String, Object> params);

	HashMap<String, Object> getChapterUnitById(
			HashMap<String, Object> chapterUnit);

	boolean saveChapterUnit(HashMap<String, Object> chapterUnit);

	String createChapterUnitHtml(HashMap<String, Object> chapterUnit,HttpServletRequest request);

	long saveChapterUnitID(HashMap<String, Object> chapterUnit);

	boolean updateChapterUnit(HashMap<String, Object> chapterUnitInsert);

	boolean deleteChapterUnit(HashMap<String, Object> params);

	List<Tree> getChapterTree(HashMap<String, Object> params);

}
