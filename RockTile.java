package com.jon.level.tile;

import com.jon.graphics.Sprite;
import com.jon.main.Screen;

public class RockTile extends Tile
{

	public RockTile(Sprite sprite) 
	{
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen)
	{
		screen.renderTile(x << 3, y << 3, this);
	}

	public boolean solid()
	{
		return true;
	}
}
