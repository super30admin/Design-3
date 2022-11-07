package Design-3;

public class problem1 {
    //TC:- O(1)
    //SC:- O(1)
    public class NestedIterator implements Iterator<Integer> {
    
        ArrayList<Integer> nums;
        int current = 0;
    
        public NestedIterator(List<NestedInteger> nestedList) {
             nums = new ArrayList<>();
            
            for(NestedInteger integer: nestedList)  {
                helper(integer);
            }
        }
    
        @Override
        public Integer next() {
            return nums.get(current++);
        }
    
        @Override
        public boolean hasNext() {
            return current<nums.size();
        }
        
        public void helper(NestedInteger value){
            if(value.isInteger()){
                nums.add(value.getInteger());
            }else{
                for(NestedInteger integer: value.getList()) {
                helper(integer);
            }
        }
    }
    }
    
}
