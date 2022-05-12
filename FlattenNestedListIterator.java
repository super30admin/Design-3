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
public class NestedIterator implements Iterator<Integer> {

    //Time Complexity : 0(l)   where l is the levels of nesting
    //Space Complexity: 0(l) where l is the levels of nesting
    //Did it successfully run on leetcode: Yes
    //Did you face any problem while coding: No

    //In brief explain your approach:

    Stack<Iterator<NestedInteger>> s;       //declaring a stack to store the nested list along with an iterator
    NestedInteger nextElement;  //to store the next element that iterator is pointing to in the stack
    public NestedIterator(List<NestedInteger> nestedList) {
        s = new Stack<>();
        s.push(nestedList.iterator());  //pushing the input onto the stack
    }

    @Override
    public Integer next() {
        return nextElement.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!s.isEmpty()){    //chcking if the stack is empty or not
            if(!s.peek().hasNext()){        //if the iterator at the top of the stack points to null, then we pop the top of the stack meaning all the elements have been taken care of
                s.pop();
            }
            else if((nextElement = s.peek().next()).isInteger()){//storing the top of the stack to next element and checking if the iterator pointing to the element is list or an integer
                return true;    //if it's an integer, then return true
            }
            else{
                s.push(nextElement.getList().iterator());//if it's a list, then we iterate over the list and push different levels of nesting on to the stack
            }
        }
        return false;   //we return false if the stack is empty
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */