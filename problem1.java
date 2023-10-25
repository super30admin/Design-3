/*

Problem 1: Flatten Nested List Iterator (https://leetcode.com/problems/flatten-nested-list-iterator/)

Time Complexity : O(1)
Space Complexity : O(N), where N is no. of nested lists.
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No

*/


public class NestedIterator implements Iterator<Integer> {

    Stack<Iterator<NestedInteger>> s;
    NestedInteger nextEl;

    public NestedIterator(List<NestedInteger> nestedList) {
        s= new Stack<>();
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
            }
            else if ((nextEl = s.peek().next()).isInteger()){
                return true;
            }
            else{
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