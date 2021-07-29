public class NestedIterator implements Iterator<Integer> {

    private Deque<NestedIterator> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new ArrayDeque(nestedList);
    }

    @Override
    public Integer next() {
        if (!hasNext()) throw new Exception("No next element");

        return stack.removeFirst().getInteger();
    }

    @Override
    public boolean hasNext() {
        rearrangeStackInteger();
        return !stack.isEmpty();
    }

    private void rearrangeStackInteger() {
        while (!stack.isEmpty() && !stack.peekFirst().isInteger()) {
            List<NestedInteger> list = stack.removeFirst().getList();

            for (int i=nestedList.size() - 1; i >= 0; i--) {
                stack.addFirst(list.get(i));
            }
        }
    }
}