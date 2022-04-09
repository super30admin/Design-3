import java.util.Iterator;
import java.util.List;
import java.util.Stack;

//Time Complexity: O(D); Where D is depth of the stack i.e. max number of lists inside each other 
//Space Complexity: O(D)
public class FlattenNestedListIterator {	
	// This is the interface that allows for creating nested lists.
	// You should not implement it, or speculate about its implementation
	public interface NestedInteger {
 
		// @return true if this NestedInteger holds a single integer, rather than a nested list.
		public boolean isInteger();
 
		// @return the single integer that this NestedInteger holds, if it holds a single integer
		// Return null if this NestedInteger holds a nested list
		public Integer getInteger();
 
		// @return the nested list that this NestedInteger holds, if it holds a nested list
		// Return empty list if this NestedInteger holds a single integer
		public List<NestedInteger> getList();
	}
	
	/**Approach: Stack of Iterator**/
	public class NestedIterator implements Iterator<Integer> {
	    Stack<Iterator<NestedInteger>> st;
	    NestedInteger nextEle;
	    		
	    public NestedIterator(List<NestedInteger> nestedList) {
	       st= new Stack<>();
	       st.push(nestedList.iterator());
	    }

	    @Override
	    public Integer next() { //O(1)
	        return nextEle.getInteger();
	    }

	    @Override
	    public boolean hasNext() { //O(D)
	       while(!st.isEmpty()) {
	    	   if(!st.peek().hasNext()) {
	    		   st.pop();
	    	   }else {
	    		   nextEle= st.peek().next(); 
	    		   if(nextEle.isInteger()) {
	    			   return true;
	    		   }else {
	    			   st.push(nextEle.getList().iterator());
	    		   }
	    	   }
	       }
	       return false;
	    }
	}
}
