// Time Complexity :  O(N)
// Space Complexity : O(N) 
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No


public class NestedIterator implements Iterator<Integer> {

    Stack<Iterator<NestedInteger>> stack;
    
    NestedInteger nextElement;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        this.stack = new Stack<>();
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
            }
            else if((nextElement = stack.peek().next()).isInteger()){ 
                // stack might become empty after previous pop, hence the else if
                return true;
            }
            else{
                stack.push(nextElement.getList().iterator());
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
/*
public class NestedIterator implements Iterator<Integer> {
    List<Integer> result;
    Iterator<Integer> it;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        result = new ArrayList<>();
        flatten(nestedList);
        it = result.iterator();
    }
    
    private void flatten(List<NestedInteger> nestedList){
        for(NestedInteger i : nestedList){
            if(i.isInteger()){
                result.add(i.getInteger());
            }else{
                flatten(i.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return it.next();
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }
}
*/