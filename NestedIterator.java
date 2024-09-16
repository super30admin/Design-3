// Time Complexity : O(H)  where H is the maximum number of nested lists
// Space Complexity : O(H)  where H is the maximum number of nested lists
public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> s;
    NestedInteger nextElement;
    // Java's native iterator can be applied on collections as this is a list we can use it on it
    public NestedIterator(List<NestedInteger> nestedList) {
        s = new Stack<>();
        s.push(nestedList.iterator());
    }
    

    @Override
    public Integer next() {
        return nextElement.getInteger();
    }

    @Override
    public boolean hasNext() 
    {
        while(!s.isEmpty())
        {
            if(!s.peek().hasNext()) //checks if iterator has any elements
            {
                // remove from stack once all elements have been processed
                s.pop();
            }
            else
            {
                 // get the next Element from iterator
                // next element can be NestedInteger or List
                nextElement = s.peek().next();
           
                if(nextElement.isInteger()) // initialize next element and keep
                {
                    return true;
                }
                else
                {
                   // since a list, convert to iterator
                    s.push(nextElement.getList().iterator());
                }
            }
        }
        return false;
    }
}


// Time Complexity : O(N)
// Space Complexity : O(N), where N is the number of elements
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
    Queue<Integer> q;
    public NestedIterator(List<NestedInteger> nestedList) {
        q = new LinkedList<>();
        flatten(nestedList);
    }
    
    
    private void flatten(List<NestedInteger> nestedList)
    {
        for(NestedInteger list : nestedList)
        {
            if(list.isInteger())
            {
                q.add(list.getInteger());
            }
            else
            {
                flatten(list.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return q.poll();
    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty();
    }
}
