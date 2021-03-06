package com.jon.level;

import java.util.Random;

public class RandomLevel extends Level 
{
	private static final Random random = new Random();
	
	public RandomLevel(int width, int height)
	{
		super(width, height);
	}
	
	/**Generate random tiles in a 64 x 64*/
	protected void generateLevel()
	{
		for(int y = 0; y < height; y += 1)
		{
			for(int x = 0; x < width; x += 1)
			{
				tilesInt[x + y * width] = random.nextInt(4);//0, 1, 2 ,3
			}
		}
	}
}
