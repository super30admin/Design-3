

public class NestedIterator implements Iterator<Integer> {

    Queue<Integer> q;
    public NestedIterator(List<NestedInteger> nestedList) {
        q = new LinkedList<>();
        flatten(nestedList);
    }

    @Override
    public Integer next() {
        return q.poll();
    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty();
    }
    
    private void flatten(List<NestedInteger> nestedList){
        for(NestedInteger nl : nestedList){
            if(nl.isInteger()){
                q.add(nl.getInteger());
            }else{
                flatten(nl.getList());
            }
        }
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
