/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.sch.elib.util;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author rizky.aditya
 */
public class ImagePanel extends JPanel {

//    private BufferedImage image;
//    public ImagePanel() {
//        try {
//           //try this if didn't success
//            //C:/Users/rizky.aditya/Documents/Another Project/Perpustakaan Nita (on progress)/Image/background.jpg
//            File file = new File("C:/Users/rizky.aditya/Documents/Another Project/Perpustakaan Nita (on progress)/Image/background.jpg");
//            if (file.exists()) {
//                image = ImageIO.read(file);
//            }else{
//                System.out.println("ImagePanel_ERROR: File didn't exist");
//            }
//        } catch (IOException ex) {
//            System.out.println("ImagePanel_ERROR: Cannot find the specified path. " + ex.getMessage());
//        }
//    }
//
    private Image image;

    public ImagePanel(String img) {
        this(new ImageIcon(img).getImage());
    }

    public ImagePanel(Image img) {
        this.image = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    public ImagePanel() {
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
    }
}
