//https://codeforces.com/contest/1490/problem/C

import java.util.HashSet;
import java.io.*;


public class sumOfCube{
    static HashSet<Long> set= new HashSet<>();

    public static void precompute(long x){
        for ( long b= 1; b*b*b <= x; b++) // all possible valid b^3 
             set.add(b*b*b);
        return;
    }
    public static boolean solve(long x){

        for ( long a= 1; a*a*a <= x; a++){ // a <= x^1/3 <-> a^3 <= x

             long new_a_cu= a*a*a; //a^3
             long new_b_cu= x-(new_a_cu); //x- a^3

             if ( set.contains(new_b_cu)){ // b^3 <= x- a^3
                 //System.out.println(a+"^3 + "+(long)Math.cbrt(new_b_cu)+"^3 = "+ x+"."); //for checking purpose
                 return true; 
             }
        }
        return false;//no pair exists 
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

    public static void main(String[] args) throws Exception{ // x= a^3 + b^3
         Reader sc= new Reader();
         int t= sc.nextInt();

         precompute(100_000_00_00_000L);//L denotes long // All valid b cubes 

         while( t-- > 0){
            long x= sc.nextLong();
            System.out.println(solve(x) == true ?"YES": "NO"); //true means pair exists 
         }
    }
}
// ACTUAL 
/* 
(base) swapnil@swapnil:~$  /usr/bin/env /usr/lib/jvm/java-18-openjdk-amd64/bin/java --enable-preview -XX:+ShowCodeDetailsInExceptionMessages -cp /tmp/vscodesws_3864b/jdt_ws/jdt.ls-java-project/bin sumOfCube 
4
16
YES
32
FALSE
35
YES
703657519796
YES
*/

// TEST 
/* 
(base) swapnil@swapnil:~$  /usr/bin/env /usr/lib/jvm/java-18-openjdk-amd64/bin/java --enable-preview -XX:+ShowCodeDetailsInExceptionMessages -cp /tmp/vscodesws_3864b/jdt_ws/jdt.ls-java-project/bin sumOfCube 
4
16
2^3 + 2^3 = 16.
YES
32
FALSE
35
2^3 + 3^3 = 35.
YES
703657519796
5779^3 + 7993^3 = 703657519796.
YES
*/
