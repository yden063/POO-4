import java.util.Queue;

public class BaseFilter extends AbstractFilter {

	public BaseFilter(Queue<Character> queue) {
		super(queue);
	}

	@Override
	protected Queue<Character> getQueue() {
		return this.queue;
	}

}
