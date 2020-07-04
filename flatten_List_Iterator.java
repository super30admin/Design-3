
//Time Complexity : O(1)
//Space Complexity: O(N)
import java.util.*;

public class NestedIterator implements Iterator<Integer> {
	NestedInteger nextEl;
	Stack<Iterator<NestedInteger>> st;

	public NestedIterator(List<NestedInteger> nestedList) {
		st = new Stack<>();
		st.push(nestedList.iterator());
	}

	@Override
	public Integer next() {
		return nextEl.getInteger();
	}

	@Override
	public boolean hasNext() {
		while (!st.isEmpty()) {
			if (!st.peek().hasNext()) {
				st.pop();
			} else if ((nextEl = st.peek().next()).isInteger()) {
				return true;
			} else {
				st.push(nextEl.getList().iterator());
			}
		}
		return false;
	}
}