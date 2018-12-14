import java.util.Queue;

public abstract class AbstractFilter {
	
	Queue<Character> queue;
	
	public AbstractFilter(Queue<Character> queue) {
		this.queue = queue;
	}
	
	protected abstract Queue<Character> getQueue();
}
