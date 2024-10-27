public class NestedIterator implements Iterator<Integer> {
    Queue<Integer> res;
    public NestedIterator(List<NestedInteger> nestedList) {
        res = new LinkedList<>();
        dfs(nestedList);
    }

    @Override
    public Integer next() {
        return res.poll();
    }

    @Override
    public boolean hasNext() {
        return !(res.isEmpty());
    }
    private void dfs(List<NestedInteger> nestedList){

        for(int i=0;i<nestedList.size();i++){
            NestedInteger el = nestedList.get(i);
            if(el.isInteger()){
                res.add(el.getInteger());
            }
            else{
                dfs(el.getList());
            }
        }
    }
}
