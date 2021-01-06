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
 Time Complexity : O(N)
 Space COmplexity : O(N)
 Idea : Store Iterator pointers on stack. if peek element is Integer then return the value from hasNext
 Now if the iterator is list then push it into thw stack u keep on doing this until u find an integer
 this takes care of depply nested list
 at last u have to remove the iterator from the stack if the next pointed by it is null
 this way the stack will eventually get empty indicating there are no more iterators to process. 
 */
public class NestedIterator implements Iterator<Integer> {
    NestedInteger nextElement;
    Stack<Iterator<NestedInteger>> stack;
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextElement.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty()){
            if(!stack.peek().hasNext()){
                stack.pop();
                continue;
            }
            nextElement = stack.peek().next();
            if(nextElement.isInteger()){
                return true;
            }else{
                stack.push(nextElement.getList().iterator());
            }
        }
        return false;
    }
}