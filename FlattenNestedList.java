/*
TC: 
Constructor: O(N + L) ; N - number of integers, L - unumber of lists in nestedList.
updateStack: O(L / N) or O(1) if top of stack is integer : O(1), else we need to call update until top has an integer.
which is O(N + L)/N.  
next(): O(L/N) or O(1) if top has integer: O(1) else updateStack will be called. 
hasNext(): O(L/N) or O(1)

SC: 
Stack: O(N + L)

*/

import java.util.*;
public class FlattenNestedList {
    
    Stack<NestedInteger> stk = new Stack<>();
    public FlattenNestedList(List<NestedInteger> nestedList){
        updateStack(nestedList);
    }

    public Integer next(){
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        return stk.pop().getInteger();
    }

    public boolean hasNext(){

        while(!stk.isEmpty() && !stk.peek().isInteger()){
            List<NestedInteger> list = stk.pop().getList();
            updateStack(list);
        }
        return !stk.isEmpty();
    }

    private void updateStack(List<NestedInteger> nestedList){
        for(int i = nestedList.size() - 1; i >= 0; i--){
            stk.push(nestedList.get(i));
        }
    }

}
