package com.weixin.role.service;

import java.sql.SQLException;
import java.util.List;

import com.weixin.role.dao.RoleDao;
import com.weixin.role.form.Role;

public class RoleService {
private static final RoleDao dao = new RoleDao();
/**
 * 插入或者修改一条老师记录
 * 
 * @param obj
 * @return
 * @throws SQLException
 */
public long insertOrUpdate(Role obj) throws SQLException {
	if(obj.getRole_id() != null && obj.getRole_id().longValue() > 0){
		return dao.update(obj);
	}else {
		return dao.insert(obj);
	}
}

/**
 * 删除一条老师记录
 * 
 * @param id
 * @return
 * @throws SQLException
 */
public int delete(Long id) throws SQLException {
	return dao.delete(id);
}

/**
 * 根据ID查询一条老师记录
 * 
 * @param id
 * @return
 * @throws SQLException
 */
public Role getById(Long id) throws SQLException {
	return dao.getById(id);
}

/**
 * 分页条件查询老师信息
 * 
 * @param obj
 * @param page
 * @return
 * @throws SQLException
 */
@SuppressWarnings("rawtypes")
public List query(Role obj, int page) throws SQLException {
	return dao.query(obj, page);
}

/**
 * 条件查询老师信息总记录数
 * 
 * @param obj
 * @return
 * @throws SQLException
 */
public int getCount(Role obj) throws SQLException {
	return dao.getCount(obj);
}
public String getGrant(long role_id) throws SQLException{
	return dao.getGrant(role_id);
	
}
public int grant(long role_id,String role_ids) throws SQLException{
	return dao.grant(role_id, role_ids);
}

}
