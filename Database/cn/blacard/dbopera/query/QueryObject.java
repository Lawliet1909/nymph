package cn.blacard.dbopera.query;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import cn.blacard.dbopera.connect.Connect;
import cn.blacard.dbopera.para.DBConnectPara;

/**
 * <h1>数据库增删改查操作</h1>
 * <b>使用注意事项：</b><br/>
 * 1. <T>要传入的是数据库对应表被查询的实体对象<br/>
 * 2. 实体对象的变量需要和表中列名完全对应<br/>
 * 3. 实体数据类型不能用基本类型，必须是封装类型。<br/>
 * 4. 其他不知道还有什么，等想到了补充上。或者请联系我的邮箱提出您宝贵的意见<br/>
 * @author Blacard
 * @changeTime 2016年8月30日16:02:16
 * @since 2016年8月30日16:02:06
 * @e-mail blacard@163.com
 * @param <T>
 */
public class QueryObject<T> extends QueryBase{
	//数据库连接参数
	private DBConnectPara para;
	
	/**
	 * 用构造器传入数据库连接参数
	 * @param para
	 */
	public QueryObject(DBConnectPara para){
		Connect.setConnPara(para);
	}
	
	
	/**
	 * 用此构造器之前
	 * 请确认给Connect设置了数据库连接参数
	 */
	public QueryObject(){
		super();
	}
	

	/**
	 * 查询方法，
	 * 主要是select查询
	 * @param sql
	 * @param args
	 * @param clazz
	 * @return
	 */
	public List<T> query(String sql, Object[] args, Class clazz) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		//
		List<T> list = new ArrayList<T>();
		
		try {
			// 
			Field[] fields = clazz.getDeclaredFields();
			
			Method method = null;

			openConnect();
			pstmt = conn.prepareStatement(sql);
			
			
			// ֵ
			if (args != null && args.length > 0) {
				for (int i = 0; i < args.length; i++) {
					pstmt.setObject(i + 1, args[i]);
				}
			}
			rs = pstmt.executeQuery();
			//
			ResultSetMetaData rsmd = rs.getMetaData();
			//
			int columnCount = rsmd.getColumnCount();
	
			
			while (rs.next()) {
				// 
				T obj = (T) clazz.newInstance();
				
				// 
				for (int i = 1; i <= columnCount; i++) {
					
					String cname = rsmd.getColumnName(i);
					int ctype = rsmd.getColumnType(i);
					
					for (Field f : fields) {
						if (cname.equalsIgnoreCase(f.getName()))
						{
							// 
							String methodName = "set"
									+ f.getName().substring(0, 1).toUpperCase()
									+ f.getName().substring(1);
							//
							switch (ctype) {
							
							case Types.INTEGER:
								method = clazz.getMethod(methodName,
										Integer.class);
								method.invoke(obj, rs.getInt(i));
								break;
							case Types.DATE:
							case Types.DATALINK:
							case Types.NVARCHAR:
							case Types.VARCHAR:
								method = clazz.getMethod(methodName,
										String.class);
								method.invoke(obj, rs.getString(i));
								break;
							default:method = clazz.getMethod(methodName,
									String.class);
							method.invoke(obj, rs.getString(i));
							}
						}
					}
				}
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
			closeAll();
		}
		return list;
	}

	/**
	 * 只查询一条数据。
	 * @author Blacard
	 * @create 2016年9月26日 下午3:17:13
	 * @param sql
	 * @param args
	 * @param clazz
	 * @return
	 */
	public Object queryOne(String sql, Object[] args, Class clazz) {
		List<T> list = query(sql,args,clazz);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	// 执行(update insert delete)之类的操作
	public int executeSQL(String sql, Object[] args) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			openConnect();
			if(conn == null) System.out.println("conn . is null");
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			//
			if (args != null && args.length > 0) {
				for (int i = 0; i < args.length; i++) {
					pstmt.setObject(i + 1,args[i]);
				}
			}
			count = pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();//出现异常，事务回滚
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			System.out.println("增删改时出现异常");
		} finally {
			closeAll(rs, pstmt, conn);
			closeAll();
		}
		return count;
	}
	
	/**
	 * 关闭连接
	 * @param rs
	 * @param stmt
	 * @param conn
	 */
	public void closeAll(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭自身所有链接
	 * @author Blacard
	 * @create 2016年12月15日 上午3:10:17
	 */
	public void closeAll(){
		this.closeConnect();
	}

}
