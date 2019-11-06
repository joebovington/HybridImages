package uk.ac.soton.ecs.jb12g17.hybridimages;

import org.openimaj.data.dataset.VFSListDataset;
import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.processing.resize.ResizeProcessor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * OpenIMAJ
 */
public class App {
    public static void main(String[] args) throws IOException {
        //Create an image
        VFSListDataset<MBFImage> images =
                new VFSListDataset<>("zip:http://comp3204.ecs.soton.ac.uk/cw/hybrid-images.zip", ImageUtilities.MBFIMAGE_READER);
        MyHybridImages hybridImages = new MyHybridImages();
        DisplayUtilities.display(hybridImages.makeHybrid(images.getInstance(6), (float) 5, images.getInstance(3), (float) 5));
        /*MBFImage image1 = ImageUtilities.readMBF(new File("C:\\Users\\Joseph\\Pictures\\compvis\\shrek2.png"));
        MBFImage image2 = ImageUtilities.readMBF(new File("C:\\Users\\Joseph\\Pictures\\compvis\\fiona2.png"));
        MBFImage shreona = hybridImages.makeHybrid(image1,(float) 4, image2, (float) 3);
        DisplayUtilities.display(shreona);
        shreona = ResizeProcessor.doubleSize(shreona);
        ArrayList<MBFImage> shreonaList = new ArrayList<MBFImage>();
        for (int i = 0; i < 4; i++) {
            DisplayUtilities.display(shreona,"Shreona");
            shreona = ResizeProcessor.halfSize(shreona);
            shreonaList.add(shreona);
        }
        DisplayUtilities.display("shreona",shreonaList);*/
    }
}
