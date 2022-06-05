//Time Complexity -
//Space Complexity - 

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class NestedIterator implements Iterator<Integer> {

    Stack<Iterator<NestedInteger>> stack;
    NestedInteger nextElement;


    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextElement.getInteger();
    }

    @Override
    public boolean hasNext() {

        while(!stack.isEmpty()){

            if(!stack.peek().hasNext()){
                stack.pop();
            }else if((nextElement = stack.peek().next()).isInteger()){
                return true;
            }else{
                stack.push(nextElement.getList().iterator());
            }
        }

        return false;
    }
}