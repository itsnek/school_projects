#pragma once
#include <iostream>
#include <string>
#include "Image.h"
#include "Array.h"
#include "..\ppm\ppm.h"

using namespace std;
using namespace math;

#define ZERO 0.0f 


bool Image :: load(const std::string & filename, const std::string & format)
{
	if (format.compare("ppm") != 0) {
		return false;
	}
	if (filename.find(format) != std::string::npos) {
		
		int lWidth, lHeight;
		int * w = &lWidth, *h = &lHeight;
		int j = 0;
		float * imgBuffer;
		const  char* cha = filename.c_str();

		imgBuffer = imaging::ReadPPM(cha, w, h);

		this->width = lWidth;
		this->height = lHeight;
		
		Color* colorArray = new Color[(width * height *3)];
		
		
		for (int i = 0; i < width * height * 3; i = i + 3)
		{
			
			Color c(imgBuffer[i], imgBuffer[i + 1], imgBuffer[i + 2]);
			
			colorArray[j] = c;
			j++;
			
		}
		this->buffer = colorArray;
		
		return true;

	}

	else return false;

}

bool Image :: save(const std::string & filename, const std::string & format)
{
	if (format.compare("ppm") != 0) {
		return false;
	}

	int w = this->width;
	int h = this->height;
	const  char* cha = filename.c_str();
	float* fBuffer = new float[(w)*(h)*3];
	int j = 0;


	for (int i = 0; i < (w)*(h)*3; i++)
	{

		fBuffer[(i * 3)] = this->buffer[j].r * 255.0f;
		fBuffer[(i * 3) + 1] = this->buffer[j].g * 255.0f;
		fBuffer[(i * 3) + 2] = this->buffer[j].b * 255.0f;
		j++;
		
	}

	return imaging::WritePPM(fBuffer,this->width,this->height,cha);
		
}

