import java.util.Iterator;
import java.util.List;
import java.util.Stack;
//Time Complexity : worst O(H), average O(1) where H is the number of nested lists
//Space Complexity :O(H) where H is the number of nested lists
//Did this code successfully run on Leet code :Yes
//Any problem you faced while coding this :
public class NestedIterator implements Iterator<Integer> {

    Stack<Iterator<NestedInteger>> stack ;
    NestedInteger nextElem=null;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
       return nextElem.getInteger();
    }

    @Override
    public boolean hasNext() {
       if(stack.isEmpty())
           return false;
        while(!stack.isEmpty()){
           if(!stack.peek().hasNext())
               stack.pop();
            else if((nextElem = stack.peek().next()).isInteger()){
                return true;
            }
            else{
                stack.push(nextElem.getList().iterator());
            }     
            
        }
        
        return false;
    }
}