//-----------------WRONG APPROACH--------------------------

// Time Complexity :O(1)for both methods
// Space Complexity :O(1)for both methods
// Did this code successfully run on Leetcode :YES
// Any problem you faced while coding this :nO
// here we are creating queue of all the values in condtructor only and returning values one by one
public class NestedIterator implements Iterator<Integer> {
    private Queue<Integer> q;

    public NestedIterator(List<NestedInteger> nestedList) {
        q = new LinkedList<>();
        helper(nestedList);

    }

    private void helper(List<NestedInteger> input) {

        // logic
        for (NestedInteger ni : input) {
            if (ni.isInteger()) {
                q.add(ni.getInteger());
            } else {
                helper(ni.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return q.poll();
    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
// ----------------Right Approach---------------------
// Time Complexity :O(1)for both methods(average)
// Space Complexity :O(1)for both methods(average)
// Did this code successfully run on Leetcode :YES
// Any problem you faced while coding this :nO

// here everytime before next method ie.hasnext method, we are applying iterator
// on nested list and
// pushing in our stack, and peeking it to find the next element until our stack
// is not empty or next integer is found
public class NestedIterator implements Iterator<Integer> {
    private Stack<Iterator<NestedInteger>> st;
    NestedInteger nextEl;

    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!st.isEmpty()) {
            if (!st.peek().hasNext()) {
                st.pop();
            } else if ((nextEl = st.peek().next()).isInteger()) {
                return true;
            } else {
                st.push(nextEl.getList().iterator());
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