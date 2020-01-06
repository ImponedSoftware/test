package com.jon.entity.mob;

import com.jon.graphics.Sprite;
import com.jon.main.KeyInputs;
import com.jon.main.Screen;

public class Player extends Mob
{
	private KeyInputs input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking = false;
	
	public Player(KeyInputs input)
	{
		this.input = input;
		sprite = Sprite.playerFront;
	}
	
	public Player(int x, int y, KeyInputs input)
	{
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.playerFront;
	}
	
	public void update()
	{
		int xa = 0, ya = 0;
		if(anim < 7500) anim += 1; // If game is open, counter doesn't break game
		else anim = 0;
		
		if(input.up) ya -= 1;
		if(input.down) ya += 1;
		if(input.left) xa -= 1;
		if(input.right) xa += 1;
		
		if(xa != 0 || ya != 0) 
		{	
			move(xa, ya);
			walking = true;
		}
		else
		{
			walking = false;
		}
	}
	
	public void render(Screen screen)
	{
		if(dir == 0) 
		{
			sprite = Sprite.playerBack; //based on direction, render
			if(walking)
			{
				if(anim % 20 >	10) //even factor
				{
					sprite = Sprite.player_back_1;
				}
				else
				{
					sprite = Sprite.player_back_2;
				}
			}
		}
		if(dir == 2) 
		{
			sprite = Sprite.playerFront;
			if(walking)
			{
				if(anim % 20 >	10) //even factor and timing for animation change
				{
					sprite = Sprite.player_front_1;
				}
				else
				{
					sprite = Sprite.player_front_2;
				}
			}
		}
		if(dir == 3) 
		{	
			sprite = Sprite.playerLeft;
			if(walking && anim % 20 > 10)
			{
				sprite = Sprite.player_left_1;
			}
		}
		if(dir == 1)
		{
			sprite = Sprite.playerRight;
			if(walking && anim % 20 > 10)
			{
				sprite = Sprite.player_right_1;
			}
		}
		screen.renderPlayer(x, y, sprite);
	}
}
