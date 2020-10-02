package s30;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

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
/*testcases:
empty list,
[1,2,3,4] normal list 
[[1,2],[4,5],6,[1]] combinations
[[1,2],[4]] only nested
[[1,2,[4,5,[8]]]] nested within nested
[[[[[3,[6]]]]]] too many nested
*/
/*Approach 1:
 * 1. Use a stack to store the list of elements.
 * 2. Use a property nextInt that always points to the next integer to be returned.
 * 3. hasNext method returns false if nextInt is null else returns true.
 * 4. When next method is called, it returns the element stored in the property nextInt. Then, it updates the nextInt value again
 * 
 * Time complexity: 
 * hasNext() --> O(1)
 * next() --> O(N) to update the nextInt property in the worst case 
 * 
 * Space complexity: O(N) to store stack
*/
public class NestedIterator implements Iterator<Integer> {
    
    Stack<NestedInteger> stack = new Stack<NestedInteger>();
    Integer nextInt = null;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        for(int i=nestedList.size()-1; i>=0; i--){
            stack.push(nestedList.get(i));
        }
        updateNextInt();
    }

    @Override
    public Integer next() {
        Integer prevInt = nextInt;
        updateNextInt();
        return prevInt;
    }
    
    /*
     * This method updates the value in nextInt property to the next possible integer that can be returned.
     * If the top element of stack is integer, it is returned.
     * It top element of the stack is a list, it is expanded until stack top reaches to an integer value
     */
    private void updateNextInt(){
        nextInt = null;
        if(!stack.isEmpty()){
            NestedInteger currElem = stack.pop();
            while(currElem != null && !currElem.isInteger()){
                List<NestedInteger> innerList = currElem.getList();
                for(int i=innerList.size()-1; i>=0 ; i--){
                    stack.push(innerList.get(i));
                }
                currElem = null;
                if(!stack.isEmpty()){
                     currElem = stack.pop();
                }
            }
            if(currElem != null)
                nextInt = currElem.getInteger();
        } 
    }
    
    @Override
    public boolean hasNext() {
        return nextInt != null;
    }
}



/*
 * Approach 2: Use queue to store flattened items.
 * 1. Iterate through the elements present in the list. 
 * 2.If integer is encountered, add it to the queue.
 * 3. If a nested list is encountered, call the flatten method recursively and go to step1.
 * 4. When next method is called, return the first element of the queue.
 * 5. When hasNext method is called, return false if queue is empty else return true.
 * 
 * Time complexity: 
 * flatten: O(N) -- N is number of elements in the total number of integers in the list
 * hasNext() -- O(1)
 * next() -- O(1)
 * 
 * Space Complexity: 
 * Extra space for queue: O(N) -- to store the flattened list
 * Max size of recursion stack --> maximum number of nested lists --> O(N) 
*/
 class NestedIterator_UsingQueue_Recursion implements Iterator<Integer> {
    Queue<Integer> que = new LinkedList<Integer>();
     
     NestedIterator_UsingQueue_Recursion(List<NestedInteger> nestedList) {
        flatten(nestedList);
    }

    @Override
    public Integer next() {
        if(hasNext()){
            return que.poll();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasNext() {
        return !que.isEmpty();
    }
    
    private void flatten(List<NestedInteger> list){
        for(NestedInteger elem: list){
            if(elem.isInteger()){
                que.add(elem.getInteger());
            } else {
                flatten(elem.getList());
            }
        }
    }
}
 

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */