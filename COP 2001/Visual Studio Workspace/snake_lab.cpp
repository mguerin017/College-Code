/******************************************************************************
* File:		snake_lab.cpp
* Class:	COP 2001 50135
* Author:	Mark Guerin
* Purpose:	main application file with startup for a snake game graphics program: lab 1
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

int main()
{
	// create a graphics window for drawing
	OpenGL window = OpenGL(windowWidth, windowHeight, windowTitle.c_str());

	// clear the window memory buffer
	window.clearWindow();

	// local variables for the coordinates of the box drawn in the graphics window, relative to top left of window
	float boxPositionX{ 100 };
	float boxPositionY{ 100 };

	// local variables for the size of the box drawn in the graphics window
	int boxWidth{ foodWidth };
	int boxHeight{ foodHeight };

	// local variables for the color of the box drawn in the graphics window
	int boxRed{0};
	int boxGreen{255};
	int boxBlue{0};

	// draw a box on the screen
	window.DrawSprite(boxPositionX, boxPositionY, boxWidth, boxHeight, boxRed, boxGreen, boxBlue);

	// Change the coordinates to center the next box on the screen
	boxPositionX = windowWidth / 2 - boxWidth / 2;
	boxPositionY = windowHeight / 2 - boxHeight / 2;

	// Change the color of the next box with respect to the user's birth date
	boxRed = 3.0f / 12.0f * 255.0f;
	boxGreen = 11.0f / 31.0f * 255.0f;
	boxBlue = 1997.0f / 2020.0f * 255.0f;

	// draw another box on the screen
	window.DrawSprite(boxPositionX, boxPositionY, boxWidth, boxHeight, boxRed, boxGreen, boxBlue);

	// Set the coordinates to a random ordered pair
	srand(time(NULL));
	boxPositionX = rand() % 600 + 1;
	boxPositionY = rand() % 400 + 1;

	// Set the box size to a random width and height
	boxWidth = rand() % 30 + 1;
	boxHeight = rand() % 30 + 1;

	// Change the color of the next box to red
	boxRed = 255;
	boxGreen = 0;
	boxBlue = 0;

	// draw another box on the screen
	window.DrawSprite(boxPositionX, boxPositionY, boxWidth, boxHeight, boxRed, boxGreen, boxBlue);

	// repaint the screen
	window.paintWindow();

	// pause the program until the user enters a value
	char pause;
	std::cin >> pause;

	return 0;	// echo %errorlevel%
}