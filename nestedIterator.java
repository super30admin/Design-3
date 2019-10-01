//queue implementation- faster runtime.
//Time Complexity:O(N)-size of the nested list.
//Space Complexity:O(N) -- queue
//The purpose of using queue is to avoid the need to iterate through the list from the end to the front to add to the stack such that the first element of the list is at the bottom of the stack. Instead using queue's FIFO property, the first element in the queue will itself be the first element in the nested list. This is the optimization achieved in terms of runtime. I'll be adding an element to the queue in the flatten function if it is an integer, else if it is a list, I'll be recusively calling the same function to further flatten the list. I'll be returning the top element from the queue, if my hasnext function return true. else I'll be returning -1. My hasnext will return true, if the queue is not empty. If it is empty, it'll return false.
//This code was executed successfully and got accepted in leetcode.

public class NestedIterator implements Iterator<Integer> {
    Queue<Integer> queue=new LinkedList<>();
    public NestedIterator(List<NestedInteger> nestedList) {
        flatten(nestedList);
    }
    public void flatten(List<NestedInteger> list){
        if(list==null){
            return;
        }
        for(NestedInteger i:list){
            if(i.isInteger()){
                queue.add(i.getInteger());
            }
            else{
                flatten(i.getList());
            }
        }
    }

    @Override
    public Integer next() {
        if(hasNext()){
            return queue.poll();
        }
        else{
            return -1;
        }
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }
}

//Stack implementation
//Time Complexity:O(N)-size of the nested list.
//Space Complexity:O(N) -- stack
//here, instead of queue, a stack is implemented to do the same operation. Since is LIFO, In order to pop the first element, the list has to be iterated through and then added to the stack in such a way that the first element of the list is at the bottom of the stack. This operation hampers the runtime of the program although the complexities and operation remain the same as that of the previous method.
//This code was executed successfully and got accepted in leetcode. 
public class NestedIterator implements Iterator<Integer> {
    Stack<NestedInteger> stack=new Stack<>();
    public NestedIterator(List<NestedInteger> nestedList) {
      flatten(nestedList);
    }
    public void flatten(List<NestedInteger> list){
        if(list==null){
            return;
        }
        for(int i=list.size()-1;i>=0;i--){
            stack.push(list.get(i));
        }
        
    }

    @Override
    public Integer next() {
       return hasNext()?stack.pop().getInteger():-1;
    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty()){
            if(stack.peek().isInteger()){
                return true;
            }
            flatten(stack.pop().getList());
        }
        return false;
    }
}
