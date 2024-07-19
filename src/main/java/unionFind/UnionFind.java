package unionFind;

/**
 * @ClassName Union
 * @Description TODO
 * @Author @hzp
 * @Date 2024/5/25 21:28
 * @Version 1.0
 */
public class UnionFind {
    // 连通分量个数
    private int[] parent;
    // 存储每个节点的父节点
    private int count;

    public UnionFind(int n){
        this.count = n;
        parent = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }
    }

    // 寻找p的父节点的过程中进行路径压缩，
    public int find(int p){
        if(parent[p] != p){
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }
    // 将节点 p 和节点 q 连通,合并都是单向的（将j合并到i，即j的parent指向i的parent
    public void union(int p, int q){
        int p_parent = find(p);
        int q_parent = find(q);
        if(p_parent == q_parent) return;
        parent[p_parent] = q_parent;
        // 两个连通分量合并成一个连通分量
        count--;
    }
    // 判断节点 p 和节点 q 是否连通
    public boolean isConnect(int p, int q){
        int p_parent = find(p);
        int q_parent = find(q);
        return p_parent == q_parent;
    }
    // 返回图中的连通分量个数
    public int count() {
        return count;
    }

}
