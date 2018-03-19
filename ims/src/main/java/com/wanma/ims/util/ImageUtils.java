package com.wanma.ims.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/***
 * 图片压缩
 */
public class ImageUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageUtils.class);

    public static float qa = 0.85f;
    public static float soften_Factor = 0.00f;

    /***
     * @param sourceFilePath 源图片路径
     * @param targetFilePath 压缩后图片路径
     * @param newWidth       压缩后图片宽度
     * @param newHeight      压缩后图片高度
     * @throws IOException
     */
    public static void compress(File sourceFilePath, String targetFilePath, int newWidth, int newHeight) throws IOException {
        compress(sourceFilePath, targetFilePath, newWidth, newHeight, qa, soften_Factor);
    }

    /***
     * @param targetFilePath 压缩后图片路径
     * @param newWidth       压缩后图片宽度
     * @param newHeight      压缩后图片高度
     * @param quality        压缩质量(0-1)
     * @param softenFactor   柔化因子(-1 -- 1),为负数时为锐化处理,正数柔化处理,0则不做任何处理
     * @throws IOException
     */
    public static void compress(File sourceFile, String targetFilePath, int newWidth, int newHeight, float quality, float softenFactor) throws IOException {
        //File sourceFile = new File(sourceFilePath);
        File targetFile = new File(targetFilePath);

        targetFile.getParentFile().mkdirs();

        int w = newWidth;
        BufferedImage src = ImageIO.read(sourceFile);
        if (src.getWidth() < src.getHeight()) {
            w = newHeight;
        }

        if (quality > 1) {
            throw new IllegalArgumentException("压缩质量的值必须(0-1)之间");
        }

        if (softenFactor > 1 || softenFactor < -1) {
            throw new IllegalArgumentException("柔化因子必须在(-1 -- 1)之间");
        }

        ImageIcon ii = new ImageIcon(sourceFile.getCanonicalPath());
        Image i = ii.getImage();
        Image resizedImage;

        int iWidth = i.getWidth(null);
        int iHeight = i.getHeight(null);

        if (iWidth >= iHeight) {
            resizedImage = i.getScaledInstance(w, (w * iHeight) / iWidth, Image.SCALE_SMOOTH);
        } else {
            resizedImage = i.getScaledInstance((w * iWidth) / iHeight, w, Image.SCALE_SMOOTH);
        }

        Image temp = new ImageIcon(resizedImage).getImage();
        BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null), temp.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.createGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));
        g.drawImage(temp, 0, 0, null);
        g.dispose();

        float[] softenArray = {0, softenFactor, 0, softenFactor, 1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0};

        Kernel kernel = new Kernel(3, 3, softenArray);
        ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        bufferedImage = cOp.filter(bufferedImage, null);

        try {
            FileOutputStream out = new FileOutputStream(targetFile);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bufferedImage);
            param.setQuality(quality, true);
            encoder.setJPEGEncodeParam(param);
            encoder.encode(bufferedImage);
            out.flush();
            out.close();
        } catch (Exception e) {
            LOGGER.error("");
        }
    }

    /**
     * 获取图片大小
     */
    public static Integer getImageSize(String path) {
        File picture = new File(path);
        return new Long(picture.length() / 1024).intValue();
    }
}
