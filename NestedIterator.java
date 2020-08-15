import java.util.*;

/**
 * Time complexity : O(N) N is integers in nested list
 * Space complexity : O(N) N is size of queue (integers in nested list)
 * 
 * Approach:
 * 1. Since we have to store all the elements and access them sequentially, we can consider using queue.
 * 2. At the first, we have to add all elements from nested list to queue, for which we can make use of 
 * provieded methods of NestedInteger class which are :  getInteger, isInteger and getList.
 * 3. for getting next element, simply pop from queue.
 * 4. for hasnext check if queue is empty or not.
 */

public class NestedIterator implements Iterator<Integer> {

    Queue<Integer> q;
    public NestedIterator(List<NestedInteger> nestedList) {
        q = new LinkedList<>();
        fillQueue(nestedList);
    }

    @Override
    public Integer next() {
        return q.poll();
    }

    @Override
    public boolean hasNext() {
        if(q.isEmpty())
            return false;
        return true;
    }
    
    private void fillQueue(List<NestedInteger> nestedList) {
        if(nestedList == null)
            return;
        
        for(NestedInteger li : nestedList) {
            if(li.isInteger()) {
                q.add(li.getInteger());
            }
            else {
                fillQueue(li.getList());
            }
        }
    }
}
