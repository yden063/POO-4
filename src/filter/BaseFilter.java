package filter;
import java.util.Queue;

public class BaseFilter extends AbstractFilter {

	public BaseFilter(Queue<Character> queue) {
		super(queue);
	}

	@Override
	public Queue<Character> getQueue() {
		return this.queue;
	}

}
