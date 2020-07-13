/**
 * Time Complexity : O(n) where n is the number of NestedIntegers. next() -> O(n), hasNext() -> O(n)
 * Space Complexity : O(n) where n is the NestedIntegers in the Stack space
 */

import java.util.*;

interface NestedInteger {
  
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
         public boolean isInteger();
    
         // @return the single integer that this NestedInteger holds, if it holds a single integer
         // Return null if this NestedInteger holds a nested list
         public Integer getInteger();
    
         // @return the nested list that this NestedInteger holds, if it holds a nested list
         // Return null if this NestedInteger holds a single integer
         public List<NestedInteger> getList();
     }

// Stack Solution
public class FlattenNestedListIterator implements Iterator<Integer> {
    Stack<ListIterator<NestedInteger>> stk;
    public FlattenNestedListIterator(List<NestedInteger> nestedList) {
        stk = new Stack<>();
        stk.push(nestedList.listIterator());                                        // Push the whole list into the stack
    }

    @Override
    public Integer next() {
        hasNext();                                                                  // Check if it has next
        return stk.peek().next().getInteger();                                      // Return the integer next is pointing to
    }

    @Override
    public boolean hasNext() {  
       while(!stk.empty())                                                                  // While the stack is not empty
       {
           if(!stk.peek().hasNext()){
               stk.pop();                                                           // Pop if there is no next() exists
           } else {
               NestedInteger x = stk.peek().next();
               if(x.isInteger()){                                                   // if next element is an integer
                   return stk.peek().previous() == x;                               // return the boolean such that the next pointer's previous is x
               } else { 
                   stk.push(x.getList().listIterator());                        // Elsewise push the list of nested integer to the stack
               }
           }
       }
        return false;   
       }    
}

// Queue Solution
/*
public class FlattenNestedListIterator implements Iterator<Integer> {
    Queue<Integer> q;
    public FlattenNestedListIterator(List<NestedInteger> nestedList) {
        q = new LinkedList<>();
        flatten(nestedList);
    }

    public void flatten(List<NestedInteger> nestedList){
        for(NestedInteger n: nestedList){                                               // Get all the nested integers in the queue
        if(n.isInteger()){
        q.add(n.getInteger());                              
        } else {
            flatten(n.getList());                                                       // Get the list and parse over the list of nested integers recursively
        }
        }
    }

    @Override
    public Integer next() {
        return q.poll();                                                                // Pop the element from queue
    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty();                                                           // If the queue is not empty, return true
    }
}
*/