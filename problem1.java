//Time: CONSTRUCTOR: O(n + l), NEXT(): O(1), HAS NEXT(): O(1); n -> # of integers, l -> # of lists
// Space: O(n + d), n -> Size of List of integers,d -> Size of recursive stack 
public class NestedIterator implements Iterator<Integer> {
	List<Integer> list;
	int curPos;

	public NestedIterator(List<NestedInteger> nestedList) {
		list = new ArrayList<Integer>();
		flattenList(nestedList);
		curPos = 0;
	}

	private void flattenList(List<NestedInteger> nestedList) {
		for (NestedInteger integer : nestedList) {
			if (integer.isInteger()) {
				list.add(integer.getInteger());
			} else {
				flattenList(integer.getList());
			}
		}
	}

	@Override
	public Integer next() {
		return list.get(curPos++);
	}

	@Override
	public boolean hasNext() {
		return curPos < list.size();
	}

}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */