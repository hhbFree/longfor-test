/*
 * FAE, Feinno App Engine
 *  
 * Create by gaolei 2012-2-14
 * 
 * Copyright (c) 2012 北京新媒传信科技有限公司
 */
package com.longfor.shopping.plugin.task.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 
 * <b>描述: </b>线程池工厂类
 * <p>
 * <b>功能: </b>用于创建及获取一个可使用的线程池
 * <p>
 * <b>用法: </b>
 * 
 * <pre>
 * Executor executor = ExecutorFactory.newFixedExecutor(&quot;demo&quot;, 10, 50);
 * </pre>
 * <p>
 * 
 * Created by Coral
 * 
 */
public class ExecutorFactory
{
	private static Map<String, Executor> executors = new Hashtable<String, Executor>();
	private static final Logger LOGGER = LoggerFactory.getLogger(ExecutorFactory.class);
	
	/**
	 * 
	 * 通过名字获取一个已经创建的线程池
	 * @param name
	 * @return
	 */
	public static Executor getExecutor(String name)
	{
		return executors.get(name);
	}

	/**
	 * 
	 * 新增一个固定大小的线程池
	 * @param name
	 * @param size 固定线程数
	 * @param limit 最大队列长度
	 * @return
	 */
	public synchronized static Executor newFixedExecutor(final String name, int size, int limit)
	{
		if(executors.get(name) == null)
		{
			ExecutorService innerExecutor = Executors.newFixedThreadPool(size, new ThreadFactory() {
				private int count = 0;
				@Override
				public Thread newThread(Runnable r)
				{
					Thread t = new Thread(r);
					t.setDaemon(false);
					t.setName("p-" + name + "-" + count);
					count++;
					return t;
				}
			});
			FixedObservableExecutor executor = new FixedObservableExecutor(name, innerExecutor, limit, size);
			executors.put(name, executor);
			LOGGER.info("Create FixedExecutor:" + name + " size = {} limit = {}", size, limit);
		}
		return getExecutor(name);
	}

}

