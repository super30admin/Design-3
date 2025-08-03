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
public class NestedIterator implements Iterator<Integer> {

    Deque<NestedInteger> stack;
    // Method 1 using Stack of NestedInteger.
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new ArrayDeque<>();
        for (int i = nestedList.size() - 1; i >= 0; --i) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return stack.pop().getInteger();
    }

    // Only if hasNext is true, our next method will be called from judge.
    // this method sets the nextElement value. So when next() is called, able to return correctly.
    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            if (stack.peek().isInteger()) {
                return true;
            }
            List<NestedInteger> list = stack.pop().getList();
            for (int i = list.size() - 1; i >= 0; --i) {
                stack.push(list.get(i));
            }
        }

        return false;
    }

    // Method 2 using Iterator of NestedInteger.


    // Stack<Iterator<NestedInteger>> s;
    // NestedInteger nextElement;

    // public NestedIterator(List<NestedInteger> nestedList) {
    //     s = new Stack<>();
    //     s.push(nestedList.iterator());
    //     nextElement = null;   
    // }

    // @Override
    // public Integer next() {
    //     return nextElement.getInteger();
    // }

    // // Only if hasNext is true, our next method will be called from judge.
    // // this method sets the nextElement value. So when next() is called, able to return correctly.
    // @Override
    // public boolean hasNext() {
    //     while (!s.isEmpty()) {
    //         if (!s.peek().hasNext()) {
    //             s.pop();
    //         } else if ((nextElement = s.peek().next()).isInteger()) { // peek element is stored in nextElement and the pointer is moved.
    //             return true;
    //         } else {
    //             s.push(nextElement.getList().iterator());
    //         }
    //     }

    //     return false;
    // }


    // Method 3 - Can also be done using queue. But it is not actually an iterator. Instead we store all elements first and then just take from the queue.


    // Queue<Integer> q;

    // public NestedIterator(List<NestedInteger> nestedList) {
    //     q = new LinkedList<>();
    //     flattenList(nestedList);
    // }

    // private void flattenList(List<NestedInteger> nestedList) {
    //     for (NestedInteger ne: nestedList) {
    //         if (ne.isInteger()) {
    //             q.add(ne.getInteger());
                
    //         } else {
    //             flattenList(ne.getList());
    //         }
    //     }
    // }

    // @Override
    // public Integer next() {
    //     return q.poll();
    // }

    // @Override
    // public boolean hasNext() {
    //     return !q.isEmpty();
    // }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */