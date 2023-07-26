// Time Complexity : O(1)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
interface NestedInteger {
    // @return true if this NestedInteger holds a single integer, rather than a
    // nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a
    // single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested
    // list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}

class NestedIterator implements Iterator<Integer> {
    Queue<Integer> q = new LinkedList<>();

    public NestedIterator(List<NestedInteger> nestedList) {
        fillQueue(nestedList);
    }

    @Override
    public Integer next() {
        return q.poll();
    }

    @Override
    public boolean hasNext() {
        if (q.isEmpty())
            return false;
        return true;
    }

    public void fillQueue(List<NestedInteger> nestList) {
        if (nestList == null)
            return;
        for (NestedInteger li : nestList) {
            if (li.isInteger())
                q.offer(li.getInteger());
            else
                fillQueue(li.getList());
        }
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */