package com.xy.subject;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xy.vo.Tree;
@Controller
@RequestMapping("/xy/subject")
public class SubjectController {
	@Autowired
	ISubjectService subjectService;
	@RequestMapping(value="list")
	public String list()
	{
		return "subject/list";
	}
	@RequestMapping(value="getTree")
	public List<Tree> getTree()
	{
		return null;
	}
}
