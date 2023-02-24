package utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ImageHandler {
	//Create hashmap for images with string as key
	HashMap<String, ImageIcon> ImageLogos = new HashMap<String, ImageIcon>();
	HashMap<String, ImageIcon> ImageIcons = new HashMap<String, ImageIcon>();

	public ImageHandler(){
		initIcons();
		initLogos();
	}
	
	void initLogos(){
		File folder = new File(getClass().getClassLoader().getResource("assets/logos/").getPath());
		File[] listOfFiles = folder.listFiles();
		for(int i =0;i<listOfFiles.length;i++){
        try {
			if (listOfFiles[i].getName().contains(".png")) {
				ImageIcon image = new ImageIcon(ImageIO.read(listOfFiles[i]));
				String name = listOfFiles[i].getName().replace(".png", "");
				//Add image to arraylist
				ImageLogos.put(name, image);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	}
	void initIcons(){
		File folder = new File(getClass().getClassLoader().getResource("assets/icons/").getPath());
		File[] listOfFiles = folder.listFiles();
		for(int i =0;i<listOfFiles.length;i++){
			try {
				if (listOfFiles[i].getName().contains(".png")) {
					ImageIcon image = new ImageIcon(ImageIO.read(listOfFiles[i]));
					String name = listOfFiles[i].getName().replace(".png", "");
					//Add image to arraylist
					ImageIcons.put(name, image);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	//Get image from hashmap
	public ImageIcon getLogo(String name){
		return ImageLogos.get(name);
	}

	public ImageIcon getIcon(String name){
		return ImageIcons.get(name);
	}
	
}
