package org.zywx.appdo.meta.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import org.zywx.appdo.common.entity.AbstractBaseEntity;

/**
 * 
 * 项目名称：appdo-web-flow <br/>
 * 类名称：MetaTemplate <br/>
 * 类描述： 读取模版信息 <br/>
 * 创建人：pengpeng.yuan@zymobi.com <br/>
 * 创建时间：2015年11月6日 下午1:07:58 <br/>
 * 修改人：Yuanpp <br/>
 * 文件名：MetaTemplate.java <br/>
 * 版本信息：1.0 <br/>
 * 正益移动互联科技股份有限公司 Copyright Corporation(2015) <br/>
 * <br/>
 * 
 * @version 1.0<br/>
 */
public class MetaTemplate extends AbstractBaseEntity implements Serializable {

	private static final long serialVersionUID = 9077090720192023286L;

	private byte[] fileBlob;// 模版字段 blob二进制数据

	private String fileValue;// 转换二进制数据返回String 类型
	private Long metaid;

	private String businame;//模版名称
	public String getBusiname() {
		return businame;
	}

	public void setBusiname(String businame) {
		this.businame = businame;
	}

	public Long getMetaid() {
		return metaid;
	}

	public void setMetaid(Long metaid) {
		this.metaid = metaid;
	}

	public String getFileValue() {

		try {
			fileValue = new String(fileBlob, "UTF-8");
			System.out.println(fileValue);
			return fileValue;
		} catch (Exception e) {
			return fileValue;
		}
	}

	public void setFileValue(String fileValue) {
		this.fileValue = fileValue;
	}

	public byte[] getFileBlob() {
		return fileBlob;
	}

	public void setFileBlob(byte[] fileBlob) {
		this.fileBlob = fileBlob;
	}
}
