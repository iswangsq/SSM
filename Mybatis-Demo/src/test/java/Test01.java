import com.test.mapper.PersonMapper;
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

public class Test01 {

    @Test
    public void test01() throws IOException {
        //1.创建SqlSessionFactory
        InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //2.创建SqlSession
        SqlSession session = factory.openSession();
        //3.调用sql得到结果
        Person p = session.selectOne("com.test.mapper.PersonMapper.selectOne",4);

        Person p2 = session.selectOne("com.test.mapper.PersonMapper.selectOne",4);
        System.out.println(p2);
        //4.处理结果
        System.out.println(p);

    }

    @Test
    public void test02() throws IOException {
        //1.创建SqlSessionFactory
        InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //2.创建SqlSession
        SqlSession session = factory.openSession();
        //3.调用sql得到结果
        List<Person> p = session.selectList("com.test.mapper.PersonMapper.selectList");
        for (Person person : p) {
            System.out.println(person);
        }

    }

    @Test
    public void test03() throws IOException {
        //1.创建SqlSessionFactory
        InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //2.创建SqlSession
        SqlSession session = factory.openSession();

        Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("min", 20);
        map.put("max", 30);
        //3.调用sql得到结果
        List<Person> p = session.selectList("com.test.mapper.PersonMapper.selectListByAge",map);
        for (Person person : p) {
            System.out.println(person);
        }

    }

    //自定义对象
    @Test
    public void test4() throws IOException {
        //1.创建SqlSessionFactory
        InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //2.创建SqlSession
        SqlSession session = factory.openSession();

        Person p = new Person();
        p.setAge(19);
        p.setId(1);

        //3.调用sql得到结果
        List<Person> plist = session.selectList("com.test.mapper.PersonMapper.selectPerson",p);
        for (Person person : plist) {
            System.out.println(person);
        }



    }

    @Test
    public void test5() throws IOException {
        //1.创建SqlSessionFactory
        InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //2.创建SqlSession
        SqlSession session = factory.openSession();

        Person p = new Person();
        p.setAge(100);
        p.setName("lili");

        int result = session.insert("com.test.mapper.PersonMapper.insertPerson", p);
        session.commit();
        System.out.println(result);


    }

    @Test
    public void test6() throws IOException {
        //1.创建SqlSessionFactory
        InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //2.创建SqlSession
        SqlSession session = factory.openSession();

       Map<String,String> map = new HashMap<String, String>();
       map.put("c","age");

        List<Person> result = session.selectList("com.test.mapper.PersonMapper.orderByColumn", map);
        for (Person p : result) {
            System.out.println(p);
        }



    }

    @Test
    public void test7() throws IOException {
        //1.创建SqlSessionFactory
        InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //2.创建SqlSession
        SqlSession session = factory.openSession();

        PersonMapper pm = session.getMapper(PersonMapper.class);
        Person p = pm.selectOne(3);
        System.out.println(p);

    }



}
