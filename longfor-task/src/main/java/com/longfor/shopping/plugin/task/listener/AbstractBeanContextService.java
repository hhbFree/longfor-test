package com.longfor.shopping.plugin.task.listener;


import com.longfor.shopping.plugin.task.event.Event;
import com.longfor.shopping.plugin.task.event.EventHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @Description:
 * @Author: hbHao
 * @Date: 2021/8/15 23:17
 */
public class AbstractBeanContextService implements BeanContextService {

    private Map<BeanIdentity, BeanContext> beans;
    private Event<BeanContextModification> syncEvent;


    public Map<BeanIdentity, BeanContext> getBeans() {
        return beans;
    }

    public void setBeans(Map<BeanIdentity, BeanContext> beans) {
        this.beans = beans;
    }

    public Event<BeanContextModification> getSyncEvent() {
        return syncEvent;
    }

    public void setSyncEvent(Event<BeanContextModification> syncEvent) {
        this.syncEvent = syncEvent;
    }

    public AbstractBeanContextService() {
        //TODO 初始化
        beans = new HashMap<>();
        syncEvent = new Event<>(this);
    }

    @Override
    public void syncBeans(EventHandler<BeanContextModification> handler, Function<BeanContext, Boolean> filter) {

        List<BeanContextModification> list = new ArrayList<>();
        System.out.println(1);
        synchronized (this) {
            //TODO 初始化
            ServletContext servletContext=new ServletContext();
            BeanIdentity beanIdentity = new BeanIdentity("4", "5");
            beans=new HashMap<>();
            beans.put(beanIdentity,servletContext);
            //list.add(new BeanContextModification(BeanContextModification.Action.INSERT, servletContext));
            for (BeanContext bean: beans.values()) {
                if (filter.apply(bean)) {
                    list.add(new BeanContextModification(BeanContextModification.Action.INSERT, bean));
                }
            }

            //TODO 添加处理器
            syncEvent.addListener((sender, m) -> {
                if (filter.apply(m.getBeanContext())) {
                    handler.run(sender, m);
                }

            });
            for (BeanContextModification m: list) {  // 为了数据一致性, 还是放在锁里面吧
                if (filter.apply(m.getBeanContext())) {
                    handler.run(this, m);

                    //TODO 触发事件
                    syncEvent.fireEvent(m);
                }
            }


        }
    }
}
