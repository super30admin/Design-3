// Time Complexity : O(N) iterating through all elements
// Space Complexity : O(H) where H is total number of iterators. We push H iterators in the stack.
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : After class solution


// Your code here along with comments explaining your approach

public class NestedIterator implements Iterator<Integer> {
   
    //stack contains only iterators
    Stack<Iterator<NestedInteger>> s;
    Integer cursor;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        
        s = new Stack<>();
        if(nestedList != null){
            s.push(nestedList.iterator());
        }
    }

    @Override
    public Integer next() {
       int value = cursor;
        cursor = null;
        return value;
    }

    @Override
    public boolean hasNext() {
        
        while(!s.isEmpty() && cursor == null){
            
            Iterator<NestedInteger> iter = s.peek();
            
            //iter exhausted, then pop from the stack
            if(iter.hasNext() == false){
                s.pop();
                continue;
            }
            
            NestedInteger nestedInteger = iter.next();
            
            //if stack holds an integer
            if(nestedInteger.isInteger()){
               cursor =  nestedInteger.getInteger();
                return true;
            }else{//if stack holds a list 
              s.push(nestedInteger.getList().iterator());     
            }
        }
        
        return false;
    }
}