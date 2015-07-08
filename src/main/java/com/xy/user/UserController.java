package com.xy.user;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

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
	  HashMap<String, Object> params=ParamUtils.getFilterParams(request);
      HashMap<String, Object> resMap = userService.getAdminUserList(dm, params);
      return resMap;
  }
  
  @RequestMapping(value="editAdminUser")
  public String addAdminUser(HttpServletRequest request){
	 
	  HashMap<String,Object> user = ParamUtils.getFilterParams(request);
	  if(user.get("ID")!=null)
		  user=userService.getUserByID(user);
	  request.setAttribute("user", user);
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
      }
      if(user.get("ID")==null)
      {
    	  HashMap<String,Object> user_is_exit=userService.getUserByUserName(user);
    	  if(user_is_exit.size()>0);
          ret.setSuccess(false);
          ret.setMsg("user_exist");
          return ret;
      }
      userService.saveUser(user);
      ret.setSuccess(true);
    } catch (Exception e) {
      ret.setSuccess(false);
      ret.setMsg("error");
    }
    return ret;
  }
  @RequestMapping(value="deleteAdminUser")
  @ResponseBody
  public RetVO deleteAdminUser(HttpServletRequest request)
  {
	  RetVO ret = new RetVO();
	  try {
		  int IDS[]=ParamUtils.getIntParameters(request, "PK", 0);
		  boolean flag=userService.deleteUser(IDS);
		  ret.setSuccess(true);
	  }catch (Exception e)
	  {
		  ret.setSuccess(false);
	      ret.setMsg("error："+e.getMessage());
	  }
	  return ret;
  }
  
  
}
