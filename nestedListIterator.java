// TC : O(N * M) where N is elements in the list and M is maximum length of nested list
// SC : O(N * M) for the stack where we are storing N elements and M nested lists

// We will push all the lists and integers from the last of nestedList onto stack, So that we can access the starting lists/ integers of the list
// If the last inserted element is a integer, pop and return as the next element, if it is a list, run the function again for the nestedlist
// which will add all the elements in nestedlist onto stack from end of the list so that we can access starting element of the list
// this way we are iteratively accesing the integers from stack by pushing from the last because of the Stack property LIFO.

import java.util.*;

public class nestedListIterator {

	Deque<nestedInteger> st;
	public nestedListIterator(List<nestedInteger> nestedList) {
		st = new ArrayDeque<>();
		prepareStack(nestedList);
		
	}
	public Integer next() {
		if(hasNext()) {
			return st.pop().getInteger();
		}
		return null;
	}
	
	public boolean hasNext() {
		while(!st.isEmpty() && !st.peek().isInteger()) {
			List<nestedInteger> list = st.pop().getList();
			prepareStack(list);
		}
		return !st.isEmpty();
	}
	
	public void prepareStack(List<nestedInteger> nestedList) {
	// Traverse over the list and nested list	
		for(int i = nestedList.size()-1; i >=0 ;i--) {
			st.push(nestedList.get(i));
		}
	}
}
