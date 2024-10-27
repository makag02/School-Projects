
public class UnsharpFilter implements Filter {
	private double[][] kernel = { { -1, -2, -1 }, { -2, 28, -2 }, { -1, -2, -1 } };
	private double scale = 16;

	public void filter(PixelImage pi) {

		pi.applyWeightedAverageFilter(kernel, scale);
	}
}
