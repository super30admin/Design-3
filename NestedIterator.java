//https://leetcode.com/problems/flatten-nested-list-iterator/
/*
Time: Next, hasNext is O(1)
Space: O(DeepestList)
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : None
*/
public class NestedIterator implements Iterator<Integer> {

    NestedInteger nextEl;
    Stack<Iterator<NestedInteger>> s; // because we need to do next, hasnext on it

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

        while (!s.isEmpty()) {
            // case1 - top of stack has been processed
            if (!s.peek().hasNext())
                s.pop();

            // case2 - You see an integer, also remember to do peek.next
            else if ((nextEl = s.peek().next()).isInteger())
                return true;

            // case3 - You see a list, just push the whole list
            else
                s.push(nextEl.getList().iterator());
        }
        return false;
    }
}