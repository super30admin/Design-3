// Time Complexity : O(N) N=number of elements inserted into the queue. O(1) to remove last element; 
// Space Complexity : O(N) element in the queue;

// Did this code successfully run on Leetcode :yes. 

// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
//the code uses recursion and a queue. if element is an integer it will add it to the queue.
//Success
//Details 
//Runtime: 2 ms, faster than 98.43% of Java online submissions for Flatten Nested List Iterator.
//Memory Usage: 41.9 MB, less than 5.00% of Java online submissions for Flatten Nested List Iterator.

public class NestedIterator implements Iterator<Integer> {
    Queue<Integer> flattenList;
    public NestedIterator(List<NestedInteger> nestedList) {
        flattenList= new LinkedList<>();
        flatNestedList(nestedList);
    }
    
    private void flatNestedList(List<NestedInteger> nestedList){
        for (NestedInteger element: nestedList){
            if (element.isInteger()){
                flattenList.add(element.getInteger());
            }else{
                flatNestedList(element.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return flattenList.remove();
    }

    @Override
    public boolean hasNext() {
        return !flattenList.isEmpty();
    }
}

//another solution
public class NestedIterator implements Iterator<Integer> {
   Stack<ListIterator<NestedInteger>> stack;
    public NestedIterator(List<NestedInteger> nestedList) {
        stack= new Stack<>();
        stack.push(nestedList.listIterator());
    }
    
    @Override
    public Integer next() {
	    hasNext();
        return stack.peek().next().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()){
		   if (!stack.peek().hasNext()){
		      stack.pop();
		   }else{
		      NestedInteger x = stack.peek().next();
			  if (x.isInteger()){
			     return stack.peek().previous()== x;
			  }
			  stack.push(x.getList().listIterator());
		   }
		}
		return false;
    }
}


