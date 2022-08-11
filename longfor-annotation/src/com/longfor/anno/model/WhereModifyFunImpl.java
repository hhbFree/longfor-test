package com.longfor.anno.model;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class WhereModifyFunImpl<M,V> implements WhereModifyFun<M,V> {
    @Override
    public Integer ex() {
        return null;
    }


    @Override
    public List<WhereModifyFun<M, V>> or() {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> unite(Consumer<WhereModifyFun<M, V>> r) {
        //规范返回值
        return null;
    }

    @Override
    public WhereModifyFun<M, V> unite(boolean condition, Consumer<WhereModifyFun<M, V>> r) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> condSQL(String sql) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> condSQL(boolean condition, String sql) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> condSQL(String sql, Map<String, Object> params) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> condSQL(boolean condition, String sql, Map<String, Object> params) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> eq(GetterFun<M, V> col, V value) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> eq(boolean condition, GetterFun<M, V> col, V value) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> ueq(GetterFun<M, V> col, V value) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> ueq(boolean condition, GetterFun<M, V> col, V value) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> gt(GetterFun<M, V> col, V value) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> gt(boolean condition, GetterFun<M, V> col, V value) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> lt(GetterFun<M, V> col, V value) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> lt(boolean condition, GetterFun<M, V> col, V value) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> gtEq(GetterFun<M, V> col, V value) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> gtEq(boolean condition, GetterFun<M, V> col, V value) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> ltEq(GetterFun<M, V> col, V value) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> ltEq(boolean condition, GetterFun<M, V> col, V value) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> between(GetterFun<M, V> col, V start, V end) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> between(boolean condition, GetterFun<M, V> col, V start, V end) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> notBetween(GetterFun<M, V> col, V start, V end) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> notBetween(boolean condition, GetterFun<M, V> col, V start, V end) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> like(GetterFun<M, V> col, V value) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> like(boolean condition, GetterFun<M, V> col, V value) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> likeLeft(GetterFun<M, V> col, V value) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> likeLeft(boolean condition, GetterFun<M, V> col, V value) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> likeRight(GetterFun<M, V> col, V value) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> likeRight(boolean condition, GetterFun<M, V> col, V value) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> in(GetterFun<M, V> col, V... values) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> in(boolean condition, GetterFun<M, V> col, V... values) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> in(GetterFun<M, V> col, Collection... values) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> in(boolean condition, GetterFun<M, V> col, Collection... values) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> notIn(GetterFun<M, V> col, V... values) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> notIn(boolean condition, GetterFun<M, V> col, V... values) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> notIn(GetterFun<M, V> col, Collection... values) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> notIn(boolean condition, GetterFun<M, V> col, Collection... values) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> isNull(GetterFun<M, V>... cols) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> isNull(boolean condition, GetterFun<M, V>... cols) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> isNotNull(GetterFun<M, V>... cols) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> isNotNull(boolean condition, GetterFun<M, V>... cols) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> exists(String existsSql) {
        return null;
    }

    @Override
    public WhereModifyFun<M, V> exists(boolean condition, String existsSql) {
        return null;
    }
}
