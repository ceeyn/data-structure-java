import java.util.HashMap;

/**
 * @ClassName LRU
 * @Description TODO
 * @Author @hzp
 * @Date 2024/5/12 18:21
 * @Version 1.0
 */
public class LRU {
    private class Node {
        // 有key是为了容量满淘汰最久未访问节点时需根据node在hash表里删除，因此node必须带key
        private int key;
        private int value;
        private Node next,pre;
        public Node(int key,int value){
            this.key = key;
            this.value = value;
        }
    }
    private Node dummy;
    private HashMap<Integer,Node> keyToNode = new HashMap<>();
    int capacity;
    public LRU( int capacity){
        dummy = new Node(-1,-1);
        this.capacity  = capacity;
        dummy.next = dummy;
        dummy.pre = dummy;
    }
    public int get(int key) {
        Node node =  getNode(key);
        return node == null? -1 :node.value;
    }
    // 放入一本新书，1.若有这本书，就抽出来放到最上面【getNode】，更新版本
    // 2.若无这本书，放到最上面【putFront】，此时若容量大于最大，移除最后一本书【remove】
    public void put(int key, int value){
        Node node = getNode(key);
        // 更新，抽出一本书放在最上面，更新书内容
        if(node != null) node.value = value;
        else{
            if(keyToNode.size() < capacity){
                // 添加一本书放到最上面,
                Node new_node = new Node(key,value);
                keyToNode.put(key,new_node);
                // 放到最上面
                putFront(new_node);
            }else{
                // 维护keyToNode
                keyToNode.remove(dummy.pre.key);
                remove(dummy.pre);
                Node new_node = new Node(key,value);
                keyToNode.put(key,new_node);
                putFront(new_node);
            }
        }
    }


    // 将书抽出来【remove】放到最上面【putFront】
    public Node getNode(int key) {
        Node node = keyToNode.get(key);
        if(node == null) return null;
        else {
            remove(node);
            putFront(node);
        }
        return node;
    }
    public void remove(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public void putFront(Node node){
        node.next = dummy.next;
        node.next.pre = node;
        dummy.next = node;
        node.pre = dummy;
    }
}
