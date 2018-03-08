package com.fpt.idoctor.common.util;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.codec.binary.Base64;

public class ImageUtil {
	private static String USER_IMAGE_PATH = "/images/user/";

	public static boolean saveImage(String imageString, String username) throws IOException {
		// byte[] imageByte = imageString.getBytes();
		// ByteArrayInputStream input = new ByteArrayInputStream(imageByte);
		// BufferedImage image = ImageIO.read(input);
		// ImageIO.write(image, "jpg", new File(USER_IMAGE_PATH + username));
		return true;
	}

	public static boolean saveBase64Image(String base64Image, String path) {
		try {
			byte[] imageByte = Base64.decodeBase64(base64Image);
			ByteArrayInputStream input = new ByteArrayInputStream(imageByte);
			File imgFile = new File(PathUtil.getAbsoluteResourcePath() + path);
			BufferedImage image = ImageIO.read(input);
			ImageIO.write(image, "png", imgFile);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean saveBase64File(String base64, String filePath, String fileName) {
		try {
			byte[] fileByte = Base64.decodeBase64(base64);
			saveBase64File(fileByte, filePath, fileName);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean saveBase64File(byte[] fileByte, String filePath, String fileName) {
		try {
			ByteArrayInputStream input = new ByteArrayInputStream(fileByte);
//			String res = ClassLoader.getSystemResource(filePath).getFile();
			Path path = Paths.get(filePath, fileName);
			Files.write(path, fileByte);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static byte[] getBase64File(String filePath, String fileName) {
		try {
			return null;
		} finally {
			
		}
	}
}
