package design.listenter.demo2;

/**
 * 处理Module的
 * Created by Coral on 7/13/15.
 */
public final class ModuleState {

	private Result result;
	private boolean completed;
	private Action<Result> listener;

	private ModuleState(boolean completed, boolean isTerminated, Throwable error) {
		this.result = new Result(isTerminated, error);
		this.completed = completed;
	}

	private ModuleState() {
		this.completed = false;
	}

	public static ModuleState newCompleted() {
		return new ModuleState(true, false, null);
	}

	public static ModuleState newTerminated(Throwable error) {
		return new ModuleState(true, true, error);
	}

	public static ModuleState newAsync() {
		return new ModuleState();
	}

	public void complete() {
		boolean runListener = false;
		synchronized (this) {
			if (!completed) {
				this.result = new Result(false, null);
				completed = true;
				if (listener != null) {
					runListener = true;
				}
			} else {
				throw new IllegalStateException("Already Completed");
			}
		}
		if (runListener) {
			runListener();
		}
	}

	public void terminate(Throwable error) {
		boolean runListener = false;
		synchronized (this) {
			if (!completed) {
				this.result = new Result(true, error);
				completed = true;
				if (listener != null) {
					runListener = true;
				}
			} else {
				throw new IllegalStateException("Already Completed");
			}
		}
		if (runListener) {
			runListener();
		}
	}

	void setListener(Action<Result> listener) {
		boolean runListener = false;
		synchronized (this) {
			if (this.listener == null) {
				this.listener = listener;
				if (completed) {
					runListener = true;
				}
			} else {
				throw new IllegalArgumentException("listener already set");
			}
		}
		if (runListener) {
			runListener();
		}
	}

	private void runListener() {
		try {
			listener.run(this.result);
		} catch (Exception ex) {
		}
	}

	public boolean isTerminated() {
		if (result == null) {
			return false;
		} else {
			return result.isTerminated;
		}
	}

	static class Result {
		boolean isTerminated;
		Throwable error;
		Result(boolean isTerminated, Throwable error) {
			this.isTerminated = isTerminated;
			this.error = error;
		}
	}
}
