#include "Filter.h"

using namespace math;

class FilterLinear :public Filter {

	protected:

		Color a;
		Color c;

	public:

		FilterLinear();

		FilterLinear(Color a, Color c);

		void setA(Color a);

		Color getA();

		void setC(Color c);
		
		Color getC();

		Image  operator << (const Image & image);

};

