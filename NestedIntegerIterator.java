// 341.
//run time - O(n) - iterate over all nested integers in the given i/p list
//space - O(n) - stack size is max, when the i/p list has only lists

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
public class NestedIterator implements Iterator<Integer> {

    Stack<ListIterator<NestedInteger>> support; //stack holds list of nested integers as a list iterator
    
    public NestedIterator(List<NestedInteger> nestedList) {
        support = new Stack<>();
        support.push(nestedList.listIterator()); //initially push the i/p list into stack as a list iterator
    }

    @Override
    public Integer next() {
        hasNext(); //ensures that the cursor of list iterator at stack top is always an integer
        return support.peek().next().getInteger(); //return integer and move cursor to next element
    }

    @Override
    public boolean hasNext() {
        while(!support.isEmpty())
        {
            if(!support.peek().hasNext()) //no next element in the list iterator at the stack top
            {
                support.pop(); //purpose of the list iterator at top is over so pop
            }
            else
            {
                NestedInteger top = support.peek().next(); //get the next element of stack top
                if(top.isInteger()) //if integer, move the cursor back and return true
                {
                    return(support.peek().previous().getInteger() == top.getInteger());
                }
                else //if list, push the list into stack as list iterator
                {
                    support.push(top.getList().listIterator());
                }
            }
        }
        return false;
    }                 |  
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */


//using queue

//time - O(n) to build the queue and iterate over it
//space - O(n) for the queue
public class NestedIterator implements Iterator<Integer> {

    Queue<Integer> support; 
    
    public NestedIterator(List<NestedInteger> nestedList) {
        support = new LinkedList<>();
        flatten(nestedList); //flatten the i/p list of nested integers into a list of integers and store it in queue
    }

    @Override
    public Integer next() {
        return support.poll();
    }

    @Override
    public boolean hasNext() {
        return (support.size() > 0);
    }
    
    private void flatten(List<NestedInteger> nestedList) {
        for(NestedInteger element : nestedList) 
        {
            if(element.isInteger())
            {
                support.offer(element.getInteger()); //add to queue
            }
            else
            {
                flatten(element.getList()); //recurse on the list
            }
        }
    }
}
