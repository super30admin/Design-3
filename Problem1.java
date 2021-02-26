//Time complexity-O(n)
//Space complexity-O(n)
//Ran on leetcode-no
//Solution with code- Failing null lost testcase

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
Stack<NestedInteger> st;//stack to store values of argument list
    public NestedIterator(List<NestedInteger> nestedList) {
        st= new Stack<NestedInteger>();
        int i=nestedList.size()-1;
        while( i>=0)
        {
            if(nestedList.get(i).isInteger()){//check if the element is integer
                st.push(nestedList.get(i));
            }
            else if(nestedList.get(i).getList().size()!=0){//check if it is not an empty list
                 st.push(nestedList.get(i));
            }
            i--;
        }
    }

    @Override
    public Integer next() {
        if(st.peek().isInteger()){
            return st.pop().getInteger();
        }
        else{
            List<NestedInteger> inner= new ArrayList<NestedInteger>(st.pop().getList());
            int i=inner.size()-1;
                while(i>=0){
                    st.push(inner.get(i));
                    i--;
                }
            
        }
        return st.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        if(!st.isEmpty())
            return true;
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */