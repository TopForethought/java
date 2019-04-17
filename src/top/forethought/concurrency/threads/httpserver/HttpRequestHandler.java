package top.forethought.concurrency.threads.httpserver;

import java.io.*;
import java.net.Socket;

import static top.forethought.concurrency.threads.httpserver.SimpleHttpServer.basePath;

public class HttpRequestHandler implements  Runnable {
  private Socket socket;
  public HttpRequestHandler(Socket socket){
      this.socket=socket;
  }

    @Override
    public void run() {
        String line=null;
        BufferedReader bufferedReader=null;
        PrintWriter out=null;
        InputStream in=null;
        try {
            bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String header=bufferedReader.readLine();
        String filePath=basePath+header.split(" ")[1];
        out=new PrintWriter(socket.getOutputStream());
        if(filePath.endsWith("jpg")||filePath.endsWith("ico")){
            in=new FileInputStream(filePath);
            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            int i=0;
            while ((i=in.read())!=-1){
                byteArrayOutputStream.write(i);
            }
            byte [] array=byteArrayOutputStream.toByteArray();
            out.println("HTTP/1.1 200 OK");
            out.println("Server: Molly");
            out.println("Content-Type: image/jpeg");
            out.println("Content-Length:"+array.length);
            out.println("");
            socket.getOutputStream().write(array,0,array.length);
        }else{
            bufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            out=new PrintWriter(socket.getOutputStream());
            out.print(""); out.println("HTTP/1.1 200 OK");
            out.println("Server: Molly");
            out.println("Content-Type: text/html ;charset=UTF-8");
            out.println("");
            while ((line=bufferedReader.readLine())!=null){
                out.println(line);
            }
        }
        out.flush();
        } catch (IOException e) {
            //e.printStackTrace();
            out.println("HTTP/1.1 500");

            out.println("");
            out.flush();

        }finally {
            close(bufferedReader,in,bufferedReader,socket);
        }
    }

    void close(Closeable ... closeable ){
      if(closeable!=null)
              for(Closeable item:closeable){
                  try {
                      if(item!=null){
                          item.close();
                      }

                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
    }
}
