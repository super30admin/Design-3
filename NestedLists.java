/*
Author: Akhilesh Borgaonkar
Problem: Given a nested list of integers, implement an iterator to flatten it.
Approach: Implemented an Iterator which iterates over a Queue holding flattened list of nested lists of integers. The approach used here is 
    recursive. If a nested list is identified while generating the flattened list, the method fillQueue() is called recursively on the
    inner nested list.
Time Complexity: O(n) - where n is number of integers in nested list (for fillQueue() method)
Space Complexity: O(n) - where n is number of integers in nested list
Issue: Will be trying to optimize it by dropping the use of storage queue as it is an overhead while trying to iterate over large lists.
*/

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
public class NestedIterator implements Iterator<Integer> {

    Queue<Integer> queue = new LinkedList<>();
    public NestedIterator(List<NestedInteger> nestedList) {
        fillQueue(nestedList);
    }
    
    private void fillQueue(List<NestedInteger> nestedList){
        for(NestedInteger nest : nestedList){
            if(nest.isInteger())
                queue.offer(nest.getInteger());
            else
                fillQueue(nest.getList());
        }
    }

    @Override
    public Integer next() {
        return queue.poll();
    }

    @Override
    public boolean hasNext() {
        return queue.size()>0;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */