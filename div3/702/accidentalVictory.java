import java.util.*;
import java.io.*;

public class accidentalVictory{
    
    static int[] arr;
    static int[] org;

    public static void solve(int n){
         Arrays.sort(arr);

         Map<Integer, Integer> map= new LinkedHashMap<>();

         for ( int i= 0; i< n; i++ ){
            if ( map.containsKey(arr[i]))
                 map.put( arr[i], map.get(arr[i])+ arr[i]);
            else
                 map.put(arr[i], arr[i]);
         }

         int c= map.size();

         int[] uniq= new int[c];
         int []prefixArray= new int[c];

         int p= 0;
         for ( int key:  map.keySet()){
            if ( p == 0) {
                 uniq[p]= key;
                 prefixArray[0]= map.get(key);
                 p+= 1;
            }
            else {
                 uniq[p]= key;
                 prefixArray[p]= prefixArray[p-1]+ map.get(key);
                 p+= 1;
            } 
         }
         
         int ans= 0;
         int maxSuffix= 0;
         for ( int i= 0; i< c; i++){
            if ( i != 0 && ans < arr[i])
                 maxSuffix= arr[i-1];

            ans= prefixArray[i];
         }
         
         int noOfElementCannotWin= 0;
         
         for ( int  i= 0; i < n; i++) {
             if (org[i] <= maxSuffix )
                 noOfElementCannotWin+= 1;
         } 
         System.out.println(n-noOfElementCannotWin);

         for ( int  i= 0; i < n; i++) {
            if (org[i] > maxSuffix )
                 System.out.print((i+1)+" ");
        }
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

    public static void main(String[] args) throws Exception {
        Reader sc= new Reader();
        
        int t= sc.nextInt();

        while ( t -- > 0 ){
            int n= sc.nextInt();
            
            arr= new int[n];
            org= new int[n];
            
            for ( int i = 0; i< n; i++){
                arr[i]= sc.nextInt();
                org[i]= arr[i];
            }
            System.out.println();
    
            solve(n);
        }
    }
}