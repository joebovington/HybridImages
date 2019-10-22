package uk.ac.soton.ecs.jb12g17.hybridimages;

import org.openimaj.data.dataset.VFSListDataset;
import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;

import java.io.IOException;

/**
 * OpenIMAJ Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        //Create an image
        VFSListDataset<MBFImage> images =
                new VFSListDataset<>("zip:http://comp3204.ecs.soton.ac.uk/cw/hybrid-images.zip", ImageUtilities.MBFIMAGE_READER);
        MyHybridImages hybridImages = new MyHybridImages();
        DisplayUtilities.display(hybridImages.makeHybrid(images.getInstance(6), (float) 5, images.getInstance(3), (float) 5));
    }
}
