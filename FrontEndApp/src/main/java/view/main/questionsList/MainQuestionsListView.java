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
import org.trananh3010.model.Question;
import org.trananh3010.model.Subject;

import ultilities.customView.MyCustomPanel;
import view.main.questionsList.ChaptersListView.ChapterListViewListener;
import view.main.questionsList.GradesListView.GradesListListener;
import view.main.questionsList.LessonsListView.LessonsListViewListener;
import view.main.questionsList.QuestionsListView.QuestionListViewListener;
import view.main.questionsList.SubjectListView.SubjectsListListener;

public class MainQuestionsListView extends MyCustomPanel implements GradesListListener, SubjectsListListener, ChapterListViewListener, LessonsListViewListener, QuestionListViewListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6875588032818047067L;

	private MyCustomPanel pTop, pCenter, pMain;

	private int position = 0;
	
	private JLabel lblBack;

	private GradesListView gradesListView;
	private SubjectListView subjectsListView;
	private ChaptersListView chaptersListView;
	private LessonsListView lessonsListView;
	private QuestionsListView questionsListView;

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
		questionsListView = new QuestionsListView(this);

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
//		pCenter.setAllBackgroundColor(Color.BLUE);

//		pMain.setAllBackgroundColor(Color.GREEN);
		pMain.setRadius(30);

		this.setLayout(new BorderLayout());

		pCenter.add(pMain, BorderLayout.CENTER);

		this.add(pTop, BorderLayout.NORTH);
		this.add(pCenter, BorderLayout.CENTER);

		pMain.setLayout(new BoxLayout(pMain, BoxLayout.Y_AXIS));

		pMain.add(gradesListView);
		pMain.add(subjectsListView);
		pMain.add(chaptersListView);
		pMain.add(lessonsListView);
		pMain.add(questionsListView);

		SwitchView();

	}

	private void SwitchView() {
		gradesListView.setVisible(false);
		subjectsListView.setVisible(false);
		chaptersListView.setVisible(false);
		lessonsListView.setVisible(false);
		questionsListView.setVisible(false);
		lblBack.setVisible(true);

		switch (position) {
		case 0: {
			gradesListView.setVisible(true);
			lblBack.setVisible(false);
			break;
		}
		case 1: {
			subjectsListView.setVisible(true);
			break;
		}
		case 2: {
			chaptersListView.setVisible(true);
			break;
		}
		case 3: {
			lessonsListView.setVisible(true);
			break;
		}
		case 4: {
			questionsListView.setVisible(true);
			break;
		}
		}

	}

	public void ItemClick(Grade grade) {
		position = 1;
		subjectsListView.createListSubject(grade);
		SwitchView();
	}

	public void ItemClick(Subject subject) {
		position = 2;
		chaptersListView.createListChapters(subject);
		SwitchView();
	}

	public void ItemClick(Chapter chapter) {
		position = 3;
		lessonsListView.createListLesson(chapter);
		SwitchView();
	}

	public void ItemClick(Lesson lesson) {
		position = 4;
		questionsListView.createListQuestions(lesson);
		SwitchView();
	}

	public void ItemClick(Question question) {
		// TODO Auto-generated method stub
		
	}
}
