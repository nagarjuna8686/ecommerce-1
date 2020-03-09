package ecommerce.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import ecommerce.exceptions.EcommerceException;

public class FileProp {

	public byte[] getFile(String url) throws EcommerceException, FileNotFoundException {
		InputStream inputStream = FileProp.class.getResourceAsStream("/image.prop");
		InputStream pathFolderImages = FileProp.class.getResourceAsStream("/pathUpload.prop");
		Properties propFiles = new Properties();
		Properties propFolder = new Properties();
		String nameImage = null;
		String URLFolder = null;
		String nameFnFImage = null;
		String completePath = null;
		FileInputStream fis;
		File file = null;
		int flag = 0;
		if (inputStream != null) {
			try {
				propFiles.load(inputStream);
				propFolder.load(pathFolderImages);
			} catch (IOException e) {
				throw new EcommerceException("Failed to load properties");
			}
			nameImage = (String) propFiles.get("images");
			URLFolder = (String) propFolder.get("path");
			nameFnFImage = (String) propFiles.getProperty("fileNotFound");
		}
		String[] arrayNameImage = nameImage.split(",");

		for (int i = 0; i < arrayNameImage.length; i++) {
			if (arrayNameImage[i].equals(url)) {
				completePath = URLFolder + arrayNameImage[i];
				flag = 1;
			}
		}
		if (flag != 1) {
			file = new File(URLFolder + nameFnFImage);
			fis = new FileInputStream(file);
		} else {
			file = new File(completePath);
			fis = new FileInputStream(file);
		}

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		try {
			for (int readNum; (readNum = fis.read(buf)) != -1;) {
				bos.write(buf, 0, readNum);
			}

			fis.close();
		} catch (IOException ex) {
			throw new EcommerceException("Failed to write file");
		}

		return bos.toByteArray();
	}

	public void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) throws EcommerceException {

		try {
			FileOutputStream out = new FileOutputStream(new File(uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
				System.out.println(read);
			}

			out.flush();
			out.close();
		} catch (IOException e) {
			throw new EcommerceException("Failed to write file");
		}

	}
	
	public void addImageOnFileProp(String imageName) throws EcommerceException, FileNotFoundException, IOException {
		InputStream inputStream = FileProp.class.getClass().getResourceAsStream("/image.prop");
		Properties propFiles = new Properties();
		String nameImage = null;
		
		if (inputStream != null) {
			try {
				propFiles.load(inputStream);
			} catch (IOException e) {
				throw new EcommerceException("File image.prop non trovato");
			}
			nameImage = (String) propFiles.get("images");
			propFiles.setProperty("images", ","+nameImage);
			propFiles.store(new FileOutputStream("C:/Users/ntt data/workspace/ecommerce/backend/src/main/resources/image.prop"), null);
		}

	}
}
