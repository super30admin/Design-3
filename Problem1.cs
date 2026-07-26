// Time Complexity : HasNext() - Amortized O(1) Next() - O(1)
// Space Complexity : O(d) where d is the depth of nested lists
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

/*
1. Initially, store the enumerator of nestedList in a stack of type IEnumerator<NestedList>()
2. For the HasNext function, check if the enumerator on top of the stack has a move next, if not then pop the enumerator. If enumerator on top of stack.Current.IsInteger() is true, return true 
straight away, else add the enumerator belonging to the nested list back to the stack. If the stack becomes empty at any point, return false.
3. For Next(), simply return Current of the enumerator on top of the stack.
*/

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
    public Stack<IEnumerator<NestedInteger>> enumeratorSt;

    public NestedIterator(IList<NestedInteger> nestedList) {
        enumeratorSt = new();
        enumeratorSt.Push(nestedList.GetEnumerator());
    }

    public bool HasNext() {
        while(enumeratorSt.Count != 0)
        {
            if(!enumeratorSt.Peek().MoveNext())
            {
                enumeratorSt.Pop();
                continue;
            }

            if(enumeratorSt.Peek().Current.IsInteger())
            {
                return true;
            }

            else
            {
                enumeratorSt.Push(enumeratorSt.Peek().Current.GetList().GetEnumerator());
            }
        }

        return false;
    }

    public int Next() {
        return enumeratorSt.Peek().Current.GetInteger();
    }
}

/**
 * Your NestedIterator will be called like this:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.HasNext()) v[f()] = i.Next();
 */