// Time Complexity :O(n)
// Space Complexity :O(n)
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
  //store the inorder traversal of and then find out the two nodes and swap them.

public class NestedIterator implements Iterator<Integer> {
    Queue<Integer> qu;

    public NestedIterator(List<NestedInteger> nestedList) {
        qu=new LinkedList<>();
        flatten(nestedList);
    }
    
    public void flatten(List<NestedInteger> nestedList){
        for(NestedInteger el:nestedList){
            if(el.isInteger()){
                qu.add(el.getInteger());
            }else{
                flatten(el.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return qu.remove();
    }

    @Override
    public boolean hasNext() {
        
        return !qu.isEmpty();
    }
}

//this is the method using the stack and controlled recursion.
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
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextElement;

    public NestedIterator(List<NestedInteger> nestedList) {
        st=new Stack<>();
        st.push(nestedList.iterator());
    }
    
    @Override
    public Integer next() {
        return nextElement.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();
            }else if((nextElement=st.peek().next()).isInteger()){
                return true;
            }else{
                st.push(nextElement.getList().iterator());
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
