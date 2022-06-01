/******************************************************************************
* File:		point.h
* Class:	COP 2001 50135
* Author:	Mark Guerin
* Purpose:	header file for a Point class that represents a point with x and y coordinates
******************************************************************************/
#ifndef POINT_H
#define POINT_H

class Point
{
public:
	// -----------------------------------------------------------------------
	// constructors
	// -----------------------------------------------------------------------
	Point();					// default constructor
	Point(float x, float y);	// property constructor
	Point(Point* ptrPoint);		// copy constructor

	// -----------------------------------------------------------------------
	// accessors (getters & setters)
	// -----------------------------------------------------------------------
	float getX();
	void setX(float value);
	float getY();
	void setY(float value);

	// -----------------------------------------------------------------------
	// member methods
	// -----------------------------------------------------------------------
	bool isEmpty();

private:
	float x;
	float y;
};

#endif POINT_H