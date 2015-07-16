package com.xy.dic;

import java.util.HashMap;
import java.util.List;

public interface IDicService {

	List<HashMap<String, Object>> getDicByType(HashMap<String, Object> params);

	HashMap<String, Object> getValueByTypeAndNid(HashMap<String, Object> params);
	HashMap<String, HashMap<String, Object>> getAllDic();

}
