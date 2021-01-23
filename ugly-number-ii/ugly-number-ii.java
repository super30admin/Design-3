class Solution {
    public int nthUglyNumber1(int n) {
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
     public int nthUglyNumber(int n) {
​
        if (n <= 0)
            return 0;
​
     int p2 =0;int p3= 0;int p5 =0; int n2 = 2;int n3= 3; int n5 =5;
         int arr[] = new int[n+1];
        arr[0] = 1;
         for(int i = 1;i<=n;i++){
             int min = Math.min(n2,Math.min(n3,n5));
             arr[i] = min;
             if(min == n2){
                 p2++;
                 n2 = arr[p2] * 2;
             }
              if(min == n3){
                 p3++;
                 n3 = arr[p3] * 3;
             }
               if(min == n5){
                 p5++;
                 n5 = arr[p5] * 5;
             }
             
             
         }
         return arr[n-1];
         
​
    }
}
