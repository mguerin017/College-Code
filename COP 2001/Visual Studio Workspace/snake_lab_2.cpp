/******************************************************************************
* File:		snake_lab_2.cpp
* Class:	COP 2001 50135
* Author:	Mark Guerin
* Purpose:	main application file with startup for a snake game graphics program: lab 2
******************************************************************************/

#include <iostream>
#include "opengl.h"		// graphics library
#include <time.h>

//-----------------------------------------------------------------------------
// Global space - constants, functions, data structures, etc.
//-----------------------------------------------------------------------------

// constants for the width and height of the graphics window
const int windowWidth{ 600 };
const int windowHeight{ 400 };

// constants for the size of the box drawn in the graphics window
const int foodWidth{ 10 };
const int foodHeight{ 10 };

// constant for the title displayed at the top of the graphics window
const std::string windowTitle = "Mark's Snake Game";

// Global variables for block structure. Determines position, height, and color of the box drawn in the window
struct Block {
	float x;
	float y;
	float width;
	float height;
	int paint;
};

// enumerated type for colors and their hex codes
enum Color {
	White = 0xFFFFFF00,
	Silver = 0xC0C0C000,
	Gray = 0x80808000,
	Black = 0x00000000,
	Red = 0xFF000000,
	Maroon = 0x80000000,
	Yellow = 0xFFFF0000,
	Olive = 0x80800000,
	Lime = 0x00FF0000,
	Green = 0x00800000,
	Aqua = 0x00FFFF00,
	Teal = 0x00808000,
	Blue = 0x0000FF00,
	Navy = 0x00008000,
	Fuchsia = 0xFF00FF00,
	Purple = 0x80008000
};

// enumerated type for possible user inputs
enum Direction {
	Exit = -1,
	None,
	Up,
	Down,
	Left,
	Right
};

int processInput(OpenGL& window);
void update(Block& food, int direc);
void render(OpenGL& window, Block food);

int main()
{
	// create a graphics window for drawing
	OpenGL window = OpenGL(windowWidth, windowHeight, windowTitle.c_str());

	// declare a purple block to start in the center of the screen
	Block food{ windowWidth / 2 - foodWidth / 2, windowHeight / 2 - foodHeight / 2, foodWidth, foodHeight, Purple };

	bool gameover{ false };
	while (!window.isClosing() && !gameover) {
		int direction{processInput(window)};
		if (direction == Exit)
			gameover = true;
		update(food, direction);
		render(window, food);
		window.pollEvents();
	}

	// pause the program until the user enters a value
	char pause;
	std::cin >> pause;

	return 0;	// echo %errorlevel%
}

// Interprets user inputs through WASD or Arrow keys, as well as Esc or X, as an integer value, then returns the appropriate value for the key pressed
// Params: an object of the class OpenGL
// Returns an integer that corresponds to a value in the Direction enumerated type
int processInput(OpenGL& window) {
	int dir{ None };
	GL_KEY key = window.GetKey();
	switch (key) {
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

// Moves the block on the screen based on the value returned by the processInput function
// Params: a Block structure and an integer corresponding to the direction that the block will be moved
void update(Block& food, int direc) {
	switch (direc) {
	case Up:
		food.y -= food.height;
		break;
	case Down:
		food.y += food.height;
		break;
	case Left:
		food.x -= food.width;
		break;
	case Right:
		food.x += food.width;
	}
}

// Sets the background color of the window and the size, color, and position of the block on the screen, then draws the window to those specifications
// Params: an object of the class OpenGL and a Block structure
void render(OpenGL &window, Block food) {
	Color background = Black;
	window.clearWindow(reinterpret_cast<const unsigned char*>(&background));
	float position[2];
	position[0] = food.x;
	position[1] = food.y;
	float size[2];
	size[0] = food.width;
	size[1] = food.height;
	unsigned char* color = reinterpret_cast<unsigned char*>(&food.paint);
	window.DrawSprite(position, size, color);
	window.paintWindow();
}