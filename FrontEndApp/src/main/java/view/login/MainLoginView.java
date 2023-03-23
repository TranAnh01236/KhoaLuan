package view.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;

import org.trananh3010.model.User;

import keeptoo.KGradientPanel;
import ultilities.customView.MyCustomPanel;

public class MainLoginView extends KGradientPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2545631879281133872L;
	private MyCustomPanel pMain;
	private Box box;
	private LoginPanel loginPanel;
	
	private MainloginViewListener mainloginViewListener;
	
	public interface MainloginViewListener{
		void login(User user);
	}
	
	public MainLoginView(MainloginViewListener listener) {
		this.mainloginViewListener = listener;
		initialize();
	}
		
	public void initialize() {
		
		pMain = new MyCustomPanel();
		pMain.setPreferredSize(new Dimension(640, 480));
		pMain.setMaximumSize(new Dimension(640, 480));
		box = Box.createHorizontalBox();
		
		loginPanel = new LoginPanel(new LoginPanel.LoginPanelListener() {
			public void btnLoginClicked(User user) {
				mainloginViewListener.login(user);
			}
		});

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(Box.createVerticalGlue());
		this.add(box);
		this.add(Box.createVerticalGlue());
		
		box.add(Box.createHorizontalGlue());
		box.add(pMain);
		box.add(Box.createHorizontalGlue());
		
		pMain.setRadius(30);
		
		pMain.setLayout(new BorderLayout());
		
		pMain.add(loginPanel, BorderLayout.CENTER);
		
		this.setkStartColor(new Color(233, 233, 230));
		this.setkEndColor(new Color(233, 233, 230));
		this.setkGradientFocus(-30);
	}
}
