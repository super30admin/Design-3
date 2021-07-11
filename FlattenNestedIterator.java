//Time Complexity - next and hasNext() - O(1)
//Space complexity - O(n) - number of nested list




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


/**
   1.put the given list in a stack
   2.using nested iterator traverse the list.
   3.first save the next element in global variable as iterator next() will move to next        element
   4. Three cases we need to check if the stack is not empty
       a) stack has next element? if not pop the tos
       b) stack element has next element then is that a integer and return true
       c) if its not integer but list push the list on top of stack
   5. continue this untill stack is empty
   6. get function will return the element from gloabal variable
   **/


public class NestedIterator implements Iterator<Integer> {
    NestedInteger nextEl;
    Stack<Iterator<NestedInteger>> st;
    public NestedIterator(List<NestedInteger> nestedList) {
      st = new Stack<>();
      st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()) {
          if(!st.peek().hasNext()) {
            st.pop();
          } else if((nextEl = st.peek().next()).isInteger()) {
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