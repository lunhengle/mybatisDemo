import com.lhl.myBatis.bean.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lunhengle on 2015/8/12.
 */
public class TestReader {
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

    @Test
    public void insertReader() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setUsername("测试reader");
        user.setPassword("3456789");
        user.setPhone("456789");
        sqlSession.insert("insertUser", user);
        com.lhl.myBatis.bean.Reader reader = new com.lhl.myBatis.bean.Reader();
        reader.setMoney(200);
        reader.setUser(user);
        sqlSession.insert("insertReader", reader);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 集合查询
     */
    @Test
    public void selectReader() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<com.lhl.myBatis.bean.Reader> list = sqlSession.selectList("selectReader");
        for (com.lhl.myBatis.bean.Reader reader : list) {
            System.out.println("reader.money: " + reader.getMoney());
            for (User user : reader.getUserList()) {
                System.out.println("reader.money: " + reader.getMoney() + "    user.username:  " + user.getUsername());
            }
        }
    }

    /**
     * 测试if
     */
    @Test
    public void selectReaderIf() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        com.lhl.myBatis.bean.Reader reader = new com.lhl.myBatis.bean.Reader();
        reader.setMoney(200);
        //List<com.lhl.myBatis.bean.Reader> list = sqlSession.selectList("selectReaderIf");
        List<com.lhl.myBatis.bean.Reader> list = sqlSession.selectList("selectReaderIf", reader);
        for (com.lhl.myBatis.bean.Reader r : list) {
            System.out.println("reader.money: " + r.getMoney());
        }
    }

    /**
     * 测试choose
     */
    @Test
    public void selectReaderChoose() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        com.lhl.myBatis.bean.Reader reader = new com.lhl.myBatis.bean.Reader();
        reader.setId(null);
        reader.setMoney(200);
        //List<com.lhl.myBatis.bean.Reader> list = sqlSession.selectList("selectReaderMoney");
        List<com.lhl.myBatis.bean.Reader> list = sqlSession.selectList("selectReaderChoose", reader);
        for (com.lhl.myBatis.bean.Reader r : list) {
            System.out.println("reader.money: " + r.getMoney());
        }
    }

    /**
     * 测试where
     */
    @Test
    public void selectReaderWhere() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        com.lhl.myBatis.bean.Reader reader = new com.lhl.myBatis.bean.Reader();
        reader.setId(1);
        reader.setMoney(200);
        //List<com.lhl.myBatis.bean.Reader> list = sqlSession.selectList("selectReaderWhere");
        List<com.lhl.myBatis.bean.Reader> list = sqlSession.selectList("selectReaderWhere", reader);
        for (com.lhl.myBatis.bean.Reader r : list) {
            System.out.println("reader.money: " + r.getMoney());
        }
    }

    /**
     * 测试set
     */
    @Test
    public void updateReaderSet() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        com.lhl.myBatis.bean.Reader reader = new com.lhl.myBatis.bean.Reader();
        reader.setId(1);
        reader.setMoney(200);
        User user = new User();
        user.setId(1);
        reader.setUser(user);
        sqlSession.update("updateReaderSet", reader);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 测试trim
     */
    @Test
    public void updateReaderTrim() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        com.lhl.myBatis.bean.Reader reader = new com.lhl.myBatis.bean.Reader();
        reader.setId(1);
        reader.setMoney(200);
        User user = new User();
        user.setId(1);
        reader.setUser(user);
        sqlSession.update("updateReaderTrim", reader);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 测试trim 查询
     */
    @Test
    public void selectReaderTrim() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        com.lhl.myBatis.bean.Reader reader = new com.lhl.myBatis.bean.Reader();
        //reader.setId(1);
        reader.setMoney(200);
        //List<com.lhl.myBatis.bean.Reader> list = sqlSession.selectList("selectReaderTrim");
        List<com.lhl.myBatis.bean.Reader> list = sqlSession.selectList("selectReaderTrim", reader);
        for (com.lhl.myBatis.bean.Reader r : list) {
            System.out.println("reader.money: " + r.getMoney());
        }
    }

    /**
     * 测试foreach 查询
     */
    @Test
    public void selectReaderForeach() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List l = new ArrayList();
        l.add(1);
        l.add(2);
        List<com.lhl.myBatis.bean.Reader> list = sqlSession.selectList("selectReaderForeach", l);
        for (com.lhl.myBatis.bean.Reader r : list) {
            System.out.println("reader.money: " + r.getMoney());
        }
    }

    /**
     * 测试循环赋值
     */
    @Test
    public void insertReaderForeach() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        com.lhl.myBatis.bean.Reader reader1 = new com.lhl.myBatis.bean.Reader();
        com.lhl.myBatis.bean.Reader reader2= new com.lhl.myBatis.bean.Reader();
        User user = new User();
        user.setId(1);
        reader1.setUser(user);
        reader1.setMoney(100);
        reader2.setUser(user);
        reader2.setMoney(300);
        List<com.lhl.myBatis.bean.Reader> list = new ArrayList<com.lhl.myBatis.bean.Reader>();
        list.add(reader1);
        list.add(reader2);
        sqlSession.insert("insertReaderForeach", list);
        sqlSession.commit();
        sqlSession.close();
    }
}
