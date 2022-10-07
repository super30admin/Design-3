/*
Constructor
Time Complexity: O(N+L)
Space Complexity:O (N+D)
hasnext,next
Time Complexity: O(1)
Space Complexity: O(N+D)
*/
public class NestedIterator implements Iterator<Integer> {
    Stack<NestedInteger> stack;
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        initialize(nestedList);
    }

    @Override
    public Integer next() {
        if(!hasNext()){
            return null;
        }
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty() && !stack.peek().isInteger()){
            List<NestedInteger> temp = stack.pop().getList();
            initialize(temp);
        }
        return !stack.isEmpty();
    }
    
    public void initialize(List<NestedInteger> nestedList){
        int curr = nestedList.size()-1;
        while(curr>-1){
            stack.push(nestedList.get(curr));
            curr--;
        }
    }
}