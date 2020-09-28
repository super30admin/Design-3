// Time Complexity : O(1)
// Space Complexity : O(n) space for the hashmap 
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : no issues as of now.. Learning


class node
{ 
    public:
    int key,value;
    node *next,*pre; //doubly linked list
    
    node(int k,int val)
    {
        key=k;
        value=val;
        next=pre=nullptr;
    }
};

class LRUCache {
public:
    
    unordered_map<int,node*> mp;
    
    node *head,*tail;
    int count,cap;
    
    LRUCache(int capacity) {
        head=new node(0,0); //creating a dummy head
        tail=new node(0,0); //creating a dummy tail
        
        head->next=tail;    //poiting head to tail
        tail->pre=head;     //pointing tail to head
        
        count=0;            //setting count to 0
        cap=capacity;       //setting capacity
    }
    
    void del(node *temp)
    {
        mp.erase(temp->key);         //deleting entry in hashmap
        
        temp->next->pre=temp->pre; 
        temp->pre->next=temp->next;  //fixing the link of previous and next node
        
        delete temp;                 // avoiding memory leak
    }
    
    void add(int key,int value)//this will add at begining of the list
    {
       node *temp=new node(key,value); //creating a node to add
       mp[key]=temp;                   //adding in the map
        
       temp->next=head->next;           
       head->next->pre=temp;
       temp->pre=head;
       head->next=temp;                //fixing link
    }
    
    int get(int key) {
        
        if(mp.find(key)==mp.end())
            return -1;              //if not present
        
        int val=mp[key]->value;     //retreiving value
        
        del(mp[key]);               //delete node and 
        add(key,val);               //updating it 
        
        return val;                 //returning the value
        
    }
    
    void put(int key, int value) {
       
        if(mp.find(key)==mp.end())          //if not present
        {
            if(count==cap) del(tail->pre);  //if at max capacity remove the last node
            else count++;                   //else increase count
            
        }
        else
            del(mp[key]);                   //found therefore delete (will update later)
        
            add(key,value);                 //add to the begining
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */