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

//-----------------------------------------------------------------------------
// Global space - constants, functions, data structures, etc.
//-----------------------------------------------------------------------------

// dimensions and title of graph window
const int windowWidth{ 600 };
const int windowHeight{ 400 };
const std::string windowTitle = "Graph of Line";

const float PI{ 3.14159265f };

enum Mode {
	TWO_POINT = 1,
	POINT_SLOPE = 2,
	EXIT = 3
};

// List of hex codes for colors
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

struct Point {
	float x;
	float y;
};

struct Line {
	Point pointOne;
	Point pointTwo;
	float slope;
	float yIntercept;
	float length;
	float angle;
};

int getProblem();
Line get2Point();
Line getPointSlope();
Point getPoint();
float slopeFrom2Point(Line graph);
float slopeIntercept(Line graph);
float lineLength(Line graph);
float lineAngle(Line graph);
void displayLine(Line graph);
void display2Pt(Line graph);
void displayPointSlope(Line graph);
void displaySlopeIntercept(Line graph);
void drawLine(Line graph);


// ----------------------------------
// Main function begins here
// ----------------------------------
int main() {
	int selection{};
	while (selection != EXIT) {
		selection = getProblem();
		if (selection == TWO_POINT) {
			Line newLine = get2Point();
			displayLine(newLine);
			display2Pt(newLine);
			displayPointSlope(newLine);
			displaySlopeIntercept(newLine);
			drawLine(newLine);
		}
		else if (selection == POINT_SLOPE) {
			Line newLine = getPointSlope();
			displayLine(newLine);
			display2Pt(newLine);
			displayPointSlope(newLine);
			displaySlopeIntercept(newLine);
			drawLine(newLine);
		}
		std::cout << "\n";
	}
	return 0;	// echo %errorlevel%
}


// Displays the user menu, then inputs and returns the integer function value of the problem number selected
int getProblem() {
	std::cout << "Select the equation type that you would like to convert to slope-intercept form.\n";
	std::cout << "1) Two-point form\n";
	std::cout << "2) Point-slope form\n";
	std::cout << "3) Exit\n";

	int problemType{};
	std::cin >> problemType;

	return problemType;
}

// Prompts the user to enter two points that define a line and calls functions to calculate the slope, y-intercept, length, and angle, then returns the line
Line get2Point() {
	Line graph{};

	std::cout << "First point:\n";
	graph.pointOne = getPoint();
	std::cout << "Second point:\n";
	graph.pointTwo = getPoint();

	graph.slope = slopeFrom2Point(graph);
	graph.yIntercept = slopeIntercept(graph);
	graph.length = lineLength(graph);
	graph.angle = lineAngle(graph);
	
	std::cout << "\n";

	return graph;
}

// Prompts the user to enter a point and a slope, then calls functions to calculate the y-intercept, second point, length, and angle, then returns the line
Line getPointSlope() {
	Line graph{};

	graph.pointOne = getPoint();

	std::cout << "Please enter the slope of the line.\n";
	std::cin >> graph.slope;

	graph.yIntercept = slopeIntercept(graph);
	graph.pointTwo = { 0, graph.yIntercept };
	graph.length = lineLength(graph);
	graph.angle = lineAngle(graph);
	

	std::cout << "\n";

	return graph;
}

// Prompts the user to enter the coordinates for a point on their line, then returns that point
Point getPoint() {
	Point plot{};

	std::cout << "Please enter the x-coordinate of the point.\n";
	std::cin >> plot.x;

	std::cout << "Please enter the y-coordinate of the point.\n";
	std::cin >> plot.y;

	return plot;
}

// Calculates the slope of the line from a two-point user input and returns that value
float slopeFrom2Point(Line graph) {
	return (graph.pointTwo.y - graph.pointOne.y) / (graph.pointTwo.x - graph.pointOne.x);
}

// Calculates the y-intercept of the line and returns that value
float slopeIntercept(Line graph) {
	return graph.pointOne.y - (graph.slope * graph.pointOne.x);
}

// Calculates the distance between the two points and returns that value
float lineLength(Line graph) {
	return std::sqrt(std::pow(graph.pointTwo.x - graph.pointOne.x, 2) + std::pow(graph.pointTwo.y - graph.pointOne.y, 2));
}

// Calculates the angle of rotation of the line with respect to the top of the y-axis and returns that value
float lineAngle(Line graph) {
	float phi{ std::atan2(graph.pointTwo.y - graph.pointOne.y, graph.pointTwo.x - graph.pointOne.x) };
	return std::fmod((90 - double(phi * 180 / PI)), 360);
}

// Outputs all the calculates properties of the line to the user
void displayLine(Line graph) {
	std::cout << "Line:\n\n";
	std::cout << std::setprecision(3) << "First Point: (" << graph.pointOne.x << ", " << graph.pointOne.y << ")\n";
	std::cout << std::setprecision(3) << "Second Point: (" << graph.pointTwo.x << ", " << graph.pointTwo.y << ")\n";
	std::cout << std::setprecision(3) << "Slope: " << graph.slope << "\n";
	std::cout << std::setprecision(3) << "Y-Intercept: " << graph.yIntercept << "\n";
	std::cout << std::setprecision(3) << "Length: " << graph.length << "\n";
	std::cout << std::setprecision(3) << "Angle: " << graph.angle << "\n\n";
}

// Outputs the two-point form of the slope of the line to the user
void display2Pt(Line graph) {
	std::cout << "Two-Point Form:\n";
	std::cout << std::setprecision(3) << "          " << graph.pointTwo.y << " - " << graph.pointOne.y << "\n";
	std::cout << "m = --------------------\n";
	std::cout << std::setprecision(3) << "          " << graph.pointTwo.x << " - " << graph.pointOne.x << "\n\n";
}

// Outputs the point-slope form of the line to the user
void displayPointSlope(Line graph) {
	std::cout << "Point-Slope Form:\n";
	std::cout << std::setprecision(3) << "y - " << graph.pointOne.y << " = " << graph.slope << "(x - " << graph.pointOne.x << ")\n\n";
}

// Outputs the slope-intercept form of the line to the user
void displaySlopeIntercept(Line graph) {
	std::cout << "Slope-Intercept Form:\n";
	std::cout << std::setprecision(3) << "y = " << graph.slope << "x + " << graph.yIntercept << "\n\n";
}

// Draws a graph of the line in a pop-up window
void drawLine(Line graph) {
	OpenGL window = OpenGL(windowWidth, windowHeight, windowTitle.c_str());

	int background{ White };

	float xAxisPosition[2] = { 0, windowHeight / 2 };
	float xAxisSize[2] = { windowWidth, 1 };
	int xAxisColor{ Black };

	float yAxisPosition[2] = { windowWidth / 2, 0 };
	float yAxisSize[2] = { 1, windowHeight };
	int yAxisColor{ Black };

	graph.pointOne.x += windowWidth / 2;
	graph.pointOne.y = windowHeight / 2 - graph.pointOne.y;
	float linePosition[2] = { graph.pointOne.x, graph.pointOne.y };
	float lineSize[2] = { 2, graph.length };
	float lineRotation = graph.angle - 180;
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