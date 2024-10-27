
public class NegativeFilter implements Filter {

	public void filter(PixelImage pi) {
		Pixel[][] data = pi.getData();

		// iterate over all pixels in the image
		for (int row = 0; row < pi.getHeight(); row++) {
			for (int col = 0; col < pi.getWidth(); col++) {
				// get the current pixel
				Pixel pixel = data[row][col];

				// invert the colors
				int red = 255 - pixel.red;
				int green = 255 - pixel.green;
				int blue = 255 - pixel.blue;

				// create a new pixel with the inverted colors
				Pixel newPixel = new Pixel(red, green, blue);

				// update the image with the new pixel
				data[row][col] = newPixel;
			}
		}

		// update the image with the modified pixel data
		pi.setData(data);
	}
}
