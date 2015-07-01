package com.xy.menu;

import java.util.HashMap;
import java.util.Map;

import com.xy.vo.Tree;

public class TreeUtils {
	public static void copyModule2Tree(HashMap<String,Object> module,Tree node){
		int id = (Integer)module.get("ID");
		String name = (String)module.get("NAME");
		String url = (String)module.get("URL");
		int pid = (Integer)module.get("PID");
		int orderNum = (Integer)module.get("ORDER_NUM");
		String icon = (String)module.get("ICONCLS");
		String target = (String)module.get("TARGET");
		node.setId(id);
		node.setPid(pid);
		node.setText(name);
		node.setOrderNum(orderNum);
		node.setIconCls(icon);
		Map<String, String> attributes = new HashMap<String, String>();
		attributes.put("url", url);
		attributes.put("target",target);
		node.setAttributes(attributes);
	}
}
