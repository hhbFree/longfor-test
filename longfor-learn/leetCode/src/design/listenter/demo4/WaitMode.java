package design.listenter.demo4;

/**
 * 
 * <b>描述: </b>等待的状态
 * 
 * @author Lv.Mingwei
 * 
 */
enum WaitMode {

	WAIT_ALL(1), WAIT_ANY(2);

	int value = 0;

	private WaitMode(int value) {
		this.value = value;
	}

	public int intValue() {
		return value;
	}
}