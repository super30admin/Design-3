/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
// Time Complexity:O(Total No of Nodes in list)
// Space Complexity:O(Total No of Nodes in list)
public class NestedIterator implements Iterator<Integer> {

    Queue<Integer> q;

    public NestedIterator(List<NestedInteger> nestedList) {
        q = new LinkedList<>();
        addAll(nestedList);
    }

    private void addAll(List<NestedInteger> nestedList) {
        for(NestedInteger ni: nestedList) {
            if(ni.isInteger()) {
                q.add(ni.getInteger());
            } else {
                addAll(ni.getList());
            }
        }
    }

    @Override
    public Integer next() {
        if(!q.isEmpty()) {
            return q.poll();
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */