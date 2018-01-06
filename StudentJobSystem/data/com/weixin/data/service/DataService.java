package com.weixin.data.service;

import java.sql.SQLException;
import java.util.List;

import com.weixin.data.dao.DataDao;
import com.weixin.data.form.Data;

/**
 * data service 层
 */
public class DataService {
	private static final DataDao  dao =new DataDao();
	/**
	 * 添加后修改 一条数据  如果传入的对象中 没有编号  那么 就是 添加
	 * @param data
	 * @return 修改或添加的 编号
	 * @throws SQLException
	 */
	public long insertOrUpdate(Data data) throws SQLException{
		if(data.getData_key()!=null &&  data.getData_key().longValue()>0){
			return dao.update(data);
		}else {
			return dao.insert(data);
		}
	}
	
	/**
	 * 删除一条数据
	 * @param data_key
	 * @return 删除了一条数据
	 * @throws SQLException
	 */
	public int delete(Long data_key) throws SQLException{
		return dao.delete(data_key);
	}
	
	/**
	 * 通过 data_key  而 找到 对应的对象
	 * @param data_key
	 * @return 查询到的 对象
	 * @throws SQLException
	 */
	public Data getById(Long data_key) throws SQLException{
		return dao.getById(data_key);
	}
	
	/**
	 * 通过datatype  查询
	 * @param dataType
	 * @return 查询到的集合
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List getByType(String dataType) throws SQLException{
		return dao.getByType(dataType);
	}
	
	/**
	 * 分页条件查询数据字典
	 * @param data
	 * @param page
	 * @return page 页的集合
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List query(Data data, int page) throws SQLException {
		return dao.query(data, page);
	}
	
	/**
	 * 条件查询数据字典总记录数
	 * @param data
	 * @return 查询到数据的总数
	 * @throws SQLException
	 */
	public int getCount(Data data) throws SQLException {
		return dao.getCount(data);
	}
}
