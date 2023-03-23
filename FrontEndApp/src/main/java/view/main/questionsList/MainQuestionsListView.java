package view.main.questionsList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import org.trananh3010.model.Chapter;
import org.trananh3010.model.Grade;
import org.trananh3010.model.Lesson;
import org.trananh3010.model.Subject;

import ultilities.customView.MyCustomPanel;
import view.main.questionsList.ChaptersListView.ChapterListViewListener;
import view.main.questionsList.GradesListView.GradesListListener;
import view.main.questionsList.LessonsListView.LessonsListViewListener;
import view.main.questionsList.SubjectListView.SubjectsListListener;

public class MainQuestionsListView extends MyCustomPanel implements GradesListListener, SubjectsListListener, ChapterListViewListener, LessonsListViewListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6875588032818047067L;

	private MyCustomPanel pTop, pCenter, pMain;

	private JLabel lblGrade, lblSubject, lblChapter, lblLesson;

	private int position = 0;

	private GradesListView gradesListView;
	private SubjectListView subjectsListView;
	private ChaptersListView chaptersListView;
	private LessonsListView lessonsListView;

	public MainQuestionsListView() {
		Initilize();
	}

	private void Initilize() {

		this.setAllBackgroundColor(new Color(233, 233, 230));
		this.setRadius(30);
//		this.setAllBackgroundColor(Color.RED);
		
		gradesListView = new GradesListView(this);
		subjectsListView = new SubjectListView(this);
		chaptersListView = new ChaptersListView(this);
		lessonsListView = new LessonsListView(this);

		pTop = new MyCustomPanel();
		pCenter = new MyCustomPanel();
		pMain = new MyCustomPanel();

		pTop.setLayout(new BoxLayout(pTop, BoxLayout.X_AXIS));
		pTop.setRadius(30);
		pTop.setAllBackgroundColor(new Color(255, 255, 255, 0));
		pTop.setBorder(new EmptyBorder(10, 15, 10, 15));

		pCenter.setRadius(30);
		pCenter.setLayout(new BorderLayout());
		pCenter.setBorder(new EmptyBorder(0, 0, 0, 0));
//		pCenter.setAllBackgroundColor(Color.BLUE);

//		pMain.setAllBackgroundColor(Color.GREEN);
		pMain.setRadius(30);

		this.setLayout(new BorderLayout());

		lblGrade = new JLabel("Lớp");
		lblGrade.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
		lblGrade.setForeground(new Color(168, 169, 166));
		lblGrade.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
				position = 0;
				SwitchView("Lớp");
			}

			public void mousePressed(MouseEvent e) {

			}

			public void mouseExited(MouseEvent e) {
				lblGrade.setForeground(new Color(168, 169, 166));
			}

			public void mouseEntered(MouseEvent e) {
				lblGrade.setForeground(new Color(100, 201, 244));
			}

			public void mouseClicked(MouseEvent e) {

			}
		});

		lblSubject = new JLabel("  >  Môn học");
		lblSubject.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
		lblSubject.setForeground(new Color(168, 169, 166));
		lblSubject.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
				position = 1;
				SwitchView("  >  Môn học");
			}

			public void mousePressed(MouseEvent e) {

			}

			public void mouseExited(MouseEvent e) {
				lblSubject.setForeground(new Color(168, 169, 166));
			}

			public void mouseEntered(MouseEvent e) {
				lblSubject.setForeground(new Color(100, 201, 244));
			}

			public void mouseClicked(MouseEvent e) {

			}
		});

		lblChapter = new JLabel("  >  Chương");
		lblChapter.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
		lblChapter.setForeground(new Color(168, 169, 166));
		lblChapter.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
				position = 2;
				SwitchView("  >  Chương");
			}

			public void mousePressed(MouseEvent e) {

			}

			public void mouseExited(MouseEvent e) {
				lblChapter.setForeground(new Color(168, 169, 166));
			}

			public void mouseEntered(MouseEvent e) {
				lblChapter.setForeground(new Color(100, 201, 244));
			}

			public void mouseClicked(MouseEvent e) {

			}
		});

		lblLesson = new JLabel("  >  Bài học");
		lblLesson.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
		lblLesson.setForeground(new Color(168, 169, 166));
		lblLesson.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
				position = 3;
				SwitchView("  >  Bài học");
			}

			public void mousePressed(MouseEvent e) {

			}

			public void mouseExited(MouseEvent e) {
				lblLesson.setForeground(new Color(168, 169, 166));
			}

			public void mouseEntered(MouseEvent e) {
				lblLesson.setForeground(new Color(100, 201, 244));
			}

			public void mouseClicked(MouseEvent e) {

			}
		});

		pTop.add(lblGrade);
		pTop.add(lblSubject);
		pTop.add(lblChapter);
		pTop.add(lblLesson);

		pCenter.add(pMain, BorderLayout.CENTER);

		this.add(pTop, BorderLayout.NORTH);
		this.add(pCenter, BorderLayout.CENTER);

		pMain.setLayout(new BoxLayout(pMain, BoxLayout.Y_AXIS));

		pMain.add(gradesListView);
		pMain.add(subjectsListView);
		pMain.add(chaptersListView);
		pMain.add(lessonsListView);

		SwitchView("Lớp");

	}

	private void SwitchView(String text) {
		gradesListView.setVisible(false);
		subjectsListView.setVisible(false);
		chaptersListView.setVisible(false);
		lessonsListView.setVisible(false);

		lblGrade.setVisible(false);
		lblSubject.setVisible(false);
		lblChapter.setVisible(false);
		lblLesson.setVisible(false);
		
		lblGrade.setForeground(new Color(168, 169, 166));
		lblSubject.setForeground(new Color(168, 169, 166));
		lblChapter.setForeground(new Color(168, 169, 166));
		lblLesson.setForeground(new Color(168, 169, 166));

		switch (position) {
		case 0: {
			lblGrade.setVisible(true);
			gradesListView.setVisible(true);
			lblGrade.setForeground(new Color(100, 201, 244));
			
			lblGrade.setText("Lớp");
			
			break;
		}
		case 1: {
			lblGrade.setVisible(true);
			lblSubject.setVisible(true);
			subjectsListView.setVisible(true);
			lblSubject.setForeground(new Color(100, 201, 244));
			
			lblSubject.setText("  >  Môn học");
			
			break;
		}
		case 2: {
			lblGrade.setVisible(true);
			lblSubject.setVisible(true);
			lblChapter.setVisible(true);
			chaptersListView.setVisible(true);
			lblChapter.setForeground(new Color(100, 201, 244));
			
			lblChapter.setText("  >  Chương");
			
			break;
		}
		case 3: {
			lblGrade.setVisible(true);
			lblSubject.setVisible(true);
			lblChapter.setVisible(true);
			lblLesson.setVisible(true);
			lessonsListView.setVisible(true);
			lblLesson.setForeground(new Color(100, 201, 244));
			
			lblLesson.setText("  >  Nội dung");
			
			break;
		}
		}

	}

	public void ItemClick(Grade grade) {
		position = 1;
		subjectsListView.createListSubject(grade);
		SwitchView(grade.getName());
		
		lblGrade.setText(grade.getName());
		
//		lblSubject.setText("  >  Môn học");
		
	}

	public void ItemClick(Subject subject) {
		position = 2;
		chaptersListView.createListChapters(subject);
		SwitchView(subject.getName());
		
		lblSubject.setText("  >  " + subject.getName());
	}

	public void ItemClick(Chapter chapter) {
		position = 3;
		lessonsListView.createListLesson(chapter);
		SwitchView(chapter.getName());
		
		lblChapter.setText("  >  " + chapter.getName());
	}

	public void ItemClick(Lesson lesson) {
	
		
	}
}
