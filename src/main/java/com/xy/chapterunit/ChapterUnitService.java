package com.xy.chapterunit;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xy.constants.DBTableConstants;
import com.xy.util.FileUtils;
import com.xy.vo.Tree;

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
	@Override
	public List<Tree> getChapterTree(HashMap<String, Object> params) {
		HashMap<String, Object> course=iacDB.get("CourseMapper.getCourseByID", params);
		params.put("COURSE_ID", params.get("ID"));
		List<Tree> list_tree=new ArrayList<Tree>();
		Tree tree_parent=new Tree();
		tree_parent.setText(course.get("NAME").toString());
		tree_parent.setId((Integer)course.get("ID"));
		tree_parent.setPid(new Integer(-1));
		   HashMap<String, Object> attMap_course = new HashMap<String, Object>();
		   attMap_course.put("type", "course");
		     	tree_parent.setAttributes(attMap_course);
		//tree_parent.setIconCls("icon-save");
		List<HashMap<String, Object>> list=iacDB.getList("ChapterMapper.getChapterByCourseID", params);
		List<Tree> list_child=new ArrayList<Tree>();
		for(HashMap<String, Object> list_map:list)
		{
			Tree tree_child=new Tree();
			tree_child.setId((Integer)list_map.get("ID"));
			tree_child.setPid((Integer)list_map.get("PID"));
			tree_child.setText(list_map.get("NAME").toString());
			tree_child.setChildren(this.getChapterChild(tree_child));
			   HashMap<String, Object> attMap = new HashMap<String, Object>();
			     	attMap.put("type", "chapter");
			     	tree_child.setAttributes(attMap);
			list_child.add(tree_child);
		}
		tree_parent.setChildren(list_child);
		list_tree.add(tree_parent);
		return list_tree;
	}
	public List<Tree> getChapterChild(Tree tree)
	{
		 List<Tree> treeList=new ArrayList<Tree>();
		 HashMap<String, Object> params=new HashMap<String, Object>();
		 params.put("PID", tree.getId());
		 List<HashMap<String, Object>> ctgList=iacDB.getList("ChapterMapper.getChapterByPID", params);
		  if (ctgList != null&&ctgList.size()>0) {
	            //HashMap<String, String> tempAttMap;
	            for (HashMap<String, Object> ct : ctgList) {
	            	if((Integer)ct.get("PID")==tree.getId())
	            	{
	            		Tree temp;
	            		temp = new Tree();
	            		temp.setId((Integer)ct.get("ID"));
	            		temp.setText(ct.get("NAME").toString());
	            		temp.setPid(tree.getId());
	    	            // temp.setIconCls("icon-save");
	    	             temp.setState("closed");
	    	             HashMap<String, Object> attMap = new HashMap<String, Object>();
		   			     	attMap.put("type", "chapter");
		   			     temp.setAttributes(attMap);
	    	             HashMap<String, Object> paramschpahter_unit=new HashMap<String, Object>();
	    	             paramschpahter_unit.put("PID", temp.getId());
	    	            List<Tree> cildList=getChapterChild(temp);
	    	            List<Tree> cildChapterList=getChapterUnit(paramschpahter_unit);
	    	            if(cildChapterList!=null&&cildChapterList.size()>0)
	    	            	cildList.addAll(cildChapterList);
	    	            temp.setChildren(cildList);
	    	              treeList.add(temp);
	            	}
	            
	            }
	          }
		return treeList;
	}
	public List<Tree> getChapterUnit(HashMap<String, Object> params)
	{
		 List<HashMap<String, Object>> ctgList=iacDB.getList("ChapterUnitMapper.getChapterUnitList", params);
		 List<Tree> treeList=new ArrayList<Tree>();
		 if (ctgList != null&&ctgList.size()>0) 
		 {
			 for (HashMap<String, Object> ct : ctgList) 
			 {
			    	Tree temp;
            		temp = new Tree();
            		temp.setId((Integer)ct.get("ID"));
            		temp.setText(ct.get("NAME").toString());
            		temp.setPid((Integer)params.get("PID"));
            		temp.setCheckebox(true);
            		  HashMap<String, Object> attMap = new HashMap<String, Object>();
	   			     	attMap.put("type", "chapterunit");
	   			     temp.setAttributes(attMap);
            		treeList.add(temp);
			 }
		 }
		 return treeList;
	}

}
