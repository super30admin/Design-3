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
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 * 
 * Time Complxity : O(n)
 * Space Complexity : O(n)
 */
public class NestedIterator implements Iterator<Integer> {

    Queue<Integer> queue = new LinkedList<>();
    
    public NestedIterator(List<NestedInteger> nestedList) {
        fillQueue(nestedList);
    }

    @Override
    public Integer next() {
        return queue.poll();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }
    
    public void fillQueue(List<NestedInteger> nestedList){
         for(NestedInteger list : nestedList){
            
            if(list.isInteger()){
                queue.add(list.getInteger());
            }else{
                fillQueue(list.getList());
            }
            
        } 
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */