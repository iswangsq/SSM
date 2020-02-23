import com.test.pojo.Person;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test03 {

    @Test
    public void test1() throws IOException {
        InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

        SqlSession session = factory.openSession();

        Person p = new Person();
        p.setAge(111);

        List<Person> plist = session.selectList("com.test.mapper.PersonMapper.selectWhere", p);
        for (Person person : plist) {
            System.out.println(person);
        }


    }

    @Test
    public void test2() throws IOException {

        InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

        SqlSession session = factory.openSession();

        Person p = new Person();

       // p.setAge(999);
        p.setName("aaa");
        session.insert("com.test.mapper.PersonMapper.insertTrim", p);
        session.commit();
        System.out.println(session);

    }

    @Test
    public void test3() throws IOException {

        InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

        SqlSession session = factory.openSession();
        Person p = new Person();
       /* p.setId(8);
        p.setName("aaa");*/
        p.setAge(33);

        int result = session.delete("com.test.mapper.PersonMapper.deleteOne", p);
        session.commit();
        System.out.println(result);

    }

    @Test
    public void test4() throws IOException {

        InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

        SqlSession session = factory.openSession();
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(2);

        int result = session.delete("com.test.mapper.PersonMapper.deleteForEach", ids);
        session.commit();
        System.out.println(result);

    }
}
