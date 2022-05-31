package design.listenter.demo4;


/**
 * 在Future中使用的Listener
 * Created by Coral
 *
 * @param <V>
 */
public interface FutureListener<V> {
	void run(Result<V> result);
}
