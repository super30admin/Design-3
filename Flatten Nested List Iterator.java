// Time Complexity : O(n) , n is input size
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :


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


//Brute force : Using Queue
//(Not using concepts of Iterator)
public class NestedIterator implements Iterator<Integer> {
    Queue<Integer> q;
    public NestedIterator(List<NestedInteger> nestedList) {
        //add all elements (integers) to queue
        q = new LinkedList<>();
        flattenNestedIterator(nestedList);
    }

    @Override
    public Integer next() {
        //get top of queue when next is called
        return q.poll();
    }

    @Override
    public boolean hasNext() {
        //for hasNext check if queue is empty
        return !q.isEmpty();
    }
    
    private void flattenNestedIterator(List<NestedInteger> nestedList) {
        //if integers, add to queue
        for(NestedInteger list : nestedList) {
            if(list.isInteger()) {
                q.add(list.getInteger());
            }
            else {
                // if bracket encoutered, call the method again
                flattenNestedIterator(list.getList());
            }
        }
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */





////////////////////////////////


// Time Complexity : O(1)
// Space Complexity : O(n) , n is input size
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :



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

//Using Iterator (stack)
//get access only to next element
public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextInteger;
    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextInteger.getInteger();
    }
    @Override
    public boolean hasNext() {
        while(!st.isEmpty()) {
            if(!st.peek().hasNext()) {
                st.pop();
            }
            else if ((nextInteger = st.peek().next()).isInteger()) {
                return true;
            }
            else {
                st.push(nextInteger.getList().iterator());
            }
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */



