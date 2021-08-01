package Design3;

public class FlattenNestedListIterator {
//Time Complexity: O(d) , d : depth 
//hasNext() and Next() will be O(1)
//Space Complexity : O(d)


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
       NestedInteger nextEle;
       Stack<Iterator<NestedInteger>> stack;

       public NestedIterator(List<NestedInteger> nestedList) {
           stack = new Stack<>();
           stack.push(nestedList.iterator());
       }

       @Override
       public Integer next() {
           return nextEle.getInteger();
       }

       @Override
       public boolean hasNext() {
           while(!stack.isEmpty()) {
               if(!stack.peek().hasNext()) {
                   stack.pop();
               } else if((nextEle = stack.peek().next()).isInteger()) {
                   return true;
               } else {
                   stack.push(nextEle.getList().iterator());
               }
           }
           return false;
       }
   }
}
