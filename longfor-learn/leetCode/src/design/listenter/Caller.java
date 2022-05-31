package design.listenter;

//回调者(用于调用回调函数的类)
public class Caller {
	public void call(ICallBack callBack) {
		System.out.println("Start...");
		callBack.callBack();
		System.out.println("End...");
	}
}
