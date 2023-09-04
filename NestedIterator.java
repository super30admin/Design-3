// Time Complexity = O(n)
// Space Complexity = O(n)
// Did this code successfully run on Leetcode : yes

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
import java.util.Iterator;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;

public class NestedIterator implements Iterator<Integer> {

   // Queue to store the flattened list of integers.
   private Queue<Integer> flattenedQueue = new LinkedList<>();

    // Constrcutor 
   public NestedIterator(List<NestedInteger> nestedList) {
       flattenList(nestedList);
   }

    // Returns the next integer in the flattened list.
   @Override
   public Integer next() {
       return flattenedQueue.poll();
   }

   // Checks if there are more integers to iterate over, returnstrue if there are more integers, false otherwise
   @Override
   public boolean hasNext() {
       return !flattenedQueue.isEmpty();
   }

   // method to flatten list
   private void flattenList(List<NestedInteger> nestedList) {
       if (nestedList == null) return;
       for (NestedInteger item : nestedList) {
           if (item.isInteger()) {
               flattenedQueue.offer(item.getInteger()); // If an item is an integer, it is added to the queue.
           } else {
               flattenList(item.getList()); //If it's a list, this method is called recursively on that list.
           }
       }
   }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */