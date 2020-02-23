import com.test.pojo.Person;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test02 {

    @Test
    public void test1() throws IOException {
        //1.创建SqlSessionFactory
        InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        Person p = new Person();
        p.setId(1);
        p.setAge(111);
        int result = sqlSession.update("com.test.mapper.PersonMapper.updateById", p);
        sqlSession.commit();
        System.out.println(result);
    }
}
