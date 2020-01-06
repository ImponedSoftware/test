package com.jon.level.tile;

import com.jon.graphics.Sprite;
import com.jon.main.Screen;

public class Tile 
{
	public int x, y;
	public Sprite sprite;
	
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	public static Tile flowerTile = new FlowerTile(Sprite.flowerTile);
	public static Tile rockTile = new RockTile(Sprite.rockTile);
	
	public Tile(Sprite sprite)
	{
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen){}
	
	public boolean solid()
	{
		return false;
	}

}
