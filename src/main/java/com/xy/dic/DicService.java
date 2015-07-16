package com.xy.dic;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.iactive.db.IACDB;

@Service
public class DicService implements IDicService {

	  @Autowired
	  private IACDB<HashMap<String,Object>> iacDB;
	@Override
	public List<HashMap<String, Object>> getDicByType(
			HashMap<String, Object> params) {
		return iacDB.getList("getDicByType", params);
	}
	@Override
	public HashMap<String, Object> getValueByTypeAndNid(
			HashMap<String, Object> params) {
		return iacDB.get("getValueByTypeAndNid", params);
	}
	@Override
	public HashMap<String, HashMap<String, Object>> getAllDic() {
		HashMap<String, HashMap<String, Object>> map=new HashMap<String, HashMap<String,Object>>();
		List<HashMap<String, Object>> list=iacDB.getList("getAllDic", null);
		for(HashMap<String, Object> dic:list)
		{
			String DIC_TYPE=dic.get("DIC_TYPE").toString();
			Integer NID=new Integer(dic.get("NID").toString());
			if(map.get(DIC_TYPE)==null)
			{
				HashMap<String, Object> nid_map=new HashMap<String, Object>();
				nid_map.put(NID.toString(), dic);
				map.put(DIC_TYPE, nid_map);
			}
			else
			{
				HashMap<String, Object> nid_map=map.get(DIC_TYPE);
				nid_map.put(NID.toString(), dic);
			}
		}
		return map;
	}

}
