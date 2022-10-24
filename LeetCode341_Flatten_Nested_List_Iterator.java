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

    Stack<Iterator<NestedInteger>> s; // this is java's native iterator
    NestedInteger nextEl;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        s = new Stack<>();
        s.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        // weill always call hasNext method
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!s.isEmpty())
        {
            //if do we have hasnext? if not thn  just pop from the stack. 
            if(!s.peek().hasNext())  // this is java's native iterator's HasNext method. 
                s.pop();
            else
            {
                nextEl = s.peek().next();
                if(nextEl.isInteger())
                {
                    return true;
                }
                else
                {
                    s.push(nextEl.getList().iterator());    
                }
            }
        }
        //if we never returned mid-way, just return false here
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

//approach- java's native iterator are used here
//create a stack of type iterator and iterator's type as NestedInteger
//push the nestedlist along with its iterator to the the stack

//next will always call hasnext, so, in hasnext, check is q is not empty, check does the iterator has enxt gives anythong, if not , just pop from the stacck, 
//else  -- peek the element and check if it's integer, if so , just retun true, else 
//make push the nextel.getlist.iterator90 to stack and it will be processed recursively for every nested list. 
//tc,sc -O(h) ; h= depth of tree = total nesting!