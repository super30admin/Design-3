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
    
    List<NestedInteger> nestedList;
    Stack<NestedInteger> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        
        this.nestedList = nestedList;
        stack = new Stack<>();
        
        for (int i=nestedList.size() - 1; i>=0; i--)
        {
            stack.add(nestedList.get(i));
        }
        
        explore();
    }
    
    private void explore()
    {
        while (!stack.isEmpty() && !stack.peek().getList().isEmpty())
        {
            NestedInteger pop = stack.pop();
            List<NestedInteger> list = pop.getList();
            
            for(int i=list.size() -1; i>=0; i--)
            {
                stack.push(list.get(i));
            }
        }
    }

    @Override
    public Integer next() {
        NestedInteger n = stack.pop();
        explore();
        return n.getInteger();
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
