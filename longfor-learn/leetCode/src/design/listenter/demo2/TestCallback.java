package design.listenter.demo2;

class TestCallback {
	static void test(String str, Action action){
  		str = "say:"+str;//方法中进行信息加工
  		action.run(str);//将加工后的信息或结果传递给回调方法
  	}

	public static void main(String[] args) {
		TestCallback.test("hh", new Action() {
			@Override
			public void run(Object a) {
				System.out.println(a+"");
			}
		});
	}
}