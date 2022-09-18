import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class FlattenNestedIterator implements Iterator<Integer>{
  /*
  [1,2,[3,[4,5]] ]

  Depth, Depth
  Whereever we encounter nesting we will call recursion till the time we are not reaching inner most integer.
  we store it in a list and get the elemets one by one.
  But this thought process won't support the dynamicability of the data, if added or removed.
  */  

  /* 
  Iterator should be worried about just the next element
  In BST Iterator we did controlled recursion
  NestedIterator: Data Structure that I am building
  NativeIterator: Ordinary Iterrator

  Both of them have next and hasNext
   [1,2,[3,[4,5]] ]
   We will be putting an ordinary Iterator on this data structure
   and we push that ordinary iterator into the stack  
   nextElement maintains next integer that it has to give out
   Controlled Recursion: 
   We put iterrator till the point we don't get an integer
   next point of the native iterator gives us an integer or not
   How do we get access of the first iterator inside the stack? stack.peek()
   how do we get the next element of the current iterator? Iterator.next()
   Each element is not a normal element it is a nested integer class
   check if it's an integer or not, if yes set the nextElement with this
   the moment we do a .next() we lose access to that element
   after storing we can check if next element is integer or not
   now we will be using hasNext function of nested iterrator to set nextElement
   Job of hasNext is to set nextElement
   nextElement can be a nestedList
   we get the list out of that nextedlist and put the iterrator of the list into the stack
   stack.push(nextElement.getList().iterator())
   we will not stop until next point of our iterrator is set to an integer
   We check if the stack.peek() i.e current itertaor has any elements left i.e hasNext as true or not
   if itertaor is finished then pop()
   At no point we are flattening the whole thing at once, one by one
  */

  Stack<Iterator<NestedInteger>> st;
    NestedInteger nextElement;
    public FlattenNestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextElement.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty())
        {
            //curent iterator not left with any element
            if(!st.peek().hasNext())
            {
                //remove it
                st.pop();
                
            }
            //it does have element then we will get access to the iterator at the top of my stack and its next element, we will store it in out next element and check if it's integer or not
            else if((nextElement = st.peek().next()).isInteger())
            {
                return true;
                
            }
            else{
                st.push(nextElement.getList().iterator());
            }
        }
        
        //if stack becomes empty then we return false
        return false;
    }



}
