package uk.ac.soton.ecs.jb12g17.hybridimages;

import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.colour.RGBColour;
import org.openimaj.image.processing.convolution.Gaussian2D;

public class MyHybridImages {
    /**
     * Compute a hybrid image combining low-pass and high-pass filtered images
     *
     * @param lowImage
     *            the image to which apply the low pass filter
     * @param lowSigma
     *            the standard deviation of the low-pass filter
     * @param highImage
     *            the image to which apply the high pass filter
     *      * @param highSigma
     *            the standard deviation of the low-pass component of computing the
     *            high-pass filtered image
     * @return the computed hybrid image
     */
    public static MBFImage makeHybrid(MBFImage lowImage, float lowSigma, MBFImage highImage, float highSigma) {
        //implement your hybrid images functionality here.
        //Your submitted code must contain this method, but you can add
        //additional static methods or implement the functionality through
        //instance methods on the `MyHybridImages` class of which you can create
        //an instance of here if you so wish.
        //Note that the input images are expected to have the same size, and the output
        //image will also have the same height & width as the inputs.

        int sizeLowPass = (int) (4.0f * lowSigma + 1.0);
        if (sizeLowPass % 2 == 0) sizeLowPass++;
        float[][] templateLowPass = Gaussian2D.createKernelImage(sizeLowPass,lowSigma).pixels;
        MyConvolution convolutionLowPass = new MyConvolution(templateLowPass);


        int sizeHighPass = (int) (4.0f * highSigma +  1.0f);
        if (sizeHighPass % 2 == 0) sizeHighPass++;
        float[][] templateHighPass = Gaussian2D.createKernelImage(sizeHighPass,lowSigma).pixels;
        MyConvolution convolutionHighPass = new MyConvolution(templateHighPass);


        MBFImage low_pass = lowImage.padding(templateLowPass[0].length,templateLowPass.length,RGBColour.BLACK).process(convolutionLowPass);
        MBFImage high_pass = highImage.padding(templateHighPass[0].length,templateHighPass.length,RGBColour.BLACK).subtract(highImage.padding(templateHighPass[0].length,templateHighPass.length,RGBColour.BLACK).process(convolutionHighPass));

        low_pass = low_pass.padding(-templateLowPass[0].length,-templateLowPass.length);
        high_pass = high_pass.padding(-templateHighPass[0].length,-templateHighPass.length);

        DisplayUtilities.display(low_pass);
        DisplayUtilities.display(high_pass.add((float) 0.5));


        return low_pass.add(high_pass);
    }
}
