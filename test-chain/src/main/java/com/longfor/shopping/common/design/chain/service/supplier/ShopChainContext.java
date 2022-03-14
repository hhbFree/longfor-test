package com.longfor.shopping.common.design.chain.service.supplier;

import com.longfor.shopping.common.config.ChainProperties;
import com.longfor.shopping.common.design.chain.dto.ChainParam;
import com.longfor.shopping.common.design.chain.dto.ChainResult;
import com.longfor.shopping.common.design.chain.service.AbstractChainService;
import com.longfor.shopping.common.design.chain.service.ShopChain;
import com.longfor.shopping.common.design.chain.util.SpringContextUtils;
import com.longfor.shopping.common.design.chain.service.chainenum.Chain;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.util.*;

@Component
public class ShopChainContext implements ApplicationRunner {

    private List<AbstractChainService> ratifies;

    @Autowired
    private SpringContextUtils springContextUtils;


    @Autowired
    private ChainProperties chainProperties;

    //链路对象
    private final HashMap<String, Class> chainMap = new HashMap<>();

    //航道对象
    HashMap<String, List<AbstractChainService>> channelMap = new HashMap<>();


    public ShopChainContext() {
        ratifies = new ArrayList<>();
    }

    //外部化配置动态添加链路对象
    public ShopChainContext addChain(AbstractChainService chainService) {
        ratifies.add(chainService);
        return this;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        init();
    }


    //外部化启动链路
    public ChainResult execute(ChainParam request) {
        ArrayList<AbstractChainService> arrayList = new ArrayList<>();
        List<AbstractChainService> shopRatifies = channelMap.get(request.getChannelType());

        arrayList.addAll(ratifies);

        //TODO spring初始化链路对象
        arrayList.addAll(shopRatifies);
        ShopChain realChain = new ShopRealChain(arrayList, request, request.getChainIndex());
        return realChain.proceed(request);
    }


    public Class getInstance(String ChainType) {
        return chainMap.get(ChainType);
    }

    public void init() {

        //spring工具类，可以获取指定路径下的全部类
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        try {

            Resource[] resources = new Resource[0];
            for (String url : chainProperties.getStrategy().getUrl()) {
                String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                        ClassUtils.convertClassNameToResourcePath(url) + chainProperties.getStrategy().getPattern();
                Resource[] resource = resourcePatternResolver.getResources(pattern);
                resources = ArrayUtils.addAll(resources, resource);
            }

            //MetadataReader 的工厂类
            MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
            for (Resource resource : resources) {
                //用于读取类信息
                MetadataReader reader = metadataReaderFactory.getMetadataReader(resource);
                //扫描到的class
                String classname = reader.getClassMetadata().getClassName();
                Class<?> clazz = Class.forName(classname);
                //判断是否有指定主解
                Chain annotation = clazz.getAnnotation(Chain.class);
                if (annotation != null) {
                    //将注解中的类型值作为key，对应的类作为 value
                    chainMap.put(annotation.value(), clazz);
                }
            }

            Map<String, List<String>> chain = chainProperties.getChain();
            for (Map.Entry<String, List<String>> chanEntry : chain.entrySet()) {
                List<AbstractChainService> classes = null;
                if (channelMap.get(chanEntry.getKey()) == null) {
                    classes = new LinkedList<>();
                }
                for (String className : chanEntry.getValue()) {
                    AbstractChainService abstractChainService = springContextUtils.getInstance(className);
                    classes.add(abstractChainService);
                }
                channelMap.put(chanEntry.getKey(), classes);
            }
        } catch (IOException | ClassNotFoundException e) {
        }
    }

    public List<AbstractChainService> getChannelMapList(String channelName){
        return  this.channelMap.get(channelName);
    }

}
