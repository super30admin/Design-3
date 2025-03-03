// To design this flattened nested iterator, we make use of Iterator and it's methods, next() and hasNext()
//We convert the given nested list  into an iterator and push it into a stack
//For each element in the iterator of nested integers, we check if an element is an integer using isInteger(), if it is, then we continue.
//If the element is a nested list, we convert into an iterator and push into the stack
//We repeat this process till the stack is empty
//Time Complexity: O(1) average time complexity for all the methods.
//space complexity: O(1) average for stack


// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

interface NestedInteger {
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();
    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();
    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}

class NestedIterator implements Iterator<Integer> {

    NestedInteger nextEl;
    Stack<Iterator<NestedInteger>> st;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack<>();
        st.push(nestedList.iterator()); // here the List of nested integers is destructured into iterator. Now, the iterator runs on nested integers
    }

    

    @Override
    public Integer next() {
        return nextEl.getInteger();

    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();
            } else if((nextEl = st.peek().next()).isInteger()){
                return true;
            } else {
                st.push(nextEl.getList().iterator());
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
