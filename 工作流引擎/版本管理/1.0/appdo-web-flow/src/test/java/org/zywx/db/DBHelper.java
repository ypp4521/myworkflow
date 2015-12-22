package org.zywx.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 
 * 项目名称：appdo-web-flow <br/>
 * 类名称：DBHelper <br/>
 * 类描述： jdbc连接帮助类<br/>
 * 创建人：pengpeng.yuan@zymobi.com <br/>
 * 创建时间：2015年11月5日 下午3:21:02 <br/>
 * 修改人：Yuanpp <br/>
 * 修改时间：2015年11月5日 下午3:21:02 <br/>
 * 修改备注： <br/>
 * 
 * @version 1.0<br/>
 */
public class DBHelper {
	private static final String url = "jdbc:mysql://192.168.1.71:3306/emm_flow_new?useUnicode=true&amp;characterEncoding=UTF-8";
	private static final String name = "com.mysql.jdbc.Driver";
	private static final String user = "root";
	private static final String password = "123456";

	private static Connection conn = null;
	private static PreparedStatement pst = null;

	public static Connection getConnection() {
		try {
			if (null == conn || conn.isClosed()) {
				Class.forName(name);// 指定连接类型
				conn = DriverManager.getConnection(url, user, password);// 获取连接
				if (null == conn) {
					System.out.println("连接[ " + url + " ]失败");
				} else {
					System.out.println("连接[ " + url + " ]成功");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void close() {
		try {
			if (!conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		getConnection();
	}

}
