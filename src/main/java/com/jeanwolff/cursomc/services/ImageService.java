package com.jeanwolff.cursomc.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jeanwolff.cursomc.services.exceptions.FileException;

@Service
public class ImageService {

	public BufferedImage getImageFromFile(MultipartFile uploadedFile) {
		String ext = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
		if("png".equalsIgnoreCase(ext) && !"jpg".equalsIgnoreCase(ext)) {
			throw new FileException("Somente imagens PNG e JPG são permitidas");
		}
		try {
			BufferedImage img = ImageIO.read(uploadedFile.getInputStream());
			if("png".equalsIgnoreCase(ext)) {
				img = pngToJpg(img);
			}
		} catch (IOException e) {
			throw new FileException("Erro ao ler a exception");
		}
		return null;
	}

	public BufferedImage pngToJpg(BufferedImage img) {
		BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
		return jpgImage;		
	}
	
	
	public InputStream getInputStream(BufferedImage img, String extension) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			ImageIO.write(img, extension, os);
			return new ByteArrayInputStream(os.toByteArray());
		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo");
		}
		
	}
	
}
