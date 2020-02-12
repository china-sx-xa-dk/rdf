package com.sxgokit.rdf.util.wordUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 导入Word文件工具类（支持“doc”和“docx”格式）
 */
public class ImportWord
{

    private static Logger log = LoggerFactory.getLogger(ImportWord.class);

    /**
     * 工作薄对象
     */
    private Document wb;

    /**
     * 构造函数
     * @param multipartFile 导入文件对象
     * @param headerNum 标题行号，数据行号=标题行号+1
     * @param sheetIndex 工作表编号
     * @return
     * @throws InvalidFormatException
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public ImportWord(MultipartFile multipartFile, int headerNum, int sheetIndex)
        throws InvalidFormatException, IOException, InstantiationException,
        IllegalAccessException
    {
        if (StringUtils.isBlank(multipartFile.getOriginalFilename()))
        {
            throw new RuntimeException("导入文档为空!");
        }
        else if (multipartFile.getOriginalFilename().toLowerCase().endsWith(
            "doc"))
        {
            this.wb = new XWPFDocument(multipartFile.getInputStream());
        }
        else if (multipartFile.getOriginalFilename().toLowerCase().endsWith(
            "docx"))
        {
            this.wb = new XWPFDocument(multipartFile.getInputStream());
        }
        else
        {
            throw new RuntimeException("文档格式不正确!");
        }
        log.debug("Initialize success.");
    }

    /**
     * 获取导入数据列表
     * @param mfile
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public List<String> getData(MultipartFile mfile)
        throws FileNotFoundException, IOException
    {
        List<String> dateList = new ArrayList();
        try
        {
            CommonsMultipartFile cf = (CommonsMultipartFile)mfile;
            DiskFileItem fi = (DiskFileItem)cf.getFileItem();
            File file = fi.getStoreLocation();
            FileInputStream in = new FileInputStream(file);
            XWPFDocument hwpf = new XWPFDocument(in);
            List<XWPFTable> tables = hwpf.getTables();
            for (XWPFTable table : tables)
            {
                // 获取表格的行
                List<XWPFTableRow> rows = table.getRows();
                for (XWPFTableRow row : rows)
                {
                    // 获取表格的每个单元格
                    List<XWPFTableCell> tableCells = row.getTableCells();
                    for (XWPFTableCell cell : tableCells)
                    {
                        // 获取单元格的内容
                        String text = cell.getText();
                        dateList.add(text);
                        // System.out.println(text);
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return dateList;
    }
}
