import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Created by lunhengle on 2015/8/12.
 */
public class TestLog4j {
    private Logger logger  = Logger.getLogger(TestLog4j.class);
    @Test
    public void testLog4j(){
      logger.debug("这是测试文件");
    }
}
