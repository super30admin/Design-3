// Time Complexity : O(N)
// Space Complexity : O(N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None

public class NestedIterator implements Iterator<Integer> {

    List<Integer> list;
    int idx;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.list = new ArrayList<>();
        this.idx = 0;
        helper(nestedList);
    }

    public void helper(List<NestedInteger> nestedList)
    {
        for(NestedInteger ne: nestedList){
            if(ne.isInteger()){
                list.add(ne.getInteger());
            }else{
                helper(ne.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return list.get(idx++);
    }

    @Override
    public boolean hasNext() {
        return idx < list.size();
    }
}