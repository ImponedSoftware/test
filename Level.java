package com.jon.level;

import com.jon.level.tile.Tile;
import com.jon.main.Screen;

public class Level 
{
	protected int width, height;
	protected int[] tilesInt;//tiles ids
	protected int[] tiles;
	
	public Level(int width, int height)
	{
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}
	
	public Level(String path)
	{
		loadLevel(path);
		generateLevel();
	}
	
	protected void generateLevel(){
	}

	protected void loadLevel(String path){
	}
	
	public void update()
	{
		
	}
	
	private void time()
	{
		
	}
	
	public void render(int xScroll, int yScroll, Screen screen)
	{
		screen.setOffset(xScroll, yScroll);
		// define render region of the screen
		int x0 = xScroll >> 3; // 2^3
		int x1 = (xScroll + screen.width + 8) >> 3; // corner pins
		int y0 = yScroll >> 3;
		int y1 = (yScroll + screen.height + 8) >> 3;
		
		for(int y = y0; y < y1; y += 1)
		{
			for(int x = x0; x < x1; x += 1)
			{
				getTile(x, y).render(x, y, screen); // render tile
			
			}
		}
	}
	
	// Grass = 0xff00a651
	// Flower = 0xffffff00
	// Rock = 0xffed1c24		
	// add ff
	public Tile getTile(int x, int y)
	{
		if(x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if(tiles[x + y * width] == 0xff00a651) return Tile.grass;
		if(tiles[x + y * width] == 0xffffff00) return Tile.flowerTile;
		if(tiles[x + y * width] == 0xffed1c24) return Tile.rockTile;
		return Tile.voidTile;
	}
	
}
