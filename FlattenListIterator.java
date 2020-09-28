// Time Complexity : O(n) intialize, O(1) for other operations
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
// Recursion and queue
public class NestedIterator implements Iterator<Integer> {
    
    private Queue<Integer> queue;

    public NestedIterator(List<NestedInteger> nestedList) {
        queue = new LinkedList<>();
        helper(nestedList);  
    }

    @Override
    public Integer next() {
        return queue.poll();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }
    
    private void helper(List<NestedInteger> nestedList){
        if(nestedList == null)
            return;
        
        for(NestedInteger list: nestedList){
            if(list.isInteger()){
                queue.add(list.getInteger());
            }else{
                helper(list.getList());
            }
        }
    } 
}