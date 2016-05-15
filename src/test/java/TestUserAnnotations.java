import com.lhl.myBatis.bean.User;
import com.lhl.myBatis.mapper.UserAnnotationsMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Created by lunhengle on 2015/8/13.
 */
public class TestUserAnnotations {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加user
     */

    @Test
    public void insertUser() {
        User user = new User();
        user.setUsername("注解测试01");
        user.setPassword("12345678");
        user.setPhone("12345678");

        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserAnnotationsMapper userMapper = sqlSession.getMapper(UserAnnotationsMapper.class);
        userMapper.insertUser(user);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 修改user
     */
    @Test
    public void updateUser() {
        User user = new User();
        user.setUsername("注解测试02");
        user.setPassword("12345678");
        user.setId(32);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserAnnotationsMapper userMapper = sqlSession.getMapper(UserAnnotationsMapper.class);
        userMapper.updateUser(user);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 删除 user
     */
    @Test
    public void deleteUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserAnnotationsMapper userMapper = sqlSession.getMapper(UserAnnotationsMapper.class);
        User user = new User();
        user.setId(32);
        userMapper.deleteUser(user);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 根据id得到user
     */
    @Test
    public void selectUserById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserAnnotationsMapper userAnnotationsMapper = sqlSession.getMapper(UserAnnotationsMapper.class);
        User user = new User();
        user.setId(31);
        user = userAnnotationsMapper.selectUserById(user);
        System.out.println("username:" + user.getUsername() + "  password:" + user.getPassword());
    }

    /**
     * 得到user
     */
    @Test
    public void selectUserList() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserAnnotationsMapper userAnnotationsMapper = sqlSession.getMapper(UserAnnotationsMapper.class);
        List<User> list = userAnnotationsMapper.selectUserList();
        for(User user:list){
            System.out.println("username:" + user.getUsername() + "  password:" + user.getPassword());
        }
    }
}
