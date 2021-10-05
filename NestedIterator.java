//https://leetcode.com/problems/flatten-nested-list-iterator/submissions/
import java.util.*;
public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextel;
   public NestedIterator(List<NestedInteger> nestedList) {
      
       st = new Stack<>();
       st.push(nestedList.iterator());
      
   }
   // time complexity : 1
   // space complexity : 1
   // did it run on leetcode : yes
   // any doubts : no 
   @Override
   public Integer next() {
       return nextel.getInteger();
   }

   // time complexity : number of nesting in the list
   // space complexity : number of nesting in the list
   // did it run on leetcode : yes
   // any doubts : no 
   @Override
   public boolean hasNext() {
        while(!st.isEmpty()){
           if(!st.peek().hasNext()){
               st.pop();
           }else if((nextel = st.peek().next()).isInteger()){
               return true;
           }else{
               st.push(nextel.getList().iterator());
           }
       }
       return false;
   }
}