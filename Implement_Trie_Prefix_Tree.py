class TrieNode:
    
    def __init__(self):
        self.hasharr = [None for i in range(26)]
        self.check = False
    
class Trie:

    def __init__(self):
        self.BaseNode = TrieNode()
        
    def insert(self, word: str) -> None:
        temp_base = self.BaseNode
        for i,char in enumerate(word):
            if temp_base.hasharr[ord(char) - ord('a')] is None:
                temp_base.hasharr[ord(char) - ord('a')] = TrieNode()
            temp_base = temp_base.hasharr[ord(char) - ord('a')]
            if i == len(word)-1:
                temp_base.check = True 

    def search(self, word: str) -> bool:
        i = 0
        
        temp = self.BaseNode
        check = False
        while i < len(word):
            if temp.hasharr[ord(word[i]) - ord('a')] is None or not temp.hasharr[ord(word[i]) - ord('a')].check:
                return False
            else:
                temp = temp.hasharr[ord(word[i]) - ord('a')]
                i += 1
        return True and temp.check

    def startsWith(self, prefix: str) -> bool:
        i = 0
        temp = self.BaseNode
        while i < len(prefix):
            if temp.hasharr[ord(prefix[i]) - ord('a')] is None:
                return False
            else:
                temp = temp.hasharr[ord(prefix[i]) - ord('a')]
                i += 1
        return True 


# Your Trie object will be instantiated and called as such:
# obj = Trie()
# obj.insert(word)
# param_2 = obj.search(word)
# param_3 = obj.startsWith(prefix)