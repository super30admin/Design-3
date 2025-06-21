// Time -> O(total no of ints)
// Space -> O(total no of ints)
public class NestedIterator implements Iterator<Integer> {
    private List<Integer> li;
    int idx = 0;
    public NestedIterator(List<NestedInteger> nestedList) {
        li = new ArrayList<>();
        dfs(nestedList);
    }
    private void dfs(List<NestedInteger> nestedList){
        //base case
        // if()
        //logic 
        for(NestedInteger element : nestedList){
            if(element.isInteger()){
                li.add(element.getInteger());
            }else{
                dfs(element.getList());
            }
        }
    }
    @Override
    public Integer next() {
        Integer result = li.get(idx);
        idx++;
        return result;
    }

    @Override
    public boolean hasNext() {
        if(idx >= li.size()) return false;
        return true;
    }
}
