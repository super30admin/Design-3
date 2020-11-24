import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class NestedIterator implements Iterator<Integer> {

	Stack<Iterator<NestedInteger>> st;
	NestedInteger nextEle;

	public NestedIterator(List<NestedInteger> nestedList) {
		st = new Stack<Iterator<NestedInteger>>();
		st.push(nestedList.iterator());
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		while (!st.isEmpty()) {

			if (!st.peek().hasNext()) {
				st.pop();
			} else if ((nextEle = st.peek().next()).isInteger()) {
				return true;
			} else {
				st.push(nextEle.getList().iterator());
			}

		}

		return false;
	}

	@Override
	public Integer next() {
		// TODO Auto-generated method stub
		return nextEle.getInteger();
	}

	public static void main(String[] args) {

		List<NestedInteger> nestedList = new LinkedList<NestedInteger>();

		NestedIterator i = new NestedIterator(nestedList);
		while (i.hasNext())
			v[f()] = i.next();
	}

}
