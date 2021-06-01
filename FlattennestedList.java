/*
Desc : dfs is applied, integers occuring are put in queue while when list encountered, we call dfs again to get through the list
and push integers encountered into to queue. 
Time complexity : next function O(1), hasNext function O(1), for preparing queue O(n)
Space complexity : O(1)
*/

public class NestedIterator implements Iterator<Integer> {
    Queue<Integer> q;
    public NestedIterator(List<NestedInteger> nestedList) {
        q = new LinkedList<>();
        dfs(nestedList);
    }

    @Override
    public Integer next() {
        return q.poll();
    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty();
    }
    
    private void dfs(List<NestedInteger> nestedList){
        for(NestedInteger ele:nestedList){
            if(ele.isInteger()){
                q.add(ele.getInteger());
            }else{
                dfs(ele.getList());
            }
        }
    }
}
