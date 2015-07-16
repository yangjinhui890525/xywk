package com.xy.vo;

import java.io.Serializable;
import java.util.List;

/**
 * EasyUI tree模型
 * 
 * @author FQ
 * 
 */
public class Tree implements Serializable,Comparable{
	private static final long serialVersionUID = -328417558183065141L;
	private int id;
	private String text;
	private String state = "open";// open,closed
	private boolean checked = false;
	private Object attributes;
	private List<Tree> children;
	private String iconCls;
	private int pid;
	private boolean checkebox = false;
	public boolean isCheckebox() {
		return checkebox;
	}
	public void setCheckebox(boolean checkebox) {
		this.checkebox = checkebox;
	}

	private int orderNum = 0;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Object getAttributes() {
		return attributes;
	}
	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}
	public List<Tree> getChildren() {
		return children;
	}
	public void setChildren(List<Tree> children) {
		this.children = children;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	
	public int compareTo(Object o) {
		return this.orderNum-((Tree)o).getOrderNum();  
	}
	
	
	
}
