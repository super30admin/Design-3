// Time Complexity: next() -> O(1); hasNext() -> O(max number of nestings in the list)
// Space Complexity: O(max number of nestings in the list)
// Run on Leetcode: Yes
// Issues: I initially made a stack of List<NestedInteger> and then I was getting ith element from the list and incrementing i and so on. 

public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> stack;
    NestedInteger nextEl;
    int i = 0;
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty()){
            if(!stack.peek().hasNext())
                stack.pop();
            else if((nextEl = stack.peek().next()).isInteger()){
                return true;
            }
            else{
                stack.push(nextEl.getList().iterator());
            }
        }
        return false;
    }
}
