package com.xy.user;

import java.util.HashMap;

import cn.com.iactive.db.DataGridModel;

public interface IUserService {
  public HashMap<String,Object> getAdminUserList(DataGridModel dm,HashMap<String,Object> params);
  
  public boolean saveUser(HashMap<String,String> user);

public HashMap<String, Object> getUserByID(HashMap<String, Object> user);

public boolean deleteUser(int[] iDS);

public HashMap<String, Object> getUserByUserName(HashMap<String, String> user);
}
