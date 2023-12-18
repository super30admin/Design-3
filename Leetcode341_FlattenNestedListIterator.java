
/********************************** APPROACH -1 *****************************************************/

/*
TC: As we are doing flattening in constructor, so we are just saying it as O(1)
SC: O(Depth of tree)

*/

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 * // @return true if this NestedInteger holds a single integer, rather than a
 * nested list.
 * public boolean isInteger();
 *
 * // @return the single integer that this NestedInteger holds, if it holds a
 * single integer
 * // Return null if this NestedInteger holds a nested list
 * public Integer getInteger();
 *
 * // @return the nested list that this NestedInteger holds, if it holds a
 * nested list
 * // Return empty list if this NestedInteger holds a single integer
 * public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    Queue<Integer> q;

    public NestedIterator(List<NestedInteger> nestedList) {
        q = new LinkedList<>();
        flattenListHelper(nestedList);
    }

    @Override
    public Integer next() {
        return q.poll();
    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty();

    }

    public void flattenListHelper(List<NestedInteger> nestedList) {
        for (NestedInteger el : nestedList) {
            if (el.isInteger())
                q.add(el.getInteger());
            else {
                flattenListHelper(el.getList());
            }

        }

    }

}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

/**********************************
 * APPROACH -2
 *****************************************************/
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 * // @return true if this NestedInteger holds a single integer, rather than a
 * nested list.
 * public boolean isInteger();
 *
 * // @return the single integer that this NestedInteger holds, if it holds a
 * single integer
 * // Return null if this NestedInteger holds a nested list
 * public Integer getInteger();
 *
 * // @return the nested list that this NestedInteger holds, if it holds a
 * nested list
 * // Return empty list if this NestedInteger holds a single integer
 * public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    // stack with builtin iterator over nested Integer, iterator will intialized
    // automatically when oushed to stakc and will point to first el

    Stack<Iterator<NestedInteger>> s;
    NestedInteger nextEl;

    public NestedIterator(List<NestedInteger> nestedList) {
        // to Intialize, push for the first time in constructor
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
            if (!s.peek().hasNext()) // no elements
            {
                s.pop(); // pointing at the index out of bound in list.
            } else if (true) {
                nextEl = s.peek().next(); // next automatically progresses when called so we need to store the current
                                          // values of list/integer
                if (nextEl.isInteger()) {
                    return true; // and automaticaly this interface's next() will be called where we fetch the
                                 // Integer value! line32
                } else // It's a list
                {
                    s.push(nextEl.getList().iterator());
                }
            }
        }
        // stack is Empty!!
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

/*
 * 
 * TC: Next O(1)
 * hasNext() - It's Kindaa a tree, so it's length of the tree/ depth of nesting
 * TC for hasNext() - O(Depth of tree)->
 * sc for hasNext - O(Depth of tree) -- for Stack!!
 */
