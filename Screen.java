package com.jon.main;

import java.util.Random;

import com.jon.entity.mob.Player;
import com.jon.graphics.Sprite;
import com.jon.level.tile.Tile;

public class Screen 
{
	public int width, height;
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;
	public int xOffset, yOffset;
	public int[] pixels;
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	private Random random = new Random();
	
	public Screen(int width, int height)
	{
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
				
		for(int i = 0; i < MAP_SIZE * MAP_SIZE; i += 1)
		{
			tiles[i] = random.nextInt(0xffffff);
		}
	}
	
	/**Sets every pixel in our pixel array to black*/
	public void clear()
	{
		//IntStream.range(0, pixels.length).parallel().mapToDouble(x -> pixels[x] = 0);
		
		for(int i = 0; i < pixels.length; i += 1)
		{
			pixels[i] = 0; // makes all pixels black
		}
		
	}
	
	/**This will render each tile individually*/
	public void renderTile(int xTilePos, int yTilePos, Tile tile)
	{
		xTilePos -= xOffset;// fix the map moving
		yTilePos -= yOffset;// adjusting location offset
		for(int y = 0; y < tile.sprite.SIZE; y += 1)
		{
			int yAbsolute = y + yTilePos;// offset yposition = tile position
			for(int x = 0; x < tile.sprite.SIZE; x += 1)
			{
				int xAbsolute = x + xTilePos;
				if(xAbsolute < -tile.sprite.SIZE || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) break;// height for y instead of width
				if(xAbsolute < 0) xAbsolute = 0;
				pixels[xAbsolute + yAbsolute * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
			}
		}
	}
	
	/**Render the player individually*/
	public void renderPlayer(int xTilePos, int yTilePos, Sprite sprite)
	{
		xTilePos -= xOffset;// fix the map moving
		yTilePos -= yOffset;// adjusting location offset
		for(int y = 0; y < 8; y += 1)
		{
			int yAbsolute = y + yTilePos;// offset yposition = tile position
			for(int x = 0; x < 8; x += 1)
			{
				//sprite flipping just 7 - x
				// reverse rendering
				int xAbsolute = x + xTilePos; // tile position
				if(xAbsolute < -8 || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) break;// height for y instead of width
				if(xAbsolute < 0) xAbsolute = 0;
				int col = sprite.pixels[x + y * 8]; // colors the sprite
				
				if(col != 0xffff10ec)//adding extra ff in front makes it so pink isn't rendered
				{
					pixels[xAbsolute + yAbsolute * width] = col; // actual rendering based on sprite position
				}
								
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset)
	{
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	
}
