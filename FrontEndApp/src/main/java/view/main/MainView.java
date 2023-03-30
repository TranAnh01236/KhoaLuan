package view.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ultilities.customView.FixedStateButtonModel;
import ultilities.customView.MyCustomButton;
import ultilities.customView.MyCustomPanel;
import ultilities.customView.MyCustomTextFiled;
import view.AppFrame.MainFrame;
import view.main.addQuestions.MainAddQuestionsView;
import view.main.questionsList.MainQuestionsListView;

public class MainView extends MyCustomPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 439556263083410271L;
	
	private MainFrame frame;
	
	private MyCustomPanel pLeft, pCenter, pSearch, pBtnSearch, pTxtSearch, pMain, pSearch1, pAccount;
	private MyCustomButton btnQuestions, btnAddQuestions, btnExams, btnAddExams;
	private MyCustomButton btnSearch;
	private Box bLeft;
	private MyCustomPanel pTitle, pQuestions, pAddQuestions, pExams, pAddExams;
	private JLabel lblTitle, lblSearch;
	private ImageIcon icBook, icOpenBook, icQuiz, icSearch;
	
	private MyCustomTextFiled txtSearch;
	
	private int position = 0;
	
	private MainQuestionsListView mainQuestionsListView;
	private MainAddQuestionsView mainAddQuestionsView;
	
	public MainView(MainFrame frame) {
		this.frame = frame;
		Initialize();
	}
	private void Initialize() {
		
		icBook = new ImageIcon(new ImageIcon("images/books.png").getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING));
		icQuiz = new ImageIcon(new ImageIcon("images/choose.png").getImage().getScaledInstance(40, 40, Image.SCALE_AREA_AVERAGING));
		icOpenBook = new ImageIcon(new ImageIcon("images/open-book.png").getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING));
		icSearch = new ImageIcon(new ImageIcon("images/search.png").getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING));
		
		pLeft = new MyCustomPanel();
		pSearch = new MyCustomPanel();
		pCenter = new MyCustomPanel();
		pMain = new MyCustomPanel();
		pBtnSearch = new MyCustomPanel();
		pSearch1 = new MyCustomPanel();
		pAccount = new MyCustomPanel();
		
		btnQuestions = new MyCustomButton();
		btnAddQuestions = new MyCustomButton();
		btnExams = new MyCustomButton();
		btnAddExams = new MyCustomButton();
		btnSearch = new MyCustomButton();
		pTxtSearch = new MyCustomPanel();
		
		bLeft = Box.createVerticalBox();
		
		pTitle = new MyCustomPanel();
		pQuestions = new MyCustomPanel();
		pAddQuestions = new MyCustomPanel();
		pExams = new MyCustomPanel();
		pAddExams = new MyCustomPanel();
		
		lblTitle = new JLabel();
		lblSearch = new JLabel();
		
		lblTitle.setText("Quiz");
		lblTitle.setIcon(icQuiz);
		lblTitle.setForeground(new Color(65, 145, 222));
		lblTitle.setFont(new Font("Leelawadee UI", Font.BOLD, 50));
		
		btnQuestions.setText("Ngân hàng câu hỏi");
		btnQuestions.setFont(new Font("Leelawadee UI", Font.BOLD, 18));
		btnQuestions.setFocusPainted(false);
		btnQuestions.setBorder(new EmptyBorder(10, 10, 10, 10));
		btnQuestions.setIcon(icBook);
		btnQuestions.setForeground(new Color(31, 32, 29));
		btnQuestions.setRadius(30);
		btnQuestions.setBackground(Color.WHITE);
		btnQuestions.setHorizontalAlignment(SwingConstants.LEFT);
		btnQuestions.setModel(new FixedStateButtonModel());
		btnQuestions.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				position = 0;
				SwitchView(position);
			}
			
			public void mousePressed(MouseEvent e) {

			}
			
			public void mouseExited(MouseEvent e) {

			}
			
			public void mouseEntered(MouseEvent e) {

			}
			
			public void mouseClicked(MouseEvent e) {

			}
		});
		
		btnAddQuestions.setText("Thêm câu hỏi");
		btnAddQuestions.setFont(new Font("Leelawadee UI", Font.BOLD, 18));
		btnAddQuestions.setFocusPainted(false);
		btnAddQuestions.setBorder(new EmptyBorder(10, 10, 10, 10));
		btnAddQuestions.setIcon(icOpenBook);
		btnAddQuestions.setForeground(new Color(31, 32, 29));
		btnAddQuestions.setRadius(30);
		btnAddQuestions.setBackground(Color.WHITE);
		btnAddQuestions.setHorizontalAlignment(SwingConstants.LEFT);
		btnAddQuestions.setModel(new FixedStateButtonModel());
		btnAddQuestions.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				position = 1;
				SwitchView(position);
			}
			
			public void mousePressed(MouseEvent e) {

			}
			
			public void mouseExited(MouseEvent e) {

			}
			
			public void mouseEntered(MouseEvent e) {

			}
			
			public void mouseClicked(MouseEvent e) {

			}
		});
		
		
		btnExams.setText("Danh sách đề thi");
		btnExams.setFont(new Font("Leelawadee UI", Font.BOLD, 18));
		btnExams.setFocusPainted(false);
		btnExams.setBorder(new EmptyBorder(10, 10, 10, 10));
		btnExams.setIcon(icOpenBook);
		btnExams.setForeground(new Color(31, 32, 29));
		btnExams.setRadius(30);
		btnExams.setBackground(Color.WHITE);
		btnExams.setHorizontalAlignment(SwingConstants.LEFT);
		btnExams.setModel(new FixedStateButtonModel());
		btnExams.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				position = 2;
				SwitchView(position);
			}
			
			public void mousePressed(MouseEvent e) {

			}
			
			public void mouseExited(MouseEvent e) {

			}
			
			public void mouseEntered(MouseEvent e) {

			}
			
			public void mouseClicked(MouseEvent e) {

			}
		});
		
		btnAddExams.setText("Tạo đề thi");
		btnAddExams.setFont(new Font("Leelawadee UI", Font.BOLD, 18));
		btnAddExams.setFocusPainted(false);
		btnAddExams.setBorder(new EmptyBorder(10, 10, 10, 10));
		btnAddExams.setIcon(icOpenBook);
		btnAddExams.setForeground(new Color(31, 32, 29));
		btnAddExams.setRadius(30);
		btnAddExams.setBackground(Color.WHITE);
		btnAddExams.setHorizontalAlignment(SwingConstants.LEFT);
		btnAddExams.setModel(new FixedStateButtonModel());
		btnAddExams.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				position = 3;
				SwitchView(position);
			}
		});
		
		pTitle.add(lblTitle);
		pTitle.setAlignmentX(SwingConstants.LEFT);
		
		pQuestions.add(btnQuestions);
		pQuestions.setRoundTopLeft(30);
		pQuestions.setRoundBottomLeft(30);
		pQuestions.setBorder(new EmptyBorder(4, 1, 4, 0));
		
		pAddQuestions.add(btnAddQuestions);
		pAddQuestions.setRoundTopLeft(30);
		pAddQuestions.setRoundBottomLeft(30);
		pAddQuestions.setBorder(new EmptyBorder(4, 1, 4, 0));
		
		pExams.add(btnExams);
		pExams.setRoundTopLeft(30);
		pExams.setRoundBottomLeft(30);
		pExams.setBorder(new EmptyBorder(4, 1, 4, 0));
		
		pAddExams.add(btnAddExams);
		pAddExams.setRoundTopLeft(30);
		pAddExams.setRoundBottomLeft(30);
		pAddExams.setBorder(new EmptyBorder(4, 1, 4, 0));
		
		bLeft.add(pTitle);
		bLeft.add(Box.createVerticalStrut(40));
		bLeft.add(pQuestions);
		bLeft.add(pAddQuestions);
		bLeft.add(pExams);
		bLeft.add(pAddExams);
		
		pLeft.setPreferredSize(new Dimension(300, pLeft.getPreferredSize().height));
		pLeft.setBorder(new EmptyBorder(0, 3, 0, 0));
		
		btnQuestions.setPreferredSize(new Dimension(pLeft.getPreferredSize().width - 15 ,btnQuestions.getPreferredSize().height));
		btnAddQuestions.setPreferredSize(new Dimension(pLeft.getPreferredSize().width - 15 ,btnQuestions.getPreferredSize().height));
		btnExams.setPreferredSize(new Dimension(pLeft.getPreferredSize().width - 15 ,btnQuestions.getPreferredSize().height));
		btnAddExams.setPreferredSize(new Dimension(pLeft.getPreferredSize().width - 15 ,btnQuestions.getPreferredSize().height));
		
		pLeft.add(bLeft);
		
		txtSearch = new MyCustomTextFiled();
		txtSearch.setRoundTopLeft(30);
		txtSearch.setRoundBottomLeft(30);
		txtSearch.setBackground(new Color(233, 233, 230));
		txtSearch.setBorder(new EmptyBorder(10, 10, 10, 10));
		txtSearch.setForeground(new Color(31, 32, 29));
		txtSearch.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
		
		btnSearch.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
		btnSearch.setRoundTopRight(30);
		btnSearch.setRoundBottomRight(30);
		btnSearch.setFocusPainted(false);
		btnSearch.setBackground(new Color(65, 145, 222));
		btnSearch.setPreferredSize(new Dimension(50, txtSearch.getPreferredScrollableViewportSize().height));
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setIcon(icSearch);
		btnSearch.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSearch.setModel(new FixedStateButtonModel());
		btnSearch.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				btnSearch.setBackground(new Color(65, 145, 222));
			}
			
			public void mousePressed(MouseEvent e) {
				btnSearch.setBackground(new Color(100, 201, 244));
			}
			
			public void mouseExited(MouseEvent e) {

			}
			
			public void mouseEntered(MouseEvent e) {

			}
			
			public void mouseClicked(MouseEvent e) {

			}
		});
		
		lblSearch.setText("Nhập nội dung câu hỏi để tìm kiếm");
		lblSearch.setFont(new Font("Leelawadee UI", Font.BOLD, 12));
		lblSearch.setBorder(new EmptyBorder(5, 5, 0, 0));
		lblSearch.setForeground(new Color(159, 159, 157));
		
		pTxtSearch.setLayout(new BorderLayout());
		pTxtSearch.add(txtSearch, BorderLayout.NORTH);
		pTxtSearch.setBorder(new EmptyBorder(5, 0, 5, 0));
		pTxtSearch.add(lblSearch, BorderLayout.CENTER);
//		pTxtSearch.setAllBackgroundColor(Color.RED);
		
		pBtnSearch.add(btnSearch);
//		pBtnSearch.setAllBackgroundColor(Color.BLUE);
		pBtnSearch.setPreferredSize(new Dimension(btnSearch.getPreferredSize().width, pBtnSearch.getPreferredSize().height));
		
		pSearch1.setLayout(new BorderLayout());
		pSearch1.add(pTxtSearch, BorderLayout.CENTER);
		pSearch1.add(pBtnSearch, BorderLayout.EAST);
		
		pAccount.setBorder(new EmptyBorder(0, 0, 0, 0));
		pAccount.setPreferredSize(new Dimension(150, pAccount.getPreferredSize().height));
		
		pSearch.setLayout(new BorderLayout());
		pSearch.setAllBackgroundColor(Color.WHITE);
		pSearch.setBorder(new EmptyBorder(0, 0, 0, 0));
		pSearch.add(pSearch1, BorderLayout.CENTER);
		pSearch.add(pAccount, BorderLayout.EAST);
		
		pMain.setAllBackgroundColor(new Color(233, 233, 230));
		pMain.setRadius(30);
		pMain.setLayout(new BorderLayout());
		
		pCenter.setLayout(new BorderLayout());
		pCenter.add(pSearch, BorderLayout.NORTH);
		pCenter.add(pMain, BorderLayout.CENTER);
		
		this.setLayout(new BorderLayout());
		this.add(pLeft, BorderLayout.WEST);
		this.add(pCenter, BorderLayout.CENTER);
		this.setBorder(new EmptyBorder(15, 15, 15, 15));
		this.setAllBackgroundColor(Color.WHITE);
		
		SwitchView(0);
	}
	
	private void SwitchView(int pos) {
		pQuestions.setAllBackgroundColor(Color.WHITE);
		pAddQuestions.setAllBackgroundColor(Color.WHITE);
		pExams.setAllBackgroundColor(Color.WHITE);
		pAddExams.setAllBackgroundColor(Color.WHITE);
		
		btnQuestions.setBackground(Color.WHITE);
		btnAddQuestions.setBackground(Color.WHITE);
		btnExams.setBackground(Color.WHITE);
		btnAddExams.setBackground(Color.WHITE);
		
		btnQuestions.setForeground(new Color(31, 32, 29));
		btnAddQuestions.setForeground(new Color(31, 32, 29));
		btnExams.setForeground(new Color(31, 32, 29));	
		btnAddExams.setForeground(new Color(31, 32, 29));	
		
		if (mainQuestionsListView != null) {
			mainQuestionsListView.setVisible(false);
		}
		
		if (mainAddQuestionsView != null) {
			mainAddQuestionsView.setVisible(false);
		}
		
		switch (pos) {
		case 0:{
			pQuestions.setAllBackgroundColor(new Color(233, 233, 230));
			btnQuestions.setForeground(new Color(65, 145, 222));
			
			if (mainQuestionsListView == null) {
				mainQuestionsListView = new MainQuestionsListView();
				pMain.add(mainQuestionsListView, BorderLayout.CENTER);
			}
			
			mainQuestionsListView.setVisible(true);
			
			break;
		}
			
		case 1:{
			pAddQuestions.setAllBackgroundColor(new Color(233, 233, 230));
			btnAddQuestions.setForeground(new Color(65, 145, 222));
			
			if (mainAddQuestionsView == null) {
				mainAddQuestionsView = new MainAddQuestionsView(frame);
				pMain.add(mainAddQuestionsView);
			}
			
			mainAddQuestionsView.setVisible(true);
			
			break;
		}
		case 2:{
			pExams.setAllBackgroundColor(new Color(233, 233, 230));
			btnExams.setForeground(new Color(65, 145, 222));
			break;
		}
		case 3:{
			pAddExams.setAllBackgroundColor(new Color(233, 233, 230));
			btnAddExams.setForeground(new Color(65, 145, 222));
			break;
		}
		}
		
		pQuestions.repaint();
		pAddQuestions.repaint();
		pExams.repaint();
		pAddExams.repaint();
		
		btnQuestions.repaint();
		btnAddQuestions.repaint();
		btnExams.repaint();
		btnAddExams.repaint();
		
//		pQuestions.setAllBackgroundColor(Color.BLUE);
//		pAddQuestions.setAllBackgroundColor(Color.BLUE);
//		pExams.setAllBackgroundColor(Color.BLUE);
//		
//		btnQuestions.setBackground(Color.RED);
//		btnAddQuestions.setBackground(Color.RED);
//		btnExams.setBackground(Color.RED);
//		
//		btnQuestions.setForeground(new Color(31, 32, 29));
//		btnAddQuestions.setForeground(new Color(31, 32, 29));
//		btnExams.setForeground(new Color(31, 32, 29));
		
	}
}
