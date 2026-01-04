import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Menu extends JFrame {

    private JTextArea textArea;
    private Color randomGreenColor;

    public Menu() {
        setTitle("Menu");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

       
        randomGreenColor = Color.getHSBColor((float)Math.random() * 0.15f + 0.25f, 1f, 1f);

       
        textArea = new JTextArea();
        textArea.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        add(scrollPane, BorderLayout.CENTER);

        
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");

        JMenuItem item1 = new JMenuItem("Show Date and Time");
        JMenuItem item2 = new JMenuItem("Save to log.txt");
        JMenuItem item3 = new JMenuItem("Change Background Color");
        JMenuItem item4 = new JMenuItem("Exit");

        
        item1.addActionListener(e -> {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            textArea.setText(LocalDateTime.now().format(formatter));
        });

       
        item2.addActionListener(e -> {
            try (FileWriter writer = new FileWriter("log.txt")) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "Saved to log.txt");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "Error writing to file",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

       
        item3.addActionListener(e ->
                getContentPane().setBackground(randomGreenColor)
        );

        // Option 4: Exit program
        item4.addActionListener(e -> System.exit(0));

        menu.add(item1);
        menu.add(item2);
        menu.add(item3);
        menu.add(item4);

        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
    	new Menu().setVisible(true);
    }
}