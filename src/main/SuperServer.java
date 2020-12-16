package main;

import main.services.AdminServer;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-16 16:34
 */
public class SuperServer {
    private int countOfClient = 0;
    public SuperServer() {
        try {
            // 1.创建服务器端socket 绑定端口
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("server start at:" + new Date());
            while (true) {
                // 2.监听
                Socket socket = serverSocket.accept();
                // 获取客户机地址
                InetAddress address = socket.getInetAddress();
                System.out.println("connected with address:"+ address.getHostAddress());
                // 3.线程启动
                new Thread(new ConnectTask(socket)).start();
                countOfClient++;
                System.out.println("this is the "+countOfClient + "(th) of client. ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SuperServer();
    }

    class ConnectTask implements Runnable {
        private Socket socket = null;

        public ConnectTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                //3.获得输入流
                InputStream is=socket.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                //获得输出流
                OutputStream os=socket.getOutputStream();
                PrintWriter pw=new PrintWriter(os);
                //4.读取用户输入信息
                String info=null;
                // 将用户信息存入str中
                String str = "";
                while(!((info=br.readLine())==null)){
                    System.out.println("我是服务器，用户信息为："+info);
                    str += info;
                }

                // 将用户信息分开来 存入array数组中
                StringTokenizer token = new StringTokenizer(str, " ,");
                String[] array = new String[2];
                int i = 0;
                while (token.hasMoreTokens()) {
                    array[i] = token.nextToken();//将分割开的子字符串放入数组中
                    i++;
                }
                for (int j = 0; j < array.length; j++) {
                    System.out.print(array[j] + "  ");//遍历输出数组
                }
                try{
                    int id = Integer.parseInt(array[0].trim());
                    String psw= array[1];

                    AdminServer adminServer = new AdminServer();

                    boolean infofalg = adminServer.adminLogin(id,psw);

                    if (infofalg){
                        String reply="true";
                        pw.write(reply);
                    } else {
                        String reply="false";
                        pw.write(reply);
                    }

                }catch (NumberFormatException e) {
                    System.out.println("SuperServer Error:"+e);
                }
                //给客户一个响应

                pw.flush();
                //5.关闭资源
                pw.close();
                os.close();
                br.close();
                is.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
