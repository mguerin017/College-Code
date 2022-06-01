/******************************************************************************
* File:		snake.h
* Class:	COP 2001 50135
* Author:	Mark Guerin
* Purpose:	header file for a Snake class for a snake game where the snake moves
around the screen and grows as it eats food
******************************************************************************/
#ifndef SNAKE_H
#define SNAKE_H

#include "block.h"

class Snake
{
public:
	// -----------------------------------------------------------------------
	// constructors
	// -----------------------------------------------------------------------
	Snake();	// default constructor

	// -----------------------------------------------------------------------
	// accessors (getters & setters)
	// -----------------------------------------------------------------------
	Block*	  getHead();
	Block*	  getTail();
	int		  getSize();
	float	  getDistance();

	Direction getCurrent();
	Direction getNext();
	void	  setNext(Direction value);

	// -----------------------------------------------------------------------
	// member methods
	// -----------------------------------------------------------------------
	bool isMoving();
	void turn();
	void move(float distance);
	bool hasMoved();
	void reset();
	bool hitSelf();
	bool hitBlock(Block* block, bool all = false);
	void grow();
	void draw(OpenGL& window);

private:
	Block head;			// root of the snake
	Block* tail;		// tail of the snake
	int size;			// how large the snake has grown (starts at 1)
	float distance;		// the distance the snake has traveled since the last full block
	Direction current;	// current direction the snake is travelling
	Direction next;		// next direction the user has entered
};

#endif // SNAKE_H