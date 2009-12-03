/**
 * MapEditorControler.java
 *
 * Created on 22 mars 2006, 11:11
 *
 * 
 */

package editor.ui.component;


import  javax.swing.*;
import  java.awt.*;
import  java.awt.event.*;

/**
 *  le controleur pour l'editeur (respecte le modele MVC!!) <br>
 *
 * @author Guillaume BOUCHON (bouchon_guillaume@yahoo.fr)
 */
public class MapEditorControler implements  ComponentListener,MouseListener,AdjustmentListener,MouseMotionListener
{
    //duree minimale que l'on doit appuyer sur un bouton de la souris pour consider un appui prolongé
   // private static  final   int MIN_APPUI_DUREE=    100;    
    
    private int xm,ym;  //positions de la souris
    
    private MapEditor   editor; //le modele et la vue (respecte pas tout a fait MVC)
    private int bouton;  //pour gerer l'appui prolongé
    
    /**
     * Constructeur de  MapEditorControler
     */
    public MapEditorControler(MapEditor editor)
    {
        this.editor=editor;
        
        //se greffe a l'editeur
        editor.addComponentListener(this);
        editor.addMouseListener(this);
        editor.addMouseMotionListener(this);
    }

    public void componentShown(ComponentEvent e)
    {
    }

    /**
     * redimensionné
     */
    public void componentResized(ComponentEvent e)
    {
        //change la taille de la vue
        Rectangle r=editor.getVisibleRect();
        editor.setView((int) r.getWidth(),(int) r.getHeight());
    }

    public void componentMoved(ComponentEvent e)
    {

    }

    public void componentHidden(ComponentEvent e)
    {
    }

    public void mouseReleased(MouseEvent e)
    {

    }

    public void mousePressed(MouseEvent e)
    {
        //debut d'un appui prolongé
        bouton=e.getButton();
    }

    public void mouseExited(MouseEvent e)
    {
    }

    public void mouseEntered(MouseEvent e)
    {
    }

    public void mouseClicked(MouseEvent e)
    {
        int x=e.getX(),y=e.getY();
        
        switch(e.getButton())
        {
            case    MouseEvent.BUTTON1: //bouton gauche
                editor.mouseLeftButton(x,y);
            break;
            case    MouseEvent.BUTTON3: //bouton droit
                editor.mouseRightButton(x,y);
            break;    
        }
    }

    public void adjustmentValueChanged(AdjustmentEvent e)
    {
        JScrollBar  s=(JScrollBar) e.getSource();
        if  (s.getOrientation()==JScrollBar.VERTICAL)
        {
            editor.setViewPosY(e.getValue());
        }else
        {
            editor.setViewPosX(e.getValue());
        }
    }

    public void mouseMoved(MouseEvent e)
    {
        xm=e.getX();
        ym=e.getY();
    }

    public void mouseDragged(MouseEvent e)
    {
        xm=e.getX();
        ym=e.getY();
        
        editor.dragMouse(bouton,xm,ym);
    }
    
  
}
