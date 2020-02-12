package com.sxgokit.rdf.config.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Dante
 * @package com.lego.common.component
 * @date 2018/11/27 002711:56
 */
@Component
@Slf4j
public class PdfComponent {

   /* @Autowired
    private QiniuCloudStorageClient qiniuCloudStorageClient;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Value("${lego-education-temp-directory}")
    private String tempPath = "C:/Users/Public/temp";

    public String generateContract(List<BufferedImage> images) {
        String pdfId = snowflakeIdWorker.nextId().getStringId();
        String fileName = String.format("%s.pdf", pdfId);
        File file = new File(tempPath, fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            Document document = new Document();
            PdfWriter p = PdfWriter.getInstance(document, fos);
            document.open();
            for (BufferedImage b : images) {
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
                ImageIO.write(b, "JPG", imOut);
                Image image = Image.getInstance(bs.toByteArray());
                image.setAlignment(Image.ALIGN_CENTER);
                document.add(image);
                bs.flush();
                bs.close();
                imOut.flush();
                imOut.close();
            }
            document.close();
            FileInputStream fis = new FileInputStream(file);
            String path = qiniuCloudStorageClient.uploadPdf(IOUtils.toByteArray(fis), pdfId);
            return path;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
*/

}
