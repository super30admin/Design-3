//Time Complexity : worst O(H), average O(1) where H is the number of nested lists
//Space Complexity :O(H) where H is the number of nested lists
//Did this code successfully run on Leet code :Yes

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     bool IsInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     int GetInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     IList<NestedInteger> GetList();
 * }
 */

public class NestedIterator {
    
    Stack<IEnumerator<NestedInteger>> stack;

    public NestedIterator(IList<NestedInteger> nestedList) {
        
        stack = new Stack<IEnumerator<NestedInteger>>();
        stack.Push(nestedList.GetEnumerator());
    }

    public bool HasNext() {
        
        while(stack.Count > 0)
        {
            if(stack.Peek().MoveNext())
            {
                if(stack.Peek().Current.IsInteger())
                    return true;
                else
                    stack.Push(stack.Peek().Current.GetList().GetEnumerator());
            }
            else
                stack.Pop();
        }
        
        return false;
    }

    public int Next() {
        
        return stack.Peek().Current.GetInteger();
    }
}

/**
 * Your NestedIterator will be called like this:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.HasNext()) v[f()] = i.Next();
 */
