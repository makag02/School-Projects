// Write your short report here (-2 if there is no report)
/**
 * For additional filters, I made a motion blur, emboss, and sharpen filters.
 * My motion blur and sharpen filters work.
 * My motion blur filter takes into account pixels in a diagonal direction to create the illusion of motion
 * My sharpen filter works by taking into account the differences in pixels that are around the pixel (not diagonally)
 * it adds the illusion of making the image appear more sharp (or vivid)
 * 
 * my emboss filter doesn't achieve the effect 
 * from the examples. However, it does seem to be add depth to the image by emphasizing the shadows and highlights. 
 * I think I'd have to make a change to my applyWeightedAverageFilter method in order to get the effect where the image is grayed out.
 * 
 * The hardest thing for me was coming up with the method applyWeightedAverageFilter
 * I spent the most time trying to figure it out but after I wrote it out and looked on the 
 * internet to make sense of how the transformations affect the image, I was able to figure it out
 * 
 * I find it interesting how filters are used in image processing. I've never really thought about how they work until now.
 *
 * 
 */
/**
 * A class to configure the SnapShop application
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class SnapShopConfiguration {
	/**
	 * Method to configure the SnapShop. Call methods like addFilter and
	 * setDefaultFilename here.
	 * 
	 * @param theShop A pointer to the application
	 */
	public static void configure(SnapShop theShop) {

		theShop.setDefaultFilename("billg.jpg");
		theShop.addFilter(new FlipHorizontalFilter(), "Flip Horizontal");
		theShop.addFilter(new FlipVerticalFilter(), "Flip Vertical");
		theShop.addFilter(new NegativeFilter(), "Negative Filter");
		theShop.addFilter(new GaussianFilter(), "Gaussian Filter");
		theShop.addFilter(new LaplacianFilter(), "Laplacian Filter");
		theShop.addFilter(new UnsharpFilter(), "Unsharp Filter");
		theShop.addFilter(new EdgyFilter(), "Edgy Filter");

		// add your other filters below
		theShop.addFilter(new MotionBlur(), "Motion Blur");
		theShop.addFilter(new EmbossFilter(), "Emboss");
		theShop.addFilter(new SharpFilter(), "Sharpen");
	}
}
