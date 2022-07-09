//Time complexity:O(n^2)
//space comlpexity: O(1)
public class NestedIterator implements Iterator<Integer> {
    int curr=0;
List<Integer> res;
    public NestedIterator(List<NestedInteger> nestedList) {
        res=new ArrayList<>();
        for(NestedInteger val : nestedList){
                helper(val);
        }
    }

    //Time complexity:O(1)
//space comlpexity: O(1)
    @Override
    public Integer next() {
        return res.get(curr++);
        
    }

    //Time complexity:O(1)
//space comlpexity: O(1)
    @Override
    public boolean hasNext() {
        if(curr>=res.size()) return false;
        return true;
    }
    
    private void helper(NestedInteger val){
         if(val.isInteger()) res.add(val.getInteger());
            else {
                for(NestedInteger n : val.getList()){
                    helper(n);
                }
    }
}
}
