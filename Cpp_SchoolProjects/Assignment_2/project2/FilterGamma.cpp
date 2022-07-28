//#define CLAMP

#include "FilterGamma.h"


using namespace math;
using namespace std;


FilterGamma::FilterGamma() {

}

FilterGamma::FilterGamma(float g) {
	this->g = g;
}

void FilterGamma::setG(float g) {
	this->g = g;
}

float FilterGamma::getG() {
	return g;
}

Image FilterGamma::operator << (const Image & image)
{
	//g = clamp(g, 0.5, 2);
	
	Image img(image);

	Color * temp = img.getRawDataPtr();
	Color * nc = new Color[img.getWidth()*img.getHeight()];

	for (int i = 0; i < img.getWidth()*img.getHeight() * 3; i++) 
	{

		nc[i].r = pow((temp[i]).r, g);
		nc[i].g = pow((temp[i]).g, g);
		nc[i].b = pow((temp[i]).b, g);
		nc[i] = nc[i].clampToLowerBound(0.5);
		nc[i] = nc[i].clampToUpperBound(2);
	}

	Image newImage(img.getWidth(), img.getHeight(), nc);
	delete[]temp;
	delete[]nc;
	return newImage;

}

#ifdef CLAMP

const double FilterGamma::clamp(const double& val, const double& lo, const double& hi)
{
	double clamped_value = val;

	if (clamped_value < lo) {
		clamped_value = lo;
		return clamped_value;
	}
	else if (clamped_value > hi) {
		clamped_value = hi;
		return clamped_value;
	}
}

#endif
