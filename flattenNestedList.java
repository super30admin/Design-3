 // TC: O(N) since we are recursing over the nestedList and visiting all the elements in list
// SC : O(N) for the recursion stack

// Recurse the NestedInteger list, if the list has integer, we add that value to our resulting list, if the nestedInteger list is again a list,
// We get that List with our predefined given functions and iterate over that recursively.
// Now, we can easily iterate over the list to get our next elements.

import java.util.*;

public class flattenNestedList {

	public class NestedInteger {

		public boolean isInteger() {
			// TODO Auto-generated method stub
			return false;
		}

		public Integer getInteger() {
			// TODO Auto-generated method stub
			return null;
		}

		public List<NestedInteger> getList(){
			
			List<NestedInteger> res = new ArrayList<>();
			return res;
		}
		
	}

	List<Integer> list;
	public flattenNestedList(List<NestedInteger> nestedList) {
		list = new ArrayList<>();
		flattenList(nestedList);
	}
	
	public boolean hasNext() {
		return list.size()!=0;
	}
	
	public Integer next() {
		
		if(hasNext()) {
			return list.remove(0);
		}
		return null;
	}
	
	public void flattenList(List<NestedInteger> nestedList) {
		
		for(NestedInteger nestedInteger: nestedList) {
			if(nestedInteger.isInteger()) {
				list.add(nestedInteger.getInteger());
			}
			else {
				flattenList(nestedList.getList());
			}
		}
	}
}
