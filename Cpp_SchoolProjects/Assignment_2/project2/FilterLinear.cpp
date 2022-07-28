#include "FilterLinear.h"


using namespace math;
using namespace std;



FilterLinear::FilterLinear() {

}

FilterLinear::FilterLinear(Color a, Color c) {
	this->a = a;
	this->c = c;
}

void FilterLinear::setA(Color a) {
	this->a = a;
}

Color FilterLinear::getA() {
	return a;
}

void FilterLinear::setC(Color c) {
	this->c = c;
}

Color FilterLinear::getC() {
	return c;
}

Image FilterLinear::operator << (const Image & image)
{
	a = a.clampToLowerBound(0);
	a = a.clampToUpperBound(1);
	c = c.clampToLowerBound(0);
	c = c.clampToUpperBound(1);

	Image img(image);
	Color * temp = img.getRawDataPtr();
	Color* nc = new Color[img.getWidth()*img.getHeight()];

	for(int i = 0; i < img.getWidth()*img.getHeight()*3;i++)
	{
		nc[i] = (a *  temp[i]) + c;
	}
				
	Image newImage (img.getWidth(), img.getHeight(), nc);
	delete[]temp;
	delete[]nc;
	return newImage;

}