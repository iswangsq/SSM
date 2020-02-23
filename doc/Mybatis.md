# Mybatis

## Mybatis是什么
MyBatis是最近几年非常流行的数据访问层(DAO)框架，能够**简单高效的实现对数据层访问**。

## 常见的数据层访问方式比较

ORM

对象关系映射（Object Relational Mapping，简称ORM）是通过使用描述**对象和数据库之间映射的[元数据](https://baike.baidu.com/item/元数据/1946090)**，将面向对象语言程序(java)中的对象**自动**持久化到关系数据库中。本质上就是将数据从一种形式转换到另外一种形式。

- JDBC （java原生的一种访问数据库的方式）

1. 需要自己手动构建连接池
2. 需要手动编写sql，并手动添加查询参数
3. sql写死在了java文件中，没有解耦
4. 需要手动封装对象
5. 没有缓存机制

- Hibernate

**完全的ORM框架，不需要手动写sql**，但是是一个重配置的框架

- Mybatis

结合了2者的优点，轻量化的框架，**是一个半ORM框架，需要自己写sql，但是无需自己封装**，Mybatis会根据定义的映射规则自动封装，提高了灵活性

## MyBatis的结构

![](E:\workspace\SSM\doc\img\mybatis结构.png)

第一步：配置全局xml文件，里面需要配置数据源，别名标签和缓存等。

第二步：需要编写pojo，java接口和与它对应的xml文件 （eg: UserMapper.java  <> UserMapper.xml **UserMapper.xml里面标签的id 需要和java接口里面的方法同名**

第三步：创建SqlSessionFactory，他会生成数据库的链接

第四步：创建SqlSession,执行数据库的增删改查

- 全局配置文件

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE configuration
     PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
     "http://mybatis.org/dtd/mybatis-3-config.dtd">
  <configuration>
      <!-- 配置数据源 -->
      <environments default="mysqldb">  # 可以配置多个数据源，default是指定默认的数据源
          <environment id="mysqldb">
              <transactionManager type="JDBC"/>
              <dataSource type="POOLED">
                  <property name="driver" value="com.mysql.jdbc.Driver"/>
                  <property name="url" value="jdbc:mysql:///mydb"/>
                  <property name="username" value="root"/>
                  <property name="password" value="root"/>
              </dataSource>
          </environment>
          // 配置其他数据源
      </environments>
      // 别名
      // 缓存
  </configuration>
  
  ```

- 映射文件

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE mapper
  	PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  	"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <mapper namespace="cn.tedu.UserMapper"> 
  	<select id="select01" resultType="cn.tedu.domain.User"> 
  		select * from user;
  	</select>
  </mapper>
  # 命名空间必须和接口的全路径名称相匹配,标签的id必须和接口的方法相同，
  # resultType ：返回值如果是普通pojo类型或者是List<pojo>的类型，直接用pojo的全路径就可以了
  # resultMap : 一对一 一对多 多对多
  ```

  **映射文件写完后需要配置到sqlMapConfig.xml中**

- 测试

  ```java
  @Test
  public void test01() throws Exception {
  	//1.创建SqlSessionFactory
  	InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
  	SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
  	//2.创建SqlSession
  	SqlSession session = factory.openSession();
  	//3.调用sql得到结果
  	List<User> list = session.selectList("cn.tedu.UserMapper.select01");
  	//4.处理结果
  	System.out.println(list);
  }
  ```

- 上述操作调用具体原理

  ![](E:\workspace\SSM\doc\img\Mybais调用.png)

## Mybatis值的传递

### Map传值 (多个参数)

可以通过对象 获取 Map传递值，在配置文件中 通过 #{} 或 ${}进行应用

```java
Map<String, Integer> map = new HashMap<String, Integer>();
map.put("min",20); // #{min}
map.put("max",30); // #{max}

List<Person> pList = session.selectList("com.test.mapper.PersonMapper.selectListByAge",map);

<select id="selectListByAge" resultType="com.test.pojo.Person">
    select * from user where age between #{min} and #{max}
</select>
```

### 对象传值（多个参数）

可以通过对象 获取 Map传递值，在配置文件中 通过 #{} 或 ${}进行应用

```java
Person p = new Person();
p.setAge(19);
p.setId(1);

List<Person> pList = session.selectList("com.test.mapper.PersonMapper.selectListByAgeAndId",p);

<select id="selectListByAgeAndId" resultType="com.test.pojo.Person">
    select * from user where age = #{age} and id = #{id}
</select>
```

### 单值传值

**如果程序中只有一个参数需要传递给sql，则不需要封装到bean或map中，可以直接传入。**在sql中可以使用任意名称获取到这个参数，**虽然名称可以任意，但通常仍然使用该属性的名称，以便于阅读**。

```java
Person p = session.selectOne("com.test.mapper.PersonMapper.selectOne", 3);

<select id="selectOne" resultType="com.test.pojo.Person">
	select * from user where id = #{id}
</select>
```

### \#{} 和 ${}区别

如果传递的是一个**非字符串**值，则**两者等效**

如果是一个**字符串值**，则

\#{}在引用时，如果发现目标是一个字符串，则会将其值作为一个字符串拼接在sql上,即拼接时**自动包裹引号**

${}在引用时，即使发现目标是一个字符串，也不会作为字符串处理，拼接在sql时**不会自动包裹引号** 

**\#{} 用于传递参数  ${}用于列名**

```sql
“age” - 》
	select * from user order by "age"   # 错误 
	select * from user order by age     $ 正确
	
“zhangsan” - 》
	Select * from where name  =  "zhangsan" # 正确
	Select * from where name  =  zhangsan   $ 错误
	
19 》
	Select * from where age= "19"   # 正确
	Select * from where age= 19     $ 正确
```

## Mybatis 特殊标签的使用

<u>使用Mybatis的动态标签，就是让sql可以动态的拼接，来应对不同场景的CRUD</u>

### 更新set标签

update修改也可以使用之前的机制在配置文件中直接编写sql

**但是update语句 的 set字句中 拼接哪些字段 是根据传入的值决定，此时可以通过MyBatis提供的标签 实现判断 动态拼接update语句**：

```xml
<update id="updateById">
    update user
    <set>
        <if test="age != 0 ">age = #{age} ,</if>
        <if test="name != null">name = #{name},</if>
    </set>
    where id = #{id}
</update>
```

**注意 age != 0  不需要取值符号#{} ,!=0应该写到“”**
**在每一个if的语句内都要加逗号，Mybatis会在拼接sql的时候自动去除掉最后一个逗号**

eg:

```sql
update user
<set>
    <if test="age != 0 ">age = #{age} ,</if>
    <if test="name != null">name = #{name},</if>
</set>
where id = #{id}

如果 name 为null,则 拼接出来的sql就是：

update user set  age = #{age} (这里的逗号会自动去除) where id = #{id}
```

### 查询where标签

select语句 的 **where字句中 拼接哪些查询字段 是根据传入的值决定**，此时可以通过MyBatis提供的标签 实现判断 动态拼接select语句

```xml
<select id="selectWhere" resultType="com.test.pojo.Person">
    select * from user
    <where>
        <if test="age != 0">age = #{age}  </if>
        <if test="name != null">and name = #{name}  </if>
    </where>
</select>
## 多个条件之间如果有and 或者 or，注意不要忘记
```

### 插入Trim标签

insert语句 的**参数和值的列表 拼接哪些字段 是根据传入的值决定**，此时可以通过MyBatis提供的标签 实现判断 动态拼接insert语句

```xml
<insert id="insertTrim">
    insert into user
    <trim prefix="(" suffix=")" suffixOverrides=",">
        id,
        <if test="name != null">name,</if>
        <if test="age != 0">age,</if>
    </trim>
    values (
    <trim suffixOverrides=",">
        null,
        <if test="name !=null">#{name},</if>
        <if test="age != 0">#{age},</if>
    </trim>
    )
</insert>

# （） 可以有两种写法，可以个根据自己的习惯来使用
```

### forEach标签

如果有多个值，需要动态拼接，如：in 关键字

```xml
<delete id="deleteForEach">
    delete from user
    where id in
    <foreach collection="list" separator="," open="(" close=")" item="id">
        #{id}
    </foreach>
    <!--  (
          <foreach collection="list" separator="," item="id">
              #{id}
          </foreach>
          )-->

</delete>
collection -> 
### 作为入参时，List对象默认用"list"代替作为键，数组对象有"array"代替作为键
```

## 手动映射结果集

MyBatis可以自动将查询结果封装到bean中，前提条件是bean的属性名和查询的结果列名相同， 就会依次对应存储。

如果查询结果的列名和bean的属性名不一致，则需要手动映射结果集

### 一对一

```java
class Dept { 
	int id
	String name
	Dept P_Dept  // 一对一
} 
```

```xml
<select id="selectForEmp" resultMap="rm02">
    SELECT
    e.id AS e_id,
    e. NAME AS e_name,
    d.id AS d_id,
    d. NAME AS d_name
    FROM
    emp e
    LEFT JOIN dept d ON d.id = e.deptid
</select>

<resultMap id="rm02" type="com.test.pojo.Emp"> # 以谁为主去查，就填谁的全路径名
    # 封装主键必须用<id>这个标签  column ->代表sql查询结果的列 property 代表类的属性
    <id column="e_id" property="id"/> 
    # <result> 封装其他类型
    <result column="e_name" property="name"/>
    # 封装一对一使用association  !!! javaType
    <association property="dept" javaType="com.test.pojo.Dept">
        <id column="d_id" property="id"/>
        <result column="d_name" property="name"/>
    </association>
</resultMap>
```

### 一对多

eg: 

- 一个部门有多个员工（一对多）

```java
class Dept { 
	int id
	String name
	List<Emp> eList // 一对多
} 
```

```xml
<select id="selectForDept" resultMap="rm01" >
    SELECT
    d.id AS d_id,
    d. NAME AS d_name,
    e.id AS e_id,
    e. NAME AS e_name
    FROM
    dept d
    LEFT JOIN emp e ON d.id = e.deptid
</select>

<resultMap id="rm01" type="com.test.pojo.Dept">
    <id column="d_id" property="id"/>
    <result column="d_name" property="name"/>
	# 一对多，使用collection  ！！！ ofType  List<T>
    <collection property="eList" ofType="com.test.pojo.Emp">
        <id column="e_id" property="id"/>
        <result column="e_name" property="name"/>
    </collection>
</resultMap>
```



- 一个员工对应一个部门(一对一)

和上面的一对一相同



### 多对多

可以把多对多拆分成两个一对多，产生出一张中间表，每个原始表和这个中间表和都是一对多

![](E:\workspace\SSM\doc\img\多对多.png)

所以这样子就变成了两个一对多，所用用上面的一对多方式封装即可

## MyBatis中的其他细节

- 别名标签

如果在映射文件中，大量使用类名比较长，可以在sqlMapConfig.xml声明别名，在映射文件中可以使用别名缩短配置，**注意此配置要放在最前面**

```xml
<!--配置别名-->
<typeAliases>
    <typeAlias type="com.test.pojo.Dept" alias="Dept"/>
    <typeAlias type="com.test.pojo.Emp" alias="Emp"/>
</typeAliases>
```

- sql的复用

  如果某段sql语句的片段在映射文件中重复出现，可以将其单独配置为一个引用，从而在需要时直接引用，减少配置量

```xml
<sql id="select01">
    select * from user
</sql>

<select id="selectListByAge" resultType="com.test.pojo.Person">
    <include refid="select01"/>
    where age between #{min} and #{max}
</select>
```

## Mybatis缓存机制

​		优点：缓存机制可以**减轻数据库的压力**，**原理是在第一查询时，将查询结果缓存起来，之后再查询同样的sql，不是真的去查询数据库，而是直接返回缓存中的结果。**

​		缺点： 缓存可以降低数据库的压力，但**同时可能无法得到最新的结果数据**。

### 数据库缓存的实现

1. 通过第三方工具实现缓存：

​	Redis内存数据库 - 可以实现缓存

2. 通过MyBatis提供的缓存机制来实现缓存

   一级缓存：（**sqlSession**）

   ​	**缓存只在一个事务中有效，即同一个事务中先后执行多次同一个查询**，只在第一次真正去查库，并将结果缓存，之后的查询都直接获取缓存中的中数据。如果是不同的事务，则缓存是隔离的。MyBatis的一级缓存默认就是开启状态

   二级缓存：（**sqlSessionFactory**） （**需要手动开启，而且需要提交commit之后再回写入二级缓存**）

   ​		sqlSessionFactory 生成 sqlSession1 sqlSession2

   ​	**缓存在全局有效，一个事务查询一个sql得到结果，会被缓存起来，之后只要缓存未被清除，则其他事务如果查询同一个sql，得到的将会是之前缓存的结果**。二级缓存作用范围大，作用时间长，可能造成的危害也更大，所以在开发中一般很少启用Mybatis的二级缓存。（**数据不同步**），**MyBatis的二级缓存默认是关闭，需要手动开启**

## 接口的使用

为了简化MyBatis的使用，MyBatis提供了**接口方式** 自动化 生成调用过程的机制，可以大大简化MyBatis的开发

| 接口的全路径名应为映射文件中声明的名称空间          |
| --------------------------------------------------- |
| 接口中应该声明和映射文件中sql对应的id相同名称的方法 |
| 方法接收的参数应该和sql中接收的参数一致             |
| 方法的返回值应该和sql中声明的返回值类型一致         |

![](E:\workspace\SSM\doc\img\mapper接口调用流程.png)