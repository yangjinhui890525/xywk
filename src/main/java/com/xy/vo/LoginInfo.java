package com.xy.vo;

import java.util.HashMap;

public class LoginInfo implements java.io.Serializable{
  private int userId = 0;
  private String username;
  private String password;
  private String truename;
  private int type;
  private HashMap<String, Object> chapter_unit;
  public HashMap<String, Object> getChapter_unit() {
	return chapter_unit;
}
public void setChapter_unit(HashMap<String, Object> chapter_unit) {
	this.chapter_unit = chapter_unit;
}
public int getUserId() {
    return userId;
  }
  public void setUserId(int userId) {
    this.userId = userId;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getTruename() {
    return truename;
  }
  public void setTruename(String truename) {
    this.truename = truename;
  }
  public int getType() {
    return type;
  }
  public void setType(int type) {
    this.type = type;
  }
  
  
}
