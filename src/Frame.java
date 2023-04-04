import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener {

    JFileChooser fileChooser;
    JMenuBar menu = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenuItem fileMenuItemSaveAs = new JMenuItem("Save As");
    JTextArea textArea;

    Frame(){
        setMenu();

        setTextArea();

        this.setSize(new Dimension(500, 500));
        this.setLayout(new BorderLayout());
        this.add(menu,BorderLayout.NORTH);
        this.add( textArea );
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);


    }

    private void setMenu(){
        fileMenu.add(fileMenuItemSaveAs);
        fileMenu.setPreferredSize(new Dimension(50,30));
        menu.add(fileMenu);
        fileMenuItemSaveAs.addActionListener(this);

    }
    private void setTextArea(){
        textArea = new JTextArea();
        textArea.setMargin(new Insets(10,10,10,10));
    }


    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == fileMenuItemSaveAs)
        {
            fileChooser = new JFileChooser();

        }
    }
}
