/******************************************************************************
* File:		block.h
* Class:	COP 2001 50135
* Author:	Mark Guerin
* Purpose:	header file for a Block class that represents a rectangle game object
*			with position, size, and velocities
******************************************************************************/
#ifndef BLOCK_H
#define BLOCK_H

#include "snakedefs.h"
#include "opengl.h"

class Block
{
public:
	// -----------------------------------------------------------------------
	// constructors
	// -----------------------------------------------------------------------
	Block();														// default constructor
	Block(float x, float y, float width, float height, Color color, 
		float velocityX = 0, float velocityY = 0,
		Block* before = nullptr, Block* after = nullptr);			// property constructor
	Block(Block* block);											// copy constructor

	// -----------------------------------------------------------------------
	// accessors (getters & setters)
	// -----------------------------------------------------------------------
	float  getX();
	void   setX(float value);
	float  getY();
	void   setY(float value);
	int    getWidth();
	void   setWidth(int value);
	int    getHeight();
	void   setHeight(int value);
	Color  getColor();
	void   setColor(Color value);
	float  getVelocityX();
	void   setVelocityX(float value);
	float  getVelocityY();
	void   setVelocityY(float value);
	Block* getBefore();
	void   setBefore(Block* ptrBlock);
	Block* getAfter();
	void   setAfter(Block* ptrBlock);

	// -----------------------------------------------------------------------
	// member methods
	// -----------------------------------------------------------------------
	void draw(OpenGL& window);
	bool intersects(Block* other);
	bool isMoving();
	void move(float distance);
	void append(Block* other);

private:
	float x;			// left coordinate of the top-left corner
	float y;			// top coordinate of the top-left corner
	int width;			// width of the block in pixels
	int height;			// height of the block in pixels
	Color color;		// fill color for the block
	float velocityX;	// speed of the block horizontally in pixels/second
	float velocityY;	// speed of the block vertically in pixels/second
	Block* before;
	Block* after;
};

#endif // BLOCK_H