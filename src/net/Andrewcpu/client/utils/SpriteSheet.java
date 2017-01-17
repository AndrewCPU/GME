package net.Andrewcpu.client.utils;

import net.Andrewcpu.client.entities.utils.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created by stein on 1/3/2017.
 */
public class SpriteSheet {
    private HashMap<Direction,BufferedImage[][]> sprites;
    private int rows, columns;
    private BufferedImage main;
    public static BufferedImage load(String s){
       return toBufferedImage(new ImageIcon(SpriteSheet.class.getResource(s)).getImage());
    }
    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
    public SpriteSheet(int x, int y, BufferedImage main){
        sprites = new HashMap<>();
        rows = x;
        columns = y;
        this.main = main;
        setupSprites();
    }
    public void setupSprites(){
        sprites.put(Direction.LEFT, new BufferedImage[rows][columns]);
        sprites.put(Direction.RIGHT, new BufferedImage[rows][columns]);
        int width = main.getWidth() / rows;
        int height = main.getHeight() / columns;
        for(int i = 0; i<rows; i++){
            for(int j = 0; j<columns; j++){
                sprites.get(Direction.LEFT)[i][j] = main.getSubimage(i * width, j * height, width, height);
            }
        }
        for(int i = 0; i<rows; i++){
            for(int j = 0; j<columns; j++){
                BufferedImage image =main.getSubimage(i * width, j * height, width, height);
                AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                tx.translate(-image.getWidth(null), 0);
                op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                image = op.filter(image, null);
                sprites.get(Direction.RIGHT)[i][j] = image;
            }
        }
    }

    public BufferedImage getSprite(Direction d, int x, int y){
        return sprites.get(d)[x][y];
    }


    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public BufferedImage getMain() {
        return main;
    }

    public void setMain(BufferedImage main) {
        this.main = main;
    }

    public HashMap<Direction, BufferedImage[][]> getSprites() {
        return sprites;
    }

    public void setSprites(HashMap<Direction, BufferedImage[][]> sprites) {
        this.sprites = sprites;
    }
}
