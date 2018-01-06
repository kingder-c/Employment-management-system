package com.weixin.auth.service;

import java.sql.SQLException;
import java.util.List;

import com.weixin.auth.dao.AuthDao;
import com.weixin.auth.form.Auth;
import com.weixin.data.dao.DataDao;
/**
 * @注释 ：
 */
public class AuthService {
	private static final AuthDao dao = new AuthDao();
	@SuppressWarnings("unused")
	private static final DataDao datadao = new DataDao();
	/**
	 * 增加和修改权限
	 * @param chai
	 * @return 插入或更新
	 * @throws SQLException
	 */
	public long insertOrUpdate(Auth obj) throws SQLException {
		if(obj.getAuth_id() != null && obj.getAuth_id().longValue() > 0){
			return dao.update(obj);
		}else {
			return dao.insert(obj);
		}
	}
	/**
	 * 删除权限
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int delete(long id) throws SQLException{
		return dao.delete(id);
	}
	/**
	 * 
	 * @param auth_id
	 * @return 得到Auth
	 * @throws SQLException
	 */
	public Auth getAuthById(Long auth_id) throws SQLException{
		return dao.getAuthById(auth_id);
	}
	/**
	 * 
	 * @return 得到子权限集合
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List getAuthChild() throws SQLException{
		return dao.getAuthChild();
	}
	/**
	 * 
	 * @return 取得父权限集合
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List getAuthParent() throws SQLException{
		return dao.getAuthParent();
	}
}
