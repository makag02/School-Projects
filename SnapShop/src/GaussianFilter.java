/**
 * Adds a gaussian filter effect to image
 * @author makag
 * 5x5 gaussian blur from the internet scale factor value 159
 * { 		
 * 			{ 2, 4,  5,  4,  2 },
			{ 4, 9,  12, 9,  4 }, 
			{ 5, 12, 15, 12, 5 }, 
			{ 4, 9,  12, 9,  4 },
			{ 2, 4,  5,  4,  2 } 
			}
			it has close to similar ratios like the 3x3
			3x3 gaussian blur from assignment
			{{1, 2, 1}, {2, 4, 2}, {1, 2, 1}}; scale factor val 16.0
 */

public class GaussianFilter implements Filter{
    //given weight for transformaton
	private double[][] kernel = { 	{1, 2, 1}, {2, 4, 2}, {1, 2, 1}};	
			
    //divide by 16 to scale down to 0
	private double scale = 16.0;

    public void filter(PixelImage pi) {
        pi.applyWeightedAverageFilter(kernel, scale);
    }
	    }
	

