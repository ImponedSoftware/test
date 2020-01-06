package com.jon.main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.jon.entity.mob.Player;
import com.jon.level.Level;
import com.jon.level.RandomLevel;
import com.jon.level.SpawnLevel;

public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;

	
	public static final int WIDTH = 160;
	public static final int HEIGHT = WIDTH/ 12 * 9;
	public static final int SCALE = 3;
	private static final String NAME = "Imponed";
	
	public boolean running = false;
	
	public Screen screen;
	
	public int tickCount;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);//basic RGB
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData(); //how many pixels in the image and can update that image
	
	private JFrame frame;
	private KeyInputs key;
	private Level level;
	private Player player;
	
	//runs constructor first
	public Game()
	{
		screen = new Screen(WIDTH, HEIGHT);
		
		setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		frame = new JFrame();//creates new Jframe
		//level = new RandomLevel(64, 64); // random level 64 x 64
		key = new KeyInputs();
		level = new SpawnLevel("/textures/levels/test3.png"); 
		player = new Player(7 * 8, 7 * 8, key);

		addKeyListener(key);
		

		// put instances below here
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//press x to exit
		frame.setLayout(new BorderLayout());//sets the border layout no gaps between components
		
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		
		frame.setResizable(false);//can't resize
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);//sets the frames visibility	
	}

	public synchronized void start() 
	{
		running = true;
		new Thread(this).start();//instance of runnable
		//runs the main game loop in the run function
	}

	public synchronized void stop() 
	{
		running = false;
	}

	@Override
	public void run() 
	{
		long lastTime = System.nanoTime();//current time since 1976
		double nsPerTick = 1000000000D/60D;//how many nano seconds in one tick
		
		int ticks = 0;//we only want 1 a second
		int frames = 0;
		
		long lastTimer = System.currentTimeMillis();//we want when that second has occured
		double nanoSecondsGoneBy = 0;//how many nano seconds have gone by. Once we hit one we will minus one from it
		requestFocus();
		
		//main game loop
		while(running)
		{
			long now = System.nanoTime(); //current time to check against lastTime
			
			nanoSecondsGoneBy += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			
			while(nanoSecondsGoneBy >= 1)
			{
				ticks += 1;
				tick();//limit this to 60 updates per second
				
				nanoSecondsGoneBy -= 1;
				shouldRender = true;
			}
			
			try 
			{
				Thread.sleep(2);//so no overloading happens
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
			if(shouldRender)
			{
				frames += 1;
				render();
			}
			
			//we need to reset frames and ticks
			
			if(System.currentTimeMillis() - lastTimer >= 1000) //1 second is 1000 miliseconds: if current time - the last time is greater than 1 second...update
			{
				lastTimer += 1000; //update another second
				frame.setTitle(NAME + "  |  " + "Frames: " + frames + " Ticks: " + ticks);
				System.out.println("Frames: " + frames + " Ticks: " + ticks);
				frames = 0;
				ticks = 0;
			}
		}
		
	}

	//updates the game: internal and logic of the game
	public void tick()
	{	
		key.update();
		player.update();
	}
	
	//displays logic from ticks: We need an image
	public void render()
	{
		BufferStrategy bs = getBufferStrategy();//organize data on Canvas
												 //the pre-loaded screen replacing the old screen so there is no tearing
		
		if(bs == null)
		{
			createBufferStrategy(3);//triple buffer, and reduces tairing
			return;
		}
		
		screen.clear(); // sets every pixel to black
		
		int xScroll = player.x - screen.width / 2; // get screen offset based on player position
		int yScroll = player.y - screen.height / 2;

		level.render(xScroll, yScroll, screen);
		player.render(screen);
		
		for(int i = 0; i < pixels.length; i += 1)
		{
			pixels[i] = screen.pixels[i];
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.WHITE);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);//contents of image will be displayed on screen
		
		// image gives you the pixel data to manipulate the pixels on the screen
		
		g.dispose();//free up any graphics resources
		bs.show();//show contents
	}
	
	public static void main(String[] args)
	{
		new Game().start();
	}
}
