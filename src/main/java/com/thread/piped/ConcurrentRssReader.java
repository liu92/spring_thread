package com.thread.piped;

import com.thread.lock.DefaultThreadFactory;
import com.thread.util.Tools;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 边下载边解析的RSS阅读器
 * @ClassName: ConcurrentRssReader
 * @Description:
 * @Author: lin
 * @Date: 2019/9/22 12:43
 * History:
 * @<version> 1.0
 */
public class ConcurrentRssReader {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        final int argc = args.length;
        String url = argc > 0 ? args[0] : "http://lorem-rss.herokuapp.com/feed";
        // 从网络中解析RSS数据
        InputStream in = loadRSS(url);
        //从 输入流中解析XML数据
        Document document = parseXML(in);

        Element eleRss = (Element) document.getFirstChild();
        Element eleChannel = (Element) eleRss.getElementsByTagName("channels").item(0);
        Node ndTtile = eleChannel.getElementsByTagName("title").item(0);
        String title = ndTtile.getFirstChild().getNodeValue();
        System.out.println(title);
    }


    private static Document parseXML(InputStream in) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = db.parse(in);
        return  document;
    }

   private static   ExecutorService executorService = new ThreadPoolExecutor(5,
            50, 200,
            TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024),
            new DefaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    private  static  InputStream loadRSS(final  String url){
        final PipedInputStream in  = new PipedInputStream();
        final PipedOutputStream pos = new PipedOutputStream();

        executorService.execute(new ThreadA(in, pos, url));
//        Thread workerThread   = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    doDownload(url, pos);
//                }catch (Exception e){
//                    // RSS数据下载过程中出现异常时，关闭相关输出流和输入流。
//                    // 注意，此处我们不能像平常那样在finally块中关闭相关输出流
//                    Tools.silentClose(pos, in);
//                    e.printStackTrace();
//                }
//            }
//        }, "rss-loader");
//        workerThread.start();
        return  in;
    }




    static BufferedInputStream issueRequest(String url) throws Exception {
        URL requestURL = new URL(url);
        final HttpURLConnection conn = (HttpURLConnection) requestURL.openConnection();
        conn.setConnectTimeout(2000);
        conn.setReadTimeout(2000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Connection", "close");
        conn.setDoInput(true);
        conn.connect();
        int statusCode = conn.getResponseCode();
        if(HttpURLConnection.HTTP_OK != statusCode){
            conn.disconnect();
            throw  new Exception("Server exception, status code:"+ statusCode);
        }

        BufferedInputStream in = new BufferedInputStream(conn.getInputStream()){
            /**
             * Closes this input stream and releases any system resources
             * associated with the stream.
             * Once the stream has been closed, further read(), available(), reset(),
             * or skip() invocations will throw an IOException.
             * Closing a previously closed stream has no effect.
             *
             * @throws IOException if an I/O error occurs.
             */
            @Override
            public void close() throws IOException {
                try {
                    super.close();
                }finally {
                    conn.disconnect();
                }
            }
        };
        return  in;
    }

    /**
     * 下载
     * @param url
     * @param os
     */
    static void doDownload(String url, OutputStream os){
        ReadableByteChannel readChannel = null;
        WritableByteChannel writeChannel = null;

        try {
            // 对指定的URL发起HTTP请求
            BufferedInputStream in = issueRequest(url);
            readChannel = Channels.newChannel(in);
            ByteBuffer buf = ByteBuffer.allocate(1024);
            writeChannel = Channels.newChannel(os);
            while(readChannel.read(buf) > 0){
                buf.flip();
                writeChannel.write(buf);
                buf.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            Tools.silentClose(readChannel, writeChannel);
        }
    }

    static class  ThreadA implements Runnable{
         PipedInputStream in ;
         PipedOutputStream pos ;
        String url;

        public ThreadA(){}

        public ThreadA (PipedInputStream in , PipedOutputStream pos , String url){
            this.in = in;
            this.pos = pos;
            this.url = url;
        }

        @Override
        public void run() {
            try {
                doDownload(url, pos);
            }catch (Exception e){
                // RSS数据下载过程中出现异常时，关闭相关输出流和输入流。
                // 注意，此处我们不能像平常那样在finally块中关闭相关输出流
                Tools.silentClose(pos, in);
                e.printStackTrace();
            }
        }
    }

}
