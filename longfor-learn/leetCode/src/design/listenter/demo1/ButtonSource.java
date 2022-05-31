package design.listenter.demo1;

import java.util.Vector;

/**
 * 事件源
 * @author wq
 *
 */
public class ButtonSource {
	private Vector<ButtonListener> buttonListenerList = new Vector<ButtonListener>();
	
	public void addButtonListener(ButtonListener buttonListener){
		buttonListenerList.add(buttonListener);
	}
	
	//接收外部按钮对象
	public void notifyListenerEvents(ButtonObject buttonObject){
		for (ButtonListener buttonListener : buttonListenerList) {
			buttonListener.listenerButtonClick(buttonObject);
		}
	}

	public void doubleClick(){
		this.notifyListenerEvents(new ButtonObject("双击事件按钮"));
	}

	public void singleClick(){
		this.notifyListenerEvents(new ButtonObject("单机事件按钮"));
	}


	public static void main(String[] args) {
		ButtonSource buttonSource = new ButtonSource();
		buttonSource.addButtonListener(new ButtonListener() {
			public void listenerButtonClick(ButtonObject buttonObject) {
				String buttonName = buttonObject.getButtonName();
				if(buttonName.equals("双击事件按钮")){
					System.out.println(buttonObject.getButtonName()+"我擦按钮要被点击了 而且是双击");
					buttonObject.clickButton();
					System.out.println(buttonObject.getButtonName()+"我擦按钮已经被点击了 而且是双击");
				}else{
					System.out.println(buttonObject.getButtonName()+"我擦按钮要被点击了");
					buttonObject.clickButton();
					System.out.println(buttonObject.getButtonName()+"我擦按钮已经被点击了");
				}
				
			}
		});
		buttonSource.notifyListenerEvents(new ButtonObject("button1"));
		buttonSource.doubleClick();
		buttonSource.singleClick();
	}
}