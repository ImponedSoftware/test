package com.jon.entity;

import java.util.Random;

import com.jon.level.Level;
import com.jon.main.Screen;

public abstract class Entity 
{
	public int x, y;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	
	// runs at 60 ticks per second
	public void update()
	{
		
	}

	public void render(Screen screen) 
	{
		
	}
	
	public void remove() 
	{
		// Remove from level
		removed = true;
	}
	
	public boolean isRemoved() {return removed;}
}
