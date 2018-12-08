package dao.impl;

import utils.MyOMConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OMTransferBase {

    public static void OMTransferBase(String type, String xmlString){
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(MyOMConfig.getTransferUrl());
            // 打开和URL之间的连接，设备地址和web远程端口
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(xmlString);
            // flush输出流的缓冲
            out.flush();
            System.out.println("[*] "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) +" 向OM设备发送命令完成："+type);
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("[x] "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) +" 发送POST请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        //System.out.println("[*] "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) +" 接收到OM设备返回信息："+result);
    }

}
