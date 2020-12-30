                
                j++;
            }else{
                
                j--;
            }
            //just checking 
            if(j==r){
                i--;              
                j--;
                even++;
                
            }
            if(j<0){
                i--;              
                j++;
                even++;
            }
            
        }
        int minMoves=0;
        
        Queue<Integer> q = new LinkedList<>();
        q.add(0); singleBoard[0]= -2;
        while(!q.isEmpty()){
            int size = q.size();
            for(int k = 0;k<size;k++){            
                int curr = q.poll();
                if(curr == singleBoard.length-1){
                    return minMoves;
                }
                for(int l =1;l<=6;l++){
                    int baby = curr + l;
                    if(baby <= singleBoard.length-1 && singleBoard[baby]!=-2){
                        if(singleBoard[baby]== -1){
                            q.add(baby);
                        }else{
                            q.add(singleBoard[baby]);
                        }
                          singleBoard[baby] = -2;
                    }
                    
                    
                }
                
            }
            minMoves++;
        }
        
        
        return -1;
    }
}
