package com.longfor.anno.model;



/**
 * @Description: where
 * @Auther: create by cmj on 2022/5/4 21:44
 */
public interface WhereModifyFun<M,V> extends  WhereFun<M, GetterFun<M,V>,V, WhereModifyFun<M,V>> , ExecutOneFun<Integer> {


}
