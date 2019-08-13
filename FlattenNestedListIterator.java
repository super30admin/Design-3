package design3;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// TC: next(), hasNext() -> O(1), iterator -> O(N) where N is the number of total elements in the list.
// SC: O(N) for populating N elements in the queue.

public class FlattenNestedListIterator{
	
//	NestedIterator i = new NestedIterator(nestedList);
//	while (i.hasNext()) v[f()] = i.next();
	


	Queue<Integer> q;
    public void NestedIterator(List<NestedInteger> nestedList) {
        q = new LinkedList<>();
        
        addAll(nestedList);
    }

    public void addAll(List<NestedInteger> nestedList) {
        // below is traversal in inner list
    for(NestedInteger ni : nestedList ) {
        if(ni.isInteger()) {   // if every element in the list is integer,
            q.offer(ni.getInteger());  // insert in queue
        } 
        else {
            // if it is not an integer, we are passing inner list again in method 'addAll', 
        	// so as to go deep in the list
            addAll(ni.getList()); // recursion
            }
        }   
    }
   
    public Integer next() {
        return !q.isEmpty() ? q.poll() : -1;
    }

    public boolean hasNext() {
        return !q.isEmpty();
    }
	
}
