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

    Stack<Integer> s;

    public NestedIterator(List<NestedInteger> nestedList) {
        s=new Stack<>();
        constructStack(nestedList);
    }
    public void constructStack(List<NestedInteger> nestedList)
    {
        int n = nestedList.size();
        ListIterator<NestedInteger> li = nestedList.listIterator(nestedList.size());

        while(li.hasPrevious())
        {
            NestedInteger a = li.previous();
            if(a.isInteger())
            {
                s.push(a.getInteger());
            }
            else
            {
                constructStack(a.getList());
            }
        }
    }

    @Override
    public Integer next() {
        if(hasNext())
        {
            return s.pop();
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return !s.isEmpty();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */