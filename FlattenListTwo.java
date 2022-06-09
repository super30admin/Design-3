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
/**
Time Complexity : O(N)
Space Complexity : O(N)
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No
*/
public class NestedIterator implements Iterator<Integer> 
{
    Stack<Iterator<NestedInteger>> stack = new Stack<>();
    Iterator<NestedInteger> iterator;
    Integer nextElement = null;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.stack = new Stack<>();
        this.iterator = nestedList.iterator();
        setNext();
    }

    @Override
    public Integer next() 
    {
        Integer next = this.nextElement;
        this.setNext();
        return next;
    }

    @Override
    public boolean hasNext() 
    {
        return this.nextElement != null;
    }
    
    private void setNext()
    {
        this.nextElement = null;
        boolean found = false;
        
        
        while((!stack.isEmpty() || iterator.hasNext()) && !found)
        {
            if(!stack.isEmpty())
            {
                Iterator<NestedInteger> currentIterator = stack.peek();
                if(currentIterator.hasNext())
                {
                    NestedInteger currentNestedInteger = currentIterator.next();
                    if(currentNestedInteger.isInteger())
                    {
                        this.nextElement = currentNestedInteger.getInteger();
                        found = true;
                        break;
                    }
                    else
                    {
                        stack.push(currentNestedInteger.getList().iterator());
                    }
                }
                else
                {
                    stack.pop();
                }
            }
            else if(iterator.hasNext())
            {
                NestedInteger currentNestedInteger = iterator.next();
                if(currentNestedInteger.isInteger())
                {
                        this.nextElement = currentNestedInteger.getInteger();
                        found = true;
                        break;
                }
                else
                {
                    stack.push(currentNestedInteger.getList().iterator());
                }
            }

        }
        
        if(!found)
            this.nextElement = null;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */