/**
 * ImageCutterCanvas.java
 *
 * Created on 3 avril 2006, 14:03
 *
 */

package editor.ui.component;

import  javax.swing.*;
import  java.awt.*;
import  java.awt.image.*;

/**
 *
 * @author Guillaume BOUCHON (bouchon_guillaume@yahoo.fr)
 */
public class ImageCutterComponent extends  JComponent
{
    /**
     * dimension 
     */
    private Dimension   dim=    new Dimension(400,400);
    
    /**
     * couleur du fond
     */
    private Color   back_color=new  Color(0,0,0);  
    
    /**
     * largeur des images
     */
    private int     tw=32;
    
    /**
     * hauteur des images
     */
    private int     th=32;
    
    /**
     * l'image a afficher
     */
    private  BufferedImage   img;
    
    /**
     * couleur du quadrillage
     */
    private Color   grid_color=new  Color(255,0,0);
    
    /**
     * decalage x
     */
    private int dx;
    
    /**
     * decalage y
     */
    private int dy;
    
    /**
     * Constructeur de  ImageCutterComponent
     */
    public ImageCutterComponent()
    {
        setSize(dim);
        setPreferredSize(dim);
        setMaximumSize(dim);
    }
    
    /**
     * defini l'image a afficher
     @param img l'image a afficher
     */
    public  void    setImage(BufferedImage img)
    {
        this.img=img;
        if  (img!=null)
        {
            dim=new Dimension(img.getWidth(),img.getHeight());
            setSize(dim);
            setPreferredSize(dim);
            setMaximumSize(dim);
            revalidate();
        }
        repaint();
    }
    
    /**
     * defini les decalage x et y
     @param dx decalage sur les x
     @param dy decalage sur les y
     */
    public  void    setDecalage(int dx,int dy)
    {
        this.dx=dx;
        this.dy=dy;
        repaint();
    }
    
    /**
     * incremente le decalage
     @param ix increment sur les x
     @param iy increment sur les y
     */
    public  void    incDecalage(int ix,int iy)
    {
        dx+=ix;
        dy+=iy;
        repaint();
    }
    
    /**
     * defini les dimensions des images
     @param w largeur des images
     @param h hauteur des images
     */
    public  void    setImagesSize(int w,int h)
    {
        tw=w;
        th=h;
        repaint();
    }
    
    /**
     * donne la sous image a la coordonnées specifiée
     @param px position x en pixels
     @param py position y en pixels
     @return l'image decoupee
     */
    public  BufferedImage   subImage(int px,int py)
    {
        if  (img==null) return  null;
        
        //positions
        int x=(px-dx%tw)/tw;
        x=x*tw+dx%tw;
        int y=(py-dy%th)/th;
        y=y*th+dy%th;
        if  (x<0 || y<0 || x+tw>=img.getWidth() || y+th>=img.getHeight()) return  null;
        
        return  img.getSubimage(x,y,tw,th);
    }
    
    /**
     * rafraichissement
     @param g le grapjics où afficher
     */
    public  void    paint(Graphics g)
    {
        //dimensions
        int w=getWidth();
        int h=getHeight();
        
        if  (img!=null)
        {
            w=img.getWidth();
            h=img.getHeight();
        }
       
        //g.setClip(0,0,w,h);
        
        //affiche le fond
        g.setColor(back_color);
        g.fillRect(0,0,w,h);
        
        //l'image
        if  (img!=null)
            g.drawImage(img,0,0,null);
        
        //le quadrillage
        if  (tw!=0 && th!=0)
        {
            g.setColor(grid_color);
            for(int x=dx%tw;x<w;x+=tw)  //verticales
            {
                g.drawLine(x,0,x,h);
            }
            for(int y=dy%th;y<h;y+=th)  //horizontales
            {
                g.drawLine(0,y,w,y);
            }
        }
    }
    
}
