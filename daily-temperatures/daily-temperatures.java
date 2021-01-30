class Solution {
    public int[] dailyTemperatures(int[] T) {
        
       
        int[] result = new int[T.length];
​
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < T.length; i++) {
​
            while (!st.isEmpty() && T[st.peek()] < T[i]) {
                int index = st.pop();
                result[index] = i - index;
            }
            st.push(i);
​
        }
        return result;
    }
}
