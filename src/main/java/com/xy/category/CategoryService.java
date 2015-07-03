package com.xy.category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xy.constants.DBTableConstants;
import com.xy.vo.Tree;

import cn.com.iactive.db.IACDB;
@Service
public class CategoryService implements ICategoryService {
	  @Autowired
	  private IACDB<HashMap<String, Object>> iacDB;
	public List<HashMap<String, Object>> getCategoryData(HashMap<String, Object> params) {
		List<HashMap<String, Object>> list=iacDB.getList("CategoryMapper.getCategoryData", params);
		return list;
	}
	public boolean insertCategory(HashMap<String, Object> params) {
		return iacDB.insertDynamic(DBTableConstants.TBL_CATEGORY_NAME,params);
	}
	public List<Tree> getCategoryTree() {
		//List<HashMap<String, Object>> list=iacDB.getList("CategoryMapper.getCategoryTree");
		List<Tree> treeMap = initChapterTree(-1);
		return treeMap;
	}
	  private List<Tree> initChapterTree(int pids) {
		  HashMap<String, Object> params=new HashMap<String, Object>();
		  params.put("pid", pids);
		    List<HashMap<String, Object>> chapterList =getCategoryData(params);
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
		        tree.setChildren(this.getTree(ctgList, 0));
		        tree.setIconCls("icon-save");
		        treeMap.add(tree);
		      }
		    }

		    return treeMap;
		  }
	  private List<Tree> getTree(List<HashMap<String, Object>> ctgList,int pid)
	  {
		  List<Tree> treeList=new ArrayList<Tree>();
		  if (ctgList != null) {
	            
	            //HashMap<String, String> tempAttMap;
	            for (HashMap<String, Object> ct : ctgList) {
	            	if((Integer)ct.get("PID")==pid)
	            	{
	            		Tree temp;
	            		temp = new Tree();
	            		temp.setId((Integer)ct.get("ID"));
	            		temp.setText(ct.get("NAME").toString());
	            		temp.setPid(pid);
	    	             temp.setPid(temp.getId());
	    	             temp.setIconCls("icon-save");
	    	              temp.setState("open");
	    	              for (HashMap<String, Object> ctx : ctgList) {
	    	            	  ctx.get("PID").toString().equals(ct.get("ID").toString());
	    	            	  List<Tree> cildList=getTree(ctgList ,Integer.parseInt(ct.get("ID").toString()));
	    	            	  temp.setChildren(cildList);
	    	              }
	    	              
	    	              treeList.add(temp);
	            	}
	            
	            }
	          }
		  return treeList;
		  
	  }
}
