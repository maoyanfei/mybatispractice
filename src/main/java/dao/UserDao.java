package dao;

import bean.User;

import java.util.List;

/**
 * @Author Nuc YongGuang Ji
 * Created by JiYongGuang on 2017/4/24.
 */
public interface UserDao {

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    public User findUserById(int id);

    /**
     * 添加用户
     *
     * @param user
     */
    public void insertUser(User user);

    /**
     * 根据id删除用户
     *
     * @param id
     */
    public void deleteUserById(int id);

    /**
     * 根据用户姓名查询用户信息
     * @param username
     * @return
     */
    public List<User> findUserByUsername(String username);
}
