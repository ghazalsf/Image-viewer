import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.awt.image.RescaleOp;

public class ImageViewerGUI extends JFrame implements ActionListener{
    // Buttons
    JButton selectFileButton;
    JButton showImageButton;
    JButton resizeButton;
    JButton grayscaleButton;
    JButton brightnessButton;
    JButton closeButton;
    JButton showResizeButton;
    JButton showBrightnessButton;
    JButton backButton;

    // fields
    JTextField widthTextField;
    JTextField heightTextField;
    JTextField brightnessTextField;

    String filePath = "D:\\uni\\ap\\project1";
    File file;
    JFileChooser fileChooser = new JFileChooser(filePath);
    int h = 900;
    int w = 1200;
    float brightenFactor = 1;

    boolean isPicSelected = false;

    Color gray = new Color(49, 54, 63);
    Color x = new Color(238, 238, 238);


    ImageViewerGUI(){
        this.setName("image viewer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Image Viewer");
        this.setSize(600, 400);
        this.getContentPane().setBackground(gray);
        this.setVisible(true);
        this.setResizable(false);

        mainPanel();
    }

    public void mainPanel(){
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(gray);

        // Create Grid panel for adding buttons to it, then add it all to main panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(gray);

        buttonsPanel.setLayout(new GridLayout(6, 1));
        ((GridLayout)buttonsPanel.getLayout()).setHgap(50);
        ((GridLayout)buttonsPanel.getLayout()).setVgap(50);

        // initialize label for explain program
        JLabel title = new JLabel("image viewer");
        JLabel title2= new JLabel(" {you can edit your pictures} ");

        title.setForeground(x);
        title2.setForeground(x);

        buttonsPanel.add(title);
        buttonsPanel.add(title2);

        // create buttons
        selectFileButton = new JButton("select file");
        showImageButton = new JButton("show image");
        brightnessButton = new JButton("brightness");
        grayscaleButton = new JButton("grayscale");
        resizeButton = new JButton("resize");
        closeButton = new JButton("close");

        //add action listener to buttons
        selectFileButton.addActionListener(this);
        showImageButton.addActionListener(this);
        brightnessButton.addActionListener(this);
        grayscaleButton.addActionListener(this);
        resizeButton.addActionListener(this);
        closeButton.addActionListener(this);

        //color for buttons
        Color blue = new Color(118, 171, 174);
        selectFileButton.setBackground(blue);
        showImageButton.setBackground(blue);
        brightnessButton.setBackground(blue);
        grayscaleButton.setBackground(blue);
        resizeButton.setBackground(blue);
        closeButton.setBackground(blue);

        // Adding all buttons to Grid panel
        buttonsPanel.add(this.selectFileButton);
        buttonsPanel.add(this.showImageButton);
        buttonsPanel.add(this.brightnessButton);
        buttonsPanel.add(this.grayscaleButton);
        buttonsPanel.add(this.resizeButton);
        buttonsPanel.add(this.closeButton);

        // add Grid panel that contains 6 buttons to main panel
        mainPanel.add(buttonsPanel);

        // add main panel to our frame
        this.add(mainPanel);
    }
    public void errorHandler(int choice){
        this.getContentPane().removeAll();

        JPanel tempPanel = new JPanel();
        tempPanel.setLayout(null);
        tempPanel.setBackground(gray);
        
        JLabel tempLabel = null;
        if( choice == 0){
            tempLabel = new JLabel("choose your image first!!");
        }else if( choice == 1){
            tempLabel = new JLabel("enter float number in range 0 to 1");
        } else if ( choice == 2) {
            tempLabel = new JLabel("enter valid int number");

        }
        tempLabel.setForeground(x);
        tempLabel.setBounds(220,100,300,40);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        backButton.setBounds(250,150,80,20);

        tempPanel.add(tempLabel);
        tempPanel.add(backButton);

        this.getContentPane().add(tempPanel);
        this.add(tempPanel);
        this.revalidate();
        this.repaint();
    }
    public void resizePanel(){
        this.getContentPane().removeAll();

        // check if pic selected
        try{
            BufferedImage bufferedImage = ImageIO.read(file);
        }catch (Exception exception){
            errorHandler(0);
            return;
        }
        JPanel resizePanel = new JPanel();
        resizePanel.setBackground(gray);

        //text filed
        widthTextField = new JTextField();
        heightTextField = new JTextField();
        widthTextField.setColumns(18);
        heightTextField.setColumns(18);

        // JLabels
        JLabel resizeSectionLabel = new JLabel("resize section");
        JLabel widthLabel = new JLabel("width: ");
        JLabel heightLabel = new JLabel("height: ");
        resizeSectionLabel.setForeground(x);
        widthLabel.setForeground(x);
        heightLabel.setForeground(x);

        // buttons
        backButton = new JButton("Back");
        showResizeButton = new JButton("show resized image");
        backButton.addActionListener(this);
        showResizeButton.addActionListener(this);

        //add to panel
        resizePanel.add(resizeSectionLabel);
        resizePanel.add(widthLabel);
        resizePanel.add(widthTextField);
        resizePanel.add(heightLabel);
        resizePanel.add(heightTextField);
        resizePanel.add(backButton);
        resizePanel.add(showResizeButton);

        //add to frame
        this.getContentPane().add(resizePanel);
        this.add(resizePanel);
        this.revalidate();
        this.repaint();
    }
    public void brightnessPanel(){
        //remove previous
        this.getContentPane().removeAll();

        // check if pic selected
        try{
            BufferedImage bufferedImage = ImageIO.read(file);
        }catch (Exception exception){
            errorHandler(0);
            return;
        }

        //panel
        JPanel brightnessPanel = new JPanel();
        brightnessPanel.setBackground(gray);

        //text fields
        brightnessTextField = new JTextField();
        brightnessTextField.setColumns(18);

        //label
        JLabel brightnessSectionLabel = new JLabel(" brightness section");
        JLabel enterLabel = new JLabel("enter number between 0 to 1");
        brightnessSectionLabel.setForeground(x);
        enterLabel.setForeground(x);

        //buttons
        backButton = new JButton("Back");
        showBrightnessButton = new JButton("show changed image");
        backButton.addActionListener(this);
        showBrightnessButton.addActionListener(this);

        //add to panel
        brightnessPanel.add(brightnessSectionLabel);
        brightnessPanel.add(enterLabel);
        brightnessPanel.add(brightnessTextField);
        brightnessPanel.add(showBrightnessButton);
        brightnessPanel.add(backButton);

        //add to frame
        this.getContentPane().add(brightnessPanel);
        this.add(brightnessPanel);
        this.revalidate();
        this.repaint();
    }

    public void chooseFileImage(){
        int res = fileChooser.showSaveDialog(null);

        if(res == fileChooser.APPROVE_OPTION){
            file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            System.out.println(file);
        }else{
            System.out.println("import canceled");
        }
    }
    public void showOriginalImage(){
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();

        try {
            // load original image
            BufferedImage bufferedImage = ImageIO.read(file);

            ImageIcon imageIcon = new ImageIcon(bufferedImage);
            tempPanel.setLayout(new FlowLayout());
            JLabel imageLabel = new JLabel();

            imageLabel.setIcon(imageIcon);
            tempPanel.add(imageLabel);

        } catch (Exception exception) {
            errorHandler(0);
            return;

        }

        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }

    public void grayScaleImage(){
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();

        try {
            BufferedImage bufferedImage = ImageIO.read(file);

            // change each pixel to gray
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                for (int x = 0; x < bufferedImage.getWidth(); x++) {

                    Color color = new Color(bufferedImage.getRGB(x, y));
                    int gray = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                    Color grayColor = new Color(gray, gray, gray);
                    bufferedImage.setRGB(x, y, grayColor.getRGB());
                }
            }
            JLabel imageLabel = new JLabel();

            ImageIcon grayImageIcon = new ImageIcon(bufferedImage);
            imageLabel.setIcon(grayImageIcon);
            tempPanel.add(imageLabel);

        } catch (Exception exception) {
            errorHandler(0);
            return;
        }

        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
        System.out.println("gray scaled");

    }
    public void showResizeImage(int w, int h){
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        JLabel imageLabel = new JLabel();

        try {
            BufferedImage bufferedImage = ImageIO.read(file);

            // resize image
            BufferedImage resizedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = resizedImage.createGraphics();
            g2d.drawImage(bufferedImage, 0, 0, w, h, null);
            g2d.dispose();

            ImageIcon imageIcon = new ImageIcon(resizedImage);
            tempPanel.setLayout(new FlowLayout());
            imageLabel.setIcon(imageIcon);

        } catch (Exception exception) {
            errorHandler(0);
            return;
        }

        //add to panel and frame
        tempPanel.add(imageLabel);
        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }
    public void showBrightnessImage(float f){
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        JLabel imageLabel = new JLabel();

        try {
            BufferedImage bufferedImage = ImageIO.read(file);

            // change the brightness of the image
            BufferedImage resizedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            RescaleOp op = new RescaleOp(f, 0, null);
            BufferedImage changedImage = op.filter(bufferedImage, null);

            ImageIcon imageIcon = new ImageIcon(changedImage);
            tempPanel.setLayout(new FlowLayout());
            imageLabel.setIcon(imageIcon);

        } catch (Exception exception) {
            errorHandler(0);
            return;

        }
        tempPanel.add(imageLabel);
        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==resizeButton){
            resizePanel();

        }else if(e.getSource()== showImageButton){
            showOriginalImage();

        }else if(e.getSource()==grayscaleButton){
            grayScaleImage();

        }else if(e.getSource()== showResizeButton){
            try {
                h = Integer.parseInt(heightTextField.getText());
                w = Integer.parseInt(widthTextField.getText());
                showResizeImage(h,w);
            }catch (Exception exception){
                errorHandler(2);
            }

        }else if(e.getSource()==brightnessButton){
            brightnessPanel();

        }else if(e.getSource()== showBrightnessButton){
            try {
                brightenFactor = Float.parseFloat(brightnessTextField.getText());
                showBrightnessImage(brightenFactor);

            }catch (Exception exception){
                errorHandler(1);
            }

        }else if(e.getSource()== selectFileButton){
            chooseFileImage();

        }else if(e.getSource()==closeButton){
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        else if(e.getSource()==backButton){
            this.getContentPane().removeAll();
            this.mainPanel();
            this.revalidate();
            this.repaint();
        }
    }
}