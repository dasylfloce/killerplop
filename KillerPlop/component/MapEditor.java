/**
 * MapEditor.java
 *
 * Created on 22 mars 2006, 10:20
 *
 * 
 */

package editor.ui.component;

import  editor.ui.*;
import game.map.Map;
import game.map.displayer.Displayer;
import game.map.displayer.MapDisplayerNotOptimised;
import game.animation.CanAnim;
import game.animation.AnimationThread;
import game.map.tile.TileWareHouse;

import  javax.swing.*;
import  java.awt.*;
import  java.awt.image.*;
import  java.awt.event.*;

/**
 * le modele (respecte le modele MVC, enfin, pas complettement lol) <br>
 *
 * @author Guillaume BOUCHON (bouchon_guillaume@yahoo.fr)
 */
public class MapEditor  extends JComponent implements CanAnim
{
    private MapEditorControler  control;    //le controlleur
    
    private JScrollBar  vs,hs;      //les scrollbars
    
    private MapDisplayerNotOptimised    display;    //l'afficheur
    
    private Map             map;    //la map associée
    private TileWareHouse   stock;  //stcok a tuile
    private int             tw,th;  //dimensions des tuiles
    private boolean         grid=true;   //grille affichée ou pas
    
    //couleurs
    private Color   back_color=new  Color(0,0,0);       //couleur du fond
    private Color   grid_color=new  Color(255,0,0); //couleur du quadrillage
    
    private static  final   Dimension   default_dim=new Dimension(400,400); //dimensions par defaut
  
    
    private short   ctile1=-1,ctile2=-1;   //les tuiles en cours
    
    private byte state=STATE_SET;  //etat courant
    
    private static  final   byte    STATE_SET=  1;      //mode placement de tuile
    
    /**
     * gestionnaire d'animation
     */
    private AnimationThread anims;
    
    /**
     * vitesse des animations (en millisecondes)
     */
    private int ANIM_RATE=  300;
    
    /**
     * Constructeur de  MapEditor
     */
    public MapEditor()
    {
        anims=new   AnimationThread(ANIM_RATE);
        anims.add(this);
        anims.start();
        
        control=new MapEditorControler(this);
        display=new MapDisplayerNotOptimised();
        
        setPreferredSize(default_dim);
    }
    
    /**
     * defini les scrollbars
     */
    public  void    setScrollBars(JScrollBar h,JScrollBar v)
    {
        vs=v;
        hs=h;
        vs.addAdjustmentListener(control);
        hs.addAdjustmentListener(control);
        
        updateScrollBars();
    }
    
    /**
     * met a jour les scrollbars
     */
    public void    updateScrollBars()
    {
        if  (hs==null || vs==null)  return;
        
        if  (map==null || stock==null)
        {
            hs.setValues(0,0,0,0);
            vs.setValues(0,0,0,0);
            return;
        }
        int w=tw*map.getWidth(),h=th*map.getHeight();
        hs.setValues((int) display.getViewX(),getWidth(),0,w);
        vs.setValues((int) display.getViewY(),getHeight(),0,h);
        
    }

    /**
     * defini l'id de la tuile 1 en cours
     */
    public  void    setTile1(short id)
    {
        ctile1=id;
    }
    
    /**
     * donne la tuile 1 en cours
     */
    public  short   getTile1()
    {
        return  ctile1;
    }
    
    /**
     * defini l'id de la tuile 2 en cours
     */
    public  void    setTile2(short id)
    {
        ctile2=id;
    }
    
    /**
     * donne la tuile 2 en cours
     */
    public  short   getTile2()
    {
        return  ctile2;
    }
      
    /**
     * defini la carte
     */
    public  void    setMap(Map m)
    {
        map=m;
        display.setMap(map);
       
        updateScrollBars();
        repaint();
    }
    
    /**
     * donne la carte
     */
    public  Map getMap()
    {
        return  map;
    }
    
     /**
     * donne le TileWareHouse
     */
    public  TileWareHouse   getTileWareHouse()
    {
        return  stock;
    }
    
    /**
     * defini le TileWareHouse
     */
    public  void   setTileWareHouse(TileWareHouse t)
    {
        stock=t;
        if  (stock!=null)
        {
            tw=t.getTileWidth();
            th=t.getTileHeight();
            t.convert(BufferedImage.TYPE_INT_ARGB);
            display.setTileWareHouse(t);
            updateScrollBars();
        }
        repaint();
    }
    
    /**
     * donne le displayeur
     */
    public  Displayer    getDisplayer()
    {
        return  display;
    }
    
    
    
    /**
     * defini la vue de la map
     */
    public  void    setView(int vw,int vh)
    {
        display.setView(vw,vh);
        updateScrollBars();
        repaint();
    }
    
    /**
     * defini la position de la vue (sans remettre a jour les scrollbars)
     */
    public  void    setViewPosX(float x)
    {
        setViewPosN(x,display.getViewY());
    }
    
    /**
     * defini la position de la vue (sans remettre a jour les scrollbars)
     */
    public  void    setViewPosY(float y)
    {
        setViewPosN(display.getViewX(),y);
    }
    
    /**
     * defini la position de la vue sans remettre a jour les scrollbars
     */
    public  void    setViewPosN(float x,float y)
    {
        setViewPos(x,y);
        updateScrollBars();
    }
    
    /**
     * defini la position de la vue et remet a jour les scrollbars
     */
    public  void    setViewPos(float x,float y)
    {
        display.setView(x,y);
        repaint();
    }
    
    /**
     * donne la couleur du fond
     */
    public  Color   getBackColor()
    {
        return  back_color;
    }
    
    /**
     * defini la couleur du fond
     */
    public  void    setBackColor(Color back)
    {
        back_color=back;
    }
    
    /**
     * active la grille ou pas
     */
    public  void    setGrid(boolean g)
    {
        grid=g;
        repaint();
    }
    
    /**
     * donne l'état de la grille
     */
    public  boolean getGrid()
    {
        return  grid;
    }
    
    /**
     * defini la tuile dans la map avec la tuile 1  courante
     @param x,y : positions dans la carte en coordonnées carte
     */
    public  void    set1(int x,int y)
    {
        set(ctile1,x,y);
    }
    
    /**
     * defini la tuile dans la map avec la tuile 2  courante
     @param x,y : positions dans la carte en coordonnées carte
     */
    public  void    set2(int x,int y)
    {
        set(ctile2,x,y);
    }
    
    /**
     * defini la tuile dans la map
     @param x,y : positions dans la carte en coordonnées carte
     */
    public  void    set(short id,int x,int y)
    {
        if  (map==null) return;
        
        map.set(id,x,y);
        repaint();
    }
    
    /**
     * defini la tuile dans la map avec la tuile courante 1
     @param x,y : positions  par rapport a la vue en PIXELS!
     */
    public  void    setAt1(int x,int y)
    {
        setAt(ctile1,x,y);
    }
    
    /**
     * defini la tuile dans la map avec la tuile courante21
     @param x,y : positions  par rapport a la vue en PIXELS!
     */
    public  void    setAt2(int x,int y)
    {
        setAt(ctile2,x,y);
    }
    
     /**
     * defini la tuile dans la map
     @param x,y : positions  par rapport a la vue en PIXELS!
     */
    public  void    setAt(short id,int x,int y)
    {
        if  (map==null || tw==0 || th==0) return;
        
        //coordonnées reelles
        int xm=(int) display.getViewX()+x,ym=(int) display.getViewY()+y;
        
        set(id,xm/tw,ym/th);    //ecrit dans la map
    }
    
    /**
     * bouton gauche préssé
     */
    public  void    mouseLeftButton(int x,int y)
    {
        switch(state)
        {
            case    STATE_SET:  //defini une tuile dans la carte
                setAt1(x,y);
            break;
        }
    }
    
    /**
     * quand on laisse appuiyer un bouton de la souri
     */
    public  void    dragMouse(int bouton,int xm,int ym)
    {
        switch(state)
        {
            case    STATE_SET:  //defini une tuile dans la carte
                if  (bouton==MouseEvent.BUTTON1)
                    setAt1(xm,ym);
                else if  (bouton==MouseEvent.BUTTON3)
                    setAt2(xm,ym);
                
                repaint();
            break;
        }
    }
    
    /**
     * bouton droit pressé
     */
    public  void    mouseRightButton(int x,int y)
    {
        switch(state)
        {
            case    STATE_SET:  //defini une tuile dans la carte
                setAt2(x,y);
            break;
        }
    }
    
    /**
     * affichage
     */
    public  void    paint(Graphics g)
    {
        int w=getWidth(),h=getHeight();     //dimensions du composant
        
        //efface le fond
        g.setColor(back_color);
        g.fillRect(0,0,w,h);

        //affiche la map
        if  (display!=null && stock!=null)
            display.render((Graphics2D) g);
        
        //le quadrillage
        if  (grid)
        {
            g.setColor(grid_color);
            display.renderGrid((Graphics2D) g);
        }
    }

    /**
     * image suivante dans l'animation
     * 
     * @return l'index de l'image courante ou -1 si l'animation est terminée
     */
    public int nextFrame()
    {
        if  (stock!=null)
        {
            stock.nextFrame();
            repaint();
        }
        return  0;
    }

    /**
     * donne l'attente en millisecondes entre 2 image de l'animation
     * 
     * @return attente en millisecondes entre 2 images de l'animation
     */
    public int getAnimationRate()
    {
        return  ANIM_RATE;
    }
    
    
}
