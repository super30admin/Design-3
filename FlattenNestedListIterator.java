// Time Complexity :O(k*log n) where n the length of the nestedListIterator, k is the avg length of the nested lists
// Space Complexity :O(n*k) where n is length of the nestedListIterator, k is the avg length of the nested lists
// Did this code successfully run on Leetcode :yes

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FlattenNestedListIterator {
    class NestedIterator implements Iterator<Integer> {
        private List<Integer> result;
        private int idx;

        public NestedIterator(List<NestedInteger> nestedList) {
            this.result = new ArrayList<>();
            //populate the result list
            helper(nestedList);
        }

        private void helper(List<NestedInteger> currentList){
            //base
            if(currentList.isEmpty()) return;
            for(int i=0; i<currentList.size(); i++){
                if(currentList.get(i).isInteger()){
                    result.add(currentList.get(i).getInteger());
                }else{
                    helper(currentList.get(i).getList());
                }
            }
        }
        @Override
        public Integer next() {
            return result.get(idx++);
        }
        @Override
        public boolean hasNext() {
            return idx < result.size();
        }
    }

    /**
     * // This is the interface that allows for creating nested lists.
     * // You should not implement it, or speculate about its implementation*/
     public interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }
}
