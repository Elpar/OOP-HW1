
package homework1;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Iterator;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;


/**
 * Main application class for exercise #1.
 * This application allows the user to add shapes to a graphical window and
 * to animate them.
 */
@SuppressWarnings("serial")
public class Animator extends JFrame implements ActionListener {

	// preferred frame width and height.
	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 400;

	// graphical components
	private JMenuBar menuBar;
	private JMenu fileMenu, insertMenu, helpMenu;
	private JMenuItem newItem, exitItem,
						rectangleItem, roundedRectangleItem, ovalItem,
						numberedOvalItem, sectorItem, aboutItem;
	private JCheckBoxMenuItem animationCheckItem;
	private JPanel mainPanel;

	// shapes that have been added to this
	private ArrayList<homework1.Shape> shapes = new ArrayList<>();

	/**
	 * @modifies this
	 * @effects Initializes the GUI and enables a timer that steps animation
	 * 			of all shapes in this 25 times per second while animation
	 * 			checkbox is selected.
	 */
	public Animator() {

		super("Animator");

		// create main panel and menubar
		mainPanel = (JPanel)createMainPanel();
		getContentPane().add(mainPanel);
		menuBar = (JMenuBar)createMenuBar();
        setJMenuBar(menuBar);

        // enable animation timer (ticks 25 times per second)
        Timer timer = new Timer(40, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (animationCheckItem.isSelected()) {
                	homework1.Shape currentShape = null;
					Iterator<homework1.Shape> timerIterator = shapes.iterator();
					while(timerIterator.hasNext()) {
						currentShape = timerIterator.next();
						if (currentShape instanceof Animatable) {
							((Animatable)currentShape).step(mainPanel.getBounds()); //TODO: make sure this is correct, might need to replace "mainPanel" with getContentPane().getBounds()
						}
					}
            		repaint();	// make sure that the shapes are redrawn
                }
            }
        });
        timer.start();
	}


	/**
	 * @return main GUI panel.
	 */
	private JComponent createMainPanel() {
    	JPanel mainPanel = new JPanel();
    	mainPanel.setPreferredSize(
    			new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
    	mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
    	mainPanel.setBackground(Color.WHITE);

    	return mainPanel;
	}


	/**
	 * @return main GUI menubar.
	 */
	private JMenuBar createMenuBar() {
    	JMenuBar menuBar = new JMenuBar();

    	fileMenu = new JMenu("File");
    	newItem = new JMenuItem("New");
    	newItem.addActionListener(this);
    	fileMenu.add(newItem);
    	animationCheckItem = new JCheckBoxMenuItem("Animation");
    	fileMenu.add(animationCheckItem);
    	exitItem = new JMenuItem("Exit");
    	exitItem.addActionListener(this);
    	fileMenu.add(exitItem);
    	menuBar.add(fileMenu);

    	insertMenu = new JMenu("Insert");
    	rectangleItem = new JMenuItem("Rectangle");
    	rectangleItem.addActionListener(this);
    	insertMenu.add(rectangleItem);
    	roundedRectangleItem = new JMenuItem("Rounded Rectangle");
    	roundedRectangleItem.addActionListener(this);
    	insertMenu.add(roundedRectangleItem);
    	ovalItem = new JMenuItem("Oval");
    	ovalItem.addActionListener(this);
    	insertMenu.add(ovalItem);
    	numberedOvalItem = new JMenuItem("Numbered Oval");
    	numberedOvalItem.addActionListener(this);
    	insertMenu.add(numberedOvalItem);
    	sectorItem = new JMenuItem("Sector");
    	sectorItem.addActionListener(this);
    	insertMenu.add(sectorItem);
    	menuBar.add(insertMenu);

    	helpMenu = new JMenu("Help");
    	aboutItem = new JMenuItem("About");
    	aboutItem.addActionListener(this);
    	helpMenu.add(aboutItem);
    	menuBar.add(helpMenu);

    	return menuBar;
	}


	/**
	 * @modifies g
	 * @effects Paint this including all its shapes to g. This method is
	 * 			invoked by Swing to draw components. It should not be invoked
	 * 			directly, but the repaint method should be used instead in
	 * 			order to schedule the component for redrawing.
	 */
	public void paint(Graphics g) {
		super.paint(g);
		Iterator<homework1.Shape> shapeIterator = shapes.iterator();
		while (shapeIterator.hasNext()) {
			shapeIterator.next().draw(getContentPane().getGraphics());
		}
	}


	/**
	 * @modifies this
	 * @effects Invoked when the user selects an action from the menubar
	 * 			and performs the appropriate operation.
	 */
	public void actionPerformed(ActionEvent e) {
		JMenuItem source = (JMenuItem)(e.getSource());

		// File->New : clear all shapes
		if (source.equals(newItem)) {
			shapes.clear();
			repaint();
			
			LocationChangingNumberedOval.zeroNumberOfNumberedOvals();//TODO  Add code for number of LocationChangingNumerOval = 0
		}

		// File->Exit: close application
		else if (source.equals(exitItem)) {
        	dispose();
        }

		// Insert a shape
		else if ((source.equals(rectangleItem)) || (source.equals(roundedRectangleItem)) ||
      		 	 (source.equals(ovalItem)) || (source.equals(numberedOvalItem)) || (source.equals(sectorItem))) {
			Random rand = new Random();
			int maxDimX = WINDOW_WIDTH/10 + rand.nextInt(3*WINDOW_WIDTH/10);
			int maxDimY = WINDOW_HEIGHT/10 + rand.nextInt(3*WINDOW_HEIGHT/10);
			Dimension dim = new Dimension(maxDimX, maxDimY);
			Rectangle windowBounds = getContentPane().getBounds();
			int boundX = windowBounds.width-dim.width;
			int boundY = windowBounds.height-dim.height-menuBar.getHeight();
			Point originPoint = new Point(rand.nextInt(boundX), rand.nextInt(boundY));
			Color newColor = new Color(rand.nextInt());
			homework1.Shape newShape;
			if (source.equals(rectangleItem)) {
				newShape = new homework1.LocationChangingRectangle(originPoint, newColor, dim);
			} else if (source.equals(roundedRectangleItem)) {
				newShape = new homework1.LocationChangingRoundedRectangle(originPoint, newColor, dim,
																		rand.nextInt(maxDimX-maxDimX/2),
																		rand.nextInt(maxDimY-maxDimY/2));
			} else if (source.equals(ovalItem)) {
				newShape = new homework1.LocationChangingOval(originPoint, newColor, dim);
			} else if (source.equals(numberedOvalItem)) {
				newShape = new LocationChangingNumberedOval(originPoint, newColor, dim);
			} else {
				newShape = new AngleChangingSector(originPoint, newColor, dim, rand.nextInt(360), rand.nextInt(360)); //TODO: fix AngleChangingSector, according to the instructions it needs to get 2 more variables!
			}
			//add dimension by calling setSize
			//try {
			//	newShape.setSize(dim);
			//} catch (ImpossibleSizeException exception) {
			//	dim = exception.getSuggestedDimension(); //suggesting a new dimension
			//}
			shapes.add(newShape);
			repaint();
		}

		// Help->About : show about message dialog
		else if (source.equals(aboutItem)){
			JOptionPane.showMessageDialog(
					this,
					"Animator - 1st" +
					" homework assignment",
					"About",
					JOptionPane.INFORMATION_MESSAGE);
		}
    }


	/**
	 * @effects Animator application.
	 */
	public static void main(String[] args) {
        Animator application = new Animator();

        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setResizable(false);
        application.pack();
        application.setVisible(true);
	}
}
