

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NestedIterator implements Iterator<Integer> {

	List<Integer> list = new ArrayList<>();
	Queue<Integer> queue = new LinkedList<>();

	int count;

	public NestedIterator(List<NestedInteger> nestedList) {
		count = 0;
		this.dfs(nestedList);
	}

	private void dfs(List<NestedInteger> li) {
		for (NestedInteger integer : li) {
			if (integer.isInteger()) {
				list.add(integer.getInteger());
				// queue.add(integer.getInteger());
			} else {
				dfs(integer.getList());
			}
		}
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		// return !queue.isEmpty();
		return count < list.size();
	}

	@Override
	public Integer next() {
		// TODO Auto-generated method stub
		// return queue.poll();
		return list.get(count++);
	}

}