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
    return iacDB.insertDynamic(DBTableConstants.TBL_USER_NAME, user);
  }
  
  

}
