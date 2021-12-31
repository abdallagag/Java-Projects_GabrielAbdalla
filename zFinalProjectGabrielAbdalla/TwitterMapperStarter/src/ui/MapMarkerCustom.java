package ui;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapMarkerCircle;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class MapMarkerCustom extends MapMarkerCircle {
    public static final int defaultMarkerSize = 20;
    private Color color;
    private Image image;
    private String profileImage;
    private String tweetText;
    private int borderSize = 7;

    public MapMarkerCustom(Layer layer, Coordinate coord, Color color, Image image, String profileImage, String tweetText) {
        super(layer, null, coord, defaultMarkerSize, STYLE.FIXED, getDefaultStyle());
        this.color = color;
        this.image = image;
        this.profileImage = profileImage;
        this.tweetText = tweetText;
    }

    @Override
    public void paint(Graphics g, Point position, int radius) {
        int imageSize = defaultMarkerSize;
        int halfImageSize = imageSize / 2;
        int imageXP = position.x - halfImageSize;
        int imageYP = position.y - halfImageSize;

        int size = imageSize + borderSize * 2;
        int x = imageXP - borderSize;
        int y = imageYP - borderSize;

        g.setClip(new Ellipse2D.Float(x, y, size, size));
        g.setColor(color);
        g.fillOval(x, y, size, size);

        g.setClip(new Ellipse2D.Float(imageXP, imageYP, imageSize, imageSize));
        g.drawImage(image, imageXP, imageYP, imageSize, imageSize, null);
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getTweetText() {
        return tweetText;
    }
}