/*
 * FileName：MatrixToImageWriter.java Description： Copyright: Copyright (c) 2013-2020 Company: GOK
 * Technology Author: Version: V100R01C01 Time:2015年3月27日 下午2:55:09
 */

package com.sxgokit.rdf.util.rqcodeUtil;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.google.zxing.common.BitMatrix;


public class MatrixToImageWriter
{
    private static final int BLACK = 0xFF000000;

    private static final int WHITE = 0xFFFFFFFF;

    private MatrixToImageWriter()
    {
        super();
    }

    public static BufferedImage toBufferedImage(BitMatrix matrix)
    {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++ )
        {
            for (int y = 0; y < height; y++ )
            {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    public static void writeToFile(BitMatrix matrix, String format, File file)
        throws IOException
    {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file))
        {
            throw new IOException("Could not write an image of format " + format + " to " + file);
        }
    }

    public static void writeToStream(BitMatrix matrix, String format, OutputStream stream)
        throws IOException
    {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, stream))
        {
            throw new IOException("Could not write an image of format " + format);
        }
    }
}
