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

/*
Approach: Using stack

Here we are using a stack. In this stack top element will always maintain the latest list we will be iterating. So for example  [[1,1],2,[1,1]]. Here stack's top element would have this entire list. 

What we will do is we will iterate over the top most list present on the stack and keep returing integers if present. But if in between we encounter another list we put that on top and then iterate over it.

So here we will always maintain a nextElement that will point to the current element we are iterating over, either a list or an integer. 

So while getting hasNext() if next element is a list then we will put that on top of stack.

Time complexity: O(n); where n = size of given array
Space complexity: O(n); where n = size of given array
*/
public class NestedIterator implements Iterator<Integer> {
    
    Stack<Iterator<NestedInteger>> s;
    NestedInteger nextElement;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        s = new Stack<>();
        s.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextElement.getInteger(); 
    }

    @Override
    public boolean hasNext() {
        
        while (!s.isEmpty()){
            if(!s.peek().hasNext()){
                s.pop();
            }
            else{
                nextElement = s.peek().next();
                if (nextElement.isInteger()){
                    return true;
                }
                else{
                    s.push(nextElement.getList().iterator());
                }    
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