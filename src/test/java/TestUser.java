import com.lhl.myBatis.bean.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lunhengle on 2015/8/11.
 */
public class TestUser {
    SqlSessionFactory sqlSessionFactory = null;

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
     * 插入数据
     */
    @Test
    public void insertUserTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setUsername("张三");
        user.setPassword("123");
        user.setPhone("123456789");
        sqlSession.insert("com.lhl.myBatis.mapper.UserMapper.insertUser", user);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 更新数据
     */
    @Test
    public void updateUserTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setUsername("伦恒乐");
        user.setPassword("654321");
        user.setPhone("987654321");
        user.setId(15);
        sqlSession.update("com.lhl.myBatis.mapper.UserMapper.updateUser", user);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 删除数据
     */
    @Test
    public void deleteUserTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setId(13);
        sqlSession.delete("com.lhl.myBatis.mapper.UserMapper.deleteUser", user);
        //sqlSession.delete("com.lhl.myBatis.mapper.UserMapper.deleteUser", 3);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 根据id查数据
     */
    @Test
    public void selectByIdTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(1);
        user = sqlSession.selectOne("com.lhl.myBatis.mapper.UserMapper.findById", user);
        System.out.println("username:" + user.getUsername() + "  password:" + user.getPassword() + "  phone:" + user.getPhone());
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 根据map属性查数据
     */
    @Test
    public void selectByMap() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Map map = new HashMap();
        map.put("username", "伦恒乐");
        map.put("password", "123456");
        User user = sqlSession.selectOne("findByMap", map);
        System.out.println("username:" + user.getUsername() + "  password:" + user.getPassword() + "  phone:" + user.getPhone());
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 根据对象属性查数据
     */
    @Test
    public void selectByUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setUsername("伦恒乐");
        user.setPassword("123456");
        user = sqlSession.selectOne("findByUser", user);
        System.out.println("username:" + user.getUsername() + "  password:" + user.getPassword() + "  phone:" + user.getPhone());
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 插叙list
     */
    @Test
    public void selectUserListTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> list = sqlSession.selectList("findUserList");
        for (User user : list) {
            System.out.println("username:" + user.getUsername() + "  password:" + user.getPassword() + "  phone:" + user.getPhone());
        }
    }

    /**
     * 自定义map查询
     */

    @Test
    public void selectUserByUserMap() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> list = sqlSession.selectList("findUserByUserMap");
        for (User user : list) {
            System.out.println("username:" + user.getUsername() + "  password:" + user.getPassword() + "  phone:" + user.getPhone());
        }
    }
}
