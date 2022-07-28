#include <iostream>
#include <string>
#include "Image.h"
#include "ppm.h"
#include "Color.h"
#include <cstdlib>


//string filename;
using namespace std;
using namespace imaging;


int main(int argc,char* argv[]) {
	
	int width, height;
	int * w = &width;
	int * h = &height;
	int j = 0;
	const Color c(1.0, 1.0, 1.0);
	string ans;
	const char * path;

	float * imgBuffer;

	if (argc > 2) {

		path = argv[1];
		imgBuffer = ReadPPM(path, w, h);

	}
	else {

		cout << "Please insert a file in order to continue:  ";
		getline(cin, ans);
		path = ans.c_str();
		imgBuffer = ReadPPM(path, w, h);

	}
	
	
	//const char* path = ans.c_str();
	//argv[0] = path;
	

	//Use ReadPPM
	imgBuffer = ReadPPM(path, w, h);


	Color * colorArray = new Color[(width * height)];
	Color * NegBuf = new Color[(width * height)];

	
	//Convert in Color
	for (int i = 0; i <= width * height * 3;i += 3)
	{
		Color cTmp(imgBuffer[i] , imgBuffer[i+1], imgBuffer[i+2]);
		colorArray[j] = cTmp;
		j++;
	}

	//Create an image with the given format
	Image img(width, height, colorArray);

	//Negative of the image
	for (int i = 0; i <= width*height; i++)
	{
		colorArray[i] = c - colorArray[i];
	}

	//Create an image with the negative format
	Image imgNeg(width, height, colorArray);

	//Convert again in float
	float* fBuffer = new float[(width)*(height) * 3];
	int p = 0;

	for (int i = 0; i <= (width)*(height)*3;i = i + 3)
	{
		fBuffer[i] = imgNeg.getRawDataPtr()[p].r *255;
		fBuffer[i + 1] = imgNeg.getRawDataPtr()[p].g *255;
		fBuffer[i + 2] = imgNeg.getRawDataPtr()[p].b *255;
		p++;
	}

	WritePPM(fBuffer, width, height, path);
	
	cout << "Image's bounds are: " << img.getWidth() << " X " << img.getHeight() << endl;


	system("pause");
	return 0;
}