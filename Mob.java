package com.jon.entity.mob;

import com.jon.entity.Entity;
import com.jon.graphics.Sprite;

public abstract class Mob extends Entity
{
	protected Sprite sprite;
	protected int dir = -1;
	protected boolean moving = false;
	
	public void move(int xMobDir, int yMobDir)
	{
		// 0 - north 1 - east 2 - south 3 - west
		if(xMobDir > 0) dir = 1;
		if(xMobDir < 0) dir = 3;
		if(yMobDir > 0) dir = 2;
		if(yMobDir < 0) dir = 0;

		if(!collision())
		{
			x += xMobDir;
			y += yMobDir;
		}
	}
	public void update() {}
	
	private boolean collision()
	{
		return false;
	}
	
	public void render() {}
}
