package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.trananh3010.model.User;

import jnafilechooser.api.JnaFileChooser;
import ultilities.Constants;
import ultilities.customView.ComponentResizer;
import view.login.MainLoginView;
import view.main.MainView;

public class AppFrame {
	private Dimension size = new Dimension(1600, 900);
	private OutsidePanel outsidePanel;
	private ImageIcon icCross, icSquare, icMinus, icSquare1;
	private MainLoginView mainLoginView;
	private MainView mainView;
	private BorderPanel borderPanel;

	public class MainFrame extends JFrame {
		/**
		 * 
		 */
		private static final long serialVersionUID = -2409114029391477389L;

		public MainFrame() {

		}
	}

	private MainFrame frame = new MainFrame();
	private int state = 0;

	public AppFrame() {
		initialize();
	}

	class MainPanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = -954066934712257378L;

		public MainPanel() {
			setBackground(new Color(47, 48, 67));
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(1600, 900);
		}
	}

	class BorderPanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 5443685117629005489L;
		private Box box;
		private JLabel lblExit, lblMinimize, lblMaximize;
		int pX, pY;

		public BorderPanel() {
			box = Box.createHorizontalBox();

//            lblExit = new JLabel(" X ");
			lblExit = new JLabel();
			lblExit.setIcon(icCross);
			lblExit.setFont(new Font("Leelawadee UI", Font.BOLD, 15));
			lblExit.setOpaque(true);
			lblExit.setBackground(new Color(233, 233, 230));
			lblExit.setForeground(new Color(106, 104, 106));

			lblMaximize = new JLabel();
			lblMaximize.setIcon(icSquare);
//            lblMaximize = new JLabel(" ☐ ");
			lblMaximize.setFont(new Font("Leelawadee UI", Font.BOLD, 17));
			lblMaximize.setOpaque(true);
			lblMaximize.setBackground(new Color(233, 233, 230));
			lblMaximize.setForeground(new Color(106, 104, 106));

			lblMinimize = new JLabel();
			lblMinimize.setIcon(icMinus);
//            lblMinimize = new JLabel(" ﹣ ");
			lblMinimize.setFont(new Font("Leelawadee UI", Font.BOLD, 18));
			lblMinimize.setOpaque(true);
			lblMinimize.setBackground(new Color(233, 233, 230));
			lblMinimize.setForeground(new Color(106, 104, 106));

			setBackground(new Color(255, 255, 255));
			setLayout(new FlowLayout(FlowLayout.RIGHT));

			box.add(lblMinimize);
			box.add(Box.createHorizontalStrut(10));
			box.add(lblMaximize);
			box.add(Box.createHorizontalStrut(10));
			box.add(lblExit);
			box.add(Box.createHorizontalStrut(10));

			add(box);

			lblMaximize.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					if (state == 0) {
						Rectangle rec = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
						size = frame.getPreferredSize();
						frame.setSize(rec.width, rec.height);
						frame.setLocationRelativeTo(null);
						lblMaximize.setIcon(icSquare1);
						state = 1;
					} else {
						frame.setSize(size);
						frame.setLocationRelativeTo(null);
						state = 0;
						lblMaximize.setIcon(icSquare);
					}
					
				}
			});

			lblExit.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					System.exit(0);
				}
			});

			lblMinimize.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					frame.setState(Frame.ICONIFIED);
					outsidePanel.repaint();
				}
			});

			addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent me) {
					// Get x,y and store them
					pX = me.getX();
					pY = me.getY();

				}

				public void mouseDragged(MouseEvent me) {

					frame.setLocation(frame.getLocation().x + me.getX() - pX, frame.getLocation().y + me.getY() - pY);
				}
			});

			addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent me) {

					frame.setLocation(frame.getLocation().x + me.getX() - pX, frame.getLocation().y + me.getY() - pY);
				}
			});
		}
	}

	public class OutsidePanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = -6055542885928342171L;

		public MainPanel mainPanel;

		public OutsidePanel() {
			mainPanel = new MainPanel();
			borderPanel = new BorderPanel();
			setLayout(new BorderLayout());
			add(mainPanel, BorderLayout.CENTER);
			add(borderPanel, BorderLayout.PAGE_START);
			setBorder(new LineBorder(Color.BLUE, 0));
			mainPanel.setLayout(new BorderLayout());

//			mainLoginView = new MainLoginView(new MainLoginView.MainloginViewListener() {
//				public void login(User user) {
//					Constants.saveUser(user);
//					mainLoginView.setVisible(false);
//					MainView mainView = new MainView(frame);
//					mainPanel.add(mainView);
//					borderPanel.setBackground(new Color(233, 233, 230));
//					System.out.println("Chasy");
//				}
//			});
//			mainPanel.add(mainLoginView, BorderLayout.CENTER);
			
            mainView = new MainView(frame);
            borderPanel.setBackground(new Color(233, 233, 230));
			mainPanel.add(mainView);
			
		}
	}
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                new UndecoratedExample().createAnsShowGui();
//            }
//        });
//    }

	private void initialize() {
		icCross = new ImageIcon(
				new ImageIcon("images/cross.png").getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING));
		icSquare = new ImageIcon(
				new ImageIcon("images/square.png").getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING));
		icSquare1 = new ImageIcon(
				new ImageIcon("images/square1.png").getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING));
		icMinus = new ImageIcon(
				new ImageIcon("images/minus.png").getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING));

		outsidePanel = new OutsidePanel();

		ComponentResizer cr = new ComponentResizer();
		cr.setMinimumSize(new Dimension(1366, 768));
		cr.registerComponent(frame);
		cr.setSnapSize(new Dimension(10, 10));
		frame.setUndecorated(true);
		frame.add(outsidePanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
//        frame.setShape(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), 40, 40));
		frame.setVisible(true);

//        outsidePanel.mainPanel.setLayout(new BorderLayout());
//        outsidePanel.mainPanel.add(mainLoginView, BorderLayout.CENTER);

//        resize();
		frame.addComponentListener(new ComponentListener() {

			public void componentShown(ComponentEvent e) {

			}

			public void componentResized(ComponentEvent e) {
				Constants.appHeight = frame.getSize().height;
				Constants.appWidth = frame.getSize().width;
				outsidePanel.setVisible(false);
				outsidePanel.setVisible(true);
			}

			public void componentMoved(ComponentEvent e) {

			}

			public void componentHidden(ComponentEvent e) {

			}
		});
		
	}
}
