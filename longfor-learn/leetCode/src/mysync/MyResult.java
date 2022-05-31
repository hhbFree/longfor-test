package mysync;

import design.listenter.demo4.Result;

/**
 * @Description:
 * @Author: hbHao
 * @Date: 2020/8/17 20:30
 */
public class MyResult<V> {

    private V v;

    private Exception e;

    public MyResult(V v, Exception e) {
        this.v = v;
        this.e = e;
    }

    private V get() throws Exception {
        if(this.e!=null){
            throw e;
        }
        return v;
    }

    private V getValue(){
        return this.v;
    }

    private boolean isError(){
        return this.e!=null;
    }



    public static <V> MyResult<V> createWithValue(V v){
        return new MyResult<V>(v,null);
    }

    public static <V> MyResult<V> createWithError(Exception e) {
        if (e == null) {
            throw new IllegalArgumentException("createWithError error can't be null");
        }
        return new MyResult<V>(null, e);
    }

    @Override
    public String toString() {
        if (e != null) {
            return e.getMessage();
        } else {
            if (v == null) {
                return "null";
            } else {
                return v.toString();
            }
        }
    }
}
