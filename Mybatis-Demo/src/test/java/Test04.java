import com.test.pojo.Dept;
import com.test.pojo.Emp;
import com.test.pojo.Grade;
import com.test.pojo.Person;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Test04 {

    @Test
    public void test1() throws IOException {

        InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

        SqlSession session = factory.openSession();

        List<Grade> plist = session.selectList("com.test.mapper.RoomGradeMapper.selectLeftJoin");
        for (Grade grade : plist) {
            System.out.println(grade);
        }


    }


    @Test
    public void test2() throws IOException {

        InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

        SqlSession session = factory.openSession();

        List<Dept> dlist = session.selectList("com.test.mapper.DeptEmpMapper.selectForDept");
        for (Dept dept : dlist) {
            System.out.println(dept);
        }

    }
    @Test
    public void test3() throws IOException {

        InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

        SqlSession session = factory.openSession();

        List<Emp> elist = session.selectList("com.test.mapper.DeptEmpMapper.selectForEmp");
        for (Emp emp : elist) {
            System.out.println(emp);
        }


    }
}