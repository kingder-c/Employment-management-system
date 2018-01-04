package com.weixin.company.service;

import java.sql.SQLException;
import java.util.List;

import com.weixin.company.dao.CompanyDao;
import com.weixin.company.form.Company;
import com.weixin.data.dao.DataDao;


public class CompanyService {
	private static final CompanyDao dao = new CompanyDao();
	private static final DataDao datadao = new DataDao();
	/**
	 * 添加
	 * @param obj
	 * @return
	 * @throws SQLException
	 */
	public long insertOrUpdate(Company obj) throws SQLException{
		if(obj.getCom_id() != null && obj.getCom_id().longValue() > 0){
			return dao.update(obj);
		}else {
			return dao.insert(obj);
		}
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int delete(Long id) throws SQLException{
		return dao.delete(id);
	}
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Company getById(Long id) throws SQLException{
		return dao.getById(id);
	}
	/**
	 * 条件查询
	 * @param obj
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List query(Company obj,int page) throws SQLException{
		return dao.query(obj, page);
	}
	/**
	 * 条件查询数据总记录数
	 * @param obj
	 * @return查询到数据的总数
	 * @throws SQLException
	 */
	public int getCount(Company obj) throws SQLException{
		return dao.getCount(obj);
	}
	/**
	 * 通过数据字典取值
	 * @param dataType
	 * @return List 这是 通过 datadao 的已有的方法 的到某个类型的 参数
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List getSelectData(String dataType) throws SQLException{
		return datadao.getByType(dataType);
	}
	
}
