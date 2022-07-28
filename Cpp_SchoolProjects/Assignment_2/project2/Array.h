#pragma once
#include <iostream>
#include <string>
#include <vector>


using namespace std;


namespace math
{
	template <typename T> class Array
	{
	public:

	protected:
		vector <T> buffer;                              //! Holds the Array data.

		unsigned int width, 						 //! The width of the Array (in pixels)
			height;		                 //! The height of the Array (in pixels)

	public:
		// metric accessors

		/*! Returns the width of the Array
		 */
		const unsigned int getWidth() const { return width; }

		/*! Returns the height of the Array
		 */
		const unsigned int getHeight() const { return height; }


		Array() : width(ZERO), height(ZERO), buffer() {};

		Array(unsigned int width, unsigned int height)
		{
			this->width = width;
			this->height = height;
		};

		Array(unsigned int width, unsigned int height, const T * data_ptr)
		{
			this->width = width;
			this->height = height;

			T * new_data = new T[width * height];
			for (int i = 0; i < (width * height); i++) {
				new_data[i] = data_ptr[i];
				buffer = new_data;
			}

		};

		Array(const Array &src)
		{
			this->width = src.width;
			this->height = src.height;
			this->buffer.clear();
			this->buffer = src.buffer;
		};

		~Array()
		{

		};


		void setPoint(unsigned int x, unsigned int y, T & value)
		{
			size_t index = (y * width) + x;

			if (buffer.size() < index)
			{
				cout << "Your x or y it is out of bounds." << endl;
				return;
			}

			buffer[index] = value;

		};

		void setData(const T * & data_ptr)
		{
			buffer = const_cast <T*>  (data_ptr);
		};

		T getPoint(unsigned int x, unsigned int y) const
		{
			size_t index = (y * this->width) + x;

			if (buffer.size() < index)
			{
				return 0;
			}

			return buffer[index];

		};

		T * getRawDataPtr() {

			T* temp = new T[width*height];

			for (int i = 0; i < width*height;i++) {
				temp[i] = buffer[i];
			}
			
			return temp;
		};

		Array & operator = (const Array & right)
		{
			Array arr(right.width, right.height);

			T * temp = new T[width * height];

			for (int i = 0; i < (right.width*right.height);i++) {
				temp[i] = right.buffer[i];
				this->buffer[i] = temp[i];
			}

			return arr;
		};

		Array & operator () (const unsigned int j, const unsigned int i)
		{
			Array arr = getPoint(j, i);

			return arr;
		};

	};

}
