package com.sxgokit.rdf.util.xmlUtil;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.sxgokit.rdf.util.ftpUtil.FtpUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * xml转换对象工具类 依赖xstream开源项目(BSD) 版本1.4.7
 */
public final class XmlUtil
{
    private static Logger LOG = LoggerFactory.getLogger(XmlUtil.class);

    private XmlUtil()
    {
        super();
    }

    /**
     * 将对象写成XML文件 Description: <br>
     * 
     * @param xmlPath
     *            要输出xml的绝对路径
     * @param obj
     *            对象, 注解实现 Time：2014年11月13日 上午11:42:06<br>
     * @author
     */
    public static void writeXml(String xmlPath, Object obj)
        throws Exception
    {
        XStream xStream = new XStream();
        xStream.autodetectAnnotations(true);
        OutputStreamWriter osw = null;
        try
        {
            osw = new OutputStreamWriter(FileUtils.openOutputStream(new File(
                xmlPath)), "UTF-8");
            xStream.toXML(obj, osw);
        }
        catch (IOException e)
        {
            LOG.error("xml转换失败", "xmlPath=" + xmlPath + " obj=" + obj, e);
            throw new Exception("xml转换失败", e);
        }
        finally
        {
            IOUtils.closeQuietly(osw);
        }
    }

    /**
     * 将对象写成XML文件 Description: <br>
     * 
     * @param xmlPath
     *            要输出xml的绝对路径
     * @param cls
     *            对象, 注解实现 Time：2014年11月13日 上午11:42:06<br>
     * @author
     */
    public static <T> T fromXmlFile(String xmlPath, Class<T> cls)
        throws Exception
    {
        XStream xStream = new XStream(new DomDriver());
        try
        {
            xStream.processAnnotations(cls);
            return (T)xStream.fromXML(FileUtils.openInputStream(new File(
                xmlPath)));
        }
        catch (Exception e)
        {
            LOG.error("xml转换对象失败", "xmlPath=" + xmlPath, e);
            throw new Exception("xml转换对象失败", e);
        }
    }

    /**
     * 将对象写成XML文件 Description: <br>
     * 
     * @param content
     *            要输出xml的绝对路径
     * @param cls
     *            对象, 注解实现 Time：2014年11月13日 上午11:42:06<br>
     * @author
     */
    public static <T> T fromXml(String content, Class<T> cls)
    {
        XStream xStream = new XStream(new DomDriver());
        xStream.processAnnotations(cls);
        return (T)xStream.fromXML(content);
    }

    /**
     * 将对象写成XML文件 Description: <br>
     * 
     * @param obj
     *            对象, 注解实现 Time：2014年11月13日 上午11:42:06<br>
     * @author
     */
    public static String toXml(Object obj)
    {
        XStream xStream = new XStream();
        xStream.autodetectAnnotations(true);
        return xStream.toXML(obj);
    }

}