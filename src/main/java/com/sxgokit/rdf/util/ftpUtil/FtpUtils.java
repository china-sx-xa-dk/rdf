package com.sxgokit.rdf.util.ftpUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FTP文件处理工具类
 */
public class FtpUtils
{
    private static Logger LOG = LoggerFactory.getLogger(FtpUtils.class);

    /**
     * 
     * Description: 上传FTP程序<br>
     * Time：2016年2月1日 下午7:14:44<br>
     * @author tangchaojun
     * @param url FTP服务器IP
     * @param port FTP服务器端口号
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param path  FTP服务器上的相对路径
     * @param filename 要上传的文件名
     * @param input
     * @return
     */
    public boolean uploadFile(String url, int port, String username,
                              String password, String path, String filename,
                              InputStream input)
    {
        // filename:要上传的文件  
        // path :上传的路径  
        // 初始表示上传失败  
        boolean success = false;
        // 创建FTPClient对象  
        FTPClient ftp = new FTPClient();
        try
        {
            int reply;
            // 连接FTP服务器  
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
            ftp.connect(url, port);
            // 下面三行代码必须要，而且不能改变编码格式，否则不能正确下载中文文件  
            ftp.setControlEncoding("GBK");
            FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
            conf.setServerLanguageCode("zh");
            // 登录ftp  
            ftp.login(username, password);
            // 看返回的值是不是230，如果是，表示登陆成功  
            reply = ftp.getReplyCode();
            // 以2开头的返回值就会为真  
            if (!FTPReply.isPositiveCompletion(reply))
            {
                ftp.disconnect();
                System.out.println("连接服务器失败");
                return success;

            }
            System.out.println("登陆服务器成功");
            ftp.changeWorkingDirectory(path);// 转移到FTP服务器目录  
            FTPFile[] fs = ftp.listFiles(); // 得到目录的相应文件列表  
            System.out.println(fs.length);
            System.out.println(filename);
            String filename1 = FtpUtils.changeName(filename, fs);
            String filename2 = new String(filename1.getBytes("GBK"),
                "ISO-8859-1");
            String path1 = new String(path.getBytes("GBK"), "ISO-8859-1");
            // 转到指定上传目录  
            ftp.changeWorkingDirectory(path1);
            System.out.println(path1);
            // 将上传文件存储到指定目录  
            ftp.appendFile(new String(filename.getBytes("GBK"), "ISO-8859-1"),
                input);

            ftp.setFileType(FTP.BINARY_FILE_TYPE);

            //如果缺省该句 传输txt正常 但图片和其他格式的文件传输出现乱码  

            ftp.storeFile(filename2, input);

            // 关闭输入流  

            input.close();

            // 退出ftp  

            ftp.logout();

            // 表示上传成功  

            success = true;

            System.out.println("上传成功。。。。。。");

        }
        catch (IOException e)
        {

            e.printStackTrace();

        }
        finally
        {

            if (ftp.isConnected())
            {

                try
                {

                    ftp.disconnect();

                }
                catch (IOException ioe)
                {

                }

            }

        }

        return success;

    }

    /**
     * 
     * Description: 删除<br>
     * Time：2016年2月1日 下午7:13:51<br>
     * @author tangchaojun
     * @param url FTP服务器IP
     * @param port FTP服务器端口号
     * @param username  FTP登录账号
     * @param password FTP登录密码
     * @param path FTP服务器上的相对路径
     * @param filename 要删除的文件名
     * @return
     */

    public boolean deleteFile(String url, int port, String username,

    String password, String path, String filename)
    {

        // filename:要上传的文件  
        // path :上传的路径  
        // 初始表示上传失败  
        boolean success = false;
        // 创建FTPClient对象  
        FTPClient ftp = new FTPClient();
        try
        {
            int reply;
            // 连接FTP服务器  
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
            ftp.connect(url, port);
            // 下面三行代码必须要，而且不能改变编码格式，否则不能正确下载中文文件  
            ftp.setControlEncoding("GBK");
            FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
            conf.setServerLanguageCode("zh");
            // 登录ftp  
            ftp.login(username, password);
            // 看返回的值是不是230，如果是，表示登陆成功  
            reply = ftp.getReplyCode();
            // 以2开头的返回值就会为真  
            if (!FTPReply.isPositiveCompletion(reply))
            {
                ftp.disconnect();
                System.out.println("连接服务器失败");
                return success;
            }
            System.out.println("登陆服务器成功");
            String filename2 = new String(filename.getBytes("GBK"),
                "ISO-8859-1");
            String path1 = new String(path.getBytes("GBK"), "ISO-8859-1");
            // 转到指定上传目录  
            ftp.changeWorkingDirectory(path1);
            FTPFile[] fs = ftp.listFiles(); // 得到目录的相应文件列表  
            ftp.deleteFile(filename2);
            ftp.logout();
            success = true;
        }
        catch (IOException e)
        {

            System.out.println(e);
        }
        finally
        {
            if (ftp.isConnected())
            {
                try
                {
                    ftp.disconnect();
                }
                catch (IOException ioe)
                {}
            }
        }
        return success;
    }

    /**
     * 
     * Description:下载程序 <br>
     * Time：2016年2月1日 下午7:12:31<br>
     * @author tangchaojun
     * @param url FTP服务器IP
     * @param port FTP服务器端口号
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param remotePath FTP服务器上的相对路径
     * @param fileName 要下载的文件名
     * @param localPath 下载后保存到本地的路径
     * @return
     */
    public static boolean downFile(String url, int port, String username,
                                   String password, String remotePath,
                                   String fileName, String localPath)
    {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        OutputStream is = null;
        try
        {
            int reply;
            ftp.connect(url, port);
            ftp.setControlEncoding("GBK");

            FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);

            conf.setServerLanguageCode("zh");
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器 
            ftp.login(username, password);//登录 
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply))
            {
                ftp.disconnect();
                return success;
            }
            ftp.changeWorkingDirectory(remotePath);//转移到FTP服务器目录 
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs)
            {
                if (ff.getName().equals(fileName))
                {
                    File localFile = new File(localPath + "/" + ff.getName());
                    is = new FileOutputStream(localFile);
                    ftp.retrieveFile(new String(ff.getName().getBytes("GBK"),
                        "ISO-8859-1"), is);
                    is.flush();
                    break;
                }
            }
            ftp.logout();
            success = true;
        }
        catch (IOException e)
        {
            LOG.error("ftp下载文件失败", "remotePath=" + remotePath + " fileName="
                                   + fileName, e);
        }
        finally
        {
            if (is != null)
            {
                try
                {
                    is.close();
                }
                catch (IOException ioe)
                {
                    LOG.error("ftp关闭流失败", ioe);
                }
            }
            if (ftp.isConnected())
            {
                try
                {
                    ftp.disconnect();
                }
                catch (IOException ioe)
                {
                    LOG.error("ftp关闭连接失败", ioe);
                }
            }
        }
        return success;
    }

    /**
     * 
     * Description:判断是否有重名方法   <br>
     * Time：2016年2月2日 上午10:58:10<br>
     * @author tangchaojun
     * @param fileName
     * @param fs
     * @return
     */
    public static boolean isDirExist(String fileName, FTPFile[] fs)
    {
        for (int i = 0; i < fs.length; i++ )
        {
            FTPFile ff = fs[i];
            if (ff.getName().equals(fileName))
            {
                return true; // 如果存在返回 正确信号  
            }
        }
        return false; // 如果不存在返回错误信号  
    }

    /**
     * 
     * Description: 根据重名判断的结果 生成新的文件的名称  <br>
     * Time：2016年2月2日 上午10:58:20<br>
     * @author tangchaojun
     * @param filename
     * @param fs
     * @return
     */
    public static String changeName(String filename, FTPFile[] fs)
    {
        int n = 0;
        // 创建一个可变的字符串对象 即StringBuffer对象，把filename值付给该对象  
        StringBuffer filename1 = new StringBuffer("");
        filename1 = filename1.append(filename);
        System.out.println(filename1);
        while (isDirExist(filename1.toString(), fs))
        {
            n++ ;
            String a = "[" + n + "]";
            System.out.println("字符串a的值是：" + a);
            int b = filename1.lastIndexOf(".");// 最后一出现小数点的位置  
            int c = filename1.lastIndexOf("[");// 最后一次"["出现的位置  
            if (c < 0)
            {
                c = b;
            }
            StringBuffer name = new StringBuffer(filename1.substring(0, c));// 文件的名字  
            StringBuffer suffix = new StringBuffer(filename1.substring(b + 1));// 后缀的名称  
            filename1 = name.append(a).append(".").append(suffix);
        }
        return filename1.toString();
    }

    /**
     * 
     * Description:测试程序 <br>
     * Time：2016年2月2日 上午10:56:59<br>
     * @author tangchaojun
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args)
        throws FileNotFoundException
    {
        String path = "D:\\";
        File f1 = new File("D:\\20161028105100testImg2.png");
        String filename = f1.getName();
        System.out.println(filename);
        // 上传
        InputStream input = new FileInputStream(f1);
        FtpUtils a = new FtpUtils();
        //        a.uploadFile("117.36.50.74", 21, "app", "app123~", path, filename,
        //            input);
        a.uploadFile("192.168.2.3", 21, "administrator", "Eclipse$2012", path,
            filename, input);
        //        System.out.println(FtpUtils.downFile("192.168.2.3", 21, "administrator", "Eclipse$2012",
        //            path, "20161028105100testImg.png", "F:\\"));

        //下载
        //        FtpUtils.downFile("113.140.23.202", 21, "balaccount", "BI#%EQ5B", "",
        //            "2016012402610103039911000.txt", "E:\\1");
        //        //删除
        //        FtpUtils a = new FtpUtils();
        //        a.deleteFile("113.140.23.202", 21, "gok", "Qwer6666", path, filename);
    }
}
