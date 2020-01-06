package com.jon.level.tile;

import com.jon.graphics.Sprite;
import com.jon.main.Screen;

public class GrassTile extends Tile
{

	public GrassTile(Sprite sprite) 
	{
		super(sprite);
	}
	
	// render called in the Tile class
	// render gets called and is overwritten by this render to render this tile on a specific location on the screen
	public void render(int x, int y, Screen screen)
	{
		screen.renderTile(x << 3, y << 3, this); // <<  == * 8
	}

}
