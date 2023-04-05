package view.main.addExams;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import org.trananh3010.model.Grade;

import controller.GradeController;
import ultilities.customView.FixedStateButtonModel;
import ultilities.customView.MyCustomButton;
import ultilities.customView.MyCustomPanel;

public class ChooseGradeView extends MyCustomPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2395264414701326201L;

	private MyCustomPanel pMain;

	private JScrollPane scrollPane;

	private GridLayout gridLayout;
	
	private List<Grade> lstGrades;
	
	private GradeController gradeController;
	
	private ChooseGradeViewListener chooseGradeViewListener;
	
	public interface ChooseGradeViewListener{
		void GradeClick(Grade grade);
	}

	public ChooseGradeView(ChooseGradeViewListener listener) {
		this.chooseGradeViewListener = listener;
		Initialize();
	}

	private void Initialize() {
		this.setRadius(30);
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(15, 15, 15, 0));
		
		gradeController = new GradeController();

		gridLayout = new GridLayout(0, 4);
		gridLayout.setVgap(15);
		gridLayout.setHgap(15);

		pMain = new MyCustomPanel();
		pMain.setAllBackgroundColor(new Color(233, 233, 230));
		pMain.setBorder(new EmptyBorder(0, 0, 0, 15));
		pMain.setLayout(gridLayout);

		scrollPane = new JScrollPane(pMain, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setOpaque(true);

		this.add(scrollPane, BorderLayout.CENTER);
		
		LoadGrades();
	}
	
	private void LoadGrades() {
		lstGrades = gradeController.getAllGrades();
		
		pMain.removeAll();
		pMain.setVisible(false);
		
		if(lstGrades != null && lstGrades.size() > 0) {
			for (Grade g : lstGrades) {
				final Grade grade = g;
				
				MyCustomPanel p1 = new MyCustomPanel();
				p1.setAllBackgroundColor(Color.WHITE);
				p1.setRadius(30);
				p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
				p1.setBorder(new EmptyBorder(15, 15, 15, 15));
				
				String image = getCharFromString(grade.getName().trim());
				
				MyCustomPanel pImage = new MyCustomPanel();
				pImage.setLayout(new BoxLayout(pImage, BoxLayout.X_AXIS));
				JLabel lblImage = new JLabel(image);
				lblImage.setFont(new Font("Leelawadee UI", Font.BOLD, 50));
				lblImage.setForeground(Color.WHITE);
				lblImage.setPreferredSize(new Dimension(lblImage.getPreferredSize().width, 150));
				
				pImage.setRadius(30);
				pImage.setAllBackgroundColor(new Color(68, 165, 0));
				pImage.add(Box.createHorizontalGlue());
				pImage.add(lblImage);
				pImage.add(Box.createHorizontalGlue());
				
				Box bName = Box.createHorizontalBox();
				JTextArea lblName = new JTextArea(grade.getName());
				lblName.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
				lblName.setForeground(Color.BLACK);
				lblName.setLineWrap(true);
				lblName.setWrapStyleWord(true);
				bName.add(lblName);
				
				Box bDescription = Box.createHorizontalBox();
				JTextArea lblDescription = new JTextArea("Tạo đề thi trắc nghiệm cho học sinh " + grade.getName().toLowerCase());
				lblDescription.setFont(new Font("Leelawadee UI", Font.PLAIN, 13));
				lblDescription.setForeground(new Color(31, 32, 29));
				lblDescription.setLineWrap(true);
				lblDescription.setWrapStyleWord(true);
				bDescription.add(lblDescription);
				
				Box bBtn = Box.createHorizontalBox();
				MyCustomButton btnCreateExam = new MyCustomButton();
				btnCreateExam.setText("Tạo đề thi");
				btnCreateExam.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
				btnCreateExam.setBackground(new Color(65, 145, 222));
				btnCreateExam.setForeground(Color.WHITE);
				btnCreateExam.setBorder(new EmptyBorder(7, 30, 7, 30));
				btnCreateExam.setRadius(30);
				btnCreateExam.setModel(new FixedStateButtonModel());
				btnCreateExam.setFocusPainted(false);
				bBtn.add(Box.createHorizontalGlue());
				bBtn.add(btnCreateExam);
				btnCreateExam.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						super.mouseReleased(e);
						chooseGradeViewListener.GradeClick(grade);
					}
				});
				
				p1.add(pImage);
				p1.add(Box.createVerticalStrut(10));
				p1.add(bName);
				p1.add(Box.createVerticalStrut(10));
				p1.add(bDescription);
				p1.add(Box.createVerticalStrut(10));
				p1.add(bBtn);
				
				pMain.add(p1);
				
			}
		}
		
		pMain.setVisible(true);
	}
	
	private static boolean stringIsNumber(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	private static String getCharFromString(String name) {
		String str = "";
		char[] c = name.toCharArray();
		List<String> lst = new ArrayList<String>();
		for (int i = 0; i < c.length; i++) {
			lst.add(String.valueOf(c[i]));
		}
		for (int i = 0; i < lst.size(); i++) {
			if (stringIsNumber(lst.get(i))) {
				if (i > 0) {
					if (!stringIsNumber(lst.get(i-1))) {
						if (!str.equals("")) {
							break;
						}
					}
				}
				str += c[i];
			}
		}
		if (str.trim().equals("")) {
			str = name.trim().substring(0, 2);
		}
		return str;
	}
}
