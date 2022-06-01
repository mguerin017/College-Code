/******************************************************************************
* File:		point.cpp
* Class:	COP 2001 50135
* Author:	Mark Guerin
* Purpose:	implementation file for a Point class that represents a point with x and y coordinates
******************************************************************************/
#include "point.h"

// -----------------------------------------------------------------------
// constructors
// -----------------------------------------------------------------------
Point::Point()					// default constructor
{
	x = 0.0f;
	y = 0.0f;
}
Point::Point(float x, float y)	// property constructor
{
	this->x = x;
	this->y = y;
}
Point::Point(Point* ptrPoint)	// copy constructor
{
	x = ptrPoint->x;
	y = ptrPoint->y;
}

// -----------------------------------------------------------------------
// accessors (getters & setters)
// -----------------------------------------------------------------------
float Point::getX() { return x; }
void Point::setX(float value) { x = value; }
float Point::getY() { return y; }
void Point::setY(float value) { y = value; }

// -----------------------------------------------------------------------
// member methods
// -----------------------------------------------------------------------

/***********************************************************
Checks if the point has empty coordinates
parameters:
	Point*:		a pointer to a Point class object
returns:
	bool:		true if x and y coordinates are zero
***********************************************************/
bool Point::isEmpty()
{
	if (x == 0.0f && y == 0.0f)
		{
		return true;
		}
	else
		{
		return false;
		}
}