#include <iostream>
#include <string>
#include "Color.h"
#include "Image.h"
#include "ppm.h"

using namespace std;
using namespace imaging;

//Color * new_data;


#define ZERO 0.0f 

namespace imaging {

	Image::Image() : width(ZERO), height(ZERO), buffer(nullptr) {}

	Image::Image(unsigned int width, unsigned int height)
	{
		this->width = width;
		this->height = height;
	}

	Image::Image(unsigned int width, unsigned int height, const Color * data_ptr)
	{
		this->width = width;
		this->height = height;

		Color * new_data = new Color[width * height];
		for (int i = 0; i < (width * height); i++) {
			new_data[i] = data_ptr[i];
			buffer = new_data;
		}

	}

	Image::Image(const Image &src)
	{
		this->width = src.width;
		this->height = src.height;
		this->buffer = src.buffer;
	}

	Image::~Image()
	{

	}


	void Image::setPixel(unsigned int x, unsigned int y, Color & value)
	{
		bool flag = false;
		int i = 0;
		int j = 0;

		while((i <= width) && (!flag)){
			cout << "mphka";
			while((j <= height) && (!flag)) {
				cout << "mphka2";
				if (i == x && j == y) {
					cout << "mphka3";
					buffer[x * y] = value;
					flag = true;

				}
				j++;
			}
			i++;
		}
		if (!flag){
			cout << "Out Of Image's Bounds" << endl;
			flag = true;
		}
	}

	void Image::setData(const Color * & data_ptr)
	{
		buffer = const_cast <Color*>  (data_ptr);
	}

	Color Image::getPixel(unsigned int x, unsigned int y) const
	{
		bool flag = false;
		int i = 0;
		int j = 0;
		while((i <= width) && (!flag)) {
			while ((j <= height) && (!flag)) {

				if ((i == x) && (j == y)) {

					return(buffer[x * y]);
					flag = true;

				}
				j++;
			}
			i++;
		}
		return Color();
	}

	Color * Image::getRawDataPtr() {
		return buffer;
	}

	Image & Image::operator = (const Image & right) 
	{
		Image img(right.width,right.height,right.buffer);

		return img;
	}

	bool Image :: load(const std::string & filename, const std::string & format)
	{
		if (filename.find(format) != std::string::npos) {

			int lWidth, lHeight;
			int * w = &lWidth, *h = &lHeight;
			int j = 0;

			float * imgBuffer;
			Color* colorArray = NULL;
			const  char* cha = filename.c_str();
			imgBuffer = ReadPPM(cha, w, h);

			for (int i = 0; i <= lWidth * lHeight * 3; i = i + 3)
			{
				Color c(imgBuffer[i], imgBuffer[i + 1], imgBuffer[i + 2]);
				colorArray[j] = c;
				j++;
			}

			this->width = lWidth;
			this->height = lHeight;
			this->buffer = colorArray;

			return true;
		}

		else return false;

	}

	bool Image::save(const std::string & filename, const std::string & format) 
	{
		const  char* cha = filename.c_str();
		float* fBuffer = new float[(width)*(height) * 3];
		int j = 0;
	
		for (int i = 0; i <= (width)*(height); i = i + 3)
		{
			fBuffer[i] = this->buffer[j].r * 255;
			fBuffer[i + 1] = this->buffer[j].g * 255;
			fBuffer[i + 2] = this->buffer[j].b * 255;
			j++;
		}
		return WritePPM(fBuffer,this->width,this->height,cha);
		
	}

}
