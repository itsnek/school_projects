#include <iostream>
#include <string>
#include "FilterLinear.h"
#include "FilterGamma.h"


using namespace std;
using namespace math;

typedef math::Vec3<float> Color;

int main(int argc,const char * argv[]) {

	int width, height;
	int * w = &width;
	int * h = &height;
	int j = 0, i=0;
	double g;
	string filter ;
	string tempString;
	FilterLinear f;
	//Filter f;

	const char * path = nullptr;

	float * imgBuffer;

	vector<string> dataV;
	vector<Filter*> filters;
	int lastElement = argc-1;
	string lastString = argv[lastElement];

	

	if (lastString.find(".ppm") == string::npos)
	{
		printf("Error!Missing image.");
		return 0;
	}
	else {
		path = argv[lastElement];
	}

	while (i < argc)
	{
		dataV.push_back(argv[i]);

		if (dataV[i] == "-f") {
			i++;
			dataV.push_back(argv[i]);

			if (dataV[i] != "-f") {

				if (dataV[i] == "linear") {

					Vec3<float> a(stof(argv[i + 1]), stof(argv[i + 2]), stof(argv[i + 3]));
					Vec3<float> b(stof(argv[i + 4]), stof(argv[i + 5]), stof(argv[i + 6]));
					
					FilterLinear* f = new FilterLinear(a, b);

					filters.push_back(f);
					
					tempString = argv[7 + i];

					if (tempString != "-f") {
						if (tempString == path) {}
						else {
							cout << "Wrong number of arguments!" << endl;
							return 0;
						}
					}

				}
				else if (dataV[i] == "gamma") {

					g = stof(argv[i + 1]);

					FilterGamma* f = new FilterGamma(g);

					filters.push_back(f);

					tempString = argv[2 + i];

					if (tempString != "-f") {
						if (tempString == path) {}
						else{ 
							cout << "Wrong number of arguments!" << endl; 
							return 0;
						}
					}

				}

			}
		}

		i++;
		
	}

	string imgloaded = path;

	if (filters.empty() || imgloaded.empty()) {
		printf("Error!Missing components.");
		return 0;
	}

	Image img;

	if (!img.load(imgloaded, "ppm")) {
		cout << "Something went wrong in the loading of the file." << endl;
		return 0;
	}
	
	for (size_t i = 0; i < filters.size(); i++) {
		img = *(filters[i]) << img;
	}

	string outputFile = "filter_" + imgloaded;
	img.save(outputFile, "ppm");

};