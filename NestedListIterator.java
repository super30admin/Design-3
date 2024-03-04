// Time complexity: O(1) for next(); O(depth) for hasNext() but O(1) amortized complexity
// Space complexity: O(d * l) where d = max depth and l is average list size 

//Approach: Used a stack to keep track of iterators as we dive deep.
// hasNext() ensures it's always pointing at the next element; it will keep
// adding lists to the stack till it finds an integer

public class NestedListIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> stack;
    int nextElement;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.stack = new Stack();
        stack.push(nestedList.iterator());
    }

    @Override // never called before hasNext()
    public Integer next() {
        return nextElement;
    }

    @Override
    public boolean hasNext() {
        // keep popping till the stack is empty or you find a valid iterator
        while (!stack.isEmpty()) {
            Iterator<NestedInteger> currentIterator = stack.peek();
            if (!currentIterator.hasNext()) {
                stack.pop();
                continue;
            }

            NestedInteger curr = currentIterator.next();

            if (curr.isInteger()) {
                nextElement = curr.getInteger();
                return true;
            } else {
                stack.push(curr.getList().iterator());
            }
        }
        return false;
    }
}
