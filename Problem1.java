// Time Complexity : O(1) for next and O(L) for hasNext where L is the level of nesting
// Space Complexity : O(1) for next and O(L) for hasNext where L is the level of nesting
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

public class Problem1 {
    class NestedIterator implements Iterator<Integer> {
        Stack<Iterator<NestedInteger>> s;
        NestedInteger nextEl;
        public NestedIterator(List<NestedInteger> nestedList) {
            s = new Stack<>();
            s.push(nestedList.iterator());
        }

        @Override
        public Integer next() {
            return nextEl.getInteger();
        }

        @Override
        public boolean hasNext() {
            while(!s.isEmpty()){
                if(!s.peek().hasNext()){
                    s.pop();
                }else if((nextEl = s.peek().next()).isInteger()){
                    return true;
                }else{
                    s.push(nextEl.getList().iterator());
                }
            }

            return false;
        }
    }
}
