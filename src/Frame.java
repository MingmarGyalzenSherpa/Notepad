import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class Frame extends JFrame implements ActionListener {

    JFileChooser fileChooser;

    String latest = "";

    FileNameExtensionFilter fileNameExtensionFilter;
    String text;
    FileReader fileReader;
    FileWriter fileWriter;

    File file;
    JMenuBar menu = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenuItem fileMenuItemSaveAs = new JMenuItem("Save As");
    JMenuItem fileMenuItemOpen = new JMenuItem("Open");
    JTextArea textArea;

    Frame(){
        fileChooser = new JFileChooser();
       fileNameExtensionFilter = new FileNameExtensionFilter("text","txt","docx");
       fileChooser.addChoosableFileFilter(fileNameExtensionFilter);
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
        fileMenu.add(fileMenuItemOpen);
        fileMenu.setPreferredSize(new Dimension(50,30));
        menu.add(fileMenu);
        fileMenuItemSaveAs.addActionListener(this);
        fileMenuItemOpen.addActionListener(this);


    }
    private void setTextArea(){
        textArea = new JTextArea();
        textArea.setMargin(new Insets(10,10,10,10));
    }


    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == fileMenuItemSaveAs)
        {
            try {
                saveFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource() == fileMenuItemOpen)
        {
            try {
                openFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public File setExtension(File file){

        String name = file.getName();  //get name
        String parentPath = file.getParent();
        String ext = "";
        String url = "";
        //get the extension
        if(name.length() > 4) {

            for (int i = name.length() - 4; i < name.length(); i++) {
                ext += name.charAt(i);
            }
        }
        if(ext != ".txt")
        {
            url = parentPath + "/" + name + ".txt";
        }
        return new File(url);

    }

    public void saveFile() throws IOException {
        boolean status;


        int response = fileChooser.showSaveDialog(null); //show dialog box

        if(response == JFileChooser.APPROVE_OPTION)
        {

            file = setExtension(fileChooser.getSelectedFile());  //get the selected file
            System.out.println(file.getParent());
             status =   file.createNewFile();
            //writing into the file
            if(status)
            {
                fileWriter = new FileWriter(file);
                text =  textArea.getText();
                fileWriter.write(text);
                fileWriter.close();
            }


        }

    }

    public void openFile() throws IOException{
        String texts = "";
        fileChooser.addChoosableFileFilter(fileNameExtensionFilter);
        int response = fileChooser.showOpenDialog(null);

        if(response == JFileChooser.APPROVE_OPTION)
        {
            file = fileChooser.getSelectedFile();
            fileReader = new FileReader(file);
            int character;
            while( (character = fileReader.read()) != -1)
            {
                texts += (char)character;
            }
            textArea.setText(texts);
        }
    }
}
