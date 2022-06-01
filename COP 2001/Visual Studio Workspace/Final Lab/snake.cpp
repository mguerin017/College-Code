/******************************************************************************
* File:		snake.cpp
* Class:	COP 2001 50135
* Author:	Mark Guerin
* Purpose:	implementation file for a Snake class for a snake game where the snake moves
around the screen and grows as it eats food
******************************************************************************/
#include "snake.h"

// -----------------------------------------------------------------------
// constructors
// -----------------------------------------------------------------------
Snake::Snake()	// default constructor
{
	// set starting location of the snake's head
	head = Block(WINDOW_WIDTH / 2 + BLOCK_SIZE / 2,
				 WINDOW_HEIGHT / 2 + BLOCK_SIZE / 2,
				 BLOCK_SIZE,
				 BLOCK_SIZE,
				 SNAKE_COLOR);

	tail = &head;
	size = 1;			// snake has one block at start
	current = None;	// not moving, so no current or
	next = None;		// next direction
	distance = 0;		// the snake has not started moving yet
}

// -----------------------------------------------------------------------
// accessors (getters & setters)
// -----------------------------------------------------------------------
Block* Snake::getHead() { return &head; }
Block* Snake::getTail() { return tail; }
int		  Snake::getSize() { return size; }
float	  Snake::getDistance() { return distance; }

Direction Snake::getCurrent() { return current; }
Direction Snake::getNext() { return next; }
void	  Snake::setNext(Direction value) { next = value; }

// -----------------------------------------------------------------------
// member methods
// -----------------------------------------------------------------------

/***********************************************************
determines if the snake has velocity in either the x or y coordinate
parameters:
returns:
	bool:		true if the block is moving
***********************************************************/
bool Snake::isMoving()
{
	return head.isMoving();
}

/***********************************************************
turns the snake based on user input direction
parameters:
returns:
	void
***********************************************************/
void Snake::turn()
{
	// adjust the snake head's velocity based on
	// the next direction the user wants it to go
	switch (next)
	{
	case Up:
		if (current != Down)
		{
			head.setVelocityX(0);
			head.setVelocityY(-1);
			current = next;
		}
		break;
	case Down:
		if (current != Up)
		{
			head.setVelocityX(0);
			head.setVelocityY(1);
			current = next;
		}
		break;
	case Left:
		if (current != Right)
		{
			head.setVelocityX(-1);
			head.setVelocityY(0);
			current = next;
		}
		break;
	case Right:
		if (current != Left)
		{
			head.setVelocityX(1);
			head.setVelocityY(0);
			current = next;
		}
	}

	// wait for another user direction change
	next = None;

}

/***********************************************************
moves the snake a set distance
parameters:
	distance:	distance in pixels to move the snake
returns:
	void
***********************************************************/
void Snake::move(float distance)
{
	// limit distance to full blocks only
	if (this->distance + distance > BLOCK_SIZE)
		distance = BLOCK_SIZE - this->distance;

	// set snake's total distance moved
	this->distance += distance;

	Block* part = &head;
	while(part)
	{
		// move the block
		part->move(distance);

		// move the pointer to the block after this one
		part = part->getAfter();
	}
}

/***********************************************************
The snake has travelled a full block size
parameters:
returns:
	bool:	true if moved full block size
***********************************************************/
bool Snake::hasMoved()
{
	return distance >= BLOCK_SIZE;
}

/***********************************************************
Resets the snake's distance and sets snake directions and alignment
parameters:
returns:
	void
***********************************************************/
void Snake::reset()
{
	// reset the snake distance to start a mother full block move
	distance = 0;

	Block* part = tail;
	// go from the tail to the head making the velocity of each block
	// the same as the one in front of it, so they follow each
	// other around the board
	while(part->getBefore())
	{
		// copy the x-velocity from the block in front of this one
		part->setVelocityX(part->getBefore()->getVelocityX());

		// if the block is moving horizontally now, then copy the
		// vertical coordinate of the one in front of this one to
		// better align the snake blocks
		if (part->getVelocityX())
		{
			part->setY(part->getBefore()->getY());
		}

		// copy the y-velocity from the block in front of this one
		part->setVelocityY(part->getBefore()->getVelocityY());

		// if the block is moving vertically now, then copy the
		// horizontal coordinate of the one in front of this one to
		// better align the snake blocks
		if (part->getVelocityY())
		{
			part->setX(part->getBefore()->getX());
		}

		part = part->getBefore();
	} // end loop tail to head
}

/***********************************************************
check to see if the head of the snake hit its body
parameters:
returns:
	bool:	true if there is a collision
***********************************************************/
bool Snake::hitSelf()
{
	bool collide = false;

	Block* part = head.getAfter();
	int skip = 3;
	while (part && !collide)
	{
		// skip first 3 blocks after the head
		if (skip)
			skip--;
		else
			// check the head with current part
			collide = head.intersects(part);

		// move to the next part
		part = part->getAfter();
	}

	return collide;
}

/***********************************************************
check to see if a block overlaps the head of the snake or the whole snake
parameters:
	block:	pointer to block to check
	all:	flag if check whole snake body (true) or just head (false, default)
returns:
	bool:	true if there is a collision
***********************************************************/
bool Snake::hitBlock(Block* block, bool all)
{
	bool collide = head.intersects(block);

	// do we need to check the rest of the body?
	if (all && !collide)
	{
		Block* part = head.getAfter();
		while (part && !collide)
		{
			// check the block with current part
			collide = block->intersects(part);

			// move to the next part
			part = part->getAfter();
		}
	}

	return collide;
}

/***********************************************************
adds a new tail onto the end of the snake
parameters:
returns:
	void
***********************************************************/
void Snake::grow()
{
	// save off the current tail location
	tail->append(tail);

	tail = tail->getAfter();

	// move the new tail one block in the opposite direction
	// the tail is moving
	tail->setX(tail->getX() + tail->getVelocityX() * -1 * BLOCK_SIZE);
	tail->setY(tail->getY() + tail->getVelocityY() * -1 * BLOCK_SIZE);

	// increment snake size
	size++;
}

/***********************************************************
render the snake's body on the graphics window
parameters:
	window:	handle to graphics window
returns:
	void
***********************************************************/
void Snake::draw(OpenGL& window)
{
	Block* part = &head;
	while (part)
	{
		// move the block
		part->draw(window);

		// move the pointer to the block after this one
		part = part->getAfter();
	}
}