package com.xy.user;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xy.constants.DBTableConstants;

import cn.com.iactive.db.DataGridModel;
import cn.com.iactive.db.IACDB;

@Service
public class UserService implements IUserService{

  @Autowired
  private IACDB<HashMap<String, Object>> iacDB;
  
  public HashMap<String, Object> getAdminUserList(DataGridModel dm, HashMap<String, Object> params) {
    return iacDB.getDataGrid("getAdminUserList", dm, params);
  }

  public boolean saveUser(HashMap<String, String> user) {
	  if(user.get("ID")==null)
		  return iacDB.insertDynamic(DBTableConstants.TBL_USER_NAME, user);
	  else
	  {
		  return iacDB.updateDynamic(DBTableConstants.TBL_USER_NAME, DBTableConstants.TBL_USER_PK, user);
	  }
	  
    
  }

@Override
public HashMap<String, Object> getUserByID(HashMap<String, Object> user) {
	HashMap<String, Object>  map=iacDB.get("UserMapper.getUserByID", user);
	return map;
}

@Override
public boolean deleteUser(int[] iDS) {
	for(int id:iDS)
	{
		HashMap<String, Object> params=new HashMap<String, Object>();
		params.put("ID", id);
		iacDB.deleteDynamic(DBTableConstants.TBL_USER_NAME, params);
	}
	return true;
}

@Override
public HashMap<String, Object> getUserByUserName(HashMap<String, String> user) {
	HashMap<String, Object>  map=iacDB.get("UserMapper.getUserByUserName", user);
	return map;
}
  
  

}
