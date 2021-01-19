class Solution {
    public int nthUglyNumber(int n) {
​
        if (n <= 0)
            return 0;
​
        HashSet<Long> set = new HashSet<>();
        PriorityQueue<Long> pq = new PriorityQueue<>();
        long ugly = 1;
        set.add(ugly);
        pq.add(ugly);
        int [] primes = {2,3,5};
        for(int i = 1 ;i<= n;i++){
            ugly = pq.poll();
            for(int prime : primes){
                long nUgly = prime * ugly;
                 if(!set.contains(nUgly)){
                    set.add(nUgly);
                     pq.add(nUgly);
                   }
                
            }
           
            
            
        }
        return Math.toIntExact(ugly);
​
    }
}
