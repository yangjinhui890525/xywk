package com.xy.chapter;

import java.util.HashMap;
import java.util.List;

import com.xy.vo.Tree;

import cn.com.iactive.db.DataGridModel;

public interface IChapterService {

	HashMap<String, Object> getChapterListByPid(DataGridModel dm,
			HashMap<String, Object> params);

	HashMap<String, Object> getChapterByID(HashMap<String, Object> chapter);
	boolean saveChapter(HashMap<String, String> chapter);

	boolean deleteChapter(HashMap<String, Object> params);

	List<Tree> getChapterTree(HashMap<String, Object> params);

}
