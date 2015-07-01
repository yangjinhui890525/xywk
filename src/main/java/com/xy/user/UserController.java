package com.xy.user;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xy.constants.AppConstants;
import com.xy.util.ParamUtils;
import com.xy.vo.RetVO;

import cn.com.iactive.db.DataGridModel;

@Controller
@RequestMapping("/xy/user")
public class UserController {
  
  @Autowired
  private IUserService userService;
  
  @RequestMapping(value="managerAdmin")
  public String managerAdmin(){
    return "user/managerAdmin";
  }
  
  @RequestMapping(value="getAdminUserList")
  @ResponseBody
  public HashMap<String, Object> getAdminUserList(DataGridModel dm,HttpServletRequest request,HttpServletResponse response){
      HashMap<String, Object> resMap = userService.getAdminUserList(dm, null);
      return resMap;
  }
  
  @RequestMapping(value="addAdminUser")
  public String addAdminUser(){
    return "user/addAdminUser";
  }
  
  
  @RequestMapping(value="saveAdminUser")
  @ResponseBody
  public RetVO saveAdminUser(HttpServletRequest request) {
    RetVO ret = new RetVO();
    try {
      HashMap<String,String> user = ParamUtils.getParameters(request);
      if(user!=null){
        user.put("TYPE",AppConstants.USER_COMM_ADMIN+"");
        userService.saveUser(user);
      }
      ret.setSuccess(true);
    } catch (Exception e) {
      ret.setSuccess(false);
      ret.setMsg("error");
    }
    return ret;
  }
  
  
  
}
