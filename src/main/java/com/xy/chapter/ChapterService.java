package com.xy.chapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xy.constants.DBTableConstants;
import com.xy.vo.Tree;

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
	public boolean deleteChapter(HashMap<String, Object> params) {
		HashMap<String, Object> Chapter=iacDB.get("ChapterMapper.getChapterByID", params);
		this.deleteChapterChild(Chapter);
		return iacDB.deleteDynamic(DBTableConstants.TBL_CHAPTER_NAME, params);
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
	    	              temp.setState("open");
	    	            List<Tree> cildList=getChapterChild(temp);
	    	            temp.setChildren(cildList);
	    	              treeList.add(temp);
	            	}
	            
	            }
	          }
		return treeList;
	}
	public void deleteChapterChild(HashMap<String, Object> map)
	{
		 HashMap<String, Object> params=new HashMap<String, Object>();
		 params.put("PID", map.get("ID"));
		 List<HashMap<String, Object>> ctgList=iacDB.getList("ChapterMapper.getChapterByPID", params);
		  if (ctgList != null&&ctgList.size()>0) {
	            //HashMap<String, String> tempAttMap;
	            for (HashMap<String, Object> ct : ctgList) {
	            	if((Integer)ct.get("PID")==map.get("ID"))
	            	{
	            		 HashMap<String, Object> params_child=new HashMap<String, Object>();
	            		 params_child.put("PID", map.get("ID"));
	            		List<HashMap<String, Object>> child_List=iacDB.getList("ChapterMapper.getChapterByPID", params_child);
	            		if(ctgList != null&&ctgList.size()>0)
	            		{
	            			for(HashMap<String, Object> child:child_List)
	            			{
	            				this.deleteChapterChild(child);
	            			}
	            		}
	            		else
	            		{
	            			HashMap<String, Object> map_del=new HashMap<String, Object>();
	            			map_del.put("ID", map_del.get("ID"));
	            			iacDB.deleteDynamic(DBTableConstants.TBL_CHAPTER_NAME, map_del);
	            		}
	            	}
	            
	            }
	          }
	}
	

}
