// Time Complexity :O(n) --> Total number of nestedIntegers
// Space Complexity :O(n) --> stack . n:Total number of nestedIntegers
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Approach:
// 1. Push all the elements from the given list into stack in reverse order.
// 2. When hasNext() is called check if stack is not empty. Now check if top element is a nestedInteger and return true.
// 3. If top element is a list then pop it and add all its elements into stack in reverse order and repeat step 2.
public class NestedIterator implements Iterator<Integer> {
    Stack<NestedInteger> stack;
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        for(int i = nestedList.size()-1;i>=0;i--){
            stack.push(nestedList.get(i));
           // System.out.println(nestedList.get(i).getList());
        }
    }

    @Override
    public Integer next() {
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        if(stack.isEmpty()) return false;
        while(!stack.isEmpty()){
            if(stack.peek().isInteger())  return true;
            List<NestedInteger> list = stack.pop().getList();
            for(int i = list.size()-1;i>=0;i--){
                stack.push(list.get(i));
            }
        }
       return false;
            
    }
}
