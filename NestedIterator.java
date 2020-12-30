/** Given a nested list of integers, implement an iterator to flatten it.
* TC  O(N + L) N be the total number of integers within the nested list, L is the total number of lists within the nested list 
* SC O(N + L)
*/
public class NestedIterator implements Iterator<Integer> {

    Stack<Iterator<NestedInteger>> stk;
    NestedInteger nextEle;
    public NestedIterator(List<NestedInteger> nestedList) {
        stk = new Stack<>();
        stk.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEle.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!stk.isEmpty()) {
            if(!stk.peek().hasNext()) {
                stk.pop();
            }
            else if ((nextEle = stk.peek().next()).isInteger()) {
                return true;
            }
            else {
                stk.push(nextEle.getList().iterator());
            }
        }
        return false;
    }
}

