public class DSU {

    private int[] parent;
    private int[] size;
    private int count;

    public DSU(int n) {
        parent = new int[n + 1];
        size = new int[n + 1];
        count = n;
        for (int i = 1; i <= n; i++) {
            makeSet(i);
        }
    }

    public void makeSet(int v) {
        parent[v] = v;
        size[v] = 1;
    }

    public int findSet(int v) {
        if (v == parent[v]) {
            return v;
        }
        return parent[v] = findSet(parent[v]);
    }

    public void unionSets(int a, int b) {
        a = findSet(a);
        b = findSet(b);
        if (a != b) {
            if (size[a] > size[b]) {
                parent[b] = a;
                size[a] += size[b];
            } else {
                parent[a] = b;
                size[b] += size[a];
            }
            count--;
        }
    }

    public int getCount() {
        return count;
    }
}
