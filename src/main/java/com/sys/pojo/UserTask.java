package com.sys.pojo;

import java.util.Date;


public class UserTask {
	
	private String id; //任务id
	private String taskno; //任务编码
	private String taskname; //任务名称
	private String roleid; //任务对应的角色名
	private String desc;  //任务描述
	private String flowno;  //任务对应的流程编号
	private RoleInfo roleInfo;//角色实体
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTaskno() {
		return taskno;
	}
	public void setTaskno(String taskno) {
		this.taskno = taskno;
	}
	public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getFlowno() {
		return flowno;
	}
	public void setFlowno(String flowno) {
		this.flowno = flowno;
	}
	public String getRoleno() {
		return roleid;
	}
	public void setRoleno(String roleid) {
		this.roleid = roleid;
	}

	public RoleInfo getRoleInfo() {
		return roleInfo;
	}

	public void setRoleInfo(RoleInfo roleInfo) {
		this.roleInfo = roleInfo;
	}
}
