/******************************************************************************
* File:		snakegame.cpp
* Class:	COP 2001 50135
* Author:	Mark Guerin
* Purpose:	main application file with startup for a snake game graphics program: lab 4
******************************************************************************/

#include <iostream>		// console I/O
#include <stdlib.h>		// random numbers
#include <time.h>		// seeds for random numbers

#include "opengl.h"		// graphics library
#include "snakedefs.h"  // game definitions
#include "block.h"		// game block objects
#include "snake.h"		// the snake object

//-----------------------------------------------------------------------------
// Function prototypes
//-----------------------------------------------------------------------------

void InitGameObjects(Block borders[], Snake& snake, Block& food);
Direction processInput(OpenGL& window);
bool update(Snake& snake, Block borders[], Block& food, float deltaTime);
void render(OpenGL& window, Block borders[], Snake snake, Block food);
bool CollisionChecks(Snake snake, Block borders[]);
Block NextFood(Snake snake);

int main()
{
	//initiate random number sequences
	srand(time(NULL));

	// create a graphics window for drawing
	OpenGL window = OpenGL(WINDOW_WIDTH, WINDOW_HEIGHT, WINDOW_TITLE.c_str());

	// border walls
	Block borders[4];

	// the snake
	Snake snake = Snake();	// initialize with default constructor

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
		if (snake.getNext() == None)
		{
			snake.setNext(processInput(window));
		}

		// set gameover if user chose to exit
		if (snake.getNext() == Exit)
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
	snake:		snake class object
	food:		the food block
returns:
	void
***********************************************************/
void InitGameObjects(Block borders[], Snake& snake, Block& food)
{
	// initialize borders
	borders[TopWall] = Block( MARGIN,
							  MARGIN,
							  WINDOW_WIDTH - 2 * MARGIN,
							  BORDER_SIZE,
							  BORDER_COLOR );

	borders[BottomWall] = Block( MARGIN,
								 WINDOW_HEIGHT - MARGIN - BORDER_SIZE,
								 WINDOW_WIDTH - 2 * MARGIN,
								 BORDER_SIZE,
								 BORDER_COLOR );

	borders[LeftWall] = Block( MARGIN,
							   MARGIN,
							   BORDER_SIZE,
							   WINDOW_HEIGHT - 2 * MARGIN,
							   BORDER_COLOR );

	borders[RightWall] = Block( WINDOW_WIDTH - MARGIN - BORDER_SIZE,
								MARGIN,
								BORDER_SIZE,
								WINDOW_HEIGHT - 2 * MARGIN,
								BORDER_COLOR );

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
	snake:		snake class object
	borders:	the boundary walls of the game window
	food:		the food block
	deltaTime:	accumulated frame time
returns:
	bool:		true of the snake hit a wall or itself (game over)
***********************************************************/
bool update(Snake& snake, Block borders[], Block& food, float deltaTime)
{
	// skip updates until the snake begins moving
	if (!snake.isMoving())
	{
		if (snake.getNext() == None)
			return false;
		else
			// make first turn
			snake.turn();
	}

	// calculate the distance traveled during this update
	float distance = SNAKE_VELOCITY / deltaTime;
	snake.move(distance);

	// has the snake moved a full block?
	if (snake.hasMoved())
	{
		snake.turn();

		snake.reset();

		// check if the snake ate the food
		if (snake.hitBlock(&food))
		{
			snake.grow();

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
	snake.draw(window);

	// draw the food block
	food.draw(window);

	// draw border walls
	borders[TopWall].draw(window);
	borders[BottomWall].draw(window);
	borders[RightWall].draw(window);
	borders[LeftWall].draw(window);

	// repaint screen
	window.paintWindow();

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
	if (snake.getCurrent() == Up && snake.hitBlock(&borders[TopWall]))
	{
		collide = true;
	}

	// check if the snake hit the bottom wall
	else if (snake.getCurrent() == Down && snake.hitBlock(&borders[BottomWall]))
	{
		collide = true;
	}

	// check if the snake hit the left wall
	else if (snake.getCurrent() == Left && snake.hitBlock(&borders[LeftWall]))
	{
		collide = true;
	}

	// check if the snake hit the right wall
	else if (snake.getCurrent() == Right && snake.hitBlock(&borders[RightWall]))
	{
		collide = true;
	}

	// check if the snake hit itself
	else
	{
		collide = snake.hitSelf();
	}

	return collide;
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
	Block food = Block(0, 0, BLOCK_SIZE, BLOCK_SIZE, FOOD_COLOR);

	bool placed = false;
	while (!placed)
	{
		// get a random (x, y) within the game window
		int xCoord = rand() % (GAME_WIDTH - BLOCK_SIZE);	// random column
		int yCoord = rand() % (GAME_HEIGHT - BLOCK_SIZE);	// random row

		// move the coordinates into the visible screen
		xCoord += (MARGIN + BORDER_SIZE);
		yCoord += (MARGIN + BORDER_SIZE);

		food.setX(xCoord);
		food.setY(yCoord);

		placed = !snake.hitBlock(&food, true);
	}

	return food;
}