// Approach: We use a controlled iterative (controlled recursion) stack here as we want to iterate one by one and our iterator should only be concerned about the next element in the list. We use a stack of iterators. We first get an iterator for the initial list and push it to the stack. Then we iterator and whenever we encounter a list instead of an integer, we create an iterator for the list and push it to the stack. If we encounter an integer, we make our next variable point to that integer. So whenever next is called it will return that integer. Whenever our hasnext is false within our list, basically when our next pointer is out of bounds, we will pop from the stack

// Space complexity: O(D) where D is the max nested list depth

public class NestedIterator implements Iterator<Integer> {
    NestedInteger next;
    Stack<Iterator<NestedInteger>> stack;
    public NestedIterator(List<NestedInteger> nestedList) { // O(1)
        stack = new Stack<>();
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() { // O(1)
        return next.getInteger();
    }

    @Override
    public boolean hasNext() { // O(1) avg, worst case O(D)
        while (!stack.isEmpty()) {
            // if the next pointer is out of bounds for the current stack element. Basically if there is no has next on the current list, we will pop from the stack
            if (!stack.peek().hasNext()) {
                stack.pop();
            }
            else if ((next = stack.peek().next()).isInteger()) { // if the next value is integer, return true
                return true;
            }
            else {
                stack.push(next.getList().iterator()); // if not integer, get list, create a new iterator and push
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