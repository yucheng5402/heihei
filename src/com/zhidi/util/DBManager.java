package com.zhidi.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * jdbc�������ṩһЩ��ѯ���������ݵķ���
 * 
 * @author lx
 *
 */
public class DBManager {

	private static String url;
	private static String user;
	private static String pwd;
	private static String className;

	static {
		Properties properties = new Properties();
		try {
			// ��ȡ�����ļ��е�����
			properties.load(DBManager.class.getResourceAsStream("/jdbc.properties"));
			className = properties.getProperty("jdbc.driver");
			url = properties.getProperty("jdbc.url");
			user = properties.getProperty("jdbc.user");
			pwd = properties.getProperty("jdbc.pwd");
			// ��������
			Class.forName(className);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * ��ȡ����
	 * 
	 * @return ���Ӷ���
	 */
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * �ر���Դ�Ĺ�������
	 * @param conn
	 * @param ps
	 * @param result
	 */
	public void close(Connection conn, PreparedStatement ps, ResultSet result) {
		try {
			if (conn != null) {
				conn.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (result != null) {
				result.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * �޲εĹ�����ѯ����
	 * 
	 * @param sql
	 * @return
	 */
	public List<Map<String, String>> query(String sql) {
		return query(sql, new Object[] {});
	}

	/**
	 * ���ò�ѯ����
	 * 
	 * @param sql��
	 *            sql����
	 * @param arg��sql�еĲ���
	 * @return list��list�����а���Map���ϣ�Map������key�������ݿ��ѯ�����������value�������ݿ��ѯ�����ÿһ�ж�Ӧ��һ�е�����
	 */
	public List<Map<String, String>> query(String sql, Object... arg) {
		PreparedStatement ps = null;
		ResultSet result = null;
		Connection conn = null;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			conn = getConnection();
			// ��ȡsql���ִ�ж���Ȼ����sql���
			ps = conn.prepareStatement(sql);
			// ����������ݲ�Ϊ�գ���ô����sql�������Ҫ���ò���
			if (arg != null && arg.length > 0) {
				for (int i = 1; i <= arg.length; i++) {
					// ΪPreparedStatement����sql����еĲ���ʱ��λ���Ǵ�1��ʼ
					ps.setString(i, arg[i - 1].toString());
				}
			}
			// ִ�в�ѯ
			result = ps.executeQuery();
			// Ҫ�跨��ȡ����ѯ�����һ���ж��������ݣ����Ҹ����е���Ż�ȡÿһ�е�������ͨ�������Ϳ��Դӽ������ResultSet��ȡ������ڵ�����
			// Ҫʵ�����������õ�ResultSetMetaData��ͨ��ResultSet.getMetaData()������ȡResultSetMetaData����
			ResultSetMetaData metaData = result.getMetaData();
			// ͨ��ResultSetMetaData��ȡ�����������
			int columnCount = metaData.getColumnCount();
			while (result.next()) {
				// ���԰��������map���Ͽ���������Ҫ���Ǹ����󣬵������ڲ�֪��������ʲô��������map����������
				Map<String, String> map = new HashMap<String, String>();
				for (int i = 1; i <= columnCount; i++) {
					//// ͨ��ѭ������ȡÿһ�е�����
					String columnName = metaData.getColumnLabel(i);
					// ��ȡ�ж�Ӧ��ֵ
					String columnValue = result.getString(columnName);
					// ����map����
					map.put(columnName, columnValue);
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, result);
		}

		return list;
	}
	
	/**
	 * ����������ɾ���ķ���
	 * @param sql
	 * @return
	 */
	public int modify(String sql) {
		return modify(sql, new Object[]{});
	}
	
	/**
	 * ����������ɾ���ķ���
	 * @param sql
	 * @param arg
	 * @return
	 */
	public int modify(String sql, Object... arg) {
		PreparedStatement ps = null;
		Connection conn = null;
		int result = -1;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			//���ò���
			if (arg != null && arg.length > 0) {
				for (int i = 0; i < arg.length; i++) {
					ps.setObject(i + 1, arg[i]);
				}
			}
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, ps, null);
		}
		return result;
	}

}
 