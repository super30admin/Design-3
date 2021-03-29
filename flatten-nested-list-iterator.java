// Time Complexity :
hasNext() and next() has O(1) Complexity
and creating the iterator using addAll method has O(Total No. of elements in the nestedList )

// Space Complexity :
O(Total No. of elements in the nestedList) as we are using Queue.
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach


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
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

//hasnext() tells if there is next element or not, and next() gives the next element available
public class NestedIterator implements Iterator<Integer> {
  // Create a queue which holds integer as next() method returns Integer
    Queue<Integer> queue;
    public NestedIterator(List<NestedInteger> nestedList) {
        queue = new LinkedList<>();
        //add all elements of List of nexted integers i.e. nestedList into queue
        addAll(nestedList);
    }
    
    private void addAll(List<NestedInteger> nestedList){
        for(NestedInteger i : nestedList ){
            if(i.isInteger()){
                queue.offer(i.getInteger());
            }
            else{
                //if it is a list
                addAll(i.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return !queue.isEmpty() ? queue.poll() : -1 ;
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty() ;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */