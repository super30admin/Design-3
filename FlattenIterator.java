// Time Complexity :  O(N + L) - max len of nested array 
// Space Complexity : O(N) 
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No

public class NestedIterator implements Iterator<Integer> {
    List<Integer> result;
    Iterator<Integer> it;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        result = new ArrayList<>();
        flatten(nestedList);
        it = result.iterator();
    }
    
    private void flatten(List<NestedInteger> nestedList){
        for(NestedInteger i : nestedList){
            if(i.isInteger()){
                result.add(i.getInteger());
            }else{
                flatten(i.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return it.next();
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }
}