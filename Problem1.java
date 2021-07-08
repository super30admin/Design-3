/* Flatten Nested List Iterator */

//Time complexity: O(N) we are iterating over every element in list and list of list
//Space complexity: O(N) we are using queue(auxiliary data structure)
//Any problem: Yes understanding interface was difficult and its operations. But now it makes sense.

//Approach
//we will use queue using DFS
//Queue will maintain the order and DFS is the recursive solution

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
    //initialise queue
    Queue <Integer> q;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.q = new LinkedList<>();
        fillQueue(nestedList);
    }

    @Override
    public Integer next() {
        return q.poll();
    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty();
    }
    //filling the queue
    private void fillQueue(List<NestedInteger> nestedList) {
        //iterate over each element in list
        for(NestedInteger ele: nestedList){
            if(ele.isInteger()){
                //add elements to queue
                q.add(ele.getInteger());
            } else {
                fillQueue(ele.getList());
            }
        }
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */