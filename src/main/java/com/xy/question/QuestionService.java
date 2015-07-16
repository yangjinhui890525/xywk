package com.xy.question;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.iactive.db.DataGridModel;
import cn.com.iactive.db.IACDB;

import com.xy.category.ICategoryService;
import com.xy.constants.DBTableConstants;
import com.xy.course.ICourseService;
import com.xy.util.FileUtils;
import com.xy.util.SidGenerator;
import com.xy.vo.Tree;

@Service
public class QuestionService implements IQuestionService {
	@Autowired
	private ICategoryService categoryService;
	 @Autowired
	 private IACDB<HashMap<String, Object>> iacDB;
	 @Autowired
	 ICourseService courseService;
	@Override
	public List<Tree> getBaseTree() {
		return null;
		//return initChapterTree(-1);
	}
	@Override
	public HashMap<String, Object> getQuestionByCHAPTER_UNIT_ID(
			DataGridModel dm, HashMap<String, Object> params) {
		 return iacDB.getDataGrid("getQuestionList", dm, params);
	}
	/**
	 * 创建新的题库
	 */
	@Override
	public boolean saveQuestion(int[] cHAPTER_UNIT_ID_S,
			HashMap<String, Object> params,HttpServletRequest request) {
		try {
			String CONTENT_URL=this.createQuestionContext(params, request);
			params.put("CONTENT_URL", CONTENT_URL);
			HashMap<String, Object> quesion=this.insertQuestion(params, cHAPTER_UNIT_ID_S);
			params.put("ID", quesion.get("ID"));
			boolean flag= this.createorUpdateQuestionItem(params);
			return  flag;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	@Override
	public HashMap<String, Object> getQuestionById(
			HashMap<String, Object> params) {
		return iacDB.get("getQuestionById", params);
	}
	/**
	 * 更新题库
	 */
	@Override
	public boolean updateQuestion(HashMap<String, Object> params,HttpServletRequest request) {
		updateQuestionContext(params, request);
		HashMap<String, Object> data=new HashMap<String, Object>();
		data.put("ID", params.get("ID"));
		data.put("QUE_TYPE", params.get("QUE_TYPE"));
		data.put("QUE_DIFFCULT", params.get("QUE_DIFFCULT"));
		data.put("QUE_SCORE", params.get("QUE_SCORE"));
		data.put("QUE_CLASS", params.get("QUE_CLASS"));
		data.put("QUE_CONTENT_TYPE", params.get("QUE_CONTENT_TYPE"));
		iacDB.updateDynamic(DBTableConstants.TBL_QUESTION_NAME, DBTableConstants.ID, data);
		this.createorUpdateQuestionItem(params);
		return true;
	}
	
	
	
	
	
	
	/**
	 * 创建文本
	 * @param params
	 * @param request
	 * @return
	 */
	private String createQuestionContext(HashMap<String, Object> params,HttpServletRequest request)
	{
		String contextPath=request.getRealPath("/");
		String name=SidGenerator.genVerifyCode(16)+".content";
		String contextPCPath=contextPath+"upload\\question\\"+name;
		File file=new File(contextPCPath);
		try {
			FileUtils.createFile(file);
			FileUtils util=new FileUtils();
			String context=params.get("CONTENT_TEXT").toString();
			util.WriteFileUTF8(contextPCPath,context );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}
	/**
	 * 修改文本
	 * @param params
	 * @param request
	 * @return
	 */
	private boolean updateQuestionContext(HashMap<String, Object> params,HttpServletRequest request)
	{
		String contextPath=request.getRealPath("/");
		String name=params.get("CONTENT_URL").toString();
		String contextPCPath=contextPath+"upload\\question\\"+name;
		File file=new File(contextPCPath);
		if(file.exists())
			file.delete();
		try {
			FileUtils.createFile(file);
			FileUtils util=new FileUtils();
			String context=params.get("CONTENT_TEXT").toString();
			util.WriteFileUTF8(contextPCPath,context );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}
	/**
	 * 开始插入
	 * @param params
	 * @param cHAPTER_UNIT_ID_S
	 * @return
	 */
	private HashMap<String, Object> insertQuestion(HashMap<String, Object> params,int[] cHAPTER_UNIT_ID_S)
	{
		HashMap<String, Object> data=new HashMap<String, Object>();
		data.put("QUE_TYPE", params.get("QUE_TYPE"));
		data.put("QUE_DIFFCULT", params.get("QUE_DIFFCULT"));
		data.put("QUE_SCORE", params.get("QUE_SCORE"));
		data.put("QUE_CLASS", params.get("QUE_CLASS"));
		data.put("QUE_CONTENT_TYPE", params.get("QUE_CONTENT_TYPE"));
		data.put("CONTENT_URL", params.get("CONTENT_URL"));
		data.put("CREATE_TIME", new Date());
		long ID=iacDB.insertDynamicRInt(DBTableConstants.TBL_QUESTION_NAME, data);
		params.put("ID", ID);
		for(int i=0;i<cHAPTER_UNIT_ID_S.length;i++)
		{
			HashMap<String, Object> data_relation=new HashMap<String, Object>();
			data_relation.put("CHAPTER_UNIT_ID", cHAPTER_UNIT_ID_S[i]);
			data_relation.put("QUESTION_ID", ID);
			data_relation.put("COURSE_ID", params.get("COURSE_ID"));
			iacDB.insertDynamic(DBTableConstants.TBL_RELATION_NAME, data_relation);
		}
		return params;
	}
	private boolean createorUpdateQuestionItem(HashMap<String, Object> params)
	{
		params.put("QUESTION_ID", params.get("ID"));
		List<HashMap<String, Object>> list=iacDB.getList("getQuestionItemIDSByQuestionID", params);
		if(list!=null&&list.size()>0)
		{
			for(HashMap<String, Object> item:list)
			{
				iacDB.deleteDynamic(DBTableConstants.TBL_QUESTION_ITEM_NAME, item);
			}
		}
				//iacDB.deleteBatchDynamic(DBTableConstants.TBL_QUESTION_ITEM_NAME, DBTableConstants.ID, list);
		int QUE_TYPE=Integer.parseInt(params.get("QUE_TYPE").toString());
		switch (QUE_TYPE) {
		case 10://单选
			HashMap<String, Object> object=new HashMap<String, Object>();
			int IS_RIGHT=Integer.parseInt(params.get("IS_RIGHT").toString());
			object.put("ITEM_CONTENT", params.get("ITEM_CONTENT_0"));
			object.put("QUE_ITEM_ORDER_NO", 0);
			object.put("QUESTION_ID", params.get("ID"));
			if(IS_RIGHT==0)
				object.put("IS_RIGHT", 1);
			iacDB.insertDynamic(DBTableConstants.TBL_QUESTION_ITEM_NAME, object);
			object.put("IS_RIGHT", 0);
			
			object.put("ITEM_CONTENT", params.get("ITEM_CONTENT_1"));
			object.put("QUE_ITEM_ORDER_NO", 1);
			if(IS_RIGHT==1)
				object.put("IS_RIGHT", 1);
			iacDB.insertDynamic(DBTableConstants.TBL_QUESTION_ITEM_NAME, object);
			object.put("IS_RIGHT", 0);
			
			object.put("ITEM_CONTENT", params.get("ITEM_CONTENT_2"));
			object.put("QUE_ITEM_ORDER_NO", 2);
			if(IS_RIGHT==1)
				object.put("IS_RIGHT", 1);
			iacDB.insertDynamic(DBTableConstants.TBL_QUESTION_ITEM_NAME, object);
			object.put("IS_RIGHT", 0);
			
			object.put("ITEM_CONTENT", params.get("ITEM_CONTENT_3"));
			object.put("QUE_ITEM_ORDER_NO", 3);
			if(IS_RIGHT==1)
				object.put("IS_RIGHT", 1);
			iacDB.insertDynamic(DBTableConstants.TBL_QUESTION_ITEM_NAME, object);
			break;
		case 20://多选
			String IS_RIGHT1=params.get("IS_RIGHT").toString();
			HashMap<String, Object> object1=new HashMap<String, Object>();
			object1.put("ITEM_CONTENT", params.get("ITEM_CONTENT_0"));
			object1.put("QUE_ITEM_ORDER_NO", 0);
			object1.put("QUESTION_ID", params.get("ID"));
			if(IS_RIGHT1.contains("0"))
				object1.put("IS_RIGHT", 1);
			iacDB.insertDynamic(DBTableConstants.TBL_QUESTION_ITEM_NAME, object1);
			object1.put("IS_RIGHT", 0);
			
			object1.put("ITEM_CONTENT", params.get("ITEM_CONTENT_1"));
			object1.put("QUE_ITEM_ORDER_NO", 1);
			if(IS_RIGHT1.contains("1"))
				object1.put("IS_RIGHT", 1);
			iacDB.insertDynamic(DBTableConstants.TBL_QUESTION_ITEM_NAME, object1);
			object1.put("IS_RIGHT", 0);
			
			object1.put("ITEM_CONTENT", params.get("ITEM_CONTENT_2"));
			object1.put("QUE_ITEM_ORDER_NO", 2);
			if(IS_RIGHT1.contains("2"))
				object1.put("IS_RIGHT", 1);
			iacDB.insertDynamic(DBTableConstants.TBL_QUESTION_ITEM_NAME, object1);
			object1.put("IS_RIGHT", 0);
			
			object1.put("ITEM_CONTENT", params.get("ITEM_CONTENT_3"));
			object1.put("QUE_ITEM_ORDER_NO", 3);
			if(IS_RIGHT1.contains("3"))
				object1.put("IS_RIGHT", 1);
			iacDB.insertDynamic(DBTableConstants.TBL_QUESTION_ITEM_NAME, object1);
			break;
		case 30://判断
			int IS_RIGHT2=Integer.parseInt(params.get("IS_RIGHT").toString());
			HashMap<String, Object> object2=new HashMap<String, Object>();
			object2.put("ITEM_CONTENT", params.get("ITEM_CONTENT_0"));
			object2.put("QUE_ITEM_ORDER_NO", 0);
			object2.put("QUESTION_ID", params.get("ID"));
			if(IS_RIGHT2==0)
				object2.put("IS_RIGHT", 1);
			iacDB.insertDynamic(DBTableConstants.TBL_QUESTION_ITEM_NAME, object2);
			object2.put("IS_RIGHT", 0);
			
			object2.put("ITEM_CONTENT", params.get("ITEM_CONTENT_1"));
			object2.put("QUE_ITEM_ORDER_NO", 1);
			if(IS_RIGHT2==1)
				object2.put("IS_RIGHT", 1);
			iacDB.insertDynamic(DBTableConstants.TBL_QUESTION_ITEM_NAME, object2);
		break;
		case 40://填空
		case 50://简答
			HashMap<String, Object> object3=new HashMap<String, Object>();
			object3.put("ITEM_CONTENT", params.get("ITEM_CONTENT_0"));
			object3.put("QUE_ITEM_ORDER_NO", 0);
			object3.put("QUESTION_ID", params.get("ID"));
			object3.put("IS_RIGHT", 1);
			iacDB.insertDynamic(DBTableConstants.TBL_QUESTION_ITEM_NAME, object3);
			break;
		default:
			break;
		}
		return true;
	}
	/**
	 *获取文本内容
	 */
	public String getCONTENT_TEXTByURL(String name,HttpServletRequest request) {
		String contextPath=request.getRealPath("/");
		String contextPCPath=contextPath+"upload\\question\\"+name;
		try {
			FileUtils util=new FileUtils();
			String text=util.ReadFile(contextPCPath);
			return text;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
}
