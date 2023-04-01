package view.main.examsList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.print.attribute.standard.PagesPerMinute;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.trananh3010.model.Chapter;
import org.trananh3010.model.Grade;
import org.trananh3010.model.Lesson;
import org.trananh3010.model.Subject;

import controller.ChapterController;
import controller.GradeController;
import controller.LessonController;
import controller.SubjectsController;
import ultilities.customView.MyCustomPanel;
import ultilities.customView.comboboxSuggesion.ComboBoxSuggestion;
import view.AppFrame.MainFrame;
import view.main.addQuestions.AddQuestionView;

public class MainExamsListView extends MyCustomPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7804528363567706483L;
	
	private MyCustomPanel pTop, pCenter, pMain;
	
	private JLabel lblBack;
	
//	private MainFrame frame;
	
	private int position = 0;
	
	private ExamsListView examsListView;
	
	
	
	public MainExamsListView() {
		Initialize();
	}
	
	private void Initialize() {
		
		pTop = new MyCustomPanel();
		pCenter = new MyCustomPanel();
		pMain = new MyCustomPanel();

		pTop.setLayout(new BoxLayout(pTop, BoxLayout.X_AXIS));
		pTop.setRadius(30);
		pTop.setAllBackgroundColor(new Color(255, 255, 255, 0));
		pTop.setBorder(new EmptyBorder(10, 15, 0, 15));
		
		lblBack = new JLabel("< Quay lại");
		lblBack.setFont(new Font("Leelawadee UI", Font.BOLD, 18));
		lblBack.setForeground(new Color(168, 169, 166));
		lblBack.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				position--;
				SwitchView();
			}
			
			public void mousePressed(MouseEvent e) {
			}
			
			public void mouseExited(MouseEvent e) {
				lblBack.setForeground(new Color(168, 169, 166));
			}
			
			public void mouseEntered(MouseEvent e) {
				lblBack.setForeground(new Color(65, 145, 222));
			}
			
			public void mouseClicked(MouseEvent e) {
			}
		});

		pTop.add(lblBack);		

		pCenter.setRadius(30);
		pCenter.setLayout(new BorderLayout());
		pCenter.setBorder(new EmptyBorder(0, 0, 0, 0));

		pMain.setRadius(30);
		pMain.setLayout(new BoxLayout(pMain, BoxLayout.Y_AXIS));

		this.setLayout(new BorderLayout());

		pCenter.add(pMain, BorderLayout.CENTER);

		this.add(pTop, BorderLayout.NORTH);
		this.add(pCenter, BorderLayout.CENTER);
		this.setRadius(30);
		
		SwitchView();
	}
	
	private void SwitchView() {
		
		if (examsListView != null) {
			examsListView.setVisible(false);
		}
		
		lblBack.setVisible(true);
		
		switch (position) {
		case 0:
			if (examsListView == null) {
				examsListView = new ExamsListView();
				pMain.add(examsListView);
			}
			examsListView.setVisible(true);
			lblBack.setVisible(false);
			break;

		default:
			break;
		}
		
	}
	
}
