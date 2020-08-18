// There are two approaches
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :

import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

// Your code here along with comments explaining your approach
// Approach 1: Brute force
// 1. Add add element to queue if it is an integer.
// 2. Other wise do recursive call of the element.
// Time Complexity : O(n)
//      n: number of integer in the NestedInteger
//   For iterating nested integer and add into the queue 
// Space Complexity : O(n)
//      n: number of integer in the NestedInteger
//   For creating queue
class Problem1S1{
    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    public interface NestedInteger {
 
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
    }

    public class NestedIterator implements Iterator<Integer> {

        Queue<Integer> myQueue;
        public NestedIterator(List<NestedInteger> nestedList) {
            myQueue =  new LinkedList<>();
            flatten(nestedList);
        }
    
        @Override
        public Integer next() {
            return myQueue.poll();
        }
    
        @Override
        public boolean hasNext() {
            return !myQueue.isEmpty();
        }
        
        private void flatten(List<NestedInteger> nestedList){
            
            for(NestedInteger element: nestedList){
                if(element.isInteger())
                    myQueue.add(element.getInteger());
                else
                    flatten(element.getList());
            }
        }
    } 
}
// Your code here along with comments explaining your approach
// Approach 2: Using Stack
// Time Complexity : O(1)
// Space Complexity : O(d)
//      d: depth of NestedInteger
//   For creating stack
class Problem1S2{
   // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    public interface NestedInteger {
 
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();
    
        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();
    
        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }
    public class NestedIterator implements Iterator<Integer> {

        // stack
        Stack<Iterator<NestedInteger>> myStack;
        NestedInteger nextElement;

        // constructor
        public NestedIterator(List<NestedInteger> nestedList) {
            myStack = new Stack<>();
            // add as itertors
            myStack.push(nestedList.iterator());
        }
    
        @Override
        public Integer next() {
            return nextElement.getInteger();
        }
    
        @Override
        public boolean hasNext() {

            // not empty
            while(!myStack.isEmpty()){
                
                // already iterated
                if(!myStack.peek().hasNext()){
                    myStack.pop();
                // update next if it is type integer move
                }else if ((nextElement = myStack.peek().next()).isInteger()){
                    return true;
                // next element of type List add as itertors
                }else{
                    myStack.push(nextElement.getList().iterator());
                }
                
            }
            return false;
        }
    }
    
}
 

