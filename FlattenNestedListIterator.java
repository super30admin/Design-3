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

// Time Complexity : O(n); n is no. of elements in nested list 
// Space Complexity : O(n) ; recursive stack space as well as list space.
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Approach 1: Intuitive approach
// 1. Flatten the nested list recursively and store in a list.
// 2. Check and implement hasNext and next based on elements in the list.
// 3. Ideally, this method should not be used as it does not justify the intent of this question. 
//    On flattening the list inside constructor, behavior of iterator is not replicated.

/*
public class NestedIterator implements Iterator<Integer> {
    
    List<Integer> list = new ArrayList<>();
    int index = 0;

    public NestedIterator(List<NestedInteger> nestedList) {
        flatten(nestedList);
    }

    @Override
    public Integer next() {
        return list.get(index++);
    }

    @Override
    public boolean hasNext() {
        if(index < list.size()) return true;
        return false;
    }
    
    private void flatten(List<NestedInteger> nestedList) {
        if(nestedList.isEmpty()) {
            return;
        }
        for(NestedInteger num : nestedList) {
            if(num.isInteger()) {
                list.add(num.getInteger());
            }
            else flatten(num.getList());
        }
    }
}
*/

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

// Time Complexity : O(1); 
// Space Complexity : O(n) ; stack space containing n listIterators. n is the number of lists in nestedList.
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Yes, took a while to get comfortable with listIterator. Had never used it before.

// Approach 2: Using ListIterator
// 1. Flatten the nested list only when it is required (Lazy parsing). Push the listIterator in stack and retrieve next and hasNext results only when its asked for.
// 2. Check and implement hasNext and next based on elements in the list.

public class NestedIterator implements Iterator<Integer> {
    
    private Stack<ListIterator<NestedInteger>> stack; // listIterator type of object in stack

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        if(nestedList != null)
            stack.push(nestedList.listIterator()); // push initial list's iterator in stack
    }

    @Override
    public Integer next() {
        if(hasNext()) { // return next only if there exists one
            return stack.peek().next().getInteger();
        }
        else
            return -1;
    } 

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty()) { // loop until stack contains a listIterator object
            if(!stack.peek().hasNext()) stack.pop(); // remove object from top of stack if next element is not found
            else {
                NestedInteger curr = stack.peek().next(); // store list's next element in curr
                if(curr.isInteger())  {
                    curr = stack.peek().previous(); // revert back pointer if current element is integer (we had already gone to the next element while assigning curr)
                    return true;
                }
                stack.push(curr.getList().listIterator()); // push the inner list's listIterator in stack if element is not an integer
            }
        }
        return false;
    }
}

