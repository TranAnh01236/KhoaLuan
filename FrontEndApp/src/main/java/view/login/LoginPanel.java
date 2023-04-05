package view.login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import org.trananh3010.model.User;
import org.trananh3010.ultilities.MyHttpResponse;

import controller.AuthController;
import ultilities.customView.ButtonGradient;
import ultilities.customView.MyCustomPanel;
import ultilities.customView.MyCustomPasswordFiled;
import ultilities.customView.MyCustomTextFiled;

public class LoginPanel extends MyCustomPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6547589230447489967L;
	
	private JLabel lblTitle, lblTi, lblAccount, lblPassword, lblForgotPassword, lblError;
	private Box b,bTitle, bTi,bA, bP,bAccount,bPassword, bRemember, bBtnLogin, bError;
	private MyCustomTextFiled txtAccount;
	private MyCustomPasswordFiled txtPassword;
	private ImageIcon icAccount, icPassword;
	private JCheckBox cbRememberMe;
	private ButtonGradient btnLogin;
	
	private LoginPanelListener loginPanelListener;
	
	private AuthController authController;
	
	public interface LoginPanelListener{
		void btnLoginClicked(User user);
	}
	
	public LoginPanel(LoginPanelListener listener) {
		this.loginPanelListener = listener;
		intitialize();
	}
	
	private void intitialize() {
		icAccount = new ImageIcon(new ImageIcon("images/account.png").getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING));
		icPassword = new ImageIcon(new ImageIcon("images/password.png").getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING));
		
		authController = new AuthController();
		
		b = Box.createVerticalBox();
		bTitle = Box.createHorizontalBox();
		bTi = Box.createHorizontalBox();
		bA = Box.createHorizontalBox();
		bAccount = Box.createHorizontalBox();
		bP = Box.createHorizontalBox();
		bPassword = Box.createHorizontalBox();
		bError = Box.createHorizontalBox();
		bRemember = Box.createHorizontalBox();
		bBtnLogin = Box.createHorizontalBox();
		
		
		lblTitle = new JLabel("Đăng nhập");
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setFont(new Font("Leelawadee UI", Font.BOLD, 35));
		
		lblTi = new JLabel("Sử dụng tài khoản được cấp để đăng nhập");
		lblTi.setForeground(new Color(168, 169, 166));
		lblTi.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
		
		lblAccount = new JLabel("Tài khoản:");
		lblAccount.setIcon(icAccount);
		lblAccount.setForeground(Color.BLACK);
		lblAccount.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
		
		txtAccount = new MyCustomTextFiled();
		txtAccount.setRadius(30);
		txtAccount.setBackground(new Color(233, 233, 230));
		txtAccount.setBorder(new EmptyBorder(10,10,10,10));
		txtAccount.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
		txtAccount.setForeground(new Color(34, 34, 32));
		
		lblPassword = new JLabel("Mật khẩu:");
		lblPassword.setIcon(icPassword);
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
		
		txtPassword = new MyCustomPasswordFiled();
		txtPassword.setRadius(30);
		txtPassword.setBackground(new Color(233, 233, 230));
		txtPassword.setBorder(new EmptyBorder(10,10,10,10));
		txtPassword.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
		txtPassword.setForeground(new Color(34, 34, 32));
		
		lblError = new JLabel();
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Leelawadee UI", Font.BOLD, 13));
		
		cbRememberMe = new JCheckBox();
		cbRememberMe.setText("Nhớ tài khoản");
		cbRememberMe.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
//		cbRememberMe.setBorder(new EmptyBorder(10,10,10,10));
		cbRememberMe.setFocusPainted(false);
		cbRememberMe.setBackground(Color.WHITE);
		cbRememberMe.setForeground(new Color(168, 169, 166));
		
		lblForgotPassword = new JLabel("Quên mật khẩu?");;
		lblForgotPassword.setForeground(new Color(168, 169, 166));
		lblForgotPassword.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
		
		lblForgotPassword.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseExited(MouseEvent e) {
				lblForgotPassword.setForeground(new Color(130, 129, 130));
				
			}
			
			public void mouseEntered(MouseEvent e) {
				lblForgotPassword.setForeground(new Color(15, 165, 227));
				
			}
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnLogin = new ButtonGradient();
		btnLogin.setText("Đăng nhập");
		btnLogin.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
		btnLogin.setBorder(new EmptyBorder(10,10,10,10));
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFocusPainted(false);
		btnLogin.setColor1(new Color(65, 145, 222));
        btnLogin.setColor2(new Color(65, 145, 222));
        btnLogin.setSizeSpeed(30f);
		btnLogin.setRadius(30);
		btnLogin.setMaximumSize(new Dimension(580, 200));
		btnLogin.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				login();
			}
			
			public void mousePressed(MouseEvent e) {
			}
			
			public void mouseExited(MouseEvent e) {
				btnLogin.setBackground(new Color(15, 165, 227));
			}
			
			public void mouseEntered(MouseEvent e) {
				btnLogin.setBackground(new Color(8, 79, 137));
			}
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnLogin.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
				System.out.println("asdasd1");
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
			public void keyPressed(KeyEvent e) {
				System.out.println("asdasd");
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});
		
		bTitle.add(Box.createHorizontalGlue());
		bTitle.add(lblTitle);
		bTitle.add(Box.createHorizontalGlue());
		
		bTi.add(Box.createHorizontalGlue());
		bTi.add(lblTi);
		bTi.add(Box.createHorizontalGlue());
		
		bA.add(lblAccount);
		bA.add(Box.createHorizontalGlue());
		bA.setPreferredSize(new Dimension(580 ,bA.getPreferredSize().height));
		
		bAccount.add(txtAccount);
		
		bP.add(lblPassword);
		bP.add(Box.createHorizontalGlue());
		
		bPassword.add(txtPassword);
		
		bError.add(Box.createHorizontalStrut(5));
		bError.add(lblError);
		bError.add(Box.createHorizontalGlue());
		
		bRemember.add(cbRememberMe);
		bRemember.add(Box.createHorizontalGlue());
		bRemember.add(lblForgotPassword);
		
		bBtnLogin.add(btnLogin);
		
		b.add(Box.createVerticalStrut(20));
		b.add(bTitle);
		b.add(Box.createVerticalStrut(10));
		b.add(bTi);
		b.add(Box.createVerticalStrut(30));
		b.add(bA);
		b.add(Box.createVerticalStrut(10));
		b.add(bAccount);
		b.add(Box.createVerticalStrut(20));
		b.add(bP);
		b.add(Box.createVerticalStrut(10));
		b.add(bPassword);
		b.add(Box.createVerticalStrut(10));
		b.add(bError);
		b.add(Box.createVerticalStrut(10));
		b.add(bRemember);
		b.add(Box.createVerticalStrut(20));
		b.add(bBtnLogin);
		
		this.add(b);
		this.setAllBackgroundColor(Color.WHITE);
		this.setRadius(30);
		
		
	}
	
	private void login() {
		String account = txtAccount.getText().trim();
		if (account.equals("")) {
			lblError.setText("Bạn chưa nhập tài khoản");
			lblError.setForeground(Color.RED);
			return;
		}
		String password = txtPassword.getText().trim();
		if (password.equals("")) {
			lblError.setText("Bạn chưa nhập mật khẩu");
			lblError.setForeground(Color.RED);
			return;
		}
		
		try {
			MyHttpResponse myResponse = authController.login(account, password);
			if (myResponse.getStatusCode() == 200) {
				lblError.setText("Đăng nhập thành công");
				lblError.setForeground(new Color(0, 234, 117));
				User user =  org.trananh3010.ultilities.Constants.gson.fromJson(myResponse.payloadJSON(), User.class);
				loginPanelListener.btnLoginClicked(user);
				return;
			}else if(myResponse.getStatusCode() == 404) {
				lblError.setText(myResponse.getMessage());
				lblError.setForeground(Color.RED);
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
