public class Problem1 {
    /**
     * // This is the interface that allows for creating nested lists.
     * // You should not implement it, or speculate about its implementation
     * public interface NestedInteger {
     *
     *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
     *     public boolean isInteger();
     *
     *     // @return the single integer that this NestedInteger holds, if it holds a single integer
     *     // Return null if this NestedInteger holds a nested list
     *     public Integer getInteger();
     *
     *     // @return the nested list that this NestedInteger holds, if it holds a nested list
     *     // Return empty list if this NestedInteger holds a single integer
     *     public List<NestedInteger> getList();
     * }
     */
    //t.c - >O(levels to get to integer)
        //s.c -> O(levels to get to integer) recursive stack space
    public class NestedIterator implements Iterator<Integer> {
        //this will be used for control recurssion
        Stack<Iterator<NestedInteger>> s;
        //this will have the next element at every point
        NestedInteger nextEl;
        public NestedIterator(List<NestedInteger> nestedList) {
            s = new Stack<>();
            //push the list iterator into the stack
            s.push(nestedList.iterator());//stack has whole nestedList terator value at this point
            nextEl = null;
        }

        @Override
        public Integer next() {
            //as we mentioned early this will have the value at every point.
            return nextEl.getInteger();
        }

        @Override
        public boolean hasNext() {
            while(!s.isEmpty()){
                if(!s.peek().hasNext()){//if the current iterator list is empty remove it from stack
                    s.pop();
                }
                else if((nextEl = s.peek().next()).isInteger()){//this will assing the next value to nextEl as well as check if its a list or integer
                    return true;
                }
                else{//if the next is a list then push that list iterator in the stack an perform the operation all again
                    s.push(nextEl.getList().iterator());
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
}
