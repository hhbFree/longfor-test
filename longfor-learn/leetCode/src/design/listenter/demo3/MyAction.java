package design.listenter.demo3;

/**
 * @Description:
 * @Author: hbHao
 * @Date: 2020/5/21 18:26
 */
public interface MyAction {
    public void success(String msg);

    public void fail(String msg);

    public void timeout(String msg);
}
