/******************************************************************************
* File:		line.cpp
* Class:	COP 2001 50135
* Author:	Mark Guerin
* Purpose:	implementation file for a Line class that represents a line defined by two points or a point and a slope
******************************************************************************/
#include <cmath>

#include "line.h"

// -----------------------------------------------------------------------
// constructors
// -----------------------------------------------------------------------
Line::Line()									// default constructor
{
	pointOne = Point();
	pointTwo = Point();
	slope = 0.0f;
	yIntercept = 0.0f;
	length = 0.0f;
	angle = 0.0f;
}
Line::Line(Point* pointOne, Point* pointTwo)	// property constructor: two point
{
	this->pointOne = Point(pointOne);
	this->pointTwo = Point(pointTwo);
	slope = 0.0f;
	yIntercept = 0.0f;
	length = 0.0f;
	angle = 0.0f;
}
Line::Line(Point* pointOne, float slope)		// property constructor: point-slope
{
	this->pointOne = Point(pointOne);
	pointTwo = Point();
	this->slope = slope;
	yIntercept = 0.0f;
	length = 0.0f;
	angle = 0.0f;
}

// -----------------------------------------------------------------------
// accessors (getters & setters)
// -----------------------------------------------------------------------
Point* Line::getPointOne() { return &pointOne; }
Point* Line::getPointTwo() { return &pointTwo; }
float Line::getSlope() { return slope; }
void Line::setSlope(float value) { slope = value; }
float Line::getYIntercept() { return yIntercept; }
void Line::setYIntercept(float value) { yIntercept = value; }
float Line::getLength() { return length; }
void Line::setLength(float value) { length = value; }
float Line::getAngle() { return angle; }
void Line::setAngle(float value) { angle = value; }

// -----------------------------------------------------------------------
// member methods
// -----------------------------------------------------------------------

/***********************************************************
Sets the slope property
parameters:
returns:
	void
***********************************************************/
void Line::slopeFrom2Point()
{
	slope = (pointTwo.getY() - pointOne.getY()) / (pointTwo.getX() - pointOne.getX());
}

/***********************************************************
Sets the y-intercept property
parameters:
returns:
	void
***********************************************************/
void Line::slopeIntercept()
{
	yIntercept = pointOne.getY() - (slope * pointOne.getX());
}

/***********************************************************
Sets the length of the line
parameters:
returns:
	void
***********************************************************/
void Line::lineLength()
{
	length = std::sqrt(std::pow(pointTwo.getX() - pointOne.getX(), 2) + std::pow(pointTwo.getY() - pointOne.getY(), 2));
}

/***********************************************************
Sets the angle of the line
parameters:
returns:
	void
***********************************************************/
void Line::lineAngle()
{
	float phi{ std::atan2(pointTwo.getY() - pointOne.getY(), pointTwo.getX() - pointOne.getX()) };
	angle = std::fmod((90 - double(phi * 180 / 3.14159265f)), 360);
}

/***********************************************************
Calculates the slope, y-intercept, second point (if in point-slope form), length, and angle of the line
parameters:
returns:
	void
***********************************************************/
void Line::calculate()
{
	if (!pointTwo.isEmpty())
	{
		slopeFrom2Point();
		slopeIntercept();
	}
	
	else
	{
		slopeIntercept();
		pointTwo = Point( 0.0f, yIntercept );
	}

	lineLength();
	lineAngle();
}