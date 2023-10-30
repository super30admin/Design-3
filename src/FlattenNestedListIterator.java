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

//                   next    hasNext
// Time Complexity:  O(1)     O(1)
// Space Complexity: O(1)     O(1)
//
// Problem with this solution: If they give a functionality to change elements afterwords we can get that reflected number in our output as we have used the Iterator reference in Stack which will have updated value.

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
            if(!stack.peek().hasNext()) {                                       // peek list is executed
                stack.pop();                                                    // then remove it from stack
            }
            else if((nextElement = stack.peek().next()).isInteger()) {          // if next element in peek is integer
                return true;                                                    // return true and thus next() can be called
            }
            else {                                                              // if next element in peek is not integer
                stack.push(nextElement.getList().iterator());                   // get the iterator from list and push into stack
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





// // ******************** Another Method ********************

// //                   next    hasNext
// // Time Complexity:  O(1)     O(1)
// // Space Complexity: O(1)     O(1)
// //
// // Problem with this solution: If they give a functionality to change elements afterwords we can not get that reflected number in our output as we would have got queue in the beginning while creating a reference of NestedIterator, in constructor.

// public class NestedIterator implements Iterator<Integer> {

//     Queue<Integer> q;

//     public NestedIterator(List<NestedInteger> nestedList) {
//         q = new LinkedList<>();
//         dfs(nestedList);
//     }

//     @Override
//     public Integer next() {
//         return q.remove();
//     }

//     @Override
//     public boolean hasNext() {
//         if(!q.isEmpty()) {
//             return true;
//         }
//         return false;
//     }

//     private void dfs(List<NestedInteger> nestedList) {
//         for(NestedInteger nl : nestedList) {
//             if(nl.isInteger()) {
//                 q.add(nl.getInteger());
//             }
//             else {
//                 dfs(nl.getList());
//             }
//         }
//     }
// }
