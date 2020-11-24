// Time Complexity : O(n), where n is elements in the nested list
// Space Complexity :O(n), where n is depth of nested elements in the nestedlist
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

//Three Liner explanation of your code in plain English
//1. Maintaina a stack of Iterator of type NestedIntegers and Initailly push the nestedList as an iterator in the stack. Also maintain
        // a global NestedInteger nextEl (to hold the nestedInteger to be returned when next() is called)
//2. Whenever hasNext() is called, check if the iterator on stack top is pointing to an Integer, update the nextEl and return true
        //If it contains list, push it in the stack converting to an iterator. If Iterator has reached end, do stack.pop()
//3. Keep doing this till stack is not empty. (Intituation is to always keep the next immidiate nested Integer on the top to be 
        //returned when next() function is called)

// Your code here along with comments explaining your approach

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
    //maintain a stack of Iterator of type NestedInteger tp mimic iterator on the nested list
    Stack<Iterator<NestedInteger>> stack;
    //maintain a global nestedInteger to hold the integer to be returned in the next function
    NestedInteger nextEl;
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        //initailly push the nestedList containing the nestedInteger as an Iterator in the stack
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    //when hasNext() is called, our aim is to bring the nestedInteger on the top, that is stored in the nextEl to be returned when next() is called
    //check if the iterator on the list on the stack top is pointing to an integer, and update the nextEl with that val.
//If it is a list, convert it to an iterator and push back in the list
//If Iterator has reached the end of the list, pop the stack
    @Override
    public boolean hasNext() {
        while(!stack.isEmpty()){
            if(stack.peek().hasNext() == false){
                stack.pop();
            }else if((nextEl = stack.peek().next()).isInteger()){
                return true;
            }else{
                stack.push(nextEl.getList().iterator());
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