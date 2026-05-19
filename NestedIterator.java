// Time Complexity : O(n) overall where n is the total number of nested elements because each element is processed once
// Space Complexity : O(d) where d is the maximum depth of nested lists due to the iterator stack
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach in three sentences only
// We use a stack of iterators to lazily traverse the nested list depth-first without flattening the entire structure beforehand.
// hasNext keeps processing nested lists until it finds the next integer, while pushing child iterators whenever a nested list is encountered.
// next simply returns the integer prepared earlier by hasNext, making iteration efficient and lazy-loaded.

public class NestedIterator implements Iterator<Integer> {

    Stack<Iterator<NestedInteger>> stack;

    NestedInteger nextEl;

    public NestedIterator(List<NestedInteger> nestedList) {

        // initialize stack with outermost iterator
        this.stack = new Stack<>();

        this.stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {

        // return prepared integer
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {

        while (!stack.isEmpty()) {

            // current iterator exhausted
            if (!stack.peek().hasNext()) {

                stack.pop();
            }

            // integer found
            else if ((nextEl = stack.peek().next()).isInteger()) {

                return true;
            }

            // nested list found
            else {

                stack.push(nextEl.getList().iterator());
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