
// Time Complexity : O(n) 
// Space Complexity : o(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
public class NestedIterator implements Iterator<Integer> {

    Queue<Integer> queue;

    public NestedIterator(List<NestedInteger> nestedList) {
        queue = new LinkedList();
        recurr(nestedList);
    }

    @Override
    public Integer next() {

        return queue.poll();

    }

    @Override
    public boolean hasNext() {

        return !queue.isEmpty();

    }

    public void recurr(List<NestedInteger> nestedList) {
        for (NestedInteger item : nestedList) {
            if (item.isInteger()) {
                queue.add(item.getInteger());
            } else {
                recurr(item.getList());
            }
        }
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList); while (i.hasNext()) v[f()]
 * = i.next();
 */