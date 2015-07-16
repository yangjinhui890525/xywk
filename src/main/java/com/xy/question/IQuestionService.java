package com.xy.question;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.com.iactive.db.DataGridModel;

import com.xy.vo.Tree;

public interface IQuestionService {

	List<Tree> getBaseTree();

	HashMap<String, Object> getQuestionByCHAPTER_UNIT_ID(DataGridModel dm,
			HashMap<String, Object> params);

	boolean saveQuestion(int[] cHAPTER_UNIT_ID_S, HashMap<String, Object> params,HttpServletRequest request);

	HashMap<String, Object> getQuestionById(HashMap<String, Object> params);

	boolean updateQuestion(HashMap<String, Object> params,HttpServletRequest request);

	String getCONTENT_TEXTByURL(String name,HttpServletRequest request);

}
