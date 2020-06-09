package net.tenie.web.entity;

import java.util.List;
import java.util.Map;

public class Result {
	private Boolean error = false;
	private String success = "success";
	private String msg;
	private List<Map<String,Object>> data;
	private Map<String,Object> mapRs;
	 
	
	public Result(){}
	public Result(Boolean err,String massage){
		error = err;
		msg = massage;
	}
	
	public Result(String massage){ 
		msg = massage;
	}
	public Result(String massage, List<Map<String,Object>> data){
		this.data = data;
		msg = massage;
	}
	
	
	
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public Map<String, Object> getMapRs() {
		return mapRs;
	}
	public void setMapRs(Map<String, Object> mapRs) {
		this.mapRs = mapRs;
	}
	 
	public Boolean getError() {
		return error;
	}
	public void setError(Boolean error) {
		this.error = error;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<Map<String, Object>> getData() {
		return data;
	}
	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Result [error=" + error + ", success=" + success + ", msg=" + msg + ", data=" + data + ", mapRs="
				+ mapRs + "]";
	}
	 
	
}
