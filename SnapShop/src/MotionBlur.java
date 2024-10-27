
/**
 * got the kernel from the internet it seems like it takes into account
 * https://softwarebydefault.com/tag/motion-blur/ the pixels that are diagonal
 * from our current pixel in order to give the appearance of rapid motion.
 * 
 * @author makag motion blur weighted kernel { 1, 0, 0, 0, 1 }, { 0, 1, 0, 1, 0
 *         }, { 0, 0, 1, 0, 0 }, { 0, 1, 0, 1, 0 }, { 1, 0, 0, 0, 1 } };
 */

public class MotionBlur implements Filter {
	private double[][] kernel = { { 1, 0, 0, 0, 1 }, { 0, 1, 0, 1, 0 }, { 0, 0, 1, 0, 0 }, { 0, 1, 0, 1, 0 },
			{ 1, 0, 0, 0, 1 } };
	double scale = 9;

	public void filter(PixelImage pi) {

		pi.applyWeightedAverageFilter(kernel, scale);
	}
}
