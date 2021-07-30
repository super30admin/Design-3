// Time Complexity : O(1)
// Space Complexity : O(N + D) N --> List of integers and D is depth of tree for recursive stack
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach


/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 * <p>
 * // @return true if this NestedInteger holds a single integer, rather than a nested list.
 * public boolean isInteger();
 * <p>
 * // @return the single integer that this NestedInteger holds, if it holds a single integer
 * // Return null if this NestedInteger holds a nested list
 * public Integer getInteger();
 * <p>
 * // @return the nested list that this NestedInteger holds, if it holds a nested list
 * // Return empty list if this NestedInteger holds a single integer
 * public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    List<Integer> res = new ArrayList<>();
    private int index = 0;

    public NestedIterator(List<NestedInteger> nestedList) {
        flattenList(nestedList);
    }

    // Recursively unpacks a nested list in DFS order
    private void flattenList(List<NestedInteger> nestedList) {
        for (NestedInteger nestedInt : nestedList) {
            if (nestedInt.isInteger())
                res.add(nestedInt.getInteger());
            else
                flattenList(nestedInt.getList());
        }
    }


    @Override
    public Integer next() {
        if (hasNext())
            return res.get(index++);
        return -1;
    }

    @Override
    public boolean hasNext() {
        return index < res.size();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */