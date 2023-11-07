// Time Complexity : O(depth of nested lists)
// Space Complexity : O(depth of nested lists)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None

public class FlattenNestedlistIterator {
    public class NestedIterator implements Iterator<Integer> {

        Stack<Iterator<NestedInteger>> stack;
        NestedInteger nextEle;
        public NestedIterator(List<NestedInteger> nestedList) {
            this.stack = new Stack<>();
            nextEle = null;
            stack.push(nestedList.iterator());
        }

        @Override
        public Integer next() {
            return nextEle.getInteger();
        }

        @Override
        public boolean hasNext() {
            while(!stack.isEmpty()){
                if(!stack.peek().hasNext()){
                    stack.pop();
                }
                else if((nextEle = stack.peek().next()).isInteger()){
                    return true;
                }
                else{
                    stack.push(nextEle.getList().iterator());
                }
            }
            return false;
        }
    }
}
