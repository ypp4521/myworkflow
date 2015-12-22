package org.zywx.appdo.common;
/**
 * 异步请求结果对象
 * @author 秦飞
 *
 */
public class AjaxResult {

	private String status;
	
	private String info;
	
	private Object result;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
	
	
}
