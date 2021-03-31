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

    // Time Complexity: O(n)    (where n -> no. of elements in the list)
    // Space Complexity: O(n)
    
    private Queue<Integer> q;
    public NestedIterator(List<NestedInteger> nestedList) {
        q = new LinkedList<>();

        dfs(nestedList);
    }
    
    private void dfs(List<NestedInteger> list){
        // Edge Case Checking
        if(list == null || list.size() == 0)
            return;
        
        // Looping through all the elements of the list
        for(NestedInteger elem : list){
            // If it is a number --> get its value and add to the queue
            if(elem.isInteger())
                q.add(elem.getInteger());
            // If its a list of elements --> go through them (make another dfs call)
            else
                dfs(elem.getList());
        }
    }

    @Override
    public Integer next() {
        // If there is next element present -> poll the queue to get the next element else return -1
        if(hasNext())
            return q.poll();
        else
            return -1;
    }

    @Override
    public boolean hasNext() {
        // If the queue is not empty then true else false
        return !q.isEmpty();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */