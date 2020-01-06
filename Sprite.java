package com.jon.graphics;

import java.util.stream.IntStream;

public class Sprite 
{
	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;
	
	public static Sprite grass = new Sprite(8, 0, 0, SpriteSheet.tiles);//because they won't change
	//remember that 0, 1 would be the one below 0, 0
	public static Sprite voidSprite = new Sprite(8, 0x000000); // water - 0x3498eb
	public static Sprite flowerTile = new Sprite(8, 1, 0, SpriteSheet.tiles);
	public static Sprite rockTile = new Sprite(8, 2, 0, SpriteSheet.tiles);
	
	public static Sprite playerFront = new Sprite(8, 0, 31, SpriteSheet.tiles); //down
	public static Sprite playerLeft = new Sprite(8, 1, 31, SpriteSheet.tiles); //left
	public static Sprite playerRight = new Sprite(8, 2, 31, SpriteSheet.tiles); //right
	public static Sprite playerBack = new Sprite(8, 3, 31, SpriteSheet.tiles); //up
	
	public static Sprite player_front_1 = new Sprite(8, 0, 30, SpriteSheet.tiles);
	public static Sprite player_front_2 = new Sprite(8, 0, 29, SpriteSheet.tiles);
	public static Sprite player_back_1 = new Sprite(8, 3, 30, SpriteSheet.tiles);
	public static Sprite player_back_2 = new Sprite(8, 3, 29, SpriteSheet.tiles);
	public static Sprite player_left_1 = new Sprite(8, 1, 30, SpriteSheet.tiles);
	public static Sprite player_right_1 = new Sprite(8, 2, 30, SpriteSheet.tiles);

	
	public Sprite(int size, int x, int y, SpriteSheet sheet)
	{
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size; // tile position
		this.y = y * size;
		this.sheet = sheet;
		load();
	}
	
	public Sprite(int size, int color)
	{
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}
	
	private void setColor(int color)
	{
		for(int i = 0; i < SIZE * SIZE; i += 1)
		{
			pixels[i] = color;
		}
	}
	
	/** This will go into the exact sprite sheet and load that sprite in the coords
	 * 
	 * 	Note: Every coordinate is by 8 pixels or 1 tile
	 * */
	private void load()
	{
		for(int y = 0; y < SIZE; y += 1)
		{
			for(int x = 0; x < SIZE; x += 1)
			{
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
}
