package segmentTree;

/**
 * @ClassName SegementTree
 * @Description TODO
 * @Author @hzp
 * @Date 2024/5/20 10:55
 * @Version 1.0
 */
public class SegmentTree<T> {
    private T[] data;
    private T[] tree;
    private Merger<T> merger;

    public SegmentTree(T[] data,Merger<T> merger){
        this.data = data;
        tree = (T[])new Object[data.length * 4];
        this.merger = merger;
        buildSegmentTree(0,0, data.length-1);
    }
    //              根节点：tree,0位置是d[0,2]的和6
    // 左子树tree,1位置是[0,1]的和 3 ，右子树tree,2位置是[2,2]的和 3
    // 左子树[0,1]再分，0，1
    // tree[6,3,3,1,2],
    // data:[1,2,3], build(1,0,1),build(2,2,2)，tree[2]填入3
    // 在treeIndex的位置创建表示区间[l...r]的线段树
    private void buildSegmentTree(int treeIndex, int l, int r){

        if(l == r){
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        // int mid = (l + r) / 2;
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public T query(int queryL, int queryR) {
        return query(0,0,data.length-1,queryL,queryR);
    }
    // 在以treeIndex为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
    // [1,5]的mid为3， 查[1,3]去左边，查[4,5]去右边，查[2,4]需综合两边结果
    public T query(int treeIndex, int l, int r, int queryL, int queryR){
        if(l == queryL && r == queryR){
            return tree[treeIndex];
        }
        int mid = l + (r - l) / 2;
        // treeIndex的节点分为[l...mid]和[mid+1...r]两部分
        if(queryR <= mid){
            return query(leftChild(treeIndex),l,mid,queryL,queryR);
        }else if(queryL >= mid+1){
            return query(rightChild(treeIndex),mid+1,r,queryL,queryR);
        }else{
            T left_res = query(leftChild(treeIndex),l,mid,queryL,mid);
            T right_res = query(rightChild(treeIndex),mid+1,r,mid+1,queryR);
            return merger.merge(left_res,right_res);
        }
    }

    public void set(int index, T val) {
        data[index] = val;

        set(0,0,data.length-1,index,val);
    }
    // 在以treeIndex为根的线段树中更新index的值为e
    public void set(int treeIndex, int l, int r, int index ,T val){
        if(l == r) {
            tree[treeIndex] = val;
            return;
        }
        // treeIndex的节点分为[l...mid]和[mid+1...r]两部分
        int mid = l + (r - l) / 2;

        if(index <= mid){
            set(leftChild(treeIndex),l,mid,index,val);
        }else{
            set(rightChild(treeIndex),mid+1,r,index,val);
        }
        tree[treeIndex] = merger.merge(tree[leftChild(treeIndex)],tree[rightChild(treeIndex)]);

    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index){
        return 2*index + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index){
        return 2*index + 2;
    }
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append('[');
        for(int i = 0 ; i < tree.length ; i ++){
            if(tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if(i != tree.length - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }
}
