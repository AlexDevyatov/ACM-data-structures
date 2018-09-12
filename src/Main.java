import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Solver solver = new Solver(in, out);
        solver.solve();
        out.close();
    }

    static class Solver {

        InputReader in;
        PrintWriter out;

        public Solver(InputReader in, PrintWriter out) {
            this.in = in;
            this.out = out;
        }

        int n, m;
        List<IntIntPair> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        public void solve() {
            n = in.nextInt();
            m = in.nextInt();
            for (int i = 0; i < m; i++) {
                list.add(new IntIntPair(in.nextInt(), in.nextInt()));
            }
            int q = in.nextInt();
            List<Integer> query = new ArrayList<>();
            for (int i = 0; i < q; i++) {
                int tmp = in.nextInt() - 1;
                query.add(tmp);
                set.add(tmp);
            }

            DSU dsu = new DSU(n);
            for (int i = 0; i < list.size(); i++) {
                if (!set.contains(i)) {
                    int u = list.get(i).first;
                    int v = list.get(i).second;
                    dsu.unionSets(u, v);
                }
            }

            Collections.reverse(query);
            List<Integer> ans = new ArrayList<>();
            ans.add(dsu.getCount());
            for (Integer num : query) {
                int u = list.get(num).first;
                int v = list.get(num).second;
                dsu.unionSets(u, v);
                ans.add(dsu.getCount());
            }
            ans.remove(ans.size()-1);

            Collections.reverse(ans);
            for (Integer i : ans) {
                out.print(i + " ");
            }
        }

        private int int2bool(int a) {
            if (a != 0) return 1;
            return 0;
        }

        private long binpow(long a, long n) {
            long res = 1;
            while (n > 0) {
                if (n % 2 == 1)
                    res *= a;
                a *= a;
                n >>= 1;
            }
            return res;
        }

        class IntIntPair implements Comparable<IntIntPair> {

            public Integer first;
            public Integer second;

            public IntIntPair() {
                this.first = -1;
                this.second = -1;
            }

            public IntIntPair(Integer first, Integer second) {
                this.first = first;
                this.second = second;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                IntIntPair that = (IntIntPair) o;
                return Objects.equals(first, that.first) &&
                        Objects.equals(second, that.second);
            }

            @Override
            public int hashCode() {

                return Objects.hash(first, second);
            }

            @Override
            public int compareTo(IntIntPair intIntPair) {
                return second.compareTo(intIntPair.second);
            }
        }
    }


    static class ArrayUtils {
        public static void fill(int[][] array, int value) {
            for (int[] row : array) {
                Arrays.fill(row, value);
            }
        }

    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public String nextLine() {
            tokenizer = null;
            try {
                return reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

    }

}
