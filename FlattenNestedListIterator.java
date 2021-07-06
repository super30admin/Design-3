import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class FlattenNestedListIterator {

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

        Queue<Integer> q;

        public NestedIterator(List<NestedInteger> nestedList) {
            q = new LinkedList<>();
            dfs(nestedList);
        }

        private void dfs(List<NestedInteger> nestedList) {
            for (NestedInteger ele : nestedList) {

                if (ele.isInteger()) {
                    q.add(ele.getInteger());
                } else
                    dfs(ele.getList());
            }
        }

        @Override
        public Integer next() {
            return q.poll();

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

}
