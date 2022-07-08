/*
This program implements the nested iterator class for the nested integers. It is implemented on top of the native iterator class 
that already exists in Java. A stack of iterators (which are references) for the nested list of integers is created. The two 
iterator functions next() and hasNext() are implemented. The next function will return the Integer that is in the current 
NestedInteger object. The hasNext() function does 3 things:

1. If the iterator on the top ofstack is currently pointing to an element, it checks if the element is of the type List or Integer.
   If it is an Integer, it is stored as the next Element for the next() function and the iterator will move on to the next element.
2. If the iterator on the stack is currently pointing to a NestedInteger of type List, a new iterator for that list is initialized.
   and is pushed onto the top of the stack.
3. If there are no more elements for the iterator to go over i.e the native iterator hasNext() call returns false, then the top 
   element of the stack is popped.
   
The program will run till the stack is empty i.e the list is completely flattened.

Did this code run on leetcode: Yes
*/
public class NestedIterator implements Iterator<Integer> {
    
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextEl;
    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        st.push(nestedList.iterator());
        
    }
    //Time Complexity: O(1)
    @Override
    public Integer next() {
        return nextEl.getInteger();
    }
    //Time Complexity: O(n) [if all the items are nested lists within each other]
    @Override
    public boolean hasNext() {
        
        while(!st.isEmpty())
        {
            if(!st.peek().hasNext())
                st.pop();
            else if((nextEl = st.peek().next()).isInteger())
                return true;
            else
                st.push(nextEl.getList().iterator());
        }
        
        return false;
        
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */