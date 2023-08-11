// Time Complexity :O(1)
// Space Complexity :2O(n) ~ O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :NO


// Your code here along with comments explaining your approach

//2nd Approach using 2 stacks
/*class MinStack {
    Stack<Integer> st;
    Stack<Integer> minSt; //for first approach
    int min;
    public MinStack() {
        this.st = new Stack<Integer>();
        this.minSt = new Stack<Integer>();
        this.min = Integer.MAX_VALUE;
        minSt.push(min);

    }

    public void push(int val) {
       this.min = Math.min(min, val); // First approach
        minSt.push(min);
        st.push(val);


    }

    public void pop() {
        if(st.peek() != null){
            st.pop();
            minSt.pop(); //after poping , min will be whatever is at the top of the minstack, done below
            min = minSt.peek();// v imp : you have to re assign again ;
        }

    }

    public int top() {
        return st.peek();
    }

    public int getMin() {
       return  minSt.peek();
    }
}*/
/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
// Time Complexity :O(1) amortized
// Space Complexity :maximum depth, No extra space used
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :NO

//Creating stack of iterators. Lazy Approach
public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextEl;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack<>();
        st.push(nestedList.iterator());

    }

    @Override
    public Integer next() { //gets called after hasNext, if its true then next is called and it retrieves nextElements --> integer value
        return nextEl.getInteger();

    }

    @Override
    public boolean hasNext() {// parents Hasnext
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();     //it first checks the top most value and then goes to next
            }else if((nextEl = st.peek().next()).isInteger()){ // see brackets, we are checking if its Integer or not
                return true;
            }else{
                st.push(nextEl.getList().iterator()); // has to be ib else and not outside, if outside it gives TLE
            }
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

//Eager approach ie calling from constructor itself

/* public class NestedIterator implements Iterator<Integer> {
     List<Integer> li;
     int i;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.li = new ArrayList<>();
        dfs(nestedList);
    }

    private void dfs(List<NestedInteger> nestedList){
        for(NestedInteger el : nestedList ){
            if(el.isInteger()){
                li.add(el.getInteger());
            }else{
                dfs(el.getList());//passing that list again
            }

        }
    }

    @Override //o(1)
    public Integer next() {// checking in list
        Integer res = li.get(i);
        i++;
        return res;


    }

    @Override//o(1)
    public boolean hasNext() {// checking in list
        if(i == li.size()) return false;
        return true;

    }
}*/