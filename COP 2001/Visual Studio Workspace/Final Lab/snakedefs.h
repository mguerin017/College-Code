/******************************************************************************
* File:		snakedefs.h
* Class:	COP 2001 50135
* Author:	Mark Guerin
* Purpose:	header file with program definitions for snake game: lab 4
******************************************************************************/
#ifndef SNAKE_DEFS_H
#define SNAKE_DEFS_H

#include <string>

// game fps in milliseconds
const float FPS_RATE = 1000 / 60.0f;

// constants for game area boundaries
const int GAME_WIDTH{ 400 };
const int GAME_HEIGHT{ 200 };

// constants for game area MARGIN, border, and block size
const int MARGIN{ 10 };
const int BORDER_SIZE{ 2 };
const int BLOCK_SIZE{ 10 };

// the head of the snake is the block at index zero in the body array
const int SNAKE_HEAD{ 0 };

// how many pixels the snake should move each frame
const float SNAKE_VELOCITY{ BLOCK_SIZE * 5 };

// constants for the width, height, and title of the graphics window
const int WINDOW_WIDTH{ GAME_WIDTH + 2 * MARGIN + 2 * BORDER_SIZE };
const int WINDOW_HEIGHT{ GAME_HEIGHT + 2 * MARGIN + 2 * BORDER_SIZE };
const std::string WINDOW_TITLE = "Mark's Snake Game";

enum Border {
	TopWall,
	BottomWall,
	LeftWall,
	RightWall
};

// enumerated type for movement of snake
enum Direction {
	Exit = -1,
	None,
	Up,
	Down,
	Left,
	Right
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

const Color BORDER_COLOR{ Teal };
const Color SNAKE_COLOR{ Green };
const Color FOOD_COLOR{ Fuchsia };

#endif