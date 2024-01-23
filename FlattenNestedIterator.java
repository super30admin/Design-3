//T.C O(n) n-> numbers in the nested iterator
//S.C O(n) Stack space to hold the nested iterators
//Successful executed in leetcode : yes
//Solution: Use a stack to hold all the nested iterators. Before every next(), hasNext() will be called.
//In hasNext() check the stack and pop out next integer or push in if its a list
import java.util.Iterator;
import java.util.List;
import java.util.Stack;


// This is the interface that allows for creating nested lists.
 // You should not implement it, or speculate about its implementation
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

public class FlattenNestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> st;
    NestedInteger ni;

    public FlattenNestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return ni.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();
            }else if((ni = st.peek().next()).isInteger()){
                return true;
            }else{// it is List
                st.push(ni.getList().iterator());
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