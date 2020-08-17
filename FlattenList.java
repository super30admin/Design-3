// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :
// nope
// Your code here along with comments explaining your approach
*/
public class NestedIterator implements Iterator<Integer> {

    Stack<NestedInteger> stk;

    public NestedIterator(List<NestedInteger> nestedList) {
        stk = new Stack<>();
        //put the inital list in stack as is.
        for(int i = nestedList.size()-1; i >= 0 ; i--){
            stk.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return stk.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        //on has next if top elem is not int.
        //pop top elem, and copy all elems from list in stk.
        //keep doing this till top elem is int.
        while(!stk.isEmpty() && !stk.peek().isInteger()){
            NestedInteger nest = stk.pop();
            List<NestedInteger> nestedList = nest.getList();
            for(int i = nestedList.size()-1; i >= 0 ; i--){
                stk.push(nestedList.get(i));
            }
        }
        return !stk.isEmpty();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */