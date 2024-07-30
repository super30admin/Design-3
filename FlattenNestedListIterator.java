/**
 *Time Complexity: O(N)
 *Space Complexity: O(N)
 */
public class NestedIterator implements Iterator<Integer> {
    private Stack<NestedInteger> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        int n = nestedList.size();
        for(int i = n - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        NestedInteger top = stack.pop();
        return top.getInteger();

    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty()) {
            NestedInteger current = stack.peek();
            if(current.isInteger()) {
                return true;
            }

            stack.pop();
            List<NestedInteger> list =  current.getList();
            for(int i = list.size() - 1; i >= 0; i--) {
                stack.push(list.get(i));
            }
        }
        return false;

    }
}