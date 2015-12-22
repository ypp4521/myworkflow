package org.zywx.workflow.fileinput;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.zywx.db.DBHelper;

/**
 * 项目名称：appdo-web-flow <br/>
 * 类名称：FileInputSQL <br/>
 * 类描述： 使用jdbc方法把目前已经确定的流程模版放进大字段中<br/>
 * 创建人：pengpeng.yuan@zymobi.com <br/>
 * 创建时间：2015年11月5日 下午3:14:57 <br/>
 * 修改人：Yuanpp <br/>
 * 修改时间：2015年11月5日 下午3:14:57 <br/>
 * 修改备注： <br/>
 * 
 * @version 1.0<br/>
 */
public class FileInputSQL {
	public static void main(String[] args) {
		updateBlob("1");
		//queryBlob("38");
	}

	/**
	 * insertBlob(插入大字段) <br/>
	 */
	public static void updateBlob(String id) {
		Connection conn = DBHelper.getConnection();
		PreparedStatement ps = null;
		try {
			String sql = "update meta_busi set fileBlob=? where id=?";
			ps = conn.prepareStatement(sql);
			// 设置二进制参数
			File file = new File("D:\\temp\\请假单.html");
			InputStream in = new BufferedInputStream(new FileInputStream(file));
			ps.setBinaryStream(1, in, (int) file.length());
			ps.setString(2, id);
			ps.executeUpdate();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.close();
		}
	}

	/**
	 * queryBlob(读取大字段) <br/>
	 */
	public static void queryBlob(String id) {
		Connection conn = DBHelper.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "select fileBlob,id,businame from emm_flow.meta_busi where id = "+id;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				InputStream in = rs.getBinaryStream(1);
				File file = new File("D:\\temp\\" + rs.getString("id") + "_" + rs.getString("businame") + ".html");
				OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
				byte[] buff = new byte[1024];
				for (int i = 0; (i = in.read(buff)) > 0;) {
					out.write(buff, 0, i);
				}
				out.flush();
				out.close();
				in.close();
			}
			rs.close();
			stmt.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.close();
		}
	}
}
