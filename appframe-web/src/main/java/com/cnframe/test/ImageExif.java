package com.cnframe.test;

import java.io.File;
import java.io.IOException;

import com.drew.imaging.ImageProcessingException;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;


public class ImageExif {
	public static void main(String[] args) throws ImageProcessingException, IOException {

		File img = new File("E:\\照片\\手机照片\\2017-03-11\\IMG_8252.JPG");
		Metadata meta = JpegMetadataReader.readMetadata(img);

		for (Directory dir : meta.getDirectories()) {
			for (Tag tag : dir.getTags()) {
				String tagName = tag.getTagName();
				String description = tag.getDescription();
				System.out.printf("%-30s\t%-100s\n", tagName, description);
			}
		}
	}
}
