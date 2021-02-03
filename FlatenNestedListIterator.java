import java.util.Iterator;
import java.util.Stack;


// Time Complexity :O(d), where d is number of depth
// Space Complexity :O(n + l), where n is # of integers and l is # of lists
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :getting started

// Your code here along with comments explaining your approach

public class FlatenNestedListIterator {

    public class NestedIterator implements Iterator<Integer> {
        Stack<Iterator<NestedInteger>> s;
        NestedInteger nextElement;
        public NestedIterator(List<NestedInteger> nestedList) {
            s = new Stack<>();
            s.push(nestedList.iterator());
        }

        @Override
        //return next element integer
        public Integer next() {
            return nextElement.getInteger();
        }

        @Override
        //if has next pop element and return true, if next element is top element return ture, else iterate and push on to stack
        public boolean hasNext() {
            while(!s.isEmpty()){
                if(!s.peek().hasNext()){
                    s.pop();
                }else if((nextElement = s.peek().next()).isInteger()){
                    return true;
                }else{
                    s.push(nextElement.getList().iterator());
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
