public class NestedIterator implements Iterator<Integer> {

    Stack<NestedInteger> s;

    public NestedIterator(List<NestedInteger> nestedList) {
        s = new Stack<>();
        for(int i = nestedList.size()-1; i>=0; i--){
            //if(nestedList.get(i).isInteger() || nestedList.get(i).getList().size()>0)
            s.push(nestedList.get(i));
        }
        placeIntgerOnTop();
    }

    @Override
    public Integer next() {
        int val = s.pop().getInteger();
        placeIntgerOnTop();
        return val;
    }

    public void placeIntgerOnTop(){
        while(!s.isEmpty() && !s.peek().isInteger()){
            List<NestedInteger> curr = s.pop().getList();
            for(int i = curr.size()-1; i>=0; i--){
                //if(curr.get(i).isInteger() || curr.get(i).getList().size()>0)
                s.push(curr.get(i));
            }
        }
    }

    @Override
    public boolean hasNext() {
        return !s.isEmpty();
    }
}
