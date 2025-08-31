import java.util.Iterator;
import java.util.List;
import java.util.Stack;


//Time Complexity - O(1) - average
//Space Complexity - O(d) - depth of the stack - max. depth of nested list
public class Flattennestedlistiterator implements Iterator<Integer> {

    Stack<Iterator<NestedInteger>> stk;  //creating stack of type java's own iterator
    NestedInteger nextEl; //holds reference to next element in list of nested iterators
    public NestedIterator(List<NestedInteger> nestedList) {
        this.stk = new Stack<>();
        stk.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        //it works by pushing the elements onto the top of stack till an integer is found and make it nextEl
        //if there are no elements left, it returns false;
        while(!stk.isEmpty())
        {
            if(!stk.peek().hasNext()){  //iterator's hasNext() predefined method to check availability
                  stk.pop();
            }
            else
            {
                nextEl = stk.peek().next();
                if(nextEl.isInteger()){
                    return true;
                }
                else{
                    stk.push(nextEl.getList().iterator()); //inserting java's iterator for our list element onto stack
                }
            }
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.nex t();
 */
}
