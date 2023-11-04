//Time Complexity  : O(1) User Functions
//Space Complexity : O(n) 

public class NestedIterator implements Iterator<Integer> {
    List<Integer> l;
    int index;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.l = new ArrayList<>();
        dfs(nestedList);
    }
    private void dfs(List<NestedInteger> nestedList){
        for(NestedInteger el: nestedList){
            if(el.isInteger()){
                l.add(el.getInteger());
            }
            else{
                dfs(el.getList());
            }
        }
    }

    @Override
    public Integer next() {
        int toReturn = l.get(index);
        index++;
        return toReturn;
    }

    @Override
    public boolean hasNext() {
        return index != l.size();
    }
}
