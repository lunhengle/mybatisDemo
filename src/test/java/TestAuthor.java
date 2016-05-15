import com.lhl.myBatis.bean.Author;
import com.lhl.myBatis.bean.User;
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
 * Created by lunhengle on 2015/8/12.
 */
public class TestAuthor {
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
     * 事务提交
     */
    @Test
    public void insertAuthor() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            User user = new User();
            user.setUsername("测试事务");
            user.setPassword("12345678");
            user.setPhone("1234567890");
            sqlSession.insert("insertUser", user);
            System.out.println(user.getId());
            Author author = new Author();
            author.setUser(user);
            author.setIdcard("123456789");
            author.setRealname("测试事务");
            sqlSession.insert("insertAuthor", author);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        }
    }

    /**
     * 内联查询
     */
    @Test
    public void selectAuthorJoin() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Author> list = sqlSession.selectList("selectAuthorJoin");
        for (Author author : list) {
            System.out.println("author.realname: " + author.getRealname() + "   user.username: " + author.getUser().getUsername());
        }
    }

    /**
     * 构造函数内联查询
     */
    @Test
    public void selectAuthorJoinConst() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Author> list = sqlSession.selectList("selectAuthorJoinConst");
        for (Author author : list) {
            System.out.println("author.realname: " + author.getRealname() + "   user.username: " + author.getUser().getUsername());
        }
    }

    /**
     * 子查询
     */
    @Test
    public void selectAuthorMapSub() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Author> list = sqlSession.selectList("selectAuthorMapSub");
        for (Author author : list) {
            System.out.println("author.realname: " + author.getRealname() + "   user.username: " + (null == author.getUser() ? "" : author.getUser().getUsername()));
        }
    }
}
