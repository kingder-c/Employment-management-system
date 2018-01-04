package com.weixin.user.service;

import java.sql.SQLException;
import java.util.List;

import com.weixin.user.dao.UserDao;
import com.weixin.user.form.User;


/**
 *  User的Service 层   是为了 处理前台与后台的  衔接
 */
public class UserService {
	private static final UserDao  dao =new UserDao();
	
	/**
	 * 添加后修改 一条数据  如果传入的对象中 没有编号  那么 就是 添加
	 * @param user
	 * @return  修改或添加的 编号
	 * @throws SQLException
	 */
	public long insertOrUpdate(User user) throws SQLException{
		if(user.getUser_id()!=null &&  user.getUser_id().longValue()>0){
			return dao.update(user);
		}else {
			return dao.insert(user);
		}
	}
	/**
	 * 通过 user_id  而 删除 对应的对象
	 * @param user_id
	 * @return 查询到的 对象
	 * @throws SQLException
	 */
	public int delete(Long user_id) throws SQLException{
		return dao.delete(user_id);
	}
	/**
	 * 通过这个user_id 得到这个 对象User
	 * @param user_id
	 * @return 通过这个user_id 得到这个 对象
	 * @throws SQLException
	 */
	public User getById(Long user_id) throws SQLException{
		return dao.getById(user_id);
	}
	
	/**
	 * 查询 分页
	 * @param user  查询条件的封装
	 * @param page
	 * @return 查询 分页
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List query(User user, int page) throws SQLException {
		return dao.query(user, page);
	}
	
	/**
	 * 查询时 可以查询到的总数
	 * @param user
	 * @return 查询时 可以查询到的总数
	 * @throws SQLException
	 */
	public int getCount(User user) throws SQLException {
		return dao.getCount(user);
	}
	
	/**
	 * 得到用户类型列表
	 * @return 这里的UserType  是为了 user_type   而 用户类型 应该是角色 id
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public  List getUserType() throws SQLException {
		return dao.getUserType();
	}
	/**
	 * 通过user_name 得到User
	 * @param user_name
	 * @return 通过user_name 得到User
	 * @throws SQLException
	 */
	public User getByName(String user_name) throws SQLException {
		return dao.getByName(user_name);
	}
	/**
	 * 通过user_id得到 用户的Role
	 * @param user_id
	 * @return 通过user_id得到 用户的Role
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List getUserRole(Long user_id) throws SQLException {
		return dao.getUserRole(user_id);
	}
	/**
	 * 检查是否存在为user_name值得User 
	 * @param user_name
	 * @return 检查是否存在为user_name值得User 
	 */
	public boolean checkUserIsExist(String user_name) {
		
		return dao.checkUserIsExist(user_name);
	}
}

