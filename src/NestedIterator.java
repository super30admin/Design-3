import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * // This is the interface that allows for creating nested lists. // You should
 * not implement it, or speculate about its implementation public interface
 * NestedInteger {
 *
 * // @return true if this NestedInteger holds a single integer, rather than a
 * nested list. public boolean isInteger();
 *
 * // @return the single integer that this NestedInteger holds, if it holds a
 * single integer // Return null if this NestedInteger holds a nested list
 * public Integer getInteger();
 *
 * // @return the nested list that this NestedInteger holds, if it holds a
 * nested list // Return empty list if this NestedInteger holds a single integer
 * public List<NestedInteger> getList(); }
 */
class NestedIterator implements Iterator<Integer> {
	List<Integer> list = new ArrayList<>();
	int position = 0;

	public NestedIterator(List<NestedInteger> nestedList) {
		flattenList(nestedList);
	}

	/**
	 * Apply DFS by checking if it is an integer or a list. Take a global list to
	 * push all the elements. TC: O(length of the list) SC: O(length of the list)
	 */
	public void flattenList(List<NestedInteger> nestedList) {
		for (NestedInteger i : nestedList) {
			if (i.isInteger()) {
				list.add(i.getInteger());
			} else {
				flattenList(i.getList());
			}
		}
	}

	@Override
	public Integer next() {
		if (hasNext())
			return list.get(position++);
		return null;
	}

	@Override
	public boolean hasNext() {
		return position < list.size();
	}
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList); while (i.hasNext()) v[f()]
 * = i.next();
 */