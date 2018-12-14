package filter;
import java.util.ArrayDeque;
import java.util.Queue;

public class FilterOptionF3 extends FilterOption {
	public FilterOptionF3(AbstractFilter filter) {
		super(filter);
	}

	@Override
	public Queue<Character> getQueue() {
		Queue<Character> tmpDequeue = new ArrayDeque<Character>();

		Queue<Character> queueCopy = new ArrayDeque<Character>();
		queueCopy.addAll(filter.getQueue());

		while (queueCopy.size() > 1) {
			queueCopy.remove();
		}

		tmpDequeue = queueCopy;

		return tmpDequeue;
	}
}
