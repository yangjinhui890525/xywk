package com.xy.dic;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xy.util.ParamUtils;

@Controller
@RequestMapping("/xy/dic")
public class DICController {
	@Autowired
	IDicService dicService;
	@RequestMapping(value="type")
	@ResponseBody
	public List<HashMap<String, Object>> getDicByType(HttpServletRequest request)
	{
		HashMap<String, Object> params=ParamUtils.getFilterParams(request);
		List<HashMap<String, Object>> list=dicService.getDicByType(params);
		return list;
	}
	@RequestMapping(value="getValue")
	@ResponseBody
	public HashMap<String, Object> getValue(HttpServletRequest request)
	{
		HashMap<String, Object>  params=ParamUtils.getFilterParams(request);
		HashMap<String, Object> map=dicService.getValueByTypeAndNid(params);
		return map;
	}
}
