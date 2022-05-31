package design.listenter.demo1;

/**
 * 监听按钮被点击接口 需要将按钮所属的对象传给监听接口
 * @author wq
 *
 */
public interface ButtonListener {
 
	public void listenerButtonClick(ButtonObject buttonObject);
}