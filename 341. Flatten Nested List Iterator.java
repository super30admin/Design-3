//N no of integers M no of nested list D 5 [[[[[1]]]]]
public class NestedIterator implements Iterator<Integer> {
    Stack<NestedInteger> stack;

    public NestedIterator(List<NestedInteger> nestedList) { // O(N + M)
        stack = new Stack<NestedInteger>();
        prepareStack(nestedList);
        
    }

    @Override
    public Integer next() {
        Integer val = null ;
        if(hasNext()){
            val = stack.pop().getInteger() ;
        }
        return val;
    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty() && !stack.peek().isInteger()){
            List<NestedInteger> temp = stack.pop().getList();
            prepareStack(temp);
        }
        return !stack.isEmpty() ;
    }
    private void prepareStack(List<NestedInteger> nestedList){
        for(int i = nestedList.size() -1 ; i>=0 ;i--){
            stack.push(nestedList.get(i));
        }
    }
}