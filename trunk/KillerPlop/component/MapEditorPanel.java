/**
 * MapEditorPanel.java
 *
 * Created on 22 mars 2006, 19:09
 *
 */

package editor.ui.component;

import  javax.swing.*;
import  java.awt.*;

/**
 * le panel contenant le MapEditor : ce panel est obligatoire!
 *
 * @author Guillaume BOUCHON (bouchon_guillaume@yahoo.fr)
 */
public class MapEditorPanel extends JPanel
{
    
    
    /**
     * Constructeur de  MapEditorPanel
     */
    public MapEditorPanel(MapEditor editor)
    {
        setLayout(new   BorderLayout());
        
        add(editor,BorderLayout.CENTER);
        
        JScrollBar  vc=new  JScrollBar(JScrollBar.VERTICAL);
        add(vc,BorderLayout.EAST);
        
        JScrollBar  hc=new  JScrollBar(JScrollBar.HORIZONTAL);
        add(hc,BorderLayout.SOUTH);
        
        editor.setScrollBars(hc,vc);
    }
    
}
