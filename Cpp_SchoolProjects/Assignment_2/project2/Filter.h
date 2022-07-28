#pragma once
#include "Image.h"


class Filter {

	public :

		Filter() {};

		Filter(Filter & filter) {};

		virtual Image operator << (const Image & image) = 0;

};
