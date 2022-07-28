#define _CRT_SECURE_NO_WARNINGS

#include "ppm.h"
#include <iostream>
#include <fstream>
#include <string>



using namespace std;

string line, format, strWidth, strHeight, maxVal, tempStr;
int tempInt;

namespace imaging {

	float * ReadPPM(const char * filename, int * w, int * h) {

		std::ifstream fileIn;
		fileIn.open(filename, std::ios::in | ios::binary);

		if (!fileIn.is_open()) {

			cerr << "Error Opening File " << endl;
			return nullptr;

		}
		else {

			fileIn >> format >> strWidth >> strHeight >> maxVal;

			if (format != "P6") {

				cerr << "Error! Invalid format." << endl;
				return nullptr;
			}

			if (strWidth.empty()) {

				cerr << "Error! Width missing." << endl;
				return nullptr;
			}

			if (strHeight.empty()) {

				cerr << "Error! Height missing." << endl;
				return nullptr;
			}

			if (maxVal.empty()) {

				cerr << "Error! Max Channel value missing." << endl;
				return nullptr;
			}
			else {

				if (stoi(maxVal) > 255) {

					cerr << "Error! Max value exceeds 255." << endl;
					return nullptr;
				}
			}

			int width = stoi(strWidth);
			int height = stoi(strHeight);

			*w = width;
			*h = height;

			float * buffer = new float[(*w) * (*h) * 3];
			char  * charBuffer = new char[(*w) * (*h) * 3];

			tempInt = fileIn.tellg();
			tempInt += 2;
			fileIn.seekg(tempInt);

			fileIn.read(charBuffer, width * height * 3);

			for (int i = 0; i < (*w) * (*h) * 3; i++) {

				buffer[i] = charBuffer[i];
				buffer[i] /= 255;
			}
			
			return buffer;
		}
	}

	bool WritePPM(const float * data, int w, int h, const char * filename) {

		std::ofstream fileOu(filename, std::ios::out | ios::binary);
		
		fileOu << "P6"<< "\r\n" << w << " " << h << "\r\n" << maxVal << "\r\n";

		char * charBuff = new char[w * h * 3];
		for (int i = 0; i < w * h * 3; i++) {
			
			charBuff[i] = data[i];
			
		}
		
		fileOu.write(charBuff, w * h * 3);
		fileOu.clear();
		fileOu.close();
		
		return true;
	}
}
