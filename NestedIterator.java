package week6.day5;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;


public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> stack; //important
    NestedInteger value;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.stack = new Stack();
        this.stack.push(nestedList.iterator());  //[[1,1] ,2, [1,1]]
    }

    @Override
    public Integer next() {
        return value.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty()){
            if(!stack.peek().hasNext()) //nested list //[[1,1] ,2, [1,1]]   // [1,1]
                stack.pop();
            else if((value = stack.peek().next()).isInteger()) // 1
                return true;
            else
                stack.push(value.getList().iterator()); //[1,1]
        }
        return false;
    }
    public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}

interface NestedInteger {

    //@return true if this NestedInteger holds a single integer, rather than a nested list.
   public boolean isInteger();

    //@return the single integer that this NestedInteger holds, if it holds a single integer
    //Return null if this NestedInteger holds a nested list
   public Integer getInteger();

    //@return the nested list that this NestedInteger holds, if it holds a nested list
    //Return empty list if this NestedInteger holds a single integer
   public List<NestedInteger> getList();
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
