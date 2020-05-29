// Time Complexity : O(n) where n is the number of NestedIntegers. next() -> O(n), hasNext() -> O(n)
// Space Complexity : O(n) where n is the NestedIntegers in the Stack space
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Coming up wit the Stack solution is challenging
/* Algorithm: Introduce a stack of list of iterator. Whenever next() exists for the list iterator, check if it is a list of nested integers or is a
nested integer. If the stack is not empty, check where the next pointer has reached till now, if it has finished iterating over the nested integer list
start popping from the stack till it has a next. Elsewise, check if x is a list or an integer, and return the boolean accordingly. If it is a list 
of nested integer, push the list to the stack as a list of iterator and iterate over the list from start to end. next() will always return the integer it is
pointing to.  
*/
// Stack Solution
public class NestedIterator implements Iterator<Integer> {
    Stack<ListIterator<NestedInteger>> stk;
    public NestedIterator(List<NestedInteger> nestedList) {
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
public class NestedIterator implements Iterator<Integer> {
    Queue<Integer> q;
    public NestedIterator(List<NestedInteger> nestedList) {
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