package com.xy.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xy.vo.Tree;


@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	IMenuService menuService;
	
	@RequestMapping()
	@ResponseBody
	public List<Tree> getMenu(HttpServletRequest request,HttpServletResponse response){
		List<Tree> tree = menuService.getUserModuleTree(0);
		return tree;
	}
}
