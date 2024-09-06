/*
 *  ============================================================================================
 *  A2.java : Extends JFrame and contains a panel where shapes move around on the screen.
 *  YOUR UPI: mrez993
 *  ============================================================================================
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class A2  extends JFrame {
	private AnimationViewer bouncingPanel;  // panel for bouncing area
	JComboBox<ShapeType> shapesComboBox;
	JComboBox<PathType> pathComboBox;
	JButton addButton;
	JTextField widthTextField, heightTextField;

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new A2();
			}
		});
	}
	public A2() {
		super("Bouncing Application");
		JPanel mainPanel = setUpMainPanel();
		add(mainPanel, BorderLayout.CENTER);
		add(setUpToolsPanel(), BorderLayout.NORTH);
		addComponentListener(
			new ComponentAdapter() { // resize the frame and reset all margins for all shapes
				public void componentResized(ComponentEvent componentEvent) {
					bouncingPanel.resetMarginSize();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	public JPanel setUpMainPanel() {
		JPanel mainPanel = new JPanel();
		bouncingPanel = new AnimationViewer();
		bouncingPanel.setPreferredSize(new Dimension(Shape.DEFAULT_PANEL_WIDTH, Shape.DEFAULT_PANEL_HEIGHT));
		mainPanel.add(bouncingPanel, BorderLayout.CENTER);
		return mainPanel;
	}
	public JPanel setUpToolsPanel() {
		shapesComboBox = new JComboBox<ShapeType>(new DefaultComboBoxModel<ShapeType>(ShapeType.values()));
		shapesComboBox.addActionListener( new ShapeActionListener()) ;
		pathComboBox = new JComboBox<PathType>(new DefaultComboBoxModel<PathType>(PathType.values()));
		pathComboBox.addActionListener( new PathActionListener());
		addButton = new JButton("Add");
		addButton.addActionListener( new AddListener());
		widthTextField = new JTextField(""+Shape.DEFAULT_WIDTH, 5);
		heightTextField = new JTextField(""+Shape.DEFAULT_HEIGHT, 5);
		heightTextField.setEditable(false);
		widthTextField.addActionListener( new WidthListener());
		JPanel toolsPanel = new JPanel();
		toolsPanel.setLayout(new BoxLayout(toolsPanel, BoxLayout.X_AXIS));
		toolsPanel.add(new JLabel(" Shape: ", JLabel.RIGHT));
		toolsPanel.add(shapesComboBox);
		toolsPanel.add(new JLabel(" Path: ", JLabel.RIGHT));
		toolsPanel.add(pathComboBox);
		toolsPanel.add(new JLabel(" Width: ", JLabel.RIGHT));
		toolsPanel.add(widthTextField);
		toolsPanel.add(heightTextField);
		toolsPanel.add(addButton);
		return toolsPanel;
	}
	class ShapeActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			bouncingPanel.setCurrentShapeType((ShapeType)shapesComboBox.getSelectedItem());
		}
	}
	class PathActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			bouncingPanel.setCurrentPathType((PathType)pathComboBox.getSelectedItem());
		}
	}
	class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            bouncingPanel.createNewShape();
        }
    }
    
    class WidthListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String input = widthTextField.getText();
            try {
                int width = Integer.parseInt(input);
                // Validate the entered value
                if (width < 0){
                    throw new NumberFormatException();
                } else {
                    bouncingPanel.setCurrentWidth(width);
                }

            } catch (NumberFormatException ex) {
                // Handle the case where the input cannot be parsed as an integer
                widthTextField.setText(Integer.toString(bouncingPanel.getCurrentWidth()));
            }
        }
    }
}

