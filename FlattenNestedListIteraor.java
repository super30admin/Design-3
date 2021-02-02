//Problem 79 : Flatten Nested List Iterator
//TC: Amortized will be O(1), worst case : O(number of lists);
//SC: O(Number of lists in the Nested Lists)

/*
Steps 
 Bruteforce: TC: O(1) because recursive call will happen during constructor call | SC: (O(numbers)). Make recursive call, unwrap the lists and add all elements to the queue or in the list. Then poll the element from the queue or with the help of global pointer just return the element from the list.

 Optimized : Use the concept of iterator. Initialise stack with the datatype as Iterator.
              //Note: for iterator once we do iterator.next(), it will give the current value and will move the pointer to next  
              //Here we are using nextElem so that we won't loose the current value when we do iterator.next();

             Under hasNext 3 important conditions
             1) checking iterator hasNext, if there is nothing means iterator has nothing to iterate just pop peek from the stack
             2) if peek element is integer just return true;
             3) if peek element was not integer, just add nextElem as iterator in stack

*/


//Optimised Using Concept of Iterator and Controlled Recursion
public class NestedIterator implements Iterator<Integer> {

    Stack<Iterator<NestedInteger>> st;
    //Note: for iterator once we do iterator.next(), it will give the current value and will move the pointer to next  
    //Here we are using nextElem so that we won't loose the current value when we do iterator.next();
    NestedInteger nextElem;
    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextElem.getInteger();
    }

    @Override
    public boolean hasNext() {
        
        while(!st.isEmpty()){
            
            if(!st.peek().hasNext()){//checking iterator hasNext, if there is nothing means iterator has nothing to iterate just pop peek from the stack
                st.pop();
            }else if((nextElem = st.peek().next()).isInteger()){//if peek element is integer just return
                return true;
            }else{//if peek element was not integer, just add nextElem as iterator in stack
                st.push(nextElem.getList().iterator());
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

 //Bruteforce

 /*
 //Making recursive call and add all the elements in the list and then with the help of global pointer returning next integer and checking has next;
public class NestedIterator implements Iterator<Integer> {

    private List<Integer> result;
    private int idx;
    public NestedIterator(List<NestedInteger> nestedList) {
        result = new ArrayList<>();
        helper(nestedList);
    }

    @Override
    public Integer next() {
        return result.get(idx++);
    }

    @Override
    public boolean hasNext() {
        return idx<result.size();
    }
    
    private void helper(List<NestedInteger> nestedList){
        
        //System.out.println(nestedList);
        
        for(NestedInteger list : nestedList){
            
            if(list.isInteger()){
                result.add(list.getInteger());
            }else{
                helper(list.getList());
            }
            
        }
        
    }
    
}

 */
