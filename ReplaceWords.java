import java.util.List;
//Time Complexity O(NK(Dictionary)+(Sentence)LM)
//Space Complexity O(NK) (Dictionary Words)
//leetCode tested
public class ReplaceWords {
        public class Tr{
            Tr children[] = new Tr[26];
            String str;
        }
        public void insert(Tr current, String s){
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if(current.children[c-'a'] == null){
                    current.children[c-'a'] = new Tr();
                }
                current = current.children[c-'a'];

                if(current.str!=null) return;
            }
            current.str = s;
        }

        String[] arrayOfSen;
        public String replaceWords(List<String> dictionary, String sentence) {
            arrayOfSen = sentence.split(" ");
            Tr trie = new Tr();
            for (String word:dictionary) {
                insert(trie,word);
            }
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < arrayOfSen.length; i++) {
                String pre = dfs(trie,arrayOfSen[i]);
                if(pre!=null){
                    result.append(pre);
                }else result.append(arrayOfSen[i]);

                result.append(" ");
            }
            return result.toString().strip();
        }

        public String dfs(Tr root,String s){
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if(root.children[ch-'a'] == null){
                    return null;
                }
                root = root.children[ch - 'a'];
                if(root.str!=null){
                    return root.str;
                }
            }
            return null;

        }
}
