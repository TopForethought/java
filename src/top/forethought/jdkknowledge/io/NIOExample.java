package top.forethought.jdkknowledge.io;


import top.forethought.concurrency.threads.ReadThreadLocal;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author wangwei
 * @date 2019/3/31 19:26
 * @classDescription NIO 新型io,非阻塞io
 * I/O 与 NIO 最重要的区别是数据打包和传输的方式，I/O 以流的方式处理数据，而 NIO 以块的方式处理数据。
 * // 来测试一下文件复制
 */
public class NIOExample {


    public static void main(String[] args) throws IOException {
        ReadThreadLocal readThreadLocal = new ReadThreadLocal();
        final String src = "E:\\java学习视频\\207-Java并发编程与高并发解决方案(完整无密).rar";
        final String target1 = "F:\\testNio.rar";
        final String target2 = "F:\\testStream.rar";
        final String target3="F:\\testBufferedReader.rar";
        final String target4="F:\\testBufferedStream.rar";
        readThreadLocal.begin();
       // NIOCopy(src, target1);
       // IOStreamCopy(src,target2);
       // IOBufferCopy(src,target3);
        IOBuffersStreamCopy(src,target4);
        System.out.println("耗时:" + readThreadLocal.end());


    }

    //耗时:157376  6 G
    public static void NIOCopy(String src, String target) throws IOException {
        FileInputStream in = new FileInputStream(src);
        FileChannel fcin = in.getChannel();
        FileOutputStream out = new FileOutputStream(target);
        FileChannel fout = out.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (true) {
            int r = fcin.read(byteBuffer);
            if (r == -1) {
                break;
            }
            // 切换读写
            byteBuffer.flip();
            fout.write(byteBuffer);
            //清空缓存区
            byteBuffer.clear();
        }
    }
//耗时:196239  6G
    public static void IOStreamCopy(String src, String target) throws IOException {
        FileInputStream inputStream = new FileInputStream(src);
        FileOutputStream outputStream = new FileOutputStream(target);
        byte[] buffer = new byte[1024];
        while (inputStream.read(buffer) != -1) {
            outputStream.write(buffer);
        }
        inputStream.close();
        outputStream.close();
    }
//耗时:443620
    public static void IOBufferCopy(String src, String target) throws IOException {
//        FileInputStream inputStream=new FileInputStream(src);
//        FileOutputStream outputStream=new FileOutputStream(target);
        BufferedReader reader = new BufferedReader(new FileReader(new File(src)));
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(target)));
        char[] buffer = new char[1024];
        while (reader.read(buffer, 0, buffer.length) != -1) {
            writer.write(buffer);
        }
        reader.close();
        writer.close();
    }
    //耗时:162418

    public static void IOBuffersStreamCopy(String src, String target )throws FileNotFoundException , IOException {
        FileInputStream inputStream = new FileInputStream(src);
        FileOutputStream outputStream = new FileOutputStream(target);
        BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream);
        BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(outputStream);
        byte[] buffer = new byte[1024];
        while (bufferedInputStream.read(buffer)!=-1) {
           bufferedOutputStream.write(buffer);
        }
        inputStream.close();
        outputStream.close();
    }
}
