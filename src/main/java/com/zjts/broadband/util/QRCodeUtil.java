package com.zjts.broadband.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.util.Hashtable;

/**
 * 二维码工具类
 */
public class QRCodeUtil {

    private static final String CHARSET = "utf-8";
    private static final String FORMAT_NAME = "JPG";
    // 二维码尺寸
    private static final int QRCODE_SIZE = 350;
    // LOGO宽度
    private static final int WIDTH = 65;
    // LOGO高度
    private static final int HEIGHT = 65;

    private static BufferedImage createImage(String content, String imgPath, boolean needCompress) throws Exception {
        BufferedImage image = createQCcode(content);
        if (StringUtils.isEmpty(imgPath)) return image;
        // 插入图片
        QRCodeUtil.insertImage(image, imgPath, needCompress);
        return image;
    }

    private static BufferedImage createImageUrl(String content, String imgUrl, boolean needCompress) throws Exception {
        BufferedImage image = createQCcode(content);
        if (StringUtils.isBlank(imgUrl)) return image;
        // 插入图片
        QRCodeUtil.insertImageUrl(image, imgUrl, needCompress);
        return image;
    }

    /**
     * 插入LOGO
     *
     * @param source       二维码图片
     * @param imgPath      LOGO图片地址
     * @param needCompress 是否压缩
     * @throws Exception
     */
    private static void insertImage(BufferedImage source, String imgPath, boolean needCompress) throws Exception {
        File file = new File(imgPath);
        if (!file.exists()) {
            System.err.println("" + imgPath + "   该文件不存在！");
            return;
        }
        Image src = ImageIO.read(new File(imgPath));
        writeImg(source, src, needCompress);
    }


    /**
     * 生成二维码(内嵌LOGO)
     *
     * @param content      内容
     * @param imgPath      LOGO地址
     * @param destPath     存放目录
     * @param needCompress 是否压缩LOGO
     * @throws Exception
     */
    public static void encode(String content, String imgPath, String destPath, boolean needCompress) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, imgPath, needCompress);
        mkdirs(destPath);
        ImageIO.write(image, FORMAT_NAME, new File(destPath));
    }


    /**
     * 获取图片字节流
     *
     * @param content
     * @param imgURL
     * @param needCompress
     * @return
     * @throws Exception
     */
    public static InputStream getEncodeInputStream(String content, String imgURL, boolean needCompress) throws Exception {
        BufferedImage image = QRCodeUtil.createImageUrl(content, imgURL, needCompress);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, FORMAT_NAME, byteArrayOutputStream);
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    /**
     * 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
     *
     * @param destPath 存放目录
     * @author lanyuan Email: mmm333zzz520@163.com
     * @date 2013-12-11 上午10:16:36
     */
    public static void mkdirs(String destPath) {
        File file = new File(destPath);
        // 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }

    /**
     * 生成二维码(内嵌LOGO)
     *
     * @param content  内容
     * @param imgPath  LOGO地址
     * @param destPath 存储地址
     * @throws Exception
     */
    public static void encode(String content, String imgPath, String destPath) throws Exception {
        QRCodeUtil.encode(content, imgPath, destPath, false);
    }

    /**
     * 生成二维码
     *
     * @param content      内容
     * @param destPath     存储地址
     * @param needCompress 是否压缩LOGO
     * @throws Exception
     */
    public static void encode(String content, String destPath, boolean needCompress) throws Exception {
        QRCodeUtil.encode(content, null, destPath, needCompress);
    }

    /**
     * 生成二维码
     *
     * @param content  内容
     * @param destPath 存储地址
     * @throws Exception
     */
    public static void encode(String content, String destPath) throws Exception {
        QRCodeUtil.encode(content, null, destPath, false);
    }

    /**
     * 生成二维码(内嵌LOGO)
     *
     * @param content      内容
     * @param imgPath      LOGO地址
     * @param output       输出流
     * @param needCompress 是否压缩LOGO
     * @throws Exception
     */
    public static void encode(String content, String imgPath, OutputStream output, boolean needCompress)
            throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, imgPath, needCompress);
        ImageIO.write(image, FORMAT_NAME, output);
    }

    /**
     * 生成二维码
     *
     * @param content 内容
     * @param output  输出流
     * @throws Exception
     */
    public static void encode(String content, OutputStream output) throws Exception {
        QRCodeUtil.encode(content, null, output, false);
    }

    /**
     * 解析二维码
     *
     * @param file 二维码图片
     * @return
     * @throws Exception
     */
    public static String decode(File file) throws Exception {
        BufferedImage image;
        image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        result = new MultiFormatReader().decode(bitmap, hints);
        String resultStr = result.getText();
        return resultStr;
    }

    /**
     * 解析二维码
     *
     * @param url 二维码图片
     * @return
     * @throws Exception
     */
    public static String decodeUrl(String url) throws Exception {

        BufferedImage image;
        image = (BufferedImage) getImgForUrl(url);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        result = new MultiFormatReader().decode(bitmap, hints);
        String resultStr = result.getText();
        return resultStr;
    }

    /**
     * 解析二维码
     *
     * @param path 二维码图片地址
     * @return
     * @throws Exception
     */
    public static String decode(String path) throws Exception {
        return QRCodeUtil.decode(new File(path));
    }


    /**
     * 生成二维码
     *
     * @param content 内容
     * @return
     * @throws Exception
     */
    private static BufferedImage createQCcode(String content) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE,
                hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }

    /**
     * 插入LOGO
     *
     * @param source       二维码图片
     * @param imgUrl       LOGO图片URL地址
     * @param needCompress 是否压缩
     * @throws Exception
     */
    private static void insertImageUrl(BufferedImage source, String imgUrl, boolean needCompress) throws Exception {
        Image src = getImgForUrl(imgUrl);
        writeImg(source, src, needCompress);
    }


    /**
     * 获取图片流
     * @param imgUrl
     * @return
     * @throws Exception
     */
    private static Image getImgForUrl(String imgUrl) throws Exception {
        URL uri = new URL(imgUrl);
        Image src = null;
        try {
            src = ImageIO.read(uri.openStream());
        } catch (ConnectException ex) {
            throw new ConnectException("URL无效:" + imgUrl);
        }
        return src;
    }


    /**
     * 插入LOGO
     *
     * @param source       二维码图片
     * @param src          LOGO图片地址
     * @param needCompress 是否压缩
     * @throws Exception
     */
    private static void writeImg(BufferedImage source, Image src, boolean needCompress) {
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if (needCompress) { // 压缩LOGO
            if (width > WIDTH) {
                width = WIDTH;
            }
            if (height > HEIGHT) {
                height = HEIGHT;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }


    public static void main(String[] args) throws Exception {
        //String text = "http://127.0.0.1:8011/drugo2o/dynamic/scan?type=1&serial=4104";
		//QRCodeUtil.encode(text, "/Users/yuanyong/zhicheng_work_new/DrugO2O/一笑堂.png", "/Users/yuanyong/zhicheng_work_new/DrugO2O/药店静态二维码样例.jpg", true);


        //InputStream inputStream=QRCodeUtil.getEncodeInputStream(text, "https://drugo2omall.oss-cn-zhangjiakou.aliyuncs.com/backfile/7086ee72-f054-40a1-a202-638ad762f8cb.jpg", true);
        //System.out.println(AliyunOSSClientUtil.uploadInputStream(inputStream,"png"));

        System.out.println(QRCodeUtil.decodeUrl("https://drugo2omall.oss-cn-zhangjiakou.aliyuncs.com/backfile/88dae516-8bd2-4192-b9c6-866c23a91a92.png"));
    }
}