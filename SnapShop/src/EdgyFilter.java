
/**
 * edge detection filter
 * @author makag
 *
 */
public class EdgyFilter implements Filter {
	private double[][] kernel = { { -1, -1, -1 }, { -1, 9, -1 }, { -1, -1, -1 } };
	private double scale = 1;

	public void filter(PixelImage pi) {

		pi.applyWeightedAverageFilter(kernel, scale);
	}
}
