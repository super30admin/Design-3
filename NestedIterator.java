// Time Complexity : O(1)
// Space Complexity : O(N) where N is size of List
// Did this code successfully run on Leetcode : Yes


public class NestedIterator implements Iterator<Integer> {
    List<Integer> li;
    int idx;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.li = new ArrayList();
        dfs(nestedList);
    }

    @Override
    public Integer next() {
        int numToReturn = li.get(idx);
        idx++;
        return numToReturn;
    }

    @Override
    public boolean hasNext() {
        return idx != li.size();
    }
    private void dfs(List<NestedInteger> nestedList){
        for(NestedInteger el: nestedList){
            if(el.isInteger()){
                li.add(el.getInteger());
            }
            else{
                dfs(el.getList());
            }
        }
    }
}
