/******************************************************************************
* File:		snake_lab_3.cpp
* Class:	COP 2001 50135
* Author:	Mark Guerin
* Purpose:	main application file with startup for a snake game graphics program: lab 3
******************************************************************************/

#include <iostream>		// console I/O
#include <stdlib.h>		// random numbers
#include <time.h>		// seeds for random numbers

#include "opengl.h"		// graphics library
#include "snakedefs.h"  // game definitions

//-----------------------------------------------------------------------------
// Function prototypes
//-----------------------------------------------------------------------------

void InitGameObjects(Block borders[], Snake& snake, Block& food);
Direction processInput(OpenGL& window);
bool update(Snake& snake, Block borders[], Block& food, float deltaTime);
void render(OpenGL& window, Block borders[], Snake snake, Block food);
void DrawBlock(OpenGL& window, Block block);
bool CollisionChecks(Snake snake, Block borders[]);
bool Intersects(Block one, Block two);
bool IsMoving(Block block);
Block NextFood(Snake snake);

int main()
{
	//initiate random number sequences
	srand(time(NULL));

	// create a graphics window for drawing
	OpenGL window = OpenGL(WINDOW_WIDTH, WINDOW_HEIGHT, WINDOW_TITLE);

	// border walls
	Block borders[4];

	// the snake
	Snake snake;

	// the food block
	Block food;

	InitGameObjects(borders, snake, food);

	// frame time variables
	// --------------------
	float deltaTime = 0.0f;
	float lastFrame = 0.0f;

	// main game loop
	bool gameover = false;
	while (!gameover && !window.isClosing())
	{
		// calculate delta time
		// --------------------
		float currentFrame = window.getTime();
		deltaTime += (currentFrame - lastFrame) * 1000;
		lastFrame = currentFrame;
		
		// get user input if not still waiting to process input
		if (snake.next == None)
		{
			snake.next = processInput(window);
		}

		// set gameover if user chose to exit
		if (snake.next == Exit)
		{
			gameover = true;
		}

		// update game objects at each sub-frame interval
		while (!gameover && deltaTime >= FPS_RATE)
		{
			gameover = update(snake, borders, food, deltaTime);
			deltaTime -= FPS_RATE;
		}

		// draw objects
		render(window, borders, snake, food);
		window.pollEvents();
	}

	// pause the program until the user enters a value
	std::cout << "Game over.\n Press any key and then Enter to exit.\n";
	char pause;
	std::cin >> pause;

	return 0;	// echo %errorlevel%
}

/***********************************************************
initializes all game objects
parameters:
	borders:	the four boundary walls of the game window
	snake:		the snake structure
	food:		the food block
returns:
	void
***********************************************************/
void InitGameObjects(Block borders[], Snake& snake, Block& food)
{
	// initialize borders
	borders[TopWall] =		{ MARGIN,
							  MARGIN,
							  WINDOW_WIDTH - 2 * MARGIN,
							  BORDER_SIZE,
							  BORDER_COLOR,
							  0,
							  0 };

	borders[BottomWall] =	{ MARGIN,
							  WINDOW_HEIGHT - MARGIN - BORDER_SIZE,
							  WINDOW_WIDTH - 2 * MARGIN,
							  BORDER_SIZE,
							  BORDER_COLOR,
							  0,
							  0 };

	borders[LeftWall] =		{ MARGIN,
							  MARGIN,
							  BORDER_SIZE,
							  WINDOW_HEIGHT - 2 * MARGIN,
							  BORDER_COLOR,
							  0,
							  0 };

	borders[RightWall] =	{ WINDOW_WIDTH - MARGIN - BORDER_SIZE,
							  MARGIN,
							  BORDER_SIZE,
							  WINDOW_HEIGHT - 2 * MARGIN,
							  BORDER_COLOR,
							  0,
							  0 };

	// set starting location of the snake's head
	snake.body[SNAKE_HEAD] = { WINDOW_WIDTH / 2 + BLOCK_SIZE / 2,
							  WINDOW_HEIGHT / 2 + BLOCK_SIZE / 2,
							  BLOCK_SIZE,
							  BLOCK_SIZE,
							  SNAKE_COLOR,
							  0,
							  0 };

	snake.size = 1;			// snake has one block at start
	snake.current = None;	// not moving, so no current or
	snake.next = None;		// next direction
	snake.distance = 0;		// the snake has not started moving yet

	food = NextFood(snake);	// get a new block of food
}

/***********************************************************
gets user input from the keyboard
parameters:
	window:		window object that receives keyboard input
returns:
	Direction:	user selected direction or None
***********************************************************/
Direction processInput(OpenGL& window)
{
	Direction dir{ None };

	GL_KEY key = window.GetKey();

	switch (key)
	{
	case GL_KEY::KEY_ESC:
	case GL_KEY::KEY_X:
		dir = Direction::Exit;
		break;
	case GL_KEY::KEY_W:
	case GL_KEY::KEY_UP_ARROW:
		dir = Direction::Up;
		break;
	case GL_KEY::KEY_S:
	case GL_KEY::KEY_DOWN_ARROW:
		dir = Direction::Down;
		break;
	case GL_KEY::KEY_A:
	case GL_KEY::KEY_LEFT_ARROW:
		dir = Direction::Left;
		break;
	case GL_KEY::KEY_D:
	case GL_KEY::KEY_RIGHT_ARROW:
		dir = Direction::Right;
	}
	return dir;
}

/***********************************************************
updates the state of game objects
parameters:
	snake:		the snake structure
	borders:	the boundary walls of the game window
	food:		the food block
	deltaTime:	accumulated frame time
returns:
	bool:		true of the snake hit a wall or itself (game over)
***********************************************************/
bool update(Snake& snake, Block borders[], Block& food, float deltaTime)
{
	// process any user direction changes
	if (snake.next == None && !IsMoving(snake.body[SNAKE_HEAD]))
	{
		return false;
	}
	
	if (snake.next != None)
	{
		// adjust the snake head's velocity based on
		// the next direction the user wants it to go
		switch (snake.next)
		{
			case Up:
				if (snake.current != Down)
				{
					snake.body[SNAKE_HEAD].velocityX = 0;
					snake.body[SNAKE_HEAD].velocityY = -1;
					snake.current = snake.next;
				}
				break;
			case Down:
				if (snake.current != Up)
				{
					snake.body[SNAKE_HEAD].velocityX = 0;
					snake.body[SNAKE_HEAD].velocityY = 1;
					snake.current = snake.next;
				}
				break;
			case Left:
				if (snake.current != Right)
				{
					snake.body[SNAKE_HEAD].velocityX = -1;
					snake.body[SNAKE_HEAD].velocityY = 0;
					snake.current = snake.next;
				}
				break;
			case Right:
				if (snake.current != Left)
				{
					snake.body[SNAKE_HEAD].velocityX = 1;
					snake.body[SNAKE_HEAD].velocityY = 0;
					snake.current = snake.next;
				}
		}

		// wait for another user direction change
		snake.next = None;

	} // end direction changes

	// calculate the distance traveled during this update
	float distance = SNAKE_VELOCITY / deltaTime;
	snake.distance += distance;

	// has the snake moved a full block?
	if (snake.distance < BLOCK_SIZE)
	{
		// if not, then move each block a fractional amount
		for (int i = 0; i < snake.size; i++)
		{
			snake.body[i].x += snake.body[i].velocityX * distance;
			snake.body[i].y += snake.body[i].velocityY * distance;
		}
	}
	else // the snake has moved a full block
	{
		// reset the snake distance to start a mother full block move
		snake.distance = 0;

		// go from the tail to the head making the velocity of each block
		// the same as the one in front of it, so they follow each
		// other around the board
		for (int i = snake.size - 1; i > 0; i--)
		{

			// copy the x-velocity from the block in front of this one
			snake.body[i].velocityX = snake.body[i - 1].velocityX;

			// if the block is moving horizontally now, then copy the
			// vertical coordinate of the one in front of this one to
			// better align the snake blocks
			if (snake.body[i].velocityX)
			{
				snake.body[i].y = snake.body[i - 1].y;
			}

			// copy the y-velocity from the block in front of this one
			snake.body[i].velocityY = snake.body[i - 1].velocityY;

			// if the block is moving vertically now, then copy the
			// horizontal coordinate of the one in front of this one to
			// better align the snake blocks
			if (snake.body[i].velocityY)
			{
				snake.body[i].x = snake.body[i - 1].x;
			}

		} // end loop tail to head

		// check if the snake ate the food
		if (Intersects(snake.body[SNAKE_HEAD], food))
		{
			// save off the current tail location
			Block tail = snake.body[snake.size - 1];

			// move the new tail one block in the opposite direction
			// the tail is moving
			tail.x += tail.velocityX * -1 * BLOCK_SIZE;
			tail.y += tail.velocityY * -1 * BLOCK_SIZE;

			// add the new tail onto the snake and increase
			// the snake's size
			snake.body[snake.size] = tail;
			snake.size += 1;

			// generate a new food block
			food = NextFood(snake);
		}

		// check if the snake hit a wall or itself
		else if (CollisionChecks(snake, borders))
		{
			return true;	// game over
		}

	} // end full block transversal

	return false; // no collisions this update
}

/***********************************************************
draws all game objects
parameters:
	window:		graphics window
	borders:	boundary walls for the game window
	snake:		the snake structure
	food:		the food block
returns:
	void
***********************************************************/
void render(OpenGL& window, Block borders[], Snake snake, Block food)
{
	// clear window memory buffer
	window.clearWindow();

	// draw the snake's head
	for (int i = 0; i < snake.size; i++)
	{
		DrawBlock(window, snake.body[i]);
	}

	// draw the food block
	DrawBlock(window, food);

	// draw border walls
	DrawBlock(window, borders[TopWall]);
	DrawBlock(window, borders[BottomWall]);
	DrawBlock(window, borders[LeftWall]);
	DrawBlock(window, borders[RightWall]);

	// repaint screen
	window.paintWindow();

}

/***********************************************************
draws a single block object
parameters:
	window:		graphics window
	block:		game object to draw
returns:
	void
***********************************************************/
void DrawBlock(OpenGL& window, Block block)
{
	float position[2]{ block.x, block.y };			// top-left corner location
	float size[2]{ block.width, block.height };		// width and height
	window.DrawSprite(position, size, reinterpret_cast<const unsigned char*>(&block.color));
}

/***********************************************************
checks for collisions between the snake's head and the walls and snake body
parameters:
	snake:		snake structure
	borders:	walls
returns:
	bool:		true if there is a collision
***********************************************************/
bool CollisionChecks(Snake snake, Block borders[])
{
	bool collide = false;

	// check if the snake hit the top wall
	if (snake.current == Up && Intersects(snake.body[SNAKE_HEAD], borders[TopWall]))
	{
		collide = true;
	}

	// check if the snake hit the bottom wall
	else if (snake.current == Down && Intersects(snake.body[SNAKE_HEAD], borders[BottomWall]))
	{
		collide = true;
	}

	// check if the snake hit the left wall
	else if (snake.current == Left && Intersects(snake.body[SNAKE_HEAD], borders[LeftWall]))
	{
		collide = true;
	}

	// check if the snake hit the right wall
	else if (snake.current == Right && Intersects(snake.body[SNAKE_HEAD], borders[RightWall]))
	{
		collide = true;
	}

	// check if the snake hit itself
	else
	{
		for (int i = 3; i < snake.size; i++)
		{
			if (!collide)
				collide = Intersects(snake.body[SNAKE_HEAD], snake.body[i]);
		}
	}

	return collide;
}

/***********************************************************
determines if two blocks intersect one another
parameters:
	one:		block one
	two:		block two
returns:
	bool:		true if they intersect
***********************************************************/
bool Intersects(Block one, Block two)
{
	return !(int(two.x) > int(one.x + one.width) ||
			int(two.x + two.width) < int(one.x)	 ||
			int(two.y) > int(one.y + one.height) ||
			int(two.y + two.height) < int(one.y)
			);
}

/***********************************************************
determines if a block has velocity in either the x or y coordinate
parameters:
	block:		a block structure
returns:
	bool:		true if the block is moving
***********************************************************/
bool IsMoving(Block block)
{
	return int(block.velocityX) ||
		int(block.velocityY);
}

/***********************************************************
generates a random location for new food
parameters:
	snake:		snake structure
returns:
	Block:		location of the next food
***********************************************************/
Block NextFood(Snake snake)
{
	Block food = { 0, 0, BLOCK_SIZE, BLOCK_SIZE, FOOD_COLOR, 0, 0 };

	bool placed = false;
	while (!placed)
	{
		// get a random (x, y) within the game window
		food.x = rand() % (GAME_WIDTH - BLOCK_SIZE);	// random column
		food.y = rand() % (GAME_HEIGHT - BLOCK_SIZE);	// random row

		// move the coordinates into the visible screen
		food.x += (MARGIN + BORDER_SIZE);
		food.y += (MARGIN + BORDER_SIZE);

		placed = true;	// assume we are going to find an open spot
		// check if the snake occupies this location
		for (int i = 0; i < snake.size; i++)
		{
			if (Intersects(food, snake.body[i]))
			{
				placed = false;
				break;	// exit the snake loop
			}
		}
	}

	return food;
}