//https://leetcode.com/problems/flatten-nested-list-iterator/
/*
Time: Next, hasNext is O(1), constructor is O(N)
Space: O(1)
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : None
*/
public class FlattenNestedListIterator {
    List<Integer> result = null;
    int current = 0;

    public NestedIterator(List<NestedInteger> nestedList) 
    {
        result = new ArrayList<>();
        
        for(NestedInteger integer : nestedList)
        {
            helper(integer);
        }
        
    }

    @Override
    public Integer next() {
        return result.get(current++);

    }

    @Override
    public boolean hasNext() {
        return current < result.size();
    }

    private void helper(NestedInteger value) {
        if (value.isInteger())
            result.add(value.getInteger());

        else // same as constructor code
        {
            for (NestedInteger integer : value.getList()) {
                helper(integer);
            }
        }

    }

}
