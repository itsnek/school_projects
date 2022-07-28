#include "Filter.h"

using namespace math;

class FilterGamma : public Filter {
	protected:

		float g;
	
	public:
		FilterGamma(float g);

		void setG(float g);

		FilterGamma();

		float getG();

		Image  operator << (const Image & image);

		const double clamp(const double& val, const double& lo, const double& hi);
};