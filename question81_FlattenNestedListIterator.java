package Design3;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class question81_FlattenNestedListIterator {
    /* Created by palak on 7/3/2021 */

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
/*
We need to do a controlled recursion. This we will use a Stack. We will put the iterator on the given and then we will going to put a native iterator on the list and then put it inside the Stack.
We will maintain a 'NextElement' variable which will give the next element of the nested Iterator. We will build the stack in such a manner that I get the next integer out of the entire iterator.
Before calling the next() of the nested iterator, they are calling hasNext(). Even if they are not calling, I can call hasNext() before I implement the next(). We will use hasNext() to give the next element/ integer out of the iterator.
Also, Stack internally has only Iterators.
HasNext() has three cases:
while(!stack.isEmpty()) {
    a) Case 1: I will check the top of the stack here. If(!Stack.peek().hasNext()). If no next element I will simply pop (Stack.pop()) that Iterator as we are finished with all the integers in that Iterator.

    b) Case 2 (Return true/ false in case of Integer): Whichever is the iterator at the top of the stack, is it's next pointer poining towrds the Integer or not? Thus we will check, stack.peek().next().isInteger;
Problem with the Native Iterator is that: whenever we call a next() on the native iterator, the next pointer moves to the next element by returning the previous element. This being the basic nature of the iterator. Like this we loose the access to the previous element.
Thus to overcome this problem, while I call next I will assign the previous value in my 'NextElement' variable.

    c) Case 3 (If the 'NextElement' variable is not an Integer): We will push that inside the Stack. Stack.push(NextElement.getList().iterator);
So here since we got a list in the 'NextElement' variable. We will push it inside the Stack and will also put an iterator to it.

}

Time Complexity: O(d) //d is the depth of the nested iterator; Average: O(1); next() and hasNext()
Space Complexity: O(d)

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
            while(!stack.isEmpty()) {
                if(!stack.peek().hasNext()) {
                    stack.pop();
                } else if((nextElement = stack.peek().next()).isInteger()) {
                    return true;
                } else {
                    stack.push(nextElement.getList().iterator());
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

}
