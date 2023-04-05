package view.main.addExams;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import org.trananh3010.model.Chapter;
import org.trananh3010.model.Lesson;
import org.trananh3010.model.Question;
import org.trananh3010.model.Subject;
import org.w3c.dom.stylesheets.LinkStyle;

import controller.ChapterController;
import controller.LessonController;
import controller.QuestionController;
import ultilities.customView.MyCustomPanel;
import ultilities.customView.comboboxSuggesion.ComboBoxSuggestion;

public class AddExamView extends MyCustomPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2495142286261445768L;
	
	private GridLayout gridLayout;
	
	private MyCustomPanel pTop, pCenter, pLeft, pRight;
	
	private JScrollPane scrollPane1, scrollPane2;
	
	private ComboBoxSuggestion<Chapter> cbbChapters;
	
	private ComboBoxSuggestion<Lesson> cbbLessons;
	
	private DefaultComboBoxModel<Chapter> modelChapters;
	
	private DefaultComboBoxModel<Lesson> modelLessons;
	
	private List<Chapter> lstChapters;
	
	private List<Lesson> lstLessons;
	
	private ChapterController chapterController;
	
	private LessonController lessonController;
	
	private QuestionController questionController;
	
	private Subject subject;
	
	private MyCustomPanel pListQuestions;
	
	private List<Question> lstQuestionsInExam, lstQuestions;
	
	public AddExamView(Subject subject) {
		this.subject = subject;
		Initialize();
	}
	
	private void Initialize() {
		this.setRadius(30);
		this.setLayout(new BorderLayout());
		
		chapterController = new ChapterController();
		lessonController = new LessonController();
		questionController = new QuestionController();
		
		pTop = new MyCustomPanel();
		pTop.setBorder(new EmptyBorder(0, 0, 15, 0));
		JLabel lblTitle = new JLabel("Tạo đề thi");
		lblTitle.setFont(new Font("Leelawadee UI", Font.BOLD, 20));
		lblTitle.setForeground(Color.BLACK);
		pTop.add(lblTitle);
		
		gridLayout = new GridLayout(1, 2);
		gridLayout.setVgap(15);
		gridLayout.setHgap(15);
		
		pCenter = new MyCustomPanel();
		pCenter.setLayout(gridLayout);
		pCenter.setBorder(new EmptyBorder(0, 15, 15, 15));
		
		MyCustomPanel pLeftTop = new MyCustomPanel();
		JLabel lblTitleLeft = new JLabel("Ngân hàng câu hỏi");
		lblTitleLeft.setFont(new Font("Leelawadee UI", Font.BOLD, 15));
		pLeftTop.add(lblTitleLeft);
		
		MyCustomPanel pLeftCenter = new MyCustomPanel();
		pLeftCenter.setRadius(30);
		pLeftCenter.setLayout(new BorderLayout());
		
		MyCustomPanel pLeftCenterTop = new MyCustomPanel();
		pLeftCenterTop.setLayout(new BoxLayout(pLeftCenterTop, BoxLayout.Y_AXIS));
		pLeftCenterTop.setBorder(new EmptyBorder(0, 15, 15, 15));
		
		modelChapters = new DefaultComboBoxModel<Chapter>();
		cbbChapters = new ComboBoxSuggestion<Chapter>();
		cbbChapters.setModel(modelChapters);
		cbbChapters.setFont(new Font("Leelawadee UI", Font.PLAIN, 14));
		cbbChapters.setEditable(false);
		cbbChapters.setFocusable(false);
		cbbChapters.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (cbbChapters.getSelectedIndex() == 0) {
					loadlessons(null);
				}else {
					loadlessons((Chapter)cbbChapters.getSelectedItem());
				}
			}
		});
		
		JLabel lblChapter = new JLabel("Chương:    ");
		lblChapter.setFont(new Font("Leelawadee UI", Font.BOLD, 14));
		
		MyCustomPanel pChapter = new MyCustomPanel();
		pChapter.setLayout(new BoxLayout(pChapter, BoxLayout.X_AXIS));
		pChapter.add(lblChapter);
		pChapter.add(cbbChapters);
	
		modelLessons = new DefaultComboBoxModel<Lesson>();
		cbbLessons = new ComboBoxSuggestion<Lesson>();
		cbbLessons.setModel(modelLessons);
		cbbLessons.setFont(new Font("Leelawadee UI", Font.PLAIN, 14));
		cbbLessons.setEditable(false);
		cbbLessons.setFocusable(false);
		cbbLessons.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				loadQuestions(lstQuestionsInExam);
			}
		});
		
		JLabel lblLessons = new JLabel("Nội dung:  ");
		lblLessons.setFont(new Font("Leelawadee UI", Font.BOLD, 14));
		
		MyCustomPanel pLesson = new MyCustomPanel();
		pLesson.setLayout(new BoxLayout(pLesson, BoxLayout.X_AXIS));
		pLesson.add(lblLessons);
		pLesson.add(cbbLessons);
		
		pLeftCenterTop.add(pChapter);
		pLeftCenterTop.add(Box.createVerticalStrut(10));
		pLeftCenterTop.add(pLesson);
		
		pListQuestions = new MyCustomPanel();
		pListQuestions.setAllBackgroundColor(Color.WHITE);
		pListQuestions.setLayout(new BoxLayout(pListQuestions, BoxLayout.Y_AXIS));
		pListQuestions.setBorder(new EmptyBorder(0, 0, 0, 15));
		
		modelChapters.addElement(new Chapter("", "aaaaaaaaaa", "", null));
		modelChapters.addElement(new Chapter("", "aaaaaaaaaabbbbbbbbbbb", "", null));
		modelChapters.addElement(new Chapter("", "aaaaaaaaaabbbbbbbbbbcccccccc", "", null));
		modelChapters.addElement(new Chapter("", "aaaaaaaaaabbbbbbbbbbcccccccccccddddddddd", "", null));
		
		modelLessons.addElement(new Lesson("", "aaaaaaaaaa", "", null));
		modelLessons.addElement(new Lesson("", "aaaaaaaaaacccccccccc", "", null));
		modelLessons.addElement(new Lesson("", "aaaaaaaaaaccccccccccccccc", "", null));
		modelLessons.addElement(new Lesson("", "aaaaaaaaaaccccccccvvvvvvvvvvvvv", "", null));
		
		scrollPane1 = new JScrollPane(pListQuestions, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane1.setBorder(new EmptyBorder(0, 15, 15, 0));
		scrollPane1.setOpaque(true);
		scrollPane1.setBackground(Color.WHITE);
		
		pLeftCenter.add(pLeftCenterTop, BorderLayout.NORTH);
		pLeftCenter.add(scrollPane1, BorderLayout.CENTER);
		
		pLeft = new MyCustomPanel();
		pLeft.setAllBackgroundColor(Color.WHITE);
		pLeft.setRadius(30);
		pLeft.setLayout(new BorderLayout());
		pLeft.add(pLeftTop, BorderLayout.NORTH);
		pLeft.add(pLeftCenter, BorderLayout.CENTER);
		
		pRight = new MyCustomPanel();
		pRight.setAllBackgroundColor(Color.WHITE);
		pRight.setRadius(30);
		
		scrollPane2 = new JScrollPane(pRight, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane2.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane2.setOpaque(true);
		
		pCenter.add(pLeft);
		pCenter.add(pRight);
		
		this.add(pTop, BorderLayout.NORTH);
		this.add(pCenter, BorderLayout.CENTER);
		
		loadChapter(subject);
	}
	
	public void loadQuestions(List<Question> lstQ) {
		lstQuestionsInExam = lstQ;
		pListQuestions.setVisible(false);
		pListQuestions.removeAll();
		Lesson lesson = (Lesson)cbbLessons.getSelectedItem();
		if (lesson == null || lesson.getChapter() == null) {
			lstQuestions = questionController.getAllQuestions();
		}else {
			lstQuestions = questionController.getQuestionsByLesson(lesson.getId());
		}
		if (lstQuestions != null && lstQuestions.size() > 0) {
			
			for (int i = 0; i < lstQuestions.size(); i++) {
				final Question question = lstQuestions.get(i);
				
				System.out.println(question.getContent());
				
				MyCustomPanel p0 = new MyCustomPanel();
				p0.setRadius(30);
				p0.setAllBackgroundColor(Color.WHITE);
				p0.setLayout(new BorderLayout());
				p0.setBorder(new EmptyBorder(0, 0, 15, 0));
				
				MyCustomPanel p1 = new MyCustomPanel();
				p1.setRadius(30);
				p1.setAllBackgroundColor(new Color(233, 233, 230));
				p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
				p1.setBorder(new EmptyBorder(15, 15, 15, 15));
				
				Box bTitle = Box.createHorizontalBox();
				JLabel lbltitle = new JLabel("Câu " + (i + 1) + ":");
				lbltitle.setFont(new Font("Leelawadee UI", Font.BOLD, 14));
				bTitle.add(lbltitle);
				bTitle.add(Box.createHorizontalGlue());
				
				Box bContent = Box.createHorizontalBox();
				JTextArea lblContent = new JTextArea(question.getContent());
				lblContent.setFont(new Font("Leelawadee UI", Font.PLAIN, 14));
				lblContent.setWrapStyleWord(true);
				lblContent.setLineWrap(true);
				lblContent.setBackground(new Color(233, 233, 230));
				bContent.add(lblContent);
				bContent.add(Box.createHorizontalGlue());
				
				p1.add(bTitle);
				p1.add(Box.createVerticalStrut(10));
				p1.add(bContent);
				
				p0.add(p1, BorderLayout.NORTH);
				
				pListQuestions.add(p0);
			}
		}
		pListQuestions.setVisible(true);
	}
	
	private void loadChapter(Subject subject) {
		modelChapters.removeAllElements();
		if (subject.getGrade() == null) {
			lstChapters = chapterController.getAllChapters();
		}else {
			lstChapters = chapterController.getChaptersBySubject(subject.getId());
		}
		modelChapters.addElement(new Chapter("", "Tất cả", "", null));
		if (lstChapters != null && lstChapters.size() > 0) {
			for (Chapter chapter : lstChapters) {
				modelChapters.addElement(chapter);
			}
		}
	}
	
	private void loadlessons(Chapter chapter) {
		modelLessons.removeAllElements();
		if (chapter == null || chapter.getSubject() == null) {
			lstLessons = lessonController.getAllLesson();
		}else {
			lstLessons = lessonController.getLessonByChapter(chapter.getId());
		}
		modelLessons.addElement(new Lesson("", "Tất cả", "", null));
		if (lstLessons != null && lstLessons.size() > 0) {
			for (Lesson lesson : lstLessons) {
				modelLessons.addElement(lesson);
			}
		}	
	}
	
}
