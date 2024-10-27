
/**
 * emboss filter which gives the illusion of depth by emphasizing highlights and
 * shadows by if you increase scale over 1 the returned image is darker if you
 * decrease the scale to between 0 and 1, it will return a brighter image due to
 * the pixel values
 * 
 * @author makag
 *
 */
public class EmbossFilter implements Filter {

	private double[][] kernel = { { -2, -1, 0 }, { -1, 1, 1 }, { 0, 1, 2 } };
	private double scale = 4;

	public void filter(PixelImage pi) {

		pi.applyWeightedAverageFilter(kernel, scale);
	}

}
