package tire;
import java.util.TreeMap;

/**
 * @ClassName Trie
 * @Description TODO
 * @Author @hzp
 * @Date 2024/5/21 07:39
 * @Version 1.0
 */
// lc 208, 677,211
public class Trie {
    //                node
    //           / / / \ \ \
    //           node node node node
    // 一条边+一个节点才是完整的一个node，例如c的边，c这个字符的节点
    // 边是字符，节点是node,例如"aa"，"ba"
    //                  root：""，next["a:a对应的节点","b:b对应的节点"]
    //          边：a                          边b
    //   第一个儿子：边a对应的node            第二个儿子：边b对应的node,next为空
    //          边a                            null
    //  第一个儿子: 边a对应的node ,next为空
    //          null
    class Node{
        private boolean isWord;
        private TreeMap<Character,Node> next;
        public Node(boolean isLast){
            this.isWord = isLast;
            next = new TreeMap<>();
        }
        public Node(){
            this(false);
        }
    }
    private Node root;
    private int size;

    public Trie(){
        root = new Node();
        size = 0;
    }
    // 向Trie中添加一个新的单词word
    public void add(String s){
        Node cur = root;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(cur.next.get(c) == null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }
        if(!cur.isWord) {
            cur.isWord = true;
            size ++;
        }
    }
    // 查询单词word是否在Trie中
    public boolean contains(String s){
        return contains(s,0,root);
    }
    public boolean contains(String s, int i,Node node) {
        if(i == s.length()){
            return node.isWord;
        }
        boolean cur_res = false;
//        for(char c : node.next.keySet()){
//            if(s.charAt(i) == node.next.) {
        if(node.next.get(s.charAt(i)) == null) return false;
                cur_res |= contains(s,i+1,node.next.get(s.charAt(i)));
//            }
//        }
        return cur_res;
    }
    // 查询是否在Trie中有单词以prefix为前缀
    public boolean isPrefix(String s) {
        Node cur = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(cur.next.get(c) == null){
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }
}
