// https://codeforces.com/problemset/problem/1490/D 

import java.io.*;
import java.util.*;

public class permutationTransformation{
    static int []arr;

    public static  int[] compute(int left, int right, int depth, int[] depthStore){
          if ( left > right || left > arr.length-1 || left < 0 || right > arr.length-1 || right < 0 ){
              return depthStore;
          }

          int currMax= Integer.MIN_VALUE;
          int currMax_idx= -1;
          for (int i= left; i <= right; i++){
                 if ( currMax < arr[i]){
                     currMax=  arr[i];
                     currMax_idx= i;
                 }
          }
          depthStore[currMax_idx]= depth;

          // Left 
          compute(currMax_idx+ 1, right, depth+ 1, depthStore);

          // Right
          compute(left,  currMax_idx- 1, depth+ 1, depthStore);

          return depthStore;

    }
    // Fast I/O
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;
  
        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
  
        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(
                new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
  
        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    if (cnt != 0) {
                        break;
                    }
                    else {
                        continue;
                    }
                }
                buf[cnt++] = (byte)c;
            }
            return new String(buf, 0, cnt);
        }
  
        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
  
            if (neg)
                return -ret;
            return ret;
        }
  
        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }
  
        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
  
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
  
            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }
  
            if (neg)
                return -ret;
            return ret;
        }
  
        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0,
                                 BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }
  
        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
  
        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }
    public static void main(String[] args) throws Exception{
        Reader sc= new Reader();

        int t= sc.nextInt();

        while ( t -- > 0 ){
            int n= sc.nextInt();

            arr= new int[n];

            for ( int i= 0; i< n; i++)
                 arr[i]= sc.nextInt();

            int[] str= compute(0,n-1,0, new int[n]);

            for ( int i= 0;i < n; i++)
                 System.out.print(str[i]+" ");

            System.out.println();
        }
    }
}
/*
 (base) swapnil@swapnil:~$  /usr/bin/env /usr/lib/jvm/java-18-openjdk-amd64/bin/java --enable-preview -XX:+ShowCodeDetailsInExceptionMessages -cp /tmp/vscodesws_c3dae/jdt_ws/jdt.ls-java-project/bin permutationTransformation 
3
5
3 5 2 1 4
1 0 2 3 1 
1
1
0 
4
4 3 1 2
0 1 3 2 
 */