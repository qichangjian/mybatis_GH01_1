package com.qcj.test;

import com.qcj.pojo.UserInfos;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
/**
 * 修改一些配置的mybatis crud
 */

/**
 * MyBatis框架主要是围绕着SqlSessionFactory这个类进行的，这个的创建过程如下：
 *
 * 定义一个Configuration对象，其中包含数据源、事务、mapper文件资源以及影响数据库行为属性设置settings
 * 通过配置对象，则可以创建一个SqlSessionFactoryBuilder对象
 * 通过 SqlSessionFactoryBuilder 获得SqlSessionFactory 的实例。
 * SqlSessionFactory 的实例可以获得操作数据的SqlSession实例，通过这个实例对数据库进行操作
 */
public class Test {
    public static void main(String[] args) throws IOException {
        //加载配置文件，读取信息
        Reader reader = Resources.getResourceAsReader("com/qcj/config/mybatis.xml");
        //创建sqlSession面向程序员接口，提供了增删改查的方法
        SqlSessionFactory sqlSession = new SqlSessionFactoryBuilder().build(reader);
        //openSession（）中默认关闭了事务自动提交
        //SqlSession session = sqlSession.openSession();//SqlSessionFactory 的实例可以获得操作数据的SqlSession实例
        SqlSession session = sqlSession.openSession(true);
        UserInfos userInfos = session.selectOne("abcd.selectUserInfos",2);//输入PreparedStatement：命名空间+id  参数 返回mapper中返回类型
        System.out.println(userInfos);//打印输出

        /*List<UserInfos> userInfosList = session.selectList("abcd.selectAll");
        userInfosList.forEach(System.out::println);*/

        /*List<UserInfos> userInfosList1 = session.selectList("abcd.selectLikeName",'2');
        userInfosList1.forEach(System.out::println);*/

       /* //插入需要提交事务
        UserInfos user = new UserInfos("33","33");
        int result = session.insert("abcd.insertUserInfos",user);
        System.out.println(result);
        //session.commit();//方式一：手动事务提交  方式二：修改方法，加入参数true sqlSession.openSession(true)*/

        /*int result = session.delete("abcd.deleteUserInfos",18);
        System.out.println(result);*/

        /*UserInfos user = new UserInfos(17,"aaa","aaa");
        int result = session.update("abcd.updateUserInfosById",user);
        System.out.println(result);*/

        session.close();//归还连接，连接池
    }
}
