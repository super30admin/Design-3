// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
	// 1) Use stack
	// 2) When we call hasnext, we run couple of conditions:
	// 	a. If stack peek has not next, pop it
	// 	b. If next element is integer, return true
	// 	c. If element is list, 
	// 		i. Push element in stack
	// 		ii. call hasnext ecursviely, until all ement in list converted to integer
	// 3) TC: O(n)
	// 4) SC: O(n)

public class NestedIterator {

    Stack<IEnumerator<NestedInteger>> stack;
    NestedInteger nextEl;
    
    public NestedIterator(IList<NestedInteger> nestedList) {
        stack = new Stack<IEnumerator<NestedInteger>>();
        stack.Push(nestedList.GetEnumerator());
        nextEl = new NestedInteger();
    }

    public bool HasNext() {
        
        while(stack.Count > 0)
        {
            if(stack.Peek().MoveNext())
            {
                if((nextEl = stack.Peek().Current).IsInteger())
                    return true;
                else
                    stack.Push(nextEl.GetList().GetEnumerator());

            }
            else
                stack.Pop();

        }
        return false;
    }

    public int Next() {
        return nextEl.GetInteger();
    }
}