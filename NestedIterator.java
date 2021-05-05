

public class NestedIterator implements Iterator<Integer> {

    Deque<Integer> q = new ArrayDeque<>();
    
    public NestedIterator(List<NestedInteger> nestedList) {
        flatten(nestedList);
        //System.out.println(q);
    }

    @Override
    public Integer next() {
        if(hasNext()) return q.poll();
        else return -1;
        
    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty();
    }
    
    private void flatten(List<NestedInteger> nestedList){
        for(NestedInteger n : nestedList){
            if(n.isInteger()){
                q.offer(n.getInteger());
            }else{
                flatten(n.getList());
            }
        }
    }
}
