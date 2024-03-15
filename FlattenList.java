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

// Time Complexity : O(1), O(d) itself but amortized O(1)
// Space Complexity :O(d), stack will contain d number of levels, d = depth of the nested lists.
// Did this code successfully run on Leetcode : yes
// Three line explanation of solution in plain english : used inbuilt iterartors to achieve the dynamic nature i.e. lazy loading.


 import java.util.NoSuchElementException;

 public class NestedIterator implements Iterator<Integer> {
     Stack<Iterator<NestedInteger>> st;
     NestedInteger nextEl;
     public NestedIterator(List<NestedInteger> nestedList) {
         this.st = new Stack<>();
         st.push(nestedList.iterator());
     }
 
     @Override
     public Integer next() {
         // if(!hasNext()){
         //     throw new NoSuchElementException();
             
         // }
 
         return nextEl.getInteger();
         
     }
 
     @Override
     public boolean hasNext() {
         while(!st.isEmpty()){
             if(!st.peek().hasNext()){
                 st.pop(); // we finished processing the level, pop it.
             }
             //is Integer
             else if((nextEl = st.peek().next()).isInteger()){ // we store it in nextEl because next() moves the pointer ahead
                 return true;
             }
             //isList
             else{
                 st.push(nextEl.getList().iterator());// push inner list iterator in stack
             }
         }
         return false;
     }
 }
 
 /**
  * Your NestedIterator object will be instantiated and called as such:
  * NestedIterator i = new NestedIterator(nestedList);
  * while (i.hasNext()) v[f()] = i.next();
  */