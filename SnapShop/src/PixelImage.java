import java.awt.image.*;

/**
 * Provides an interface to a picture as an array of Pixels
 */
public class PixelImage {
	private BufferedImage myImage;
	private int width;
	private int height;

	/**
	 * Map this PixelImage to a real image
	 * 
	 * @param bi The image
	 */
	public PixelImage(BufferedImage bi) {
		// initialise instance variables
		this.myImage = bi;
		this.width = bi.getWidth();
		this.height = bi.getHeight();
	}

	/**
	 * Return the width of the image
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Return the height of the image
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Return the BufferedImage of this PixelImage
	 */
	public BufferedImage getImage() {
		return this.myImage;
	}

	/**
	 * Return the image's pixel data as an array of Pixels. The first coordinate is
	 * the x-coordinate, so the size of the array is [width][height], where width
	 * and height are the dimensions of the array
	 * 
	 * @return The array of pixels
	 */
	public Pixel[][] getData() {
		Raster r = this.myImage.getRaster();
		Pixel[][] data = new Pixel[r.getHeight()][r.getWidth()];
		int[] samples = new int[3];

		for (int row = 0; row < r.getHeight(); row++) {
			for (int col = 0; col < r.getWidth(); col++) {
				samples = r.getPixel(col, row, samples);
				Pixel newPixel = new Pixel(samples[0], samples[1], samples[2]);
				data[row][col] = newPixel;
			}
		}

		return data;
	}

	/**
	 * Set the image's pixel data from an array. This array matches that returned by
	 * getData(). It is an error to pass in an array that does not match the image's
	 * dimensions or that has pixels with invalid values (not 0-255)
	 * 
	 * @param data The array to pull from
	 */
	public void setData(Pixel[][] data) {
		int[] pixelValues = new int[3]; // a temporary array to hold r,g,b
										// values
		WritableRaster wr = this.myImage.getRaster();

		if (data.length != wr.getHeight()) {
			throw new IllegalArgumentException("Array size does not match");
		} else if (data[0].length != wr.getWidth()) {
			throw new IllegalArgumentException("Array size does not match");
		}

		for (int row = 0; row < wr.getHeight(); row++) {
			for (int col = 0; col < wr.getWidth(); col++) {
				pixelValues[0] = data[row][col].red;
				pixelValues[1] = data[row][col].green;
				pixelValues[2] = data[row][col].blue;
				wr.setPixel(col, row, pixelValues);
			}
		}
	}

	/**
	 * This method uses a kernel value to apply a weighted average filter to the
	 * image. The kernel array contains the weights or transformations to be used
	 * for each pixel, and scale is used to keep the number range within 0 - 255
	 * kernelRadius is used to calculate the range of the kernel to be applied at
	 * each pixel.
	 * 
	 * The method loops through each pixel in the image and calculates the weighted
	 * sum of its neighboring pixels based on the kernel. The resulting sums are
	 * divided by the scale and rounded to integers to produce the new pixel values.
	 * 
	 * The new pixel data is set using the setData method.
	 * 
	 * @param kernel is the 3x3 (or more) weighted transformation
	 * @scale is used to keep rgb numbers within range of 255 so our filter just
	 *        doesn't return a black image.
	 */
	public void applyWeightedAverageFilter(double[][] kernel, double scale) {
		Pixel[][] data = getData();
		Pixel[][] newData = new Pixel[height][width];
		// radius of our kernel is
		int kernelRadius = kernel.length / 2;
		// loop through pixel
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				// initialize sums for our color values
				double redSum = 0.0;
				double greenSum = 0.0;
				double blueSum = 0.0;
				// loop through kernel
				for (int kRow = -kernelRadius; kRow <= kernelRadius; kRow++) {
					for (int kCol = -kernelRadius; kCol <= kernelRadius; kCol++) {
						// calculate row and col index for current pixel
						int rowIndex = row + kRow;
						int colIndex = col + kCol;
						// check if element is within the boundary of image
						if (rowIndex >= 0 && rowIndex < height && colIndex >= 0 && colIndex < width) {
							// get pixel data for current element
							Pixel pixel = data[rowIndex][colIndex];
							// get the weight
							double weight = kernel[kRow + kernelRadius][kCol + kernelRadius];
							// add weighted values to the sum
							redSum += weight * pixel.red;
							greenSum += weight * pixel.green;
							blueSum += weight * pixel.blue;
						}
					}
				}
				// calculate new color value with scale to try and keep ranges within 255
				// some filters don't need a scale (i.e. scale=1)
				int newRed = (int) Math.round(redSum / scale);
				int newGreen = (int) Math.round(greenSum / scale);
				int newBlue = (int) Math.round(blueSum / scale);

				// Check for out-of-bounds pixel values sets to 0 if newColor is negative or 255
				// if newColor is bigger than 255

				newRed = Math.min(255, Math.max(0, newRed));
				newGreen = Math.min(255, Math.max(0, newGreen));
				newBlue = Math.min(255, Math.max(0, newBlue));
				// create new pixel with new color values
				newData[row][col] = new Pixel(newRed, newGreen, newBlue);
			}
		}
		// update the image
		setData(newData);
	}

}
