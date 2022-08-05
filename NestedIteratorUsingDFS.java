// Time Complexity : O(n), where n is the size of the nestedList
// Space Complexity : O(n), where n is the size of the nestedList for additional result list
// Did this code successfully run on Leetcode : Yes


// Queue Approach, Does not suit if the array is dynamic,
// since we process all the elements and store it in a result queue
// and iterate over the queue. It doesn't reflect the changes in the result queue
// if the original list changes and it is not an iterator implementation.
public class NestedIterator implements Iterator<Integer> {

    List<Integer> result;
    int idx;
    public NestedIterator(List<NestedInteger> nestedList) {
        result = new ArrayList<>();
        dfs(nestedList);
    }

    @Override
    public Integer next() {
        int next = result.get(idx);
        idx++;
        return next;
    }

    @Override
    public boolean hasNext() {
        return !(idx==result.size());
    }

    private void dfs(List<NestedInteger> nestedList) {
        for(NestedInteger element: nestedList){
            if(element.isInteger()){
                result.add(element.getInteger());
            }
            else {
                dfs(element.getList());
            }
        }
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */