/*

Passed All Test Cases 

Time Complexity: 
Time -> O(N) x O(m), n is length of nested list and m is avg length of nested integer
Space -> O(N), where N is # of elements in nested list (in worst case scenario, n of calls on the stack)


*/


public class NestedIterator implements Iterator<Integer> {
    //using stack for iterating over the elements of nested list
    Stack<ListIterator<NestedInteger>> stack ; 
    
    //default constructor
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>() ;
        stack.push(nestedList.listIterator()) ; 
    }

    @Override
    public Integer next() {
        hasNext(); 
        return stack.peek().next().getInteger() ; 
    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty()) {
            //checking if the iterator in stack hasnext or not 
            if(!stack.peek().hasNext())
                stack.pop() ; 
            else {
                NestedInteger check = stack.peek().next() ; 
                if(check.isInteger()) 
                    return stack.peek().previous() == check ; 
                stack.push(check.getList().listIterator()) ; 
                }
            }
        return false ; 
    }
}
