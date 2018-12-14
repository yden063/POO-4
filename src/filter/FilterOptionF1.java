package filter;
import java.util.ArrayDeque;
import java.util.Queue;

public class FilterOptionF1 extends FilterOption {

	public FilterOptionF1(AbstractFilter filter) {
		super(filter);
	}

	@Override
	public Queue<Character> getQueue() {
		Queue<Character> queueCopy = new ArrayDeque<Character>();
		queueCopy.addAll(filter.getQueue());

		ArrayDeque<Character> tmpDequeue = new ArrayDeque<>();

		queueCopy.forEach(elm -> {
			if (elm == '1') {
				tmpDequeue.add('D');
			} else if (elm == '0') {
				tmpDequeue.add('N');
			}
		});
		
		return tmpDequeue;
	}

}
