package com.sxgokit.rdf.util.ftpUtil;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import com.ckfinder.connector.ServletContextFactory;
import com.sxgokit.rdf.web.controller.system.SysLoginController;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FTP连接工具类
 */
public class FtpAllUtils
{
    /**
     * LOG
     */
    private static Logger LOG = LoggerFactory.getLogger(FtpAllUtils.class);

    /**
     * 汉语
     */
    private static final String LANGUAGE_ZH = "zh";

    /**
     * 本地编码
     */
    public static final String CHARACTER_LOCAL = "GBK";

    /**
     * 目标编码
     */
    public static final String CHARACTER_TARGET = "GBK";

    /**
     * 目标文件名编码
     */
    public static final String CHARACTER_TARGET_FILE = "GBK";

    /**
     * 当前系统
     */
    private static final String SYSTEM_LINUX = "WIN";

    /**
     * 目标系统
     * LINUX|WIN
     */
    public static final String TARGET_SYSTEM_NAME = "LINUX";

    /**
     * 通用编码
     */
    private static final String CHARACTER_ISO = "ISO-8859-1";

    /**
     * FTP客户端
     */
    private FTPClient ftpClient;

    /**
     * 获取ftpUtil实例
     * @return
     */
    public static FtpAllUtils getInstance()
    {
        return new FtpAllUtils();
    }

    /**
     * 服务器连接
     * @param ip 服务器IP
     * @param port 服务器端口
     * @param username 用户名
     * @param password 密码
     * @param path 服务器路径,可以为空
     */
    public boolean connectServer(String ip, int port, String username,
                                 String password, String path)
        throws Exception
    {
        try
        {
            int reply;
            // 创建FTPClient对象  
            ftpClient = new FTPClient();
            FTPClientConfig config = null;
            //                config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
            // 下面三行代码必须要，而且不能改变编码格式，否则不能正确下载中文文件  
            ftpClient.setControlEncoding(CHARACTER_LOCAL);
            if (SYSTEM_LINUX.equals(TARGET_SYSTEM_NAME))
            {
                config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
            }
            else
            {
                config = new FTPClientConfig(FTPClientConfig.SYST_NT);
            }
            config.setServerLanguageCode(LANGUAGE_ZH);
            ftpClient.configure(config);

            // 链接FTP
            ftpClient.connect(ip, port);
            // 登陆FTP
            ftpClient.login(username, password);
            // 设置成2进制传输
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            // 看返回的值是不是230，如果是，表示登陆成功
            reply = ftpClient.getReplyCode();
            // 以2开头的返回值就会为真  
            if (!FTPReply.isPositiveCompletion(reply))
            {
                ftpClient.disconnect();
                return false;
            }
            if (!StringUtils.isEmpty(path))
            {
                // 编码转换
                path = new String(path.getBytes(CHARACTER_LOCAL), CHARACTER_ISO);
                path = new String(path.getBytes(CHARACTER_ISO),
                    CHARACTER_TARGET);
                // 转移到FTP服务器目录
                ftpClient.changeWorkingDirectory(path);

            }
            LOG.info("打开FTP连接成功", "ip" + ip + ", " + port + ", " + username);
            return true;
        }
        catch (IOException e)
        {
            LOG.error("打开FTP连接失败", "ip" + ip + ", " + port + ", " + username);
            LOG.error("打开FTP连接失败", e);
            throw new Exception("打开FTP连接失败", e);
        }
    }

    /**
     * 关闭连接
     */
    public void closeConnect()
    {
        if (ftpClient.isConnected())
        {
            try
            {
                ftpClient.logout();
                ftpClient.disconnect();
                LOG.info("关闭FTP连接成功", "");
            }
            catch (IOException e)
            {
                LOG.error("关闭FTP连接失败", e);
            }
        }
    }

    /**
     * 上传文件
     * @param remote
     * @param local
     * @return
     * @throws Exception 
     */
    public String uploadFile(String remote, InputStream local)
        throws Exception
    {
        String result = null;
        try
        {
            // 每天创建新的yyyyMMdd格式的文件夹
            //            String dir = new SimpleDateFormat("yyyyMMdd").format(new Date());
            //            ftpClient.makeDirectory(dir);

            // 编码转换
            remote = new String(remote.getBytes(CHARACTER_LOCAL), CHARACTER_ISO);
            remote = new String(remote.getBytes(CHARACTER_ISO),
                CHARACTER_TARGET_FILE);
            //            FTPFile[] fs = ftpClient.listFiles(); // 得到目录的相应文件列表  
            //            System.out.println("文件列表:" + fs.length);

            // 上传文件
            ftpClient.enterLocalPassiveMode(); // 被动模式
            //            ftpClient.enterLocalActiveMode();    //主动模式
            String targetPath = "/" + remote;
            LOG.info("上传文件目录地址:", targetPath);
            boolean flag = ftpClient.storeFile(targetPath, local);
            LOG.info("上传文件返回值:", flag);
            // 关闭流
            local.close();
            if (flag)
            {
                result = targetPath;
            }
            else
            {
                LOG.error("上传文件目录地址:", targetPath);
                LOG.error("上传文件返回值:", flag);
            }
            return result;
        }
        catch (Exception e)
        {
            LOG.error("ftp上传文件失败", e);
            throw new Exception("ftp上传文件失败", e);
        }
    }

    /**
     * 上传文件
     * @param remote
     * @param local
     * @return
     * @throws Exception 
     */
    public String uploadFile(String path, String remote, InputStream local)
        throws Exception
    {
        String result = null;
        try
        {
            if (createDir(path))
            {
                // 编码转换
                remote = new String(remote.getBytes(CHARACTER_LOCAL),
                    CHARACTER_ISO);
                remote = new String(remote.getBytes(CHARACTER_ISO),
                    CHARACTER_TARGET_FILE);

                // 上传文件
                ftpClient.enterLocalPassiveMode(); // 被动模式
                String targetPath = path + remote;
                LOG.info("上传文件目录地址:", targetPath);
                boolean flag = ftpClient.storeFile(targetPath, local);
                LOG.info("上传文件返回值:", flag);
                // 关闭流
                local.close();
                if (flag)
                {
                    result = targetPath;
                }
                else
                {
                    LOG.error("上传文件目录地址:", targetPath);
                    LOG.error("上传文件返回值:", flag);
                }
            }
            return result;
        }
        catch (Exception e)
        {
            LOG.error("ftp上传文件失败", e);
            throw new Exception("ftp上传文件失败", e);
        }
    }

    /**
     * 上传文件
     * @param remote
     * @param local
     * @return
     */
    public String uploadFile(String remote, String local)
        throws Exception
    {
        try
        {
            return uploadFile(remote, new FileInputStream(local));
        }
        catch (Exception e)
        {
            LOG.error("ftp上传文件失败", e);
            throw new Exception("ftp上传文件失败", e);
        }
    }

    /**
     * 上传文件
     * @param remote
     * @param local
     * @return 
     */
    public String changeWkdrAndUploadFile(String path, String remote,
                                          String local)
        throws Exception
    {
        String picPath = null;
        try
        {
            picPath = uploadFile(path, remote, new FileInputStream(local));
        }
        catch (Exception e)
        {
            LOG.error("ftp上传文件失败", e);
            throw new Exception("ftp上传文件失败", e);
        }
        return picPath;
    }

    /**
     * 
     * Description:下载程序 <br>
     * Time：2016年2月1日 下午7:12:31<br>
     * @author tangchaojun
     * @param fileName 要下载的文件名
     * @param localPath 下载后保存到本地的路径
     * @return
     */
    public boolean downFile(String fileName, String localPath)
    {
        boolean success = false;
        OutputStream is = null;
        try
        {
            int reply = ftpClient.getReplyCode();
            System.out.println("reply" + reply);
            if (!FTPReply.isPositiveCompletion(reply))
            {
                ftpClient.disconnect();
                return success;
            }
            //            ftpClient.changeWorkingDirectory("/");

            ftpClient.enterLocalPassiveMode(); // 被动模式
            //            ftpClient.enterLocalActiveMode(); //主动模式

            //？？44为什么获取不到文件列表
            //          在调用FTPClient.listFiles()方法前，先调用FTPClient.enterLocalPassiveMode();这个方法的意思就是每次数据连接之前，ftp client告诉ftp server开通一个端口来传输数据。
            //            FTPFile[] fs = ftpClient.listFiles();
            String[] files = ftpClient.listNames("/");
            //            LOG.error("ftp下载文件,ftp文件数, length=", files.length);
            for (String fileNa : files)
            {
                fileNa = fileNa.replace("/", "");
                if (fileNa.equals(fileName))
                {
                    File localFile = new File(localPath + "/" + fileNa);
                    is = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(new String(fileNa.getBytes("GBK"),
                        "ISO-8859-1"), is);
                    is.flush();
                    LOG.info("ftp下载文件成功, localPath=", localPath + " fileName="
                                                      + fileName);
                    break;
                }
            }
            success = true;
        }
        catch (IOException e)
        {
            LOG.error("ftp下载文件失败", "localPath=" + localPath + " fileName="
                                   + fileName, e);
        }
        finally
        {
            if (is != null)
            {
                try
                {
                    is.close();
                    LOG.info("ftp下载文件，关闭流成功", "");
                }
                catch (IOException ioe)
                {
                    LOG.error("ftp下载文件，关闭流失败", ioe);
                }
            }
        }
        return success;
    }

    //获取资源文件
    public static Properties getProperties()
    {
        Properties properties = new Properties();
        try
        {
            properties.load(FtpAllUtils.class.getResourceAsStream("/ftp.properties"));
        }
        catch (IOException e)
        {
            LOG.error("获取资源文件ftp.properties失败", e);
        }
        return properties;
    }

    //获取制定资源文件
    public static Properties getProperties(String ftpName)
    {
        Properties properties = new Properties();
        try
        {
            properties.load(FtpAllUtils.class.getResourceAsStream(ftpName));
        }
        catch (IOException e)
        {
            LOG.error("获取资源文件ftp.properties失败， ftpName=", ftpName, e);
        }
        return properties;
    }

    /** 
     * 创建目录(有则切换目录，没有则创建目录) 
     * @param dir 
     * @return 
     */
    public boolean createDir(String dir)
    {
        try
        {
            //目录编码，解决中文路径问题  
            String d = new String(dir.toString().getBytes("GBK"), "iso-8859-1");

            //尝试切入目录  
            if (ftpClient.changeWorkingDirectory(d)) return true;
            String[] arr = dir.split("/");
            StringBuffer sbfDir = new StringBuffer();
            //循环生成子目录  
            for (String s : arr)
            {
                sbfDir.append("/");
                sbfDir.append(s);
                //目录编码，解决中文路径问题  
                d = new String(sbfDir.toString().getBytes("GBK"), "iso-8859-1");
                //尝试切入目录  
                if (ftpClient.changeWorkingDirectory(d)) continue;
                ftpClient.enterLocalPassiveMode(); // 被动模式
                if (!ftpClient.makeDirectory(d))
                {
                    System.out.println("[失败]ftp创建目录：" + sbfDir.toString());
                    return false;
                }
                System.out.println("[成功]创建ftp目录：" + sbfDir.toString());
            }
            //将目录切换至指定路径  
            return ftpClient.changeWorkingDirectory(d);
        }
        catch (Exception e)
        {
            LOG.error("将目录切换至指定路径失败", e);
            return false;
        }
    }

    /**
     * 上传文件
     * @return
     */
    public static String ftpUpload(File target, String picPath)
        throws Exception
    {
        try
        {
            return UploadFtpFile(target.getAbsolutePath(), picPath);
        }
        catch (Exception e)
        {
            LOG.error("ftp上传文件失败", e);
            throw new Exception("ftp上传文件失败", e);
        }
    }

    /**
     * 上传图片
     * Description: <br>
     * Time：2017年8月23日 下午2:49:43<br>
     * @author leining
     * @param localFile
     * @param picPath
     * @return
     */
    public static String UploadFtpFile(String localFile, String picPath)
    {
        FtpAllUtils ftpUtil = null;
        String resultPath = "";
        try
        {
            Properties properties = FtpAllUtils.getProperties();
            String ip = !StringUtils.isEmpty(properties.getProperty("FTPIP")) ? properties.getProperty("FTPIP") : null;
            String loginName = !StringUtils.isEmpty(properties.getProperty("FTPLGNINNAME")) ? properties.getProperty("FTPLGNINNAME") : null;
            String loginPass = !StringUtils.isEmpty(properties.getProperty("FTPLOGINPASS")) ? properties.getProperty("FTPLOGINPASS") : null;
            String path = !StringUtils.isEmpty(properties.getProperty("FTPPATH")) ? properties.getProperty("FTPPATH") : null;
            ftpUtil = getInstance();
            // ftp是否连接成功 ip, port, loginName, loginPass, null
            if (ftpUtil.connectServer(ip, 21, loginName, loginPass, path))
            {
                if (!StringUtils.isEmpty(localFile))
                {
                    // 目标文件 文件名
                    String remote = localFile.substring(localFile.lastIndexOf("\\") + 1);
                    // 替换文件路径
                    localFile = localFile.replace("/", File.separator);
                    System.out.println("localFile=" + localFile);
                    // 文件上传
                    if (ftpUtil.createDir(picPath))
                    {
                        String uploadPath = ftpUtil.changeWkdrAndUploadFile(
                            picPath, remote, localFile);
                        if (StringUtils.isEmpty(uploadPath))
                        {
                            LOG.error("文件上传失败，原文件路径" + localFile);
                        }
                        else
                        {
                            resultPath += ServletContextFactory.getServletContext().getContextPath()
                                          + uploadPath;
                            LOG.error("文件上传成功，文件路径" + resultPath);
                        }
                    }
                }
                else
                {
                    LOG.error("文件上传时localFile为空");
                }
            }
            else
            {
                LOG.error("ftp是否连接不成功 ip=" + ip + ",loginName=" + loginName
                          + ",loginPass=" + loginPass + ",path=" + path);
            }
        }
        catch (Exception e)
        {
            LOG.error("ftp打开连接上传文件失败", e);
        }
        finally
        {
            if (ftpUtil != null)
            {
                // 关闭ftp连接
                ftpUtil.closeConnect();
            }
        }
        return resultPath;
    }

    /**
     * 上传图片
     * Description: <br>
     * Time：2017年8月23日 下午2:49:43<br>
     * @author leining
     * @param localFile
     * @return
     */
    public static String uploadFile(String localFile, String ip,
                                    String loginName, String loginPass,
                                    String path)
    {
        FtpAllUtils ftpUtil = null;
        String resultPath = "";
        try
        {
            ftpUtil = getInstance();
            // ftp是否连接成功 ip, port, loginName, loginPass, null
            if (ftpUtil.connectServer(ip, 21, loginName, loginPass, path))
            {
                if (!StringUtils.isEmpty(localFile))
                {
                    // 目标文件 文件名
                    String remote = localFile.substring(localFile.lastIndexOf("\\") + 1);
                    // 替换文件路径
                    localFile = localFile.replace("/", File.separator);
                    System.out.println("localFile=" + localFile);

                    // 文件上传
                    String uploadPath = ftpUtil.uploadFile(remote, localFile);
                    if (StringUtils.isEmpty(uploadPath))
                    {
                        LOG.error("文件上传失败，原文件路径localFile=", localFile);
                    }
                    else
                    {
                        resultPath += uploadPath + ",";
                        LOG.error("文件上传成功，文件路径,resultPath=", resultPath);
                    }
                }
                else
                {
                    LOG.error("文件上传时localFile为空");
                }
            }
            else
            {
                LOG.error("ftp是否连接不成功 ip=" + ip + ",loginName=" + loginName
                          + ",loginPass=" + loginPass + ",path=" + path);
            }
        }
        catch (Exception e)
        {
            LOG.error("ftp打开连接上传文件失败", e);
        }
        finally
        {
            if (ftpUtil != null)
            {
                // 关闭ftp连接
                ftpUtil.closeConnect();
            }
        }
        return resultPath;
    }

    public static void main(String[] args)
    {
        FtpAllUtils ftpUtil = null;
        String result = null;

        try
        {
            String destDirName = ServletContextFactory.getServletContext().getContextPath();
            // 替换文件路径
            destDirName = destDirName.replace("\\", File.separator);
            ftpUtil = FtpAllUtils.getInstance();
            if (ftpUtil.connectServer("123.56.101.44", 21, "bestpay",
                "xjxgftp2016", null))
            {
                String uri = "aa/bb";

                //                boolean success = createDir(uri);  
                //                System.out.println(success+"...............................");
            }
            else
            {
                System.out.println("ftp是否连接不成功");
            }
        }
        catch (Exception e)
        {
            System.out.println("FTP上传文件失败");
            e.printStackTrace();
        }
        finally
        {
            if (ftpUtil != null)
            {
                // 关闭ftp连接
                ftpUtil.closeConnect();
            }
        }
    }

}
