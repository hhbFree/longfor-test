package com.longfor.shopping.common.design.chain.util;

import com.longfor.shopping.common.design.chain.service.AbstractChainService;
import com.longfor.shopping.common.design.chain.service.supplier.ShopChainContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtils {
	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private ShopChainContext shopChainContext;

	
	public AbstractChainService getInstance(String className) throws ClassNotFoundException {
		Class<?> clazz = shopChainContext.getInstance(className);
		// 获取策略实例
		AbstractChainService strategyInstance = ((AbstractChainService) applicationContext.getBean(clazz));
		return strategyInstance;

	}
}


