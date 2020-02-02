// S30 Big N Problem #77 {Medium }
// 341. Flatten Nested List Iterator
// Time Complexity :O(n) where n is the total number of integers in the entire nested list
// Space Complexity :O(1)
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :None


// Your code here along with comments explaining your approach
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
 */
public class NestedIterator implements Iterator<Integer> {
    Queue <Integer> q;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.q = new LinkedList<>();
        foo(nestedList);
    }
    
    private void foo(List<NestedInteger> nestedList){
        for(NestedInteger mem:nestedList){
            if(mem.isInteger()){
                q.add(mem.getInteger());
            }else{
                foo(mem.getList());
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