
// Time - for the constructor its is O(N + L) - Integers + list of Integers, for hasNext(), next it is O(1)

// Space - O(N + D)  ArrayList size + Max depth of Recursive Stack for DFS


public class NestedIterator implements Iterator<Integer> {

    // declare a stack
    Stack<Iterator<NestedInteger>> stk;
    // declare nextEl
    NestedInteger nextEl;

    public NestedIterator(List<NestedInteger> nestedList) {
        stk = new Stack<>();
        // push all the nested list elements in the stack
        stk.push(nestedList.iterator());

    }


    @Override
    public Integer next() {

        return nextEl.getInteger();

    }

    @Override
    public boolean hasNext() {


        while(!stk.isEmpty()) {

            //case 1 - check if the pointer to the hasNext element has any element if not start popping the stack
            if(!stk.peek().hasNext()) {
                stk.pop();
            }
            // case 2 - if the nextEl element in the stk is Integer return true
            else if((nextEl = stk.peek().next()).isInteger()) {

                return true;

            }
            // case 3 - if the next element in the stk is not integer then push the list on top of the stack
            else {
                stk.push(nextEl.getList().iterator());
            }


        }

        return false;


    }
}