//Time Complexity :O(N)
//Space Complexity :O(1)
//Did this code successfully run on Leetcode :yes
//Any problem you faced while coding this :When trying to update the pointer at hasNext() i felt little difficult;

public class NestedIterator implements Iterator<Integer> {

    NestedInteger nextNestedInteger;
    Stack<Iterator<NestedInteger>> stack;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        stack.push(nestedList.iterator());
        nextNestedInteger = null;
    }

    @Override
    public Integer next() {
        return nextNestedInteger.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty()){
            if(!stack.peek().hasNext()){stack.pop();}
            else if((nextNestedInteger = stack.peek().next()).isInteger()){
                return true;
            }else{
                stack.push(nextNestedInteger.getList().iterator());
            }
        }
        return false;
    }
}