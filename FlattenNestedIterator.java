// Time Complexity : O(Nk) where N is number of NestedIntegers and k is avg length of array is NestedInteger is array
// Space Complexity : O(Nk)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Your code here along with comments explaining your approach:
/*
HasNext methods has to be called before the next method.Use controlled recursion.
If the stack peek has no next, then pop the element
if stack peek is an integer then assign value and return true
else push the list as iterator
*/
public class FlattenNestedIterator implements Iterator<Integer> {

    Stack<Iterator<NestedInteger>> stack;
    NestedInteger next;
    public NestedIterator(List<NestedInteger> nestedList) {
        stack= new Stack<>();
        stack.push(nestedList.iterator());
    }


    @Override
    public Integer next() {
        return next.getInteger();
    }

    @Override
    public boolean hasNext() {

        while(!stack.isEmpty()){
            if(!stack.peek().hasNext()){
                stack.pop();
            }else if((next=stack.peek().next()).isInteger()){
                return true;
            }else{
                stack.push(next.getList().iterator());
            }
        }

        return false;
    }
}