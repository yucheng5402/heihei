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
 * jdbc操作，提供一些查询、更新数据的方法
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
			// 获取配置文件中的配置
			properties.load(DBManager.class.getResourceAsStream("/jdbc.properties"));
			className = properties.getProperty("jdbc.driver");
			url = properties.getProperty("jdbc.url");
			user = properties.getProperty("jdbc.user");
			pwd = properties.getProperty("jdbc.pwd");
			// 加载驱动
			Class.forName(className);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 获取连接
	 * 
	 * @return 连接对象
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
	 * 关闭资源的公共方法
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
	 * 无参的公共查询方法
	 * 
	 * @param sql
	 * @return
	 */
	public List<Map<String, String>> query(String sql) {
		return query(sql, new Object[] {});
	}

	/**
	 * 公用查询方法
	 * 
	 * @param sql：
	 *            sql语言
	 * @param arg：sql中的参数
	 * @return list，list集合中包含Map集合，Map集合中key代表数据库查询结果的列名，value代表数据库查询结果中每一行对应那一列的数据
	 */
	public List<Map<String, String>> query(String sql, Object... arg) {
		PreparedStatement ps = null;
		ResultSet result = null;
		Connection conn = null;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			conn = getConnection();
			// 获取sql语句执行对象，然后传入sql语句
			ps = conn.prepareStatement(sql);
			// 如果参数数据不为空，那么代表sql语句是需要设置参数
			if (arg != null && arg.length > 0) {
				for (int i = 1; i <= arg.length; i++) {
					// 为PreparedStatement设置sql语句中的参数时，位置是从1开始
					ps.setString(i, arg[i - 1].toString());
				}
			}
			// 执行查询
			result = ps.executeQuery();
			// 要设法获取到查询结果中一共有多少列数据，并且根据列的序号获取每一列的列名，通过列名就可以从结果集（ResultSet）取出其对于的数据
			// 要实现这个结果，用到ResultSetMetaData，通过ResultSet.getMetaData()方法获取ResultSetMetaData对象
			ResultSetMetaData metaData = result.getMetaData();
			// 通过ResultSetMetaData获取结果中总列数
			int columnCount = metaData.getColumnCount();
			while (result.next()) {
				// 可以把下面这个map集合看做是我们要的那个对象，但是现在不知道对象是什么，所以用map集合来代替
				Map<String, String> map = new HashMap<String, String>();
				for (int i = 1; i <= columnCount; i++) {
					//// 通过循环来获取每一列的列名
					String columnName = metaData.getColumnLabel(i);
					// 获取列对应的值
					String columnValue = result.getString(columnName);
					// 放入map集合
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
	 * 公共的增、删、改方法
	 * @param sql
	 * @return
	 */
	public int modify(String sql) {
		return modify(sql, new Object[]{});
	}
	
	/**
	 * 公共的增、删、改方法
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
			//设置参数
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
 