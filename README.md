## 什么是 MyBatis ？
> MyBatis 是支持定制化 SQL、存储过程以及高级映射的优秀的持久层框架。MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。MyBatis 可以对配置和原生Map使用简单的 XML 或注解，将接口和 Java 的 POJOs(Plain Old Java Objects,普通的 Java对象)映射成数据库中的记录。

---
### Mybatis 与 Hibernate 不同

Mybatis和hibernate不同，它不完全是一个ORM框架，因为MyBatis需要程序员自己编写Sql语句，不过mybatis可以通过XML或注解方式灵活配置要运行的sql语句，并将java对象和sql语句映射生成最终执行的sql，最后将sql执行的结果再映射生成java对象。

Mybatis学习门槛低，简单易学，程序员直接编写原生态sql，可严格控制sql执行性能，灵活度高，非常适合对关系数据模型要求不高的软件开发，例如互联网软件、企业运营类软件等，因为这类软件需求变化频繁，一但需求变化要求成果输出迅速。但是灵活的前提是mybatis无法做到数据库无关性，如果需要实现支持多种数据库的软件则需要自定义多套sql映射文件，工作量大。

Hibernate对象/关系映射能力强，数据库无关性好，对于关系模型要求高的软件（例如需求固定的定制化软件）如果用hibernate开发可以节省很多代码，提高效率。但是Hibernate的学习门槛高，要精通门槛更高，而且怎么设计O/R映射，在性能和对象模型之间如何权衡，以及怎样用好Hibernate需要具有很强的经验和能力才行。

总之，按照用户的需求在有限的资源环境下只要能做出维护性、扩展性良好的软件架构都是好架构，所以框架只有适合才是最好。

---
### Sqlsession 的使用范围

SqlSession 中封装了对数据库的操作，如：查询、插入、更新、删除等。

通过 SqlSessionFactory 创建 SqlSession，而 SqlSessionFactory 是通过 SqlSessionFactoryBuilder 进行创建。

#### 1、SqlSessionFactoryBuilder

SqlSessionFactoryBuilder 用于创建 SqlSessionFacoty，SqlSessionFacoty 一旦创建完成就不需要SqlSessionFactoryBuilder 了，因为 SqlSession 是通过 SqlSessionFactory 生产，所以可以将SqlSessionFactoryBuilder 当成一个工具类使用，最佳使用范围是方法范围即方法体内局部变量。

#### 2、SqlSessionFactory

SqlSessionFactory 是一个接口，接口中定义了 openSession 的不同重载方法，SqlSessionFactory 的最佳使用范围是整个应用运行期间，一旦创建后可以重复使用，通常以单例模式管理 SqlSessionFactory。

#### 3、SqlSession

SqlSession 是一个面向用户的接口， sqlSession 中定义了数据库操作，默认使用 DefaultSqlSession 实现类。此对象中对操作数据库实质上用的是 Executor

结论：
> 每个线程都应该有它自己的SqlSession实例。SqlSession的实例不能共享使用，它也是线程不安全的。因此最佳的范围是请求或方法范围(定义局部变量使用)。绝对不能将SqlSession实例的引用放在一个类的静态字段或实例字段中。

> 打开一个SqlSession；使用完毕就要关闭它。通常把这个关闭操作放到 finally 块中以确保每次都能执行关闭

---

> parameterType传输单个简单类型值,${}括号中只能是value。
>　　　　　　　　　　　　　　　　　#{}括号中可以写任何

#### Settings（全局配置参数）

Mybatis 框架在运行时可以调整一些运行参数

比如：开启二级缓存、开启延迟加载。。。

### typeHandlers（类型处理器）

mybatis中通过typeHandlers完成jdbc类型和java类型的转换。

通常情况下，mybatis提供的类型处理器满足日常需要，不需要自定义.

### mappers（映射器）

> 使用相对于类路径的资源
  使用完全限定路径。
  
---
>　使用 mapper 接口类路径
注意：此种方法要求 mapper 接口名称和 mapper 映射文件名称相同，且放在同一个目录中。

---
> 册指定包下的所有mapper接口 
注意：此种方法要求 mapper 接口名称和 mapper 映射文件名称相同，且放在同一个目录中。

### Sql语句传入多参

输入映射
通过 parameterType 指定输入参数的类型，类型可以是**简单类型**、**hashmap**、**pojo**的包装类型


这段代码可以很明显看出来什么时候选择字符串拼接，什么时候选择预编译后输出
```
SELECT * FROM USER WHERE username LIKE '%${user.username}%' AND password = #{user.password}
```

### resultType
使用 resultType 进行输出映射，只有查询出来的列名和 pojo 中的属性名一致，该列才可以映射成功。
如果查询出来的列名和 pojo 中的属性名全部不一致，没有创建 pojo 对象。
只要查询出来的列名和 pojo 中的属性有一个一致，就会创建 pojo 对象。

输出pojo对象和pojo列表

不管是输出的pojo单个对象还是一个列表（list中包括pojo），在mapper.xml中resultType指定的类型是一样的。

在mapper.java指定的方法返回值类型不一样：

1、输出单个pojo对象，方法返回值是单个对象类型
```
//根据id查询用户信息
    public User findUserById(int id) throws Exception;
```

2、输出pojo对象list，方法返回值是List
```
//根据用户名查询用户信息
    public List<User> findUserByUsername(String userName) throws  Exception;
```

---


### resultMap
resultType 可以指定 pojo 将查询结果映射为 pojo，但需要 pojo 的属性名和 sql 查询的列名一致方可映射成功。

如果sql查询字段名和pojo的属性名不一致，可以通过resultMap将字段名和属性名作一个对应关系 ，resultMap实质上还需要将查询结果映射到pojo对象中。

resultMap可以实现将查询结果映射为复杂类型的pojo，比如在查询结果映射对象中包括pojo和list实现一对一查询和一对多查询。

**使用方法**

1、定义 resultMap

2、使用 resultMap 作为 statement 的输出映射类型

将下面的 sql 使用 User 完成映射
```
select id id_, username username_ from user where id = #{value}
```
User 类中属性名和上边查询的列名不一致。

所以需要：定义 resultMap
```
<!--定义 resultMap
    将select id id_, username username_ from user where id = #{value} 和User类中的属性做一个映射关系
    type: resultMap最终映射的java对象类型
    id:对resultMap的唯一标识
    -->
    <resultMap id="userResultMap" type="user">
        <!--id表示查询结果中的唯一标识
        column：查询出来的列名
        property：type指定pojo的属性名
        最终resultMap对column和property做一个映射关系（对应关系）
        -->
        <id column="id_" property="id"/>

        <!--result: 对普通结果映射定义
        column：查询出来的列名
        property：type指定pojo的属性名
        最终resultMap对column和property做一个映射关系（对应关系）
        -->
        <result column="username_" property="username"/>
    </resultMap>
```

### 动态 SQL

通过mybatis提供的各种标签方法实现动态拼接sql。

对查询条件进行判断，如果输入的参数不为空才进行查询条件拼接。
```
public class Customer {

    private int id;
    private String username;
    private String password;
    private User user;
    
}
```

```
public interface CustomerDao {

    public Customer findCustomerById(int id);

    public Customer findCustomerByCustomer(@Param(value = "customer") Customer customer);//指定传入到xml配置文件中的参数的使用名称

}
```

```
<select id="findCustomerByCustomer" parameterType="Customer" resultType="Customer">
        SELECT * FROM customer
        <where><!-- where 1=1 -->
            <if test="customer.user != null"><!--用到java属性进行判断的地方,全名称引用避免参数找不到错误-->
                <if test="customer.user.password != null and customer.user.password != ''">
                    AND password = #{customer.user.password}
                </if>
                <if test="customer.user.username !=null and customer.user.username !=''">
                    AND username LIKE '%${customer.user.username}%'<!--自动忽略第一个通过条件判断接入的sql语句的 'and' 拼接前缀。-->
                </if>
            </if>
        </where>
</select>
```
---
### Sql 片段

通过上面的其实看到在 where sql语句中有很多重复代码，可以将其抽取出来，组成一个sql片段，其他的statement就可以引用这个sql片段，利于系统的开发。

这里我们就拿上边sql 中的where定义一个sq片段如下：
```
select * from user
        <where>
        <!--refid: 指定sql片段的id，如果是写在其他的mapper文件中，则需要在前面加上namespace-->
            <include refid="query_user_where"/>
        </where>
```
---
### selectKey useGeneratedKeys 
插入更新一条数据时，可以使用selectKey获取id操作。当做多条数据插入更新时，而selectKey只能使用一次，此时应该使用useGeneratedKeys操作。

selectKey:
```
<insert id="insert">
 <selectKey keyProperty="id" resultType="int" order="BEFORE">
  <if test="_databaseId == 'oracle'">
   select seq_users.nextval from dual
  </if>
  <if test="_databaseId == 'db2'">
   select nextval for seq_users from sysibm.sysdummy1"
  </if>
 </selectKey>
 insert into users values (#{id}, #{name})
</insert>
```

useGeneratedKeys:
```
<insert id="insertAuthor" useGeneratedKeys="true" keyProperty="id">
    insert into Author (username,password,email,bio)
    values (#{username},#{password},#{email},#{bio})
</insert>
```
---

### typeAliases
```
 <typeAliases>
        <typeAlias alias="User" type="po.User"/>
        <typeAlias alias="Customer" type="po.Customer"/>
        <!--
             <package napobean"></package>     包下的所有类的别名为他们的类名
        -->
        <typeAlias type="po.Orders" alias="Orders"/>
        <typeAlias type="po.OrdersUser" alias="OrdersUser"/>
        <typeAlias type="po.Items" alias="Items"/>
        <typeAlias type="po.Orderdetail" alias="Orderdetail"/>
        <typeAlias type="hashMap" alias="java.util.HashMap"/>
    </typeAliases>
```

---
### foreach
```
<if test="ids!=null">
<!-- 使用 foreach遍历传入ids
collection：指定输入 对象中集合属性
item：每个遍历实体的名称
open：开始遍历时拼接的串
close：结束遍历时拼接的串
separator：遍历的两个对象中需要拼接的串
-->

<!-- 使用实现下边的sql拼接：
       AND (id=1 OR id=10 OR id=16) 
-->
            <foreach collection="ids" item="user_id" open="AND (" close=")" separator="or">
                <!-- 每个遍历需要拼接的串 -->
                id=#{user_id}
            </foreach>

            <!-- 实现  “ and id IN(1,10,16)”拼接 -->
            <!-- <foreach collection="ids" item="user_id" open="and id IN(" close=")" separator=",">               
                每个遍历需要拼接的串
                #{user_id}
            </foreach> -->
</if>
```

> 要做foreach的对象，作为入参时，List<?>对象默认用list代替作为键，数组对象有array代替作为键，**Map对象没有默认的键**。

---

### 使用 resultType 和 resultMap 一对一查询小结

> 使用 resultMap 将查询结果中的订单信息映射到 Orders 对象中，在 orders 类中添加 User 属性，将关联查询出来的用户信息映射到 orders 对象中的 user 属性中。使用 resultMap 将查询结果中的订单信息映射到 Orders 对象中，在 orders 类中添加 User 属性，将关联查询出来的用户信息映射到 orders 对象中的 user 属性中。

> 使用 resultType 将查询结果中的订单信息映射到 OrdersUser 对象中，因为该OrdersUser对象继承了Orders实体类，所以订单信息便可以映射到OrdersUser对象中。同时为该OrdersUser对象添加User实体的特有属性，同时将查询到的User属性映射到该类中。

+ resultType：使用resultType实现较为简单，如果pojo中没有包括查询出来的列名，需要增加列名对应的属性，即可完成映射。如果没有查询结果的特殊要求建议使用resultType。
+ resultMap：需要单独定义resultMap，实现有点麻烦，如果对查询结果有特殊的要求，使用resultMap可以完成将关联查询映射pojo的属性中。resultMap可以实现延迟加载，resultType无法实现延迟加载。

### 一对多查询

mybatis使用resultMap的collection对关联查询的多条记录映射到一个list集合属性中。

使用resultType实现：将订单明细映射到orders中的orderdetails中，需要自己处理，使用双重循环遍历，去掉重复记录，将订单明细放在orderdetails中。

### 多对多查询总结

将查询用户购买的商品信息明细清单，（用户名、用户地址、购买商品名称、购买商品时间、购买商品数量）

针对上边的需求就使用resultType将查询到的记录映射到一个扩展的pojo中，很简单实现明细清单的功能。

一对多是多对多的特例，如下需求：

> 查询用户购买的商品信息，用户和商品的关系是多对多关系。

需求1：
> 查询字段：用户账号、用户名称、用户性别、商品名称、商品价格(最常见)
  企业开发中常见明细列表，用户购买商品明细列表，
  使用resultType将上边查询列映射到pojo输出。
  
需求2：
> 查询字段：用户账号、用户名称、购买商品数量、商品明细（鼠标移上显示明细）
  使用resultMap将用户购买的商品明细列表映射到user对象中。
  
总结：
> 使用resultMap是针对那些对查询结果映射有特殊要求的功能，比如特殊要求映射成的list中包括多个list。查询多表字段，不易用resultType实现的时候使用。

### ResultMap 总结

resultType：
作用：
> 将查询结果按照sql列名pojo属性名一致性映射到pojo中。
> 场合：常见一些明细记录的展示，比如用户购买商品明细，将关联查询信息全部展示在页面时，此时可直接使用resultType将每一条记录映射到pojo中，在前端页面遍历list（list中是pojo）即可。

resultMap：
作用：
> 使用association和collection完成一对一和一对多高级映射（对结果有特殊的映射要求）。

association：
作用：
> 将关联查询信息映射到一个pojo对象中。
> 场合：为了方便查询关联信息可以使用association将关联订单信息映射为用户对象的pojo属性中，比如：查询订单及关联用户信息。 
使用resultType无法将查询结果映射到pojo对象的pojo属性中，根据对查询出的结果集查询并遍历匹配时的需要选择使用resultType还是resultMap。

collection：
作用：
> 将关联查询信息映射到一个list集合中。
> 场合：为了方便查询遍历关联信息可以使用collection将关联信息映射到list集合中，比如：查询用户权限范围模块及模块下的菜单，可使用collection将模块映射到模块list中，将菜单列表映射到模块对象的菜单list属性中，这样的作的目的也是方便对查询结果集进行遍历查询。如果使用resultType无法将查询结果映射到list集合中的时候。


### 延迟加载

#### 什么是延迟加载？

> resultMap可以实现高级映射（使用association、collection实现一对一及一对多映射），association、collection具备延迟加载功能。 

需求： 
如果查询订单并且关联查询用户信息。如果先查询订单信息即可满足要求，当我们需要查询用户信息时再查询用户信息。把对用户信息的按需去查询就是延迟加载。

> 延迟加载：先从单表查询、需要时再从关联表去关联查询，大大提高数据库性能，因为查询单表要比关联查询多张表速度要快。

整个延迟加载的思路：

1. 执行上边mapper方法（findOrdersUserLazyLoading），内部去调用cn.zhisheng.mybatis.mapper.OrdersMapperCustom 中的 findOrdersUserLazyLoading 只查询 orders 信息（单表）。

2. 在程序中去遍历上一步骤查询出的 List，当我们调用 Orders 中的 getUser 方法时，开始进行延迟加载。

3. 延迟加载，去调用 UserMapper.xml 中 findUserbyId 这个方法获取用户信息。

思考：

> 不使用 mybatis 提供的 association 及 collection 中的延迟加载功能，如何实现延迟加载？？

实现方法如下：
定义两个mapper方法：

1. 查询订单列表
2. 根据用户id查询用户信息

实现思路：
1. 先去查询第一个mapper方法，获取订单信息列表
2. 在程序中（service），按需去调用第二个mapper方法去查询用户信息。

总之：
使用延迟加载方法，先去查询简单的 sql（最好单表，也可以关联查询），再去按需要加载关联查询的其它信息。

#### 一对多延迟加载

上面的那个案例是一对一延迟加载，那么如果我们想一对多进行延迟加载呢，其实也是很简单的。

一对多延迟加载的方法同一对一延迟加载，在collection标签中配置select内容。

延迟加载总结：
作用：
1. 当需要查询关联信息时再去数据库查询，默认不去关联查询，提高数据库性能。 
只有使用resultMap支持延迟加载设置。

场合：
1. 当只有部分记录需要关联查询其它信息时，此时可按需延迟加载，需要关联查询时再向数据库发出sql，以提高数据库性能。

2. 当全部需要关联查询信息时，此时不用延迟加载，直接将关联查询信息全部返回即可，可使用resultType或resultMap完成映射。
---

### 查询缓存

什么是查询缓存？

> mybatis提供查询缓存，用于减轻数据压力，提高数据库性能。

mybaits提供一级缓存，和二级缓存。

+ 一级缓存是SqlSession级别的缓存。在操作数据库时需要构造 sqlSession对象，在对象中有一个数据结构（HashMap）用于存储缓存数据。不同的sqlSession之间的缓存数据区域（HashMap）是互相不影响的。
+ 二级缓存是mapper级别的缓存，多个SqlSession去操作同一个Mapper的sql语句，多个SqlSession可以共用二级缓存，二级缓存是跨SqlSession的。

![alt text](http://ohfk1r827.bkt.clouddn.com/cache1.jpg)

+ 第一次发起查询用户id为1的用户信息，先去找缓存中是否有id为1的用户信息，如果没有，从数据库查询用户信息。
 + 得到用户信息，将用户信息存储到一级缓存中。

+ 如果sqlSession去执行commit操作（执行插入、更新、删除），清空SqlSession中的一级缓存，这样做的目的为了让缓存中存储的是最新的信息，避免脏读。

+ 第二次发起查询用户id为1的用户信息，先去找缓存中是否有id为1的用户信息，缓存中有，直接从缓存中获取用户信息。

#### 一级缓存

> Mybatis 默认支持一级缓存，不需要在配置文件中配置。

```
public void testCache1() throws Exception {
        sqlSession = sqlSessionFactory.openSession();
        //创建UserDao对象,mybatis自动生成代理对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);

        //查询使用的是同一个session
        //第一次发起请求，查询Id 为1的用户信息
        User user1 = userDao.findUserById(1);
        System.out.println(user1);

        /*
            //如果sqlSession去执行commit操作（执行插入、更新、删除），
            // 清空SqlSession中的一级缓存，这样做的目的为了让缓存中存储的是最新的信息，避免脏读。
            //更新user1的信息，
            user1.setUsername("李飞");
            //user1.setSex("男");
            //user1.setAddress("北京");
            userDao.updateUser(user1);
            System.out.println("updateUser /================================/ had down");
            //提交事务,才会去清空缓存
            sqlSession.commit();
        */

        //第二次发起请求，查询Id 为1的用户信息
        //通过log结果可以看出两次查询之间sqlsession不含commit操作。第二次没有发出sql查询请求，
        User user2 = userDao.findUserById(1);
        System.out.println(user2);
    }
```

一级缓存应用:
正式开发，是将 mybatis 和 spring 进行整合开发，事务控制在 service 中。

一个 service 方法中包括很多 mapper 方法调用。
```
service{

     //开始执行时，开启事务，创建SqlSession对象

     //第一次调用mapper的方法findUserById(1)

     //第二次调用mapper的方法findUserById(1)，从一级缓存中取数据

     //方法结束，sqlSession关闭
}
```

> 如果是执行两次service调用查询相同的用户信息，不走一级缓存，因为session方法结束，**sqlSession就关闭，一级缓存就清空**。

#### 二级缓存

![alt_text](http://ohfk1r827.bkt.clouddn.com/cache2.jpg)

> 首先开启mybatis的二级缓存。

1. sqlSession1去查询用户id为1的用户信息，查询到用户信息会将查询数据存储到二级缓存中。

2. 如果SqlSession3去执行相同 mapper下sql，执行commit提交，清空该 mapper下的二级缓存区域的数据。

3. sqlSession2去查询用户id为1的用户信息，去缓存中找是否存在数据，如果存在直接从缓存中取出数据。

二级缓存与一级缓存区别:
+ 二级缓存的范围更大，多个sqlSession可以共享一个UserMapper的二级缓存区域。

UserMapper有一个二级缓存区域（按namespace分），其它mapper也有自己的二级缓存区域（按namespace分）。

每一个namespace的mapper都有一个二缓存区域，两个mapper的namespace如果相同(一个接口配置两个mapper)，这两个mapper执行sql查询到数据将存在相同的二级缓存区域中。

> 开启二级缓存：
mybaits的二级缓存是mapper范围级别，除了Configuration.xml设置二级缓存的总开关，还要在具体的mapper.xml中开启二级缓存。

Configuration.xml：
```
<setting name="cacheEnabled" value="true"/><!--开启二级缓存-->
```

mapper.xml：
```
<cache/><!--开启该mapper下的二级缓存-->
```

调用 pojo 类实现序列化接口：
二级缓存需要查询结果映射的pojo对象实现Java.io.Serializable接口，来实现序列化和反序列化操作（因为二级缓存数据存储介质多种多样，在内存不一样），注意如果该需要缓存的类存在父类、或者属性成员 pojo都需要实现序列化接口。
```
public class Orders implements Serializable
public class User implements Serializable
```

#### useCache 配置

在 statement 中设置 useCache=false 可以禁用当前 select 语句的二级缓存，即每次查询都会发出sql去查询，默认情况是true，即该sql使用二级缓存。

```
<select id="findUserById" parameterType="int" resultType="user" useCache="false">
```

总结：针对每次查询都需要最新的数据sql，要设置成useCache=false，禁用二级缓存。

#### 刷新缓存（清空缓存）

在mapper的同一个namespace中，如果有其它insert、update、delete操作数据后需要刷新缓存，如果不执行刷新缓存会出现脏读。

设置statement配置中的flushCache=”true” 属性，默认情况下为true即刷新缓存，如果改成false则不会刷新。使用缓存时如果手动修改数据库表中的查询数据会出现脏读。

如下：
```
<insert id="insetrUser" parameterType="cn.zhisheng.mybatis.po.User" 
flushCache="true">
```
一般下执行完commit操作都需要刷新缓存，flushCache=true表示刷新缓存，这样可以避免数据库脏读。

#### Mybatis Cache参数

+ flushInterval（刷新间隔）可以被设置为任意的正整数，而且它们代表一个合理的毫秒形式的时间段。默认情况是不设置，也就是没有刷新间隔，缓存仅仅调用语句时刷新。相当于 cache 的全局刷新属性设置。到时间段清空

+ size（引用数目）可以被设置为任意正整数，要记住你缓存的对象数目和你运行环境的可用内存资源数目。默认值是1024。

+ readOnly（只读）属性可以被设置为true或false(如果设置为false为可读写)。只读的缓存会给所有调用者返回缓存对象的相同实例。因此这些对象不能被修改。这提供了很重要的性能优势。可读写的缓存会返回缓存对象的拷贝（通过序列化）。这会慢一些，但是安全，因此该属性默认是false。即缓存数据可以被修改

如下例子：

```
<cache  eviction="FIFO" flushInterval="60000"  size="512" readOnly="true"/>
```

这个更高级的配置创建了一个 FIFO 缓存,并每隔 60 秒刷新,存数结果对象或列表的 512 个引用,而且返回的对象被认为是只读的,因此在不同线程中的调用者之间修改它们会导致冲突。可用的收回策略有, 默认的是 LRU:

+ eviction 缓存内容的回收策略

  + LRU – 最近最少使用的:移除最长时间不被使用的对象。

  + FIFO – 先进先出:按对象进入缓存的顺序来移除它们。

  + SOFT – 软引用:移除基于垃圾回收器状态和软引用规则的对象。

  + WEAK – 弱引用:更积极地移除基于垃圾收集器状态和弱引用规则的对象。
  
  说意义不大是在于：
a、面对一定规模的数据量，内置的cache方式就派不上用场了;
b、对查询结果集做缓存并不是MyBatis框架擅长的，它专心做的应该是sql mapper。采用此框架的Application去构建缓存更合理，比如采用OSCache、Memcached啥的。

#### Mybatis 整合 ehcache

ehcache 是一个分布式缓存框架。

**分布缓存：**

我们系统为了提高系统并发，性能、一般对系统进行分布式部署（集群部署方式）

不使用分布缓存，缓存的数据在各各服务单独存储，不方便系统 开发。所以要使用分布式缓存对缓存数据进行集中管理。

mybatis无法实现分布式缓存，需要和其它分布式缓存框架进行整合。**分布缓存**

我们系统为了提高系统并发，性能、一般对系统进行分布式部署（集群部署方式）

![](http://ohfk1r827.bkt.clouddn.com/eheache.jpg)

不使用分布缓存，缓存的数据在各各服务单独存储，不方便系统 开发。所以要使用分布式缓存对缓存数据进行**集中管理**。

mybatis无法实现分布式缓存，需要和其它分布式缓存框架进行整合。

**整合方法：**

mybatis 提供了一个二级缓存 cache 接口（org.apache.ibatis.cache 下的 Cache），如果要实现自己的缓存逻辑，实现cache接口开发即可。

```java
import java.util.concurrent.locks.ReadWriteLock;
public interface Cache {
    String getId();
    void putObject(Object var1, Object var2);
    Object getObject(Object var1);
    Object removeObject(Object var1);
    void clear();
    int getSize();
    ReadWriteLock getReadWriteLock();
}
```

mybatis和ehcache整合，mybatis 和 ehcache 整合包中提供了一个 cache 接口的实现类(org.apache.ibatis.cache.impl 下的 PerpetualCache)。

```java
package org.apache.ibatis.cache.impl;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.CacheException;
public class PerpetualCache implements Cache {
    private String id;
    private Map<Object, Object> cache = new HashMap();
    public PerpetualCache(String id) {
        this.id = id;
    }
    public String getId() {
        return this.id;
    }
    public int getSize() {
        return this.cache.size();
    }
    public void putObject(Object key, Object value) {
        this.cache.put(key, value);
    }
    public Object getObject(Object key) {
        return this.cache.get(key);
    }
    public Object removeObject(Object key) {
        return this.cache.remove(key);
    }
    public void clear() {
        this.cache.clear();
    }
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
    public boolean equals(Object o) {
        if(this.getId() == null) {
            throw new CacheException("Cache instances require an ID.");
        } else if(this == o) {
            return true;
        } else if(!(o instanceof Cache)) {
            return false;
        } else {
            Cache otherCache = (Cache)o;
            return this.getId().equals(otherCache.getId());
        }
    }
    public int hashCode() {
        if(this.getId() == null) {
            throw new CacheException("Cache instances require an ID.");
        } else {
            return this.getId().hashCode();
        }
    }
}
```

通过实现 Cache 接口可以实现 mybatis 缓存数据通过其它缓存数据库整合，mybatis 的特长是sql操作，缓存数据的管理不是 mybatis 的特长，为了提高缓存的性能将 mybatis 和第三方的缓存数据库整合，比如 ehcache、memcache、redis等。

+ 引入依赖包

ehcache-core-2.6.5.jar 和 mybatis-ehcache-1.0.2.jar

+ 引入缓存配置文件

classpath下添加：ehcache.xml(EhcacheCache实现类的默认配置文件)

内容如下：
```
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:noNamespaceSchemaLocation="http://ehcache.org/ehcache.xsd">
<diskStore path="C:\JetBrains\IDEAProject\ehcache" />
<defaultCache 
    maxElementsInMemory="1000" 
    maxElementsOnDisk="10000000"
    eternal="false" 
    overflowToDisk="false" 
    timeToIdleSeconds="120"
    timeToLiveSeconds="120" 
    diskExpiryThreadIntervalSeconds="120"
    memoryStoreEvictionPolicy="LRU">
</defaultCache>
</ehcache>
```

属性说明：

> diskStore：指定数据在磁盘中的存储位置。
defaultCache：当借助 CacheManager.add(“demoCache”) 创建Cache时，EhCache 便会采用指定的的管理策略
以下属性是必须的：

>　maxElementsInMemory - 在内存中缓存的element的最大数目
maxElementsOnDisk - 在磁盘上缓存的element的最大数目，若是0表示无穷大
eternal - 设定缓存的elements是否永远不过期。如果为true，则缓存的数据始终有效，如果为false那么还要根据timeToIdleSeconds，timeToLiveSeconds判断
overflowToDisk- 设定当内存缓存溢出的时候是否将过期的element缓存到磁盘上
以下属性是可选的：

> timeToIdleSeconds - 当缓存在EhCache中的数据前后两次访问的时间超过timeToIdleSeconds的属性取值时，这些数据便会删除，默认值是0,也就是可闲置时间无穷大
timeToLiveSeconds - 缓存element的有效生命期，默认是0.,也就是element存活时间无穷大

> diskSpoolBufferSizeMB 这个参数设置DiskStore(磁盘缓存)的缓存区大小.默认是30MB.每个Cache都应该有自己的一个缓冲区.

> diskPersistent- 在VM重启的时候是否启用磁盘保存EhCache中的数据，默认是false。

> diskExpiryThreadIntervalSeconds - 磁盘缓存的清理线程运行间隔，默认是120秒。每个120s，相应的线程会进行一次EhCache中数据的清理工作
memoryStoreEvictionPolicy - 当内存缓存达到最大，有新的element加入的时候， 移除缓存中element的策略。默认是LRU（最近最少使用），可选的有LFU（最不常使用）和FIFO（先进先出）

+ 开启ehcache缓存

EhcacheCache 是ehcache对Cache接口的实现；修改mapper.xml文件，在cache标签的type属性中指定EhcacheCache。覆盖其默认实现

根据需求调整缓存参数：(根据条件对EhcacheCache实现类的参数调整)
```xml
<cache type="org.mybatis.caches.ehcache.EhcacheCache" > 
      <property name="timeToIdleSeconds" value="3600"/>
      <property name="timeToLiveSeconds" value="3600"/>
      <!-- 同ehcache参数maxElementsInMemory -->
    <property name="maxEntriesLocalHeap" value="1000"/>
    <!-- 同ehcache参数maxElementsOnDisk -->
      <property name="maxEntriesLocalDisk" value="10000000"/>
      <property name="memoryStoreEvictionPolicy" value="LRU"/>
  </cache>
```

应用场景

+ 对于访问多的查询请求且用户对查询结果实时性要求不高，此时可采用 mybatis 二级缓存技术降低数据库访问量，提高访问速度，业务场景比如：耗时较高的统计分析sql、电话账单查询sql等。

 + 实现方法如下：通过设置刷新间隔时间，由 mybatis 每隔一段时间自动清空缓存，根据数据变化频率设置缓存刷新间隔 flushInterval，比如设置为30分钟、60分钟、24小时等，根据需求而定。

局限性

+ mybatis 二级缓存对细粒度的数据级别的缓存实现不好，比如如下需求：对商品信息进行缓存，由于商品信息查询访问量大，但是要求用户每次都能查询最新的商品信息，此时如果使用 mybatis 的二级缓存就无法实现当一个商品变化时只刷新该商品的缓存信息而不刷新其它商品的信息，因为 mybaits 的二级缓存区域以 mapper 为单位划分，当一个商品信息变化会将所有商品信息的缓存数据全部清空。解决此类问题需要在业务层根据需求对数据有针对性缓存。
