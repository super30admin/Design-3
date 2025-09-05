
// Time Complexity: O(1) for next() and O(d) for hasNext() where d is the depth of the nested list
// Space Complexity: O(d) where d is the depth of the nested list

// we use a stack to keep track of the iterators of the nested lists.
// In hasNext(), we check the top iterator of the stack. If it has no next element, we pop it from the stack.
// If it has a next element, we check if it's an integer. If it is, we return true. 
// If it's a list, we push its iterator onto the stack and continue the process.
// In next(), we simply return the integer stored in nextele.


public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> stk;
    NestedInteger nextele;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.stk=new Stack<>();
       stk.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextele.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!stk.isEmpty()){
            if(!stk.peek().hasNext())
                stk.pop();
            else{
                nextele=stk.peek().next();
                if(nextele.isInteger())
                    return true;
                else
                    stk.push(nextele.getList().iterator());
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