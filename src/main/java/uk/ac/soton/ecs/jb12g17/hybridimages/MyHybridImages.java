package uk.ac.soton.ecs.jb12g17.hybridimages;

import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.colour.RGBColour;
import org.openimaj.image.processing.convolution.Gaussian2D;

public class MyHybridImages {
    /**
     * Compute a hybrid image combining low-pass and high-pass filtered images
     *
     * @param lowImage  the image to which apply the low pass filter
     * @param lowSigma  the standard deviation of the low-pass filter
     * @param highImage the image to which apply the high pass filter
     *                  * @param highSigma
     *                  the standard deviation of the low-pass component of computing the
     *                  high-pass filtered image
     * @return the computed hybrid image
     */
    public static MBFImage makeHybrid(MBFImage lowImage, float lowSigma, MBFImage highImage, float highSigma) {

        int sizeLowPass = (int) (4.0f * lowSigma + 1.0);
        if (sizeLowPass % 2 == 0) sizeLowPass++;
        float[][] templateLowPass = Gaussian2D.createKernelImage(sizeLowPass, lowSigma).pixels;
        MyConvolution convolutionLowPass = new MyConvolution(templateLowPass);


        int sizeHighPass = (int) (4.0f * highSigma + 1.0f);
        if (sizeHighPass % 2 == 0) sizeHighPass++;
        float[][] templateHighPass = Gaussian2D.createKernelImage(sizeHighPass, lowSigma).pixels;
        MyConvolution convolutionHighPass = new MyConvolution(templateHighPass);

        // adds a 0 padding equal to the width of the kernel, processes the convolution then removes the padding

        MBFImage low_pass = lowImage.padding(templateLowPass[0].length, templateLowPass.length, RGBColour.BLACK).process(convolutionLowPass);
        MBFImage high_pass = highImage.padding(templateHighPass[0].length, templateHighPass.length, RGBColour.BLACK).subtract(highImage.padding(templateHighPass[0].length, templateHighPass.length, RGBColour.BLACK).process(convolutionHighPass));

        low_pass = low_pass.padding(-templateLowPass[0].length, -templateLowPass.length);
        high_pass = high_pass.padding(-templateHighPass[0].length, -templateHighPass.length);

        //DisplayUtilities.display(low_pass);
        //DisplayUtilities.display(high_pass.add((float) 0.5));


        return low_pass.add(high_pass);
    }
}
