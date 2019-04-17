package top.forethought.concurrency.threads.httpserver;


import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {

   static ThreadPool<HttpRequestHandler> threadPool=new DefaultThreadPool<HttpRequestHandler>(10);

    static String basePath;
    static ServerSocket serverSocket;
    static int port=8080;
    public static void setPort(int port){
        if(port>0){
          SimpleHttpServer.port=port;
        }
    }
    public static void setBasePath(String basePath){
        if(basePath!=null && new File(basePath).exists() && new File(basePath).isDirectory()){
            SimpleHttpServer.basePath=basePath;
        }
    }
    //
    public  static void start() throws IOException {
        serverSocket=new ServerSocket(port);
        Socket socket=null;
       while ((socket=serverSocket.accept())!=null){
           threadPool.execute(new HttpRequestHandler(socket));
       }
       serverSocket.close();
    }

    public static void main(String[] args) throws IOException {
        SimpleHttpServer.setBasePath("E:\\学习文档\\项目\\forethought\\webapp");
        SimpleHttpServer.setPort(8090);
        SimpleHttpServer.start();
    }
}
