class Solution {
    public boolean isIsomorphic(String s, String t) {
      
        if(s.length() != t.length())
            return false;
        
        HashMap<Character,Character> smap = new HashMap<>();
        HashMap<Character,Character> tmap = new HashMap<>();
        
        for(int i =0;i<s.length();i++){
            
            char sch = s.charAt(i);
            char tch = t.charAt(i);
            
            if(!smap.containsKey(sch))
            {
                smap.put(sch, tch);
                
            }else {
                if(smap.get(sch)!=tch)
                    return false;
                
            }
            if(!tmap.containsKey(tch))
            {
                tmap.put(tch, sch);
                
            }else {
                if(tmap.get(tch)!=sch)
                    return false;
                
            }
            
            
            
        }
        
        return true;
        
    }
}
