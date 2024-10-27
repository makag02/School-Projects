
public class LaplacianFilter implements Filter {
	// weight for a laplacian filter
	private double[][] kernel = { { -1, -1, -1 }, { -1, 8, -1 }, { -1, -1, -1 } };
	private double scale = 1;

	public void filter(PixelImage pi) {
		// kernalSum of 1 because scaling is not need

		pi.applyWeightedAverageFilter(kernel, scale);
	}
}
