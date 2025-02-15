// TC --> O(n) total numbers in nested list as we need to build the queue for the first time
// SC --> O(n)

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
public class NestedIterator
{
    Queue<int> queue;
    public NestedIterator(IList<NestedInteger> nestedList)
    {
        queue = new Queue<int>();
        FlattenList(nestedList);
    }

    public bool HasNext()
    {
        return queue.Count > 0;
    }

    public int Next()
    {
        return queue.Dequeue();
    }

    public void FlattenList(IList<NestedInteger> nestedList)
    {
        foreach (var element in nestedList)
        {
            if (element.IsInteger())
            {
                queue.Enqueue(element.GetInteger());
            }
            else
            {
                FlattenList(element.GetList());
            }
        }
    }
}

/**
 * Your NestedIterator will be called like this:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.HasNext()) v[f()] = i.Next();
 */