import dao.UserDao;

/**
 * @Author Nuc YongGuang Ji
 * Created by JiYongGuang on 2017/4/24.
 */
public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        userDao.getSqlSessionFactory();
    }
}