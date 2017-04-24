import dao.UserDao;
import dao.UserMapper;

import java.io.IOException;

/**
 * @Author Nuc YongGuang Ji
 * Created by JiYongGuang on 2017/4/24.
 */
public class Main {
    public static void main(String[] args) {
//        UserMapper userDao = new UserMapper();
//        userDao.findUserById();
//        userDao.findUserByUsername();
//        userDao.insertUser();
//        userDao.deleteUserById();
//        userDao.updateUserById();


        UserDaoImpTest userDaoImpTest = new UserDaoImpTest();
        try {
            userDaoImpTest.testFindUserByUsername();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
