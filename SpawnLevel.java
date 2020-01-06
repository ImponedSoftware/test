package com.jon.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.jon.level.tile.Tile;

public class SpawnLevel extends Level
{	
	public SpawnLevel(String path)
	{
		super(path);
	}

	protected void loadLevel(String path)
	{
		try 
		{
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path)); // gets the image
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			
			System.out.println(w + " x " + h);
			
			tiles = new int[w * h];
			
			image.getRGB(0, 0, w, h, tiles, 0, w); //convert image to array of pixels
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("Could not load level file!");
		}
	}
	
	protected void generateLevel()
	{
	}
	
}
