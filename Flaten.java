//https://leetcode.com/problems/flatten-nested-list-iterator/
// Time Complexity : O(1)
// Space Complexity : O(N)
public class NestedIterator implements Iterator<Integer> {
    Queue<Integer> q;
    public NestedIterator(List<NestedInteger> nestedList) {
        q = new LinkedList<>();
        unpack(q,nestedList);
    }
    
    
    public void unpack(Queue<Integer> q,List<NestedInteger> nestedList){
        for(NestedInteger element : nestedList){
            if(element.isInteger()){
                q.add(element.getInteger());
            }else{
               unpack(q,element.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return q.poll();
    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty();
    }
}
