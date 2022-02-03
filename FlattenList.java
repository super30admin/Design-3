// Time Complexity : O(1)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Approach

// We use Queue to store all the values of the list

public class NestedIterator implements Iterator<Integer> {

    Queue<Integer> q;

    public NestedIterator(List<NestedInteger> nestedList) {
        q = new LinkedList<>();
        flatten(nestedList);
    }

    @Override
    public Integer next() {
        return q.poll();
    }

    @Override
    public boolean hasNext() {
        return q.size() > 0;
    }

    private void flatten(List<NestedInteger> nestedList) {
        for (NestedInteger el : nestedList) {
            if (el.isInteger()) {
                q.add(el.getInteger());
            } else {
                flatten(el.getList());
            }
        }
    }
}

// Time Complexity : O(1)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Approach

// We use Stack and iterator to design this
// WE put the iterator onto the stack
// We check if the element is integer if yes we return it else we put it on to
// stack again
public class NestedIterator implements Iterator<Integer> {

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
        while (!s.isEmpty()) {
            if (!s.peek().hasNext()) {
                s.pop();
            } else if ((nextEl = s.peek().next()).isInteger())
                return true;
            else {
                s.push(nextEl.getList().iterator());
            }
        }

        return false;
    }

}