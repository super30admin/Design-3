import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NestedIterator implements Iterator<Integer> {

	Queue<Integer> q;

	public NestedIterator(List<NestedInteger> nestedList) {
		q = new LinkedList<>();
		flatten(nestedList);

	}

	@Override
	public Integer next() {
		return q.poll();
	}

	@Override
	public boolean hasNext() {
		return !q.isEmpty();
	}

	private void flatten(List<NestedInteger> nestedList) {
		for (NestedInteger ele : nestedList) {
			if (ele.isInteger()) {
				q.add(ele.getInteger());
			} else {
				flatten(ele.getList());

			}

		}

	}

}
