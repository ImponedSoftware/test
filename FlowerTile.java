package com.jon.level.tile;

import com.jon.graphics.Sprite;
import com.jon.main.Screen;

public class FlowerTile extends Tile{

	public FlowerTile(Sprite sprite) 
	{
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen)
	{
		screen.renderTile(x << 3, y << 3, this);
	}

}
