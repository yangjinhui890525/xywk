package com.xy.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.iactive.db.IACDB;

import com.xy.vo.Tree;

@Service
public class MenuService implements IMenuService {
  @Autowired
  private IACDB<HashMap<String,Object>> iacDB;
  
  public List<Tree> getUserModuleTree(int userId) {
    List<HashMap<String,Object>> moduleList = iacDB.getList("getUserModule",null);
    List<Tree> tree = null;
    if(moduleList != null && moduleList.size() > 0){
        tree = new ArrayList<Tree>();
        for(HashMap<String,Object> module : moduleList){
            Tree node = new Tree();
            TreeUtils.copyModule2Tree(module, node);
            tree.add(node);
        }
        Collections.sort(tree);
    }
    return tree;
  }

}
