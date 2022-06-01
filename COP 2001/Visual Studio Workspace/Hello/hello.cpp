/******************************************************************************
* File:		hello.cpp
* Class:	COP 2001 20205
* Author:	Mark Guerin
* Purpose:	main application file with startup for a hello world demo of
*			C++ code
******************************************************************************/

#include <iostream>

int main()
{

	std::cout << "Hello!" << std::endl;
	std::cout << "Press any key and hit enter to continue..." << std::endl;
	char pause{};
	std::cin >> pause;


	return 0;	// echo %errorlevel%
}