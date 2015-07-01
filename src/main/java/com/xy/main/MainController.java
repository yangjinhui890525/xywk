package com.xy.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/xy/main")
public class MainController {
    
  //@RequestMapping("/main")
  @RequestMapping()
  public String main(){
    return "main/main";
  }
}
