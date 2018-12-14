import java.util.ArrayDeque;
import java.util.Queue;

public class FilterOptionF2 extends FilterOption {

	public FilterOptionF2(AbstractFilter filter) {
		super(filter);
	}

	@Override
	protected Queue<Character> getQueue() {
		ArrayDeque<Character> tmpDequeue = new ArrayDeque<Character>();

		Queue<Character> queueCopy = new ArrayDeque<Character>();
		queueCopy.addAll(filter.getQueue());

		tmpDequeue.add(queueCopy.remove());

		char character = tmpDequeue.peek();

		while (queueCopy.size() > 0) {
			if (queueCopy.peek() != character) {
				character = queueCopy.peek();
				tmpDequeue.add(queueCopy.remove());
			} else {
				queueCopy.remove();
			}
		}

		return tmpDequeue;
	}
}
