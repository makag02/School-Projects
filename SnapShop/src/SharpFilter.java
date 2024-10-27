
/**
 * multiplies center pixel but only takes into account pixels that are north
 * south east or west of the pixel to create a sharpening effect.
 * 
 * @author makag
 *
 */

public class SharpFilter implements Filter {
	private double[][] kernel = { { 0, -2, 0 }, { -2, 28, -2 }, { 0, -2, 0 } };
	private double scale = 20;

	public void filter(PixelImage pi) {

		pi.applyWeightedAverageFilter(kernel, scale);
	}
}
