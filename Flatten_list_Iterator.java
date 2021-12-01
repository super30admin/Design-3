// Time Complexity : O(1)
// Space Complexity : O(N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

public class NestedIterator implements Iterator<Integer> {
    
    NestedInteger nextEl;
    Stack<Iterator<NestedInteger>> stack;
    public NestedIterator(List<NestedInteger> nestedList) {
         stack=new Stack<>();
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty()){
            if(!stack.peek().hasNext()){
                stack.pop();
            }else if((nextEl=stack.peek().next()).isInteger()){
                  return true;
            }else{
                stack.push(nextEl.getList().iterator());
            }
        } 
        
        return false;
    }
}
