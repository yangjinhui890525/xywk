package com.xy.menu;
import java.util.List;

import com.xy.vo.Tree;
public interface IMenuService {
  public List<Tree> getUserModuleTree(int userId);
}
