// Time Complexity : O(n), where n is the size of the nestedList
// Space Complexity : O(n), where n is the size of the nestedList for additional queue
// Did this code successfully run on Leetcode : Yes

//Queue Approach, Does not suit if the array is dynamic,
// since we process all the elements and store it in a result queue
// and iterate over the queue. It doesn't reflect the changes in the result queue
// if the original list changes and it is not an iterator implementation.
public class NestedIterator implements Iterator<Integer> {

    Queue<Integer> queue;
    public NestedIterator(List<NestedInteger> nestedList) {
        queue = new LinkedList<>();
        flattenList(nestedList);
    }

    @Override
    public Integer next() {
        return queue.poll();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    private void flattenList(List<NestedInteger> nestedList) {
        for(NestedInteger element: nestedList){
            if(element.isInteger()){
                queue.add(element.getInteger());
            }
            else {
                flattenList(element.getList());
            }
        }
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */