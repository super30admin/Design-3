// Time Complexity : O(m) where m is the no of integers in array 
// Space Complexity : O(m)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No



public class NestedIterator implements Iterator<Integer> {
private Queue<Integer> q;
    public NestedIterator(List<NestedInteger> nestedList) {
       q=new LinkedList<>(); 
        recur(nestedList);
    }

    @Override
    public Integer next() {
        return q.poll();
    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty();
    }
    private void recur(List<NestedInteger> nestedList){
        if(nestedList == null){
            return;
        }
        for(NestedInteger data: nestedList){
            if(data.isInteger()){
                q.add(data.getInteger());
            }
            else{
                recur(data.getList());
            }
        }
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */