package user;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @描述：
 * @创建人:ZelongChen
 * @日期:2020-12-16 16:35
 */
public class Client{
    public boolean loginInfo(String id,String pwd){
        try {
            //1.建立客户端socket连接，指定服务器位置及端口
            Socket socket = new Socket("localhost", 8000);
            //2.得到socket读写流
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            //输入流
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            //3.利用流按照一定的操作，对socket进行读写操作
            String info = id + "," + pwd;
            pw.write(info);
            pw.flush();
            socket.shutdownOutput();
            //接收服务器的相应
            String reply = null;
            String flag = "";
            while (!((reply = br.readLine()) == null)) {
                System.out.println("接收服务器的信息：" + reply);
                flag += reply;
            }

            //4.关闭资源
            br.close();
            is.close();
            pw.close();
            os.close();
            socket.close();

            if (flag.equals("true"))
                return true;
            else
                return false;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
