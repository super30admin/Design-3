// Time Complexity : O(1)
// Space Complexity : O(stack space)
// Method used : Iterator

public class NestedIterator implements Iterator<Integer> {

    // Iterator concept follows lazy loading. So we don't need to process all the elements at a single go. We need to load only 
    // those elements which are needed.

    // If we use recursion then we end up adding all the elements but this shouldn't happen we shoiuld control the recursion so 
    // we use iterative stack and control the loading of elements.

    // We use Stack data structure and the elements inside the stack would be iterator objects. We start traversing the 
    // nested list below and make the decisions accordingly :

    // a) If the NestedInteger is actually an integer we return it
    // b) Else it should be a list then we traverse that again. So we keep on adding iterator objects into stack

    // Before making next() operation we should always make hasNext()


    Stack<Iterator<NestedInteger>> stack;
    NestedInteger element;

    public NestedIterator(List<NestedInteger> nestedList) {
        
        stack = new Stack();
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        
        return element.getInteger();

    }

    @Override
    public boolean hasNext() {
        
        while(!stack.isEmpty())
        {
            // This means that the iterator of stack's peek has already processed all the elements, so pop it
            if(!stack.peek().hasNext())
            {
                stack.pop();
            }

            // As we are calling next we need to store the previous element.
            else if(( element = stack.peek().next() ).isInteger()) return true;

            // If the above fetched element is not an integer then it should be a list create an iterator on it and push to 
            // stack.
            else
            {
                stack.push(element.getList().iterator());
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