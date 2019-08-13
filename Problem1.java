// Time Complexity : O(n)  , since traversing all the elements recursively ( recursive stack is neglected)
// Space Complexity :  O(n) , since storing all the elem in Queue.

// Store all the element in the Queue and used all the function os NestedInteger.

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
    
    Queue<Integer> q = new LinkedList<Integer>();

    public NestedIterator(List<NestedInteger> nestedList) {
        fillQueue(nestedList);
    }

    @Override
    public Integer next() {
        return q.poll();
    }

    @Override
    public boolean hasNext() {
         if(q.isEmpty()) return false;
        return true;

    }
    
    public void fillQueue(List<NestedInteger> nestList){
        if(nestList == null) return;
            
        for(NestedInteger li : nestList){
            if(li.isInteger()) q.offer(li.getInteger());
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
