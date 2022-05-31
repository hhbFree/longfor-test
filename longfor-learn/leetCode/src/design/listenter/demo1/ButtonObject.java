package design.listenter.demo1;

/**
 * 事件对象也就是按钮对象ButtonObject
 * @author wq
 *
 */
public class ButtonObject {
	private String buttoNname = null;
	public ButtonObject(String buttoNname){
		this.buttoNname = buttoNname;
	}
	public String getButtonName(){
		return buttoNname;
	}
	/**
	 * 按钮被点击的方法
	 */
	public void clickButton(){
		System.out.println(buttoNname+"被点击了一下");
	}
}