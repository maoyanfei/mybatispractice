package db;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Types;

/**
 * @Author Nuc YongGuang Ji
 * Created by JiYongGuang on 2017/4/23.
 */
public class Conn {

    //DB层获取SqlSession会话时的异常需要抛给Dao层处理，因为Dao层会负责将它关闭
    public static SqlSessionFactory getSqlSessionFactory() throws IOException {
        String configPath = "Configuration.xml";
        InputStream inputStream = Resources.getResourceAsStream(configPath);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        
        return sqlSessionFactory;
    }
}
