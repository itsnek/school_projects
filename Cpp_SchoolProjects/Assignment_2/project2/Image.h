#pragma once
#ifndef _IMAGE
#define _IMAGE
#define ZERO 0.0f 

#include "Array.h"
#include "Vec3.h"
#include <string>

using namespace math;

typedef math::Vec3<float> Color;


class Image : public Array<Color>
{
	public:
		                                             
	protected:

		Color* buffer;                              //! Holds the image data.

		unsigned int width, 						 //! The width of the image (in pixels)
					 height;		                 //! The height of the image (in pixels)

	public:
		Image() : Array() {}//width(ZERO), height(ZERO), buffer(nullptr) {};

		Image(unsigned int width, unsigned int height) {
			this->width = width;
			this->height = height;
		};

		Image(unsigned int width, unsigned int height, const Color * data_ptr) {
			this->width = width;
			this->height = height;

			Color * new_data = new Color[width * height];
			for (int i = 0; i < (width * height); i++) {
				new_data[i] = data_ptr[i];
				buffer = new_data;
			}
		};

		Image(const Image &src) {
			this->width = src.width;
			this->height = src.height;
			this->buffer = src.buffer;
		};

		~Image() {};

		/*!
		 * Loads the image data from the specified file, if the extension of the filename matches the format string.
		 *
		 * Only the "ppm" extension is supported for now. The extension comparison should be case-insensitive. If the 
		 * Image object is initialized, its contents are wiped out before initializing it to the width, height and data
		 * read from the file.
		 * 
		 * \param filename is the string of the file to read the image data from.
		 * \param format specifies the file format according to which the image data should be decoded from the file.
		 * Only the "ppm" format is a valid format string for now.
		 * 
		 * \return true if the loading completes successfully, false otherwise.
		 */
		bool load(const std::string & filename, const std::string & format);

		/*!
		* Stores the image data to the specified file, if the extension of the filename matches the format string.
		*
		* Only the "ppm" extension is supported for now. The extension comparison should be case-insensitive. If the
		* Image object is not initialized, the method should return immediately with a false return value.
		*
		* \param filename is the string of the file to write the image data to.
		* \param format specifies the file format according to which the image data should be encoded to the file.
		* Only the "ppm" format is a valid format string for now.
		*
		* \return true if the save operation completes successfully, false otherwise.
		*/
		bool save(const std::string & filename, const std::string & format);

};


#endif