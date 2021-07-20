//Time complexity-O(n)
//Space complexity-O(n)
//Ran on leetcode-yes
//Solution with code- 

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
    Queue<Integer> q ;//storing all value to the queue first
    
    public NestedIterator(List<NestedInteger> nestedList) {
            q= new LinkedList<>();
            dfs(nestedList);
    }
    
    public void dfs(List<NestedInteger> elements){
        for(NestedInteger n: elements){
            if(n.isInteger()){
                q.add(n.getInteger());
            }
            else{
                dfs(n.getList());//dfs for list in list
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

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */