/******************************************************************************
* File:		slope_intercept_Guerin.cpp
* Class:	COP 2001 50135
* Author:	Mark Guerin
* Purpose:	Application to convert an equation in either two-point or point-slope form into slope-intercept form and graph the equation
******************************************************************************/

#include <iostream>
#include <iomanip>
#include <math.h>

#include "opengl.h"		// graphics library
#include "point.h"
#include "line.h"

//-----------------------------------------------------------------------------
// Global space - constants, functions, data structures, etc.
//-----------------------------------------------------------------------------

// dimensions and title of graph window
const int WINDOW_WIDTH{ 600 };
const int WINDOW_HEIGHT{ 400 };
const std::string WINDOW_TITLE = "Graph of Line";

enum Mode
{
	TWO_POINT = 1,
	POINT_SLOPE = 2,
	EXIT = 3
};

// List of hex codes for colors
enum Color
{
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

int getProblem();
Line get2Point();
Line getPointSlope();
Point getPoint();
void displayLine(Line* graph);
void display2Pt(Line* graph);
void displayPointSlope(Line* graph);
void displaySlopeIntercept(Line* graph);
void drawLine(Line* graph);


// ----------------------------------
// Main function begins here
// ----------------------------------
int main()
{
	int selection{};
	while (selection != EXIT) {
		selection = getProblem();
		Line newLine = Line();
		if (selection == TWO_POINT) {
			newLine = get2Point();
			newLine.calculate();
			displayLine(&newLine);
			display2Pt(&newLine);
			displayPointSlope(&newLine);
			displaySlopeIntercept(&newLine);
			drawLine(&newLine);
		}
		else if (selection == POINT_SLOPE) {
			newLine = getPointSlope();
			newLine.calculate();
			displayLine(&newLine);
			display2Pt(&newLine);
			displayPointSlope(&newLine);
			displaySlopeIntercept(&newLine);
			drawLine(&newLine);
		}
		std::cout << "\n";
	}
	return 0;	// echo %errorlevel%
}

/***********************************************************
Displays the user menu, then inputs and returns the integer function value of the problem number selected
parameters:
returns:
	int:		a number corresponding to the enumerated type Mode
***********************************************************/
int getProblem()
{
	std::cout << "Select the equation type that you would like to convert to slope-intercept form.\n";
	std::cout << "1) Two-point form\n";
	std::cout << "2) Point-slope form\n";
	std::cout << "3) Exit\n";

	int problemType{};
	std::cin >> problemType;

	return problemType;
}

/***********************************************************
Prompts the user to enter two points that define a line and calls functions to calculate the slope, y-intercept, length, and angle, then returns the line
parameters:
returns:
	Line:		A Line class object
***********************************************************/
Line get2Point()
{
	Line graph = Line();

	std::cout << "First point:\n";
	Point firstPoint = getPoint();
	std::cout << "Second point:\n";
	Point secondPoint = getPoint();

	graph = Line(&firstPoint, &secondPoint);
	
	std::cout << "\n";

	return graph;
}

/***********************************************************
Prompts the user to enter a point and a slope, then calls functions to calculate the y-intercept, second point, length, and angle, then returns the line
parameters:
returns:
	Line:		A Line class object
***********************************************************/
Line getPointSlope()
{
	Line graph = Line();

	Point firstPoint = getPoint();

	std::cout << "Please enter the slope of the line.\n";
	float newSlope{};
	std::cin >> newSlope;

	graph = Line(&firstPoint, newSlope);
	
	std::cout << "\n";

	return graph;
}

/***********************************************************
Prompts the user to enter the coordinates for a point on their line, then returns that point
parameters:
returns:
	Point:		A Point class object
***********************************************************/
Point getPoint()
{
	Point plot = Point();

	std::cout << "Please enter the x-coordinate of the point.\n";
	float newX{};
	std::cin >> newX;
	plot.setX(newX);

	std::cout << "Please enter the y-coordinate of the point.\n";
	float newY{};
	std::cin >> newY;
	plot.setY(newY);

	return plot;
}

/***********************************************************
Outputs all the calculated properties of the line to the user
parameters:
	graph:	a Line class object
returns:
	void
***********************************************************/
void displayLine(Line* graph)
{
	std::cout << "Line:\n\n";
	std::cout << std::setprecision(3) << "First Point: (" << graph->getPointOne()->getX() << ", " << graph->getPointOne()->getY() << ")\n";
	std::cout << std::setprecision(3) << "Second Point: (" << graph->getPointTwo()->getX() << ", " << graph->getPointTwo()->getY() << ")\n";
	std::cout << std::setprecision(3) << "Slope: " << graph->getSlope() << "\n";
	std::cout << std::setprecision(3) << "Y-Intercept: " << graph->getYIntercept() << "\n";
	std::cout << std::setprecision(3) << "Length: " << graph->getLength() << "\n";
	std::cout << std::setprecision(3) << "Angle: " << graph->getAngle() << "\n\n";
}

/***********************************************************
Outputs the two-point form of the slope of the line to the user
parameters:
	graph:	a Line class object
returns:
	void
***********************************************************/
void display2Pt(Line* graph)
{
	std::cout << "Two-Point Form:\n";
	std::cout << std::setprecision(3) << "          " << graph->getPointTwo()->getY() << " - " << graph->getPointOne()->getY() << "\n";
	std::cout << "m = --------------------\n";
	std::cout << std::setprecision(3) << "          " << graph->getPointTwo()->getX() << " - " << graph->getPointOne()->getX() << "\n\n";
}

/***********************************************************
Outputs the point-slope form of the line to the user
parameters:
	graph:	a Line class object
returns:
	void
***********************************************************/
void displayPointSlope(Line* graph)
{
	std::cout << "Point-Slope Form:\n";
	std::cout << std::setprecision(3) << "y - " << graph->getPointOne()->getY() << " = " << graph->getSlope() << "(x - " << graph->getPointOne()->getX() << ")\n\n";
}

/***********************************************************
Outputs the slope-intercept form of the line to the user
parameters:
	graph:	a Line class object
returns:
	void
***********************************************************/
void displaySlopeIntercept(Line* graph)
{
	std::cout << "Slope-Intercept Form:\n";
	std::cout << std::setprecision(3) << "y = " << graph->getSlope() << "x + " << graph->getYIntercept() << "\n\n";
}

/***********************************************************
Draws a graph of the line in a pop-up window
parameters:
	graph:	a Line class object
returns:
	void
***********************************************************/
void drawLine(Line* graph)
{
	OpenGL window = OpenGL(WINDOW_WIDTH, WINDOW_HEIGHT, WINDOW_TITLE.c_str());

	int background{ White };

	float xAxisPosition[2] = { 0, WINDOW_HEIGHT / 2 };
	float xAxisSize[2] = { WINDOW_WIDTH, 1 };
	int xAxisColor{ Black };

	float yAxisPosition[2] = { WINDOW_WIDTH / 2, 0 };
	float yAxisSize[2] = { 1, WINDOW_HEIGHT };
	int yAxisColor{ Black };

	graph->getPointOne()->setX(graph->getPointOne()->getX() + ( WINDOW_WIDTH / 2 ));
	graph->getPointOne()->setY(WINDOW_HEIGHT / 2 - graph->getPointOne()->getY());
	float linePosition[2] = { graph->getPointOne()->getX(), graph->getPointOne()->getY() };
	float lineSize[2] = { 2, graph->getLength() };
	float lineRotation = graph->getAngle() - 180;
	int lineColor{ Red };

	while (!window.isClosing()) {
		window.clearWindow(reinterpret_cast<const unsigned char*>(&background));
		window.DrawSprite(xAxisPosition, xAxisSize, reinterpret_cast<unsigned char*>(&xAxisColor));
		window.DrawSprite(yAxisPosition, yAxisSize, reinterpret_cast<unsigned char*>(&yAxisColor));
		window.DrawSprite(linePosition, lineSize, reinterpret_cast<unsigned char*>(&lineColor), lineRotation, false);
		window.paintWindow();
		window.pollEvents();
	}
}