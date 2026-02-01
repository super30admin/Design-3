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
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
 //Use a stack with iterators of nestedIntegers
 //Create an iterator on given list and put in stack
 //Create a global variable nextEle to store next value
 //travel through stack
 //Peek the elemnt with iterator
 //Store in nextEle
 //If element is Integer, return true
 //Else create another iterator on list and put on stack
 //TC: O(1)
 //SC: O(n) n->max nested iterations of lists
public class NestedIterator implements Iterator<Integer> {
    NestedInteger nextEle;
    Stack<Iterator<NestedInteger>> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.stack=new Stack();
        stack.push(nestedList.iterator());
        
        
    }

    @Override
    public Integer next() {
        return nextEle.getInteger();
        
    }

    @Override
    public boolean hasNext() {
        while(!(stack.isEmpty())){
            if(!(stack.peek().hasNext())){
                stack.pop();
            }else{
                nextEle=stack.peek().next();
                if(nextEle.isInteger()){
                    return true;
                }else{
                    stack.push(nextEle.getList().iterator());
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
