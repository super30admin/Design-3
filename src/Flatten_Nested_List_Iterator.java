import java.util.*;
//This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
interface NestedInteger {

	// @return true if this NestedInteger holds a single integer, rather than a nested list.
	public boolean isInteger();

	// @return the single integer that this NestedInteger holds, if it holds a single integer
	// Return null if this NestedInteger holds a nested list
	public Integer getInteger();

	// @return the nested list that this NestedInteger holds, if it holds a nested list
	// Return null if this NestedInteger holds a single integer
	public List<NestedInteger> getList();
}
/*************************************************************************************/
//Time Complexity :	O(1) average : hasnext gives O(n) but average O(1)
//Space Complexity : O(n) stack
//Did this code successfully run on Leetcode : Yes
//Any problem you faced while coding this : No

/**We do not flatten here, instead makes use of hasNext***/

/* Iterators for every list are stored in a stack, if the iterator does not have a next it is discarded,
 * otherwise if an integer is encountered it updates the global variable to be used by next
 * else if a list is encounter, the iterator for that list is added to the stack*/

class NestedIterator implements Iterator<Integer> {
	Stack<Iterator<NestedInteger>> stack;
	NestedInteger nextElement;
	public NestedIterator(List<NestedInteger> nestedList) {
		stack = new Stack<>();
		stack.push(nestedList.iterator());
	}

	@Override
	public Integer next() {
		return nextElement.getInteger();     //get the integer from the nextEl nestedinteger element
	}

	@Override
	public boolean hasNext() {
		while(!stack.isEmpty()){
			//case1, //check if the element at peek has a next element, if not it has been traversed so pop
			if(!stack.peek().hasNext()) 
				stack.pop();
			//case2, // check if the peek next element is integer, next moves the iterator so storing the value in nextEl
			else if((nextElement = stack.peek().next()).isInteger())
				return true;
			//case3, // get the list and put an iterator and push to stack
			else
				stack.push(nextElement.getList().iterator());
		}
		return false;
	}
}

/*******************************Recursion*********************************/
//Time Complexity :	O(n) ,iterating through the list + recursion stack space
//Space Complexity : O(n) queue
//Did this code successfully run on Leetcode : Yes
//Any problem you faced while coding this : No

/* Using recursion and queue, flattens all the lists into one single list and removes and returns when next is called*/

public class Flatten_Nested_List_Iterator implements Iterator<Integer> {
	Queue<Integer> q;
	public Flatten_Nested_List_Iterator(List<NestedInteger> nestedList) {
		q = new LinkedList<>();
		flatten(nestedList);
	}

	@Override
	public Integer next() {
		return q.poll();
	}

	@Override
	public boolean hasNext() {
		return !q.isEmpty();
	}
	private void flatten(List<NestedInteger> nestedList){
		//base case

		//logic
		for(NestedInteger element: nestedList){
			if(element.isInteger()){
				q.add(element.getInteger());
			}
			else{
				flatten(element.getList());
			}
		}
	}
}
