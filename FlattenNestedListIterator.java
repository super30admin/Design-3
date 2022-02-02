package design3;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

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
	 
	 //Handles Dynamic Concurrency data by using a controlled stack
	 //Time Complexity : O(h) in the worst case, where h is the depth of nested list, 
	 //average is O(1)
	 //Space Complexity : O(n) for explicit stack
	 //Did this code successfully run on Leetcode : Yes
	 //Any problem you faced while coding this : Followed the method used in class
	 public class NestedIterator implements Iterator<Integer> {
		    Stack<Iterator<NestedInteger>> st;
		    NestedInteger el;
		    public NestedIterator(List<NestedInteger> nestedList) {
		        st = new Stack<>();
		        st.push(nestedList.iterator());
		    }

		    @Override
		    public Integer next() {
		        return el.getInteger();
		    }

		    @Override
		    public boolean hasNext() {
		        while(!st.isEmpty()) {
		            if(!st.peek().hasNext())
		                st.pop();
		            else if((el = st.peek().next()).isInteger())
		                return true;
		            else
		                st.push(el.getList().iterator());
		        }
		        return false;
		    }
		}

	 //Time Complexity : O(1) everything is done in the constructor
	 //Space Complexity : O(n) for queue
	 //Did this code successfully run on Leetcode : Yes
	 //Any problem you faced while coding this : No
	 public class NestedIterator1 implements Iterator<Integer> {
		 Queue<Integer> q;
		 public NestedIterator1(List<NestedInteger> nestedList) {
			 q = new LinkedList<>();
			 dfs(nestedList);
		 }

		 @Override
		 public Integer next() {
			 return q.poll();
		 }

		 @Override
		 public boolean hasNext() {
			 return !q.isEmpty();
		 }
	    
		 private void dfs(List<NestedInteger> nestedList) {
			 for(NestedInteger elem: nestedList) {
				 if(elem.isInteger())
					 q.offer(elem.getInteger());
				 else 
					 dfs(elem.getList());
			 }
		 }
	 }
}
