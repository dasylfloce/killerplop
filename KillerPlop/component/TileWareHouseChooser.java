/**
 * TileWareHouseChooser.java
 *
 * Created on 24 mars 2006, 11:07
 *
 */

package editor.ui.component;

import game.map.tile.Tile;
import game.map.tile.TileWareHouse;
import  javax.swing.*;
import  java.awt.*;
import  java.awt.event.*;

/**
 * selection d'une tuile a partir d'une warehouse<br>
 * Pour recuperer le TileComponent on a cliqué, recuperer l'evenement MouseEvent de TileWareHouseChooser,
* la source contient le TileComponent cliqué
 *
 * @author Guillaume BOUCHON (bouchon_guillaume@yahoo.fr)
 */
public class TileWareHouseChooser   extends JScrollPane implements MouseListener
{
    private JPanel          ptiles; //le panel contenant les tuiles
    private TileWareHouse   tiles;  //là où sont les tuiles
    
    /**
     * Constructeur de  TileWareHouseChooser
     */
    public TileWareHouseChooser()
    {
        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
        setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
        
        ptiles=new  JPanel();
        ptiles.setLayout(new BoxLayout(ptiles,BoxLayout.X_AXIS));
        setViewportView(ptiles);
        
    }
    
    /**
     * donne la warehouse
     @return la warehouse courante
     */
    public  TileWareHouse    getTileWareHouse()
    {
        return  tiles;
    }
    
    /**
     * remet a jour
     */
    public  void    update()
    {
        setTileWareHouse(tiles);
    }
    
    /**
     * defini la warehouse
     @param t la warehouse a afficher
     */
    public  void    setTileWareHouse(TileWareHouse t)
    {
        tiles=t;
        ptiles.removeAll();
        if  (t!=null)
        {
            //ajout des tuiles dans le panel
            Tile    ti[]=t.getTiles();
            
            for(int i=0;i<ti.length;i++)
            {
                TileComponent   tc=new  TileComponent(ti[i]);
                tc.addMouseListener(this);
                tc.repaint();
                ptiles.add(tc);
            }
        }
        revalidate();
        repaint();
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

    /**
     * cliqué sur une tuile<br>
     * remonte l'evenement vers l'ecouteur du TileWareHouseChooser
     */
    public void mouseClicked(MouseEvent e)
    {
        MouseListener   m[]=getMouseListeners();
        for(int i=0;i<m.length;i++)
            m[i].mouseClicked(e);   
    }
    
    
    
}
