# -*- coding: utf-8 -*-
"""
Created on Mon Feb  3 17:35:05 2020

@author: WELCOME
"""
"""
Working on Leetcode
Time - Every operation is O(1)
Space - O(capacity)
"""

class LinkedList:
    def __init__(self,key,value):
        self.key=key
        self.value=value
        self.next=None
        self.prev=None
        
class LRUCache:

    def __init__(self, capacity: int):
        self.hashMap={}
        self.capacity=capacity
        self.head=LinkedList(None,None)
        self.tail=LinkedList(None,None)
        self.head.next=self.tail
        self.tail.prev=self.head
        
    def insertNode(self,node):
        self.hashMap[node.key]=node
        self.head.next.prev=node
        node.next=self.head.next
        self.head.next=node
        node.prev=self.head
    
    def removeNode(self,node):
        temp= self.hashMap[node.key]
        del self.hashMap[node.key]
        node.next.prev=node.prev
        node.prev.next=node.next
        return temp

    def get(self, key: int) -> int:
        if key not in self.hashMap:
            return -1
        val=self.hashMap[key]
        temp=self.removeNode(self.hashMap[key])
        self.insertNode(temp)
        return val.value

    def put(self, key: int, value: int) -> None:
        if key in self.hashMap:
            self.hashMap[key].value=value
            temp=self.removeNode(self.hashMap[key])
            self.insertNode(temp)
        elif len(self.hashMap)<self.capacity:
            self.insertNode(LinkedList(key,value))
        else:
            leastUsedNode=self.tail.prev
            self.removeNode(leastUsedNode)
            self.insertNode(LinkedList(key,value))
