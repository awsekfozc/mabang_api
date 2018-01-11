package com.smartdo.scc.mabang.backend.factory;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

/**
 * 获取mybatis sql session
 */
public class DbFactory {

    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    /**
     * 获取一个session
     *
     * @return
     */
    public static SqlSessionFactory getInstance() {
        try {
            reader = Resources.getResourceAsReader("Configure.xml");
            if (sqlSessionFactory == null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sqlSessionFactory;
    }

    /**
     * 返回一个bean dao
     *
     * @param beanClass dao class path
     * @param <T>
     * @return
     */
    public static <T> T getBeanMapper(Class<T> beanClass,SqlSession session) {
        if (!session.getConfiguration().hasMapper(beanClass)) {
            session.getConfiguration().addMapper(beanClass);
        }
        return session.getMapper(beanClass);
    }
}
