// Time Complexity : O(n)
// Space Complexity : Recursion stack i.e. how many nested lists the given input list has
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

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
    
    List<Integer> output = new ArrayList();
    int index = 0;

    public NestedIterator(List<NestedInteger> nestedList) {
        getListElements(nestedList);
    }
    
    private void getListElements(List<NestedInteger> nestedList){
        
        for(int i = 0; i < nestedList.size(); i++){
            if(nestedList.get(i).isInteger()){
                output.add(nestedList.get(i).getInteger());
            }else{
                getListElements(nestedList.get(i).getList());
            }
        }        
    }

    @Override
    public Integer next() {
        return output.get(index++);
    }

    @Override
    public boolean hasNext() {
        return index < output.size();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
