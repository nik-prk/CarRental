package com.nyit.carrental.imageservice.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;

public class ImageResizeUtil {

	public static File resize(MultipartFile inputFile, int scaledWidth, int scaledHeight) throws IOException {
		// reads input image
		File convFile = new File(inputFile.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(inputFile.getBytes());
		fos.close();
		BufferedImage inputImage = ImageIO.read(convFile);
		// creates output image
		BufferedImage outputImage = Scalr.resize(inputImage, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_EXACT,
				scaledWidth, scaledHeight);
		File newFile = new File(inputFile.getOriginalFilename());
		String type = inputFile.getOriginalFilename().substring(inputFile.getOriginalFilename().lastIndexOf(".") + 1,
				inputFile.getOriginalFilename().length());
		ImageIO.write(outputImage, type, newFile);
		return newFile;
	}

}
