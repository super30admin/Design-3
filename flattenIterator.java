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

//Time Complexity : next() and hasNext() is O(1) while for creation the object it is O(n)
//Space Complexity : O(N) , since we have used a queue.
public class NestedIterator implements Iterator<Integer> {
    //we initialize a queue
    Queue<Integer> q;

    public NestedIterator(List<NestedInteger> nestedList) {
        q=new LinkedList<>();
        addAll(nestedList);
        //helper function which adds all the integer values to the Queue.
    }

    private void  addAll(List<NestedInteger> nestedList){
        //we will traverse over the input nested list and if it is integer we will add it to the Queue.
        for(NestedInteger ni : nestedList){
            if(ni.isInteger()){
                q.offer(ni.getInteger());
            }else{
                addAll(ni.getList());
                //small recursion call
            }
        }

    }

    @Override
    public Integer next() {
        //if q is not empty then we will poll the value
        if(!q.isEmpty()){
            return q.poll();
        }
        else{
            return -1;
        }

    }

    @Override
    public boolean hasNext() {
        //if there is nothing in the q then it is false else it is true
        if(!q.isEmpty()){
            return true;
        }else{
            return false;
        }


    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */