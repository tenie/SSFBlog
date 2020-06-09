package net.tenie.myblog.tools;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
 

/**
 * spring JdbcTemplate 查询时需要一个RowMapper参数, 
 * 该类的作用把查询到的行数据的key-val 放入map中, 再把Map放入list中;
 * 最终只要变量list就能获取行的数据了
 * 
 * 警告:
 * 	存入map的value是个Object所以,在获取的时候类型必须和数据库中的类型匹配,不然获取时转换类型不批评会报错
 * 
 * @author tenie
 *
 */

public class JDBCRowMapper implements RowMapper{
	private ArrayList<Map> list;
	
	public JDBCRowMapper(){
		list = new ArrayList<Map>();
	}
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Map m = new HashMap<String,Object>();		
		ResultSetMetaData rsmd = rs.getMetaData();	//获取查询结果的元数据对象
		int colCount = rsmd.getColumnCount();     	//查询的结果有多少列
		for(int i=0;i<colCount; i++){			
			m.put(rsmd.getColumnName(i+1),			//获取第?列的列名,作为key, 索引都是从1开始而不是0!
				  rs.getObject(i+1));				//获取第?列的值,作为value
		}
		list.add(m);							   //以后数据放入list中	
		return null;
	}
	
	public List  getList(){
		return list;
	} 
	

}
