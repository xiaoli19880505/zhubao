package com.sys.common;

import java.text.NumberFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.springframework.stereotype.Component;

@Component("batchutil")
public class BatchUtil
{

	@Resource private DefaultSqlSessionFactory sqlSessionFactory;

	/**
	 * 批量插入数据库，并设置进度条
	 * <p>
	 * The save
	 * </p>
	 * 
	 * @param data 传输的list
	 * @param mapperType mapper的名称
	 * @author:Administrator 2018-8-2
	 * @update: [updatedate] [changer][change description]
	 */
	public void save(List<?> data, String mapperType)
	{
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mybatis.xml");     
		//DefaultSqlSessionFactory sqlSessionFactory = (DefaultSqlSessionFactory) ctx.getBean("sqlSessionFactory");  
		SqlSession batchSqlSession = null;
		try
		{
			batchSqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
			int batchCount = 100;//每批commit的个数
			for (int index = 0; index < data.size();index++)
			{
				batchSqlSession.insert("com.sys.mapper." + mapperType + ".insert",
					data.get(index));
				//batchSqlSession.getMapper(WingpaySetMapper.class).insert((WingpaySet)data.get(index));
				if(index % batchCount == 0)
				{
					batchSqlSession.commit();
					batchSqlSession.clearCache();
				}
			}
			batchSqlSession.commit();
			batchSqlSession.clearCache();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(batchSqlSession != null)
			{
				batchSqlSession.close();
				batchSqlSession.clearCache();
			}
		}
	}

	/**
	 * 批量更新数据库
	 * <p>
	 * The save
	 * </p>
	 *
	 * @param data 传输的list
	 * @param mapperType mapper的名称
	 * @author:Administrator 2018-8-2
	 * @update: [updatedate] [changer][change description]
	 */
	public void update(List<?> data, String mapperType)
	{
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		//DefaultSqlSessionFactory sqlSessionFactory = (DefaultSqlSessionFactory) ctx.getBean("sqlSessionFactory");
		SqlSession batchSqlSession = null;
		try
		{
			batchSqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
			int batchCount = 100;//每批commit的个数
			for (int index = 0; index < data.size();index++)
			{
				batchSqlSession.insert("com.sys.mapper." + mapperType + ".update",
						data.get(index));
				//batchSqlSession.getMapper(WingpaySetMapper.class).insert((WingpaySet)data.get(index));
				if(index % batchCount == 0)
				{
					batchSqlSession.commit();
					batchSqlSession.clearCache();
				}
			}
			batchSqlSession.commit();
			batchSqlSession.clearCache();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(batchSqlSession != null)
			{
				batchSqlSession.close();
				batchSqlSession.clearCache();
			}
		}
	}

}
