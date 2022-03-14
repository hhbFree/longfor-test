package com.longfor.shopping.plugin.task.listener;




import com.longfor.shopping.plugin.task.event.EventHandler;

import java.util.function.Function;

/**
 * 提供Bean获取功能给BeanContext作为组装
 * Created by Coral on 7/4/15.
 */
public interface BeanContextService {

	/**
	 * 当Beans环境发生变化的时候得到通知
	 * @param
	 */
	void syncBeans(EventHandler<BeanContextModification> handler, Function<BeanContext, Boolean> filter);

}
