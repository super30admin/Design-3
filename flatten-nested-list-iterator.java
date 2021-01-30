// Time Complexity : O(n) n : size of input , O(1) for next() and hasNext()
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
// flatten and add to list. if a node is not flatted call funcion recursively.

public class NestedIterator implements Iterator<Integer> {
    List<Integer> list;
    int p = 0;
    public NestedIterator(List<NestedInteger> nestedList) {
        list = new ArrayList<>();
        helper(nestedList);
    }
    public void helper(List<NestedInteger> nestedList) {
        for(int i = 0; i< nestedList.size();i++){
            if(nestedList.get(i).isInteger()){
                list.add(nestedList.get(i).getInteger());
            } else {
                helper(nestedList.get(i).getList());
            }
        }
    }
    @Override
    public Integer next() {
        return list.get(p++);
    }

    @Override
    public boolean hasNext() {
        return p < list.size();
    }
}
