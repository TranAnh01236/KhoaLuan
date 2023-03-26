package view.main.questionsList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.trananh3010.model.Grade;

import controller.GradeController;
import ultilities.customView.MyCustomPanel;

public class GradesListView extends MyCustomPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3771273667587376479L;

	private MyCustomPanel pMain;
	private GradeController gradeController = new GradeController();
	private List<Grade> grades;
	private JScrollPane scrollPane;
	private GradesListListener gradesListListener;
	
	public interface GradesListListener{
		void ItemClick(Grade grade);
	}
	
	public GradesListView(GradesListListener listener) {
		this.gradesListListener = listener;
		Initialize();
	}
	
	private void Initialize() {
//		this.setAllBackgroundColor(new Color(100, 201, 244));
		this.setRadius(30);
		this.setBorder(new EmptyBorder(15, 15, 15, 0));
		
		pMain = new MyCustomPanel();
		pMain.setAllBackgroundColor(new Color(233, 233, 230));
		pMain.setLayout(new BoxLayout(pMain, BoxLayout.Y_AXIS));
		pMain.setRadius(30);
		
		CreateListGrades();
		
//		pMain.add(new JLabel("asdasdasdasd"));
		
		scrollPane = new JScrollPane(pMain,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBackground(Color.RED);
		scrollPane.setOpaque(true);
		
//		scrollPane.setBackground(Color.GREEN);
//		scrollPane.setVerticalScrollBar(scrollBar);
		
//		this.setLayout(new BorderLayout());
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(scrollPane);
	}
	
	public void CreateListGrades() {
		pMain.removeAll();
		grades = gradeController.getAllGrades();
		if (grades != null && grades.size() > 0) {
			for (Grade grade : grades) {
				final Grade g = grade;
				MyCustomPanel p = new MyCustomPanel();
				p.setAllBackgroundColor(new Color(233, 233, 230));
				p.setPreferredSize(new Dimension(p.getPreferredSize().width, 100));
				p.setMaximumSize(new Dimension(p.getMaximumSize().width, 100));
				p.setBorder(new EmptyBorder(0, 0, 15, 15));
				p.setRadius(30);
				p.setLayout(new BorderLayout());
				final MyCustomPanel p1 = new MyCustomPanel();
				p1.setAllBackgroundColor(Color.WHITE);
				p1.setRadius(30);
				p1.setBorder(new EmptyBorder(15, 15, 15, 15));
				p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
				final JLabel l1 = new JLabel(grade.getName());
				l1.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
				l1.setForeground(new Color(255, 151, 0));
				l1.setBorder(new EmptyBorder(0, 0, 10, 0));
				final JLabel l2 = new JLabel(grade.getDescription());
				l2.setFont(new Font("Leelawadee UI", Font.BOLD, 14));
				l2.setForeground(new Color(70, 70, 68));
				p1.add(l1);
				p1.add(l2);
				p.add(p1, BorderLayout.CENTER);
				p1.addMouseListener(new MouseListener() {
					public void mouseReleased(MouseEvent e) {
						gradesListListener.ItemClick(g);
					}
					
					public void mousePressed(MouseEvent e) {

					}
					
					public void mouseExited(MouseEvent e) {
						p1.setAllBackgroundColor(Color.WHITE);
						l2.setForeground(new Color(70, 70, 68));
						p1.repaint();
					}
					
					public void mouseEntered(MouseEvent e) {
						p1.setAllBackgroundColor(new Color(100, 201, 244));
						l2.setForeground(Color.WHITE);
						p1.repaint();
					}
					
					public void mouseClicked(MouseEvent e) {

					}
				});
				pMain.add(p);
			}
		}
	}
	
}
