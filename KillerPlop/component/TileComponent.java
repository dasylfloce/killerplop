/**
 * TileComponent.java
 *
 * Created on 24 mars 2006, 10:41
 *
 */

package editor.ui.component;

import game.map.tile.Tile;
import  javax.swing.*;
import  javax.swing.border.*;
import  java.awt.*;
import  java.awt.event.*;
import  java.awt.image.*;

/**
 * Composant qui affiche les tuiles<br>
 * Quand on clique sur la tuile, recuperer l'evenement mouseClicked de MouseEvent : getSource() contient
 * le TileComponent sur lequel on a cliqué
 *
 *
 * @author Guillaume BOUCHON (bouchon_guillaume@yahoo.fr)
 */
public class TileComponent  extends JComponent implements MouseListener
{

    private static  final   String  ID="";
    
    private Tile    tile;   //la tuile associée
    private JLabel  gfx; //affiche la tuile
    private JLabel  id;     //le id
    
    private JPanel  animatedPanel;
    
    private int     c_img;  //image courante de l'animation
    
    private BufferedImage   anim[];   //images de l'anim
    
    private JLabel  txt_cimg;
    
    /**
     * Constructeur de  TileComponent
     */
    public TileComponent()
    {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        
        //panel des animation
        animatedPanel=new   JPanel();
        animatedPanel.setLayout(new BoxLayout(animatedPanel,BoxLayout.X_AXIS));
        animatedPanel.setVisible(false);
        
        JButton but=new JButton("<");
        animatedPanel.add(but);
        but.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { prevImage(); }  });
        
        txt_cimg=new JLabel();
        animatedPanel.add(txt_cimg);
        
        but=new JButton(">");
        but.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { nextImage(); }  });
        animatedPanel.add(but);
        //
        
        gfx= new JLabel();
        add(gfx);
        gfx.addMouseListener(this);

        id=new  JLabel(ID);
        add(id);
        
        add(animatedPanel);
        
        setBorder(new TitledBorder(""));
    }
    
    /**
     * constructeur
     @param tw largeur de la tuile
     @param th hauteur de la tuile
     */
    public TileComponent(int tw,int th)
    {
        this();
        setDim(tw,th);
    }
    
    /**
     * Constructeur de  TileComponent
     @parma t la tuile a afficher
     */
    public TileComponent(Tile t)
    {
        this();
        setTile(t);
    }
    
    /**
     * donne la tuile
     @return la tuile
     */
    public  Tile    getTile()
    {
        return  tile;
    }
    
    /**
     * defini la taille
     @param tw largeur de la tuile
     @param th hauteur de la tuile
     */
    private void    setDim(int tw,int th)
    {
         Dimension d=new Dimension(tw+10,th+30);
        // setMinimumSize(d);
        // setPreferredSize(d);
        // setMaximumSize(d);
    }
    
    /**
     * donne les images
     @return les images de l'animation
     */
    public  BufferedImage[]   getImages()
    {
        return  anim;
    }
    
    /**
     * defini l'image
     @param img l'image a mettre
     */
    public  void    setImage(BufferedImage img)
    {
        if  (img!=null)
        {
            gfx.setIcon(new ImageIcon(img));
            setDim(img.getWidth(),img.getHeight());
            
            if  (anim==null || c_img-1>=anim.length)
                anim=new BufferedImage[1];
            
            anim[c_img-1]=img;
            
        }else   gfx.setIcon(null);
    }
    
    /**
     * affiche le numero de l'image de l'animation en cours
     */
    private void    showCurrentImageIndex()
    {
        txt_cimg.setText(c_img+"/"+tile.getImages().length);
    }
    
    /**
     * affiche l'image courante
     */
    private void    showCurrentAnimImage()
    {
        if  (anim[c_img-1]!=null)
            gfx.setIcon(new ImageIcon(anim[c_img-1]));
        else    gfx.setIcon(null);
    }
    
    /**
     * image precedente dans l'anim
     */
    private void    prevImage()
    {
        if  (c_img>1)   c_img--;
        showCurrentImageIndex();
        showCurrentAnimImage();
    }
    
    /**
     * image suivante dans l'anim
     */
    private void    nextImage()
    {
        if  (c_img<anim.length)   c_img++;
        showCurrentImageIndex();
        showCurrentAnimImage();
    }
    
    /**
     * donne l'index de l'image courante
     @return l'index de l'image courante
     */
    public  int getCurrentAnimImage()
    {
        return  c_img-1;
    }
    
    /**
     * defini la tuile
     @param t la tuile a afficher
     */
    public  void    setTile(Tile t)
    {
        animatedPanel.setVisible(false);
        tile=t;
        if  (t!=null)
        {
            
            id.setText(ID+t.getId());
            anim=t.getImages();
            if  (t.isAnimated())    
            {   add(animatedPanel); 
                c_img=1; 
                
                showCurrentAnimImage();
                showCurrentImageIndex();
                
                animatedPanel.setVisible(true);
            }else     
            {
                c_img=1; 
                setImage(t.getImage());
            }
            
        }else 
        {
            c_img=1; 
            id.setText(ID);
            gfx.setIcon(null);
        }
        
        gfx.repaint();
        revalidate();
    }
    
    public void mouseReleased(MouseEvent e)
    {
    }

    public void mousePressed(MouseEvent e)
    {
    }

    public void mouseExited(MouseEvent e)
    {
    }

    public void mouseEntered(MouseEvent e)
    {
    }

    public void mouseClicked(MouseEvent e)
    {
        MouseEvent n=new    MouseEvent(this,e.getID(),e.getWhen(),e.getModifiers(),e.getX(),e.getY(),e.getClickCount(),true);
        
        //remonte au parent
        MouseListener   m[]=getMouseListeners();
        for(int i=0;i<m.length;i++)
            m[i].mouseClicked(n);  
    }
}
