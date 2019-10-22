package uk.ac.soton.ecs.jb12g17.hybridimages;

import org.openimaj.image.FImage;
import org.openimaj.image.processor.SinglebandImageProcessor;

public class MyConvolution implements SinglebandImageProcessor<Float, FImage> {
    private float[][] kernel;

    public MyConvolution(float[][] kernel) {

        this.kernel = kernel;
    }

    @Override
    public void processImage(FImage image) {
        int kernelCol = kernel.length;
        int kernelRow = kernel[0].length;
        int halfCol = kernelCol / 2;
        int halfRow = kernelRow / 2;
        FImage clone = image.newInstance(image.width, image.height);
        for(int y = halfCol; y < image.height - (kernelCol - halfCol); ++y) {
            for(int x = halfRow; x < image.width - (kernelRow - halfRow); ++x) {
                float sum = 0.0F;
                int j = 0;
                for(int j2 = kernelCol - 1; j < kernelCol; --j2) {
                    int i = 0;
                    for(int i2 = kernelRow - 1; i < kernelRow; --i2) {
                        int resX = x + i - halfRow;
                        int resY = y + j - halfCol;
                        sum += image.pixels[resY][resX] * kernel[j2][i2];
                        ++i;
                    }
                    ++j;
                }
                clone.pixels[y][x] = sum;
            }
        }
        image.internalAssign(clone);
    }
}
