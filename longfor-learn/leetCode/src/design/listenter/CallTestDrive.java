package design.listenter;

//回调测试
public class CallTestDrive {
	public static void main(String args[]) {
		Caller call = new Caller();

		//第二种写法
		ICallBack callBack = new ICallBack() {
			@Override
			public void callBack() {
				// TODO Auto-generated method stub
				System.out.println("回调函数回调成功!");
			}
		};
//		call.call(callBack);








		//第一种写法
//		call.call(new ICallBack() {
//			
//			@Override
//			public void callBack() {
//				// TODO Auto-generated method stub
//				System.out.println("回调函数调用成功!");
//			}
//		});
		

		
		/*
		 第三种写法，实现这个ICallBack接口类
		 class CallBackC implements ICallBack{
		 	public void callBack(){
		 	System.out.println("回调函数回调成功!");
		 	}
		 }
		 call.call(new CallBackC());
		 */
	}
}
