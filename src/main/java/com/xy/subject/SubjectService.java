package com.xy.subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.iactive.db.IACDB;

import com.xy.category.ICategoryService;
import com.xy.course.ICourseService;
import com.xy.vo.Tree;

@Service
public class SubjectService implements ISubjectService {
	@Autowired
	private ICategoryService categoryService;
	 @Autowired
	 private IACDB<HashMap<String, Object>> iacDB;
	 @Autowired
	 ICourseService courseService;
	@Override
	public List<Tree> getBaseTree() {
		
		return initChapterTree(-1);
	}
	
	 private List<Tree> initChapterTree(int pids) {
		  HashMap<String, Object> params=new HashMap<String, Object>();
		  params.put("pid", pids);
		    List<HashMap<String, Object>> chapterList =categoryService.getCategoryData(params);
		    List<Tree> treeMap =new ArrayList<Tree>();
		    if (chapterList != null && chapterList.size() > 0) {
		      List<HashMap<String, Object>> ctgList = iacDB.getList("CategoryMapper.getCategoryTree");
		      Tree tree;
		      for (HashMap<String, Object> chapter : chapterList) {
		        int id = (Integer) chapter.get("ID");
		        String name = (String) chapter.get("NAME");
		        int pid = (Integer) chapter.get("PID");
		        tree = new Tree();
		        tree.setId(id);
		        tree.setText(name);
		        tree.setPid(pid);
		        HashMap<String, String> attMap = new HashMap<String, String>();
		        tree.setAttributes(attMap);
		        tree.setChildren(this.getCategoryTree(ctgList, 0));
		       
		       // tree.setIconCls("icon-save");
		        treeMap.add(tree);
		      }
		    }

		    return treeMap;
		  }
	  private List<Tree> getCategoryTree(List<HashMap<String, Object>> ctgList,int pid)
	  {
		  List<Tree> treeList=new ArrayList<Tree>();
		  if (ctgList != null) {
	            for (HashMap<String, Object> ct : ctgList) {
	            	if((Integer)ct.get("PID")==pid)
	            	{
	            		Tree temp;
	            		temp = new Tree();
	            		temp.setId((Integer)ct.get("ID"));
	            		temp.setText(ct.get("NAME").toString());
	            		temp.setPid(pid);
	    	             temp.setPid(temp.getId());
	    	             //temp.setIconCls("icon-save");
	    	              temp.setState("open");
	    	              for (HashMap<String, Object> ctx : ctgList) {
	    	            	  ctx.get("PID").toString().equals(ct.get("ID").toString());
	    	            	  List<Tree> cildList=getCategoryTree(ctgList ,Integer.parseInt(ct.get("ID").toString()));
	    	            	  List<Tree> cildList_course=getCourseTree(temp.getId());
	    	            	  if(cildList_course!=null&&cildList_course.size()>0)
	    	            		  cildList.addAll(cildList_course);
	    	            	  temp.setChildren(cildList);
	    	              }
	    	              treeList.add(temp);
	            	}
	            
	            }
	          }
		  return treeList;
		  
	  }
	  private List<Tree> getCourseTree(int pid)
	  {
		  List<Tree> treeList=new ArrayList<Tree>();
		  HashMap<String, Object> params_tree = new HashMap<String, Object>();
	        params_tree.put("category_id", pid);
	        List<HashMap<String, Object>> ctgList=courseService.getCourseDateByCategroyIDNoPage(params_tree);
		  if (ctgList != null) {
	            //HashMap<String, String> tempAttMap;
	            for (HashMap<String, Object> ct : ctgList) {
	            		Tree temp;
	            		temp = new Tree();
	            		temp.setId((Integer)ct.get("ID"));
	            		temp.setText(ct.get("NAME").toString());
	            		temp.setPid(pid);
	    	             temp.setPid(temp.getId());
	    	             temp.setIconCls("icon-folder");
	    	              List<Tree> chlilren=this.getCourseTree(temp.getId());
	    	              HashMap<String, Object> params_chapter=new HashMap<String, Object>();
	    	              params_chapter.put("COURSE_ID", temp.getId());
	    	              List<Tree> chapter_list=this.getChapterTree(params_chapter);
	    	              if(chapter_list!=null&&chapter_list.size()>0)
	    	            	  chlilren.addAll(chapter_list);
	    	              temp.setChildren(chlilren);
	    	              if(chlilren!=null&&chlilren.size()>0)
	    	            	  temp.setState("closed");
	    	              treeList.add(temp);
	            	}
	            }
		  return treeList;
	  }
	  public List<Tree> getChapterTree(HashMap<String, Object> params) {
			List<Tree> list_tree=new ArrayList<Tree>();
			List<HashMap<String, Object>> list=iacDB.getList("ChapterMapper.getChapterByCourseID", params);
			for(HashMap<String, Object> list_map:list)
			{
				Tree tree_child=new Tree();
				tree_child.setId((Integer)list_map.get("ID"));
				tree_child.setPid((Integer)list_map.get("PID"));
				tree_child.setText(list_map.get("NAME").toString());
				List<Tree> child_tree=this.getChapterChild(tree_child);
				tree_child.setChildren(child_tree);
				if(child_tree!=null&&child_tree.size()>0)
				tree_child.setState("closed");
			     HashMap<String, Object> attMap = new HashMap<String, Object>();
			     attMap.put("checkbox", true);
			     tree_child.setAttributes(attMap);
				list_tree.add(tree_child);
			}
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
		    	              HashMap<String, Object> attMap = new HashMap<String, Object>();
		    				     attMap.put("checkbox", true);
		    				     attMap.put("onlyLeafCheck", true);
		    				     attMap.put("cascadeCheck", true);
		    				     temp.setAttributes(attMap);
		    				    
		    	            List<Tree> cildList=getChapterChild(temp);
		    	            temp.setChildren(cildList);
		    	            if(cildList!=null&&cildList.size()>0)
		    	            	 temp.setState("closed");
		    	              treeList.add(temp);
		            	}
		            
		            }
		          }
			return treeList;
		}
}
