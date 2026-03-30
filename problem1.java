    /**
    Time Complexity :
    next()    -> Amortized O(1)
    hasNext() -> Amortized O(1)

    Explanation:
    Each NestedInteger is processed once.
    Flattening is done lazily using a stack of iterators.

    Space Complexity : O(D)
    Explanation:
    D = maximum depth of nested structure.
    Stack stores iterators corresponding to nesting levels.

    Did this code successfully run on LeetCode : Yes

    Any problem you faced while coding this :
    Initially tried flattening the entire structure upfront,
    which used extra space.
    Optimized using lazy evaluation with a stack of iterators.
    Also had to ensure next() only returns a prepared integer,
    so pre-processing is done inside hasNext().
    */

/**
 * // This is the interface that allows for creating nested lists.
 * public interface NestedInteger {
 *     public boolean isInteger();
 *     public Integer getInteger();
 *     public List<NestedInteger> getList();
 * }
 */

public class NestedIterator implements Iterator<Integer> {



    private Stack<Iterator<NestedInteger>> st;
    private NestedInteger nextEle;

    public NestedIterator(List<NestedInteger> nestedList) {

        this.st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEle.getInteger();
    }

    @Override
    public boolean hasNext() {

        while (!st.isEmpty()) {

            // If current iterator is exhausted → pop
            if (!st.peek().hasNext()) {
                st.pop();
            }
            else {

                nextEle = st.peek().next();

                // If integer → ready to return
                if (nextEle.isInteger()) {
                    return true;
                }
                else {
                    // If list → go deeper
                    st.push(nextEle.getList().iterator());
                }
            }
        }

        return false;
    }
}