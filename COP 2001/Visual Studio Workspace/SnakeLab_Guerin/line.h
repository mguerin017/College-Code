/******************************************************************************
* File:		line.h
* Class:	COP 2001 50135
* Author:	Mark Guerin
* Purpose:	header file for a Line class that represents a line defined by two points or a point and a slope
******************************************************************************/
#ifndef LINE_H
#define LINE_H

#include "point.h"

class Line
{
public:
	// -----------------------------------------------------------------------
	// constructors
	// -----------------------------------------------------------------------
	Line();									// default constructor
	Line(Point* pointOne, Point* pointTwo);	// property constructor: two point
	Line(Point* pointOne, float slope);		// property constructor: point-slope

	// -----------------------------------------------------------------------
	// accessors (getters & setters)
	// -----------------------------------------------------------------------
	Point* getPointOne();
	Point* getPointTwo();
	float getSlope();
	void setSlope(float value);
	float getYIntercept();
	void setYIntercept(float value);
	float getLength();
	void setLength(float value);
	float getAngle();
	void setAngle(float value);

	// -----------------------------------------------------------------------
	// member methods
	// -----------------------------------------------------------------------
	void slopeFrom2Point();
	void slopeIntercept();
	void lineLength();
	void lineAngle();
	void calculate();

private:
	Point pointOne;
	Point pointTwo;
	float slope;
	float yIntercept;
	float length;
	float angle;
};

#endif LINE_H