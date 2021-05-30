// Time Complexity: hasNext()-O(h) for worst case where h=depth of nested integer, O(1) for amortised
//                  next() - O(1)
// Space Complexity: O(h)
// Did it reun on leetcode: yes
// Did you face any problem: no
public class NestedIterator implements Iterator<Integer> {

    //stack to store native iterators
    Stack<Iterator<NestedInteger>> st;
    //variable to store NestedInteger
    NestedInteger nextEl;

    public NestedIterator(List<NestedInteger> nestedList) {
        //initializing stack
        st = new Stack<>();
        //adding given input to the stack
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        //return
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        //if stack is not empty
        while(!st.isEmpty()){
            //if top iterator don't have next element
            if(!st.peek().hasNext()){
                //pop top iterator
                st.pop();
            }//if top iterator does have next element
            else{
                //get next element in iterator
                nextEl = st.peek().next();
                //if next element in iterator is integer
                if(nextEl.isInteger()){
                    return true;
                }//not it is a NestedInteger
                else{
                    //get the list, convert it into iterator and push it to the stack
                    st.push(nextEl.getList().iterator());
                }
            }
        }
        //if stack is empty
        return false;
    }
}
