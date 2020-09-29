
// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : YES
// Any problem you faced while coding this :
// 	not a intuitive solution

// Your code here along with comments explaining your approach
public class NestedIterator implements Iterator<Integer> {
    private LinkedList<Integer> list = new LinkedList<>();

    public NestedIterator(List<NestedInteger> nestedList) {
        traversalList(nestedList);
    }

    public void traversalList(List<NestedInteger> nestedList) {
        for (NestedInteger item :nestedList) {
            if (item.isInteger()) {
                list.addLast(item.getInteger());
            } else {
                traversalList(item.getList());
            }
        }
    }

    @Override
    public boolean hasNext() {
        return !list.isEmpty();
    }

    @Override
    public Integer next() {
        return list.pollFirst();
    }
}
