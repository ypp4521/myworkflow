package org.zywx.appdo.common.exception;
/**
 * 流程中使用的自定义异常
 * @author 秦飞
 *
 */
public class FlowBusinessRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FlowBusinessRuntimeException(Exception e) {
		super(e);
	}
	
	public FlowBusinessRuntimeException(String msg,Exception e) {
		super(msg, e);
	}
	public FlowBusinessRuntimeException(String msg) {
		super(msg);
	}
}
