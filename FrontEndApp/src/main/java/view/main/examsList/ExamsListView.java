package view.main.examsList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import org.trananh3010.model.Chapter;
import org.trananh3010.model.Exam;
import org.trananh3010.model.Grade;
import org.trananh3010.model.Lesson;
import org.trananh3010.model.Subject;

import controller.ChapterController;
import controller.ExamController;
import controller.GradeController;
import controller.LessonController;
import controller.SubjectsController;
import ultilities.customView.FixedStateButtonModel;
import ultilities.customView.MyCustomButton;
import ultilities.customView.MyCustomPanel;
import ultilities.customView.comboboxSuggesion.ComboBoxSuggestion;

public class ExamsListView extends MyCustomPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4432080372620639069L;
	
	private MyCustomPanel pLeft, pCenter, pMain;
	
	private ComboBoxSuggestion<Grade> cbbGrades;
	private ComboBoxSuggestion<Subject> cbbSubjects;
	private ComboBoxSuggestion<Chapter> cbbChapters;
	private ComboBoxSuggestion<Lesson> cbbLessons;

	private DefaultComboBoxModel<Grade> gradesModel;
	private DefaultComboBoxModel<Subject> subjectsModel;
	private DefaultComboBoxModel<Chapter> chaptersModel;
	private DefaultComboBoxModel<Lesson> lessonsModel;

	private List<Grade> lstGrades;
	private List<Subject> lstSubjects;
	private List<Chapter> lstChapters;
	private List<Lesson> lstLessons;
	
	private List<Exam> lstExams;

	private GradeController gradeController;
	private SubjectsController subjectsController;
	private ChapterController chapterController;
	private LessonController lessonController;
	
	private ExamController examController;
	
	private ImageIcon icFilter;

	private JScrollPane scrollPane;
	
	private GridLayout gridLayout;

	private MyCustomPanel pListExams;
	
	private ImageIcon icExam, icClock, icSee;
	
	private ExamListViewListener examListViewListener;
	
	public interface ExamListViewListener{
		void ExamItemClick(Exam exam);
	}
	
	public ExamsListView(ExamListViewListener listener) {
		this.examListViewListener = listener;
		Initialize();
	}
	
	private void Initialize() {
		this.setRadius(30);
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(15, 15, 15, 0));
		
		gradeController = new GradeController();
		subjectsController = new SubjectsController();
		chapterController = new ChapterController();
		lessonController = new LessonController();
		examController = new ExamController();
		
		icFilter = new ImageIcon(new ImageIcon("images/filter.png").getImage().getScaledInstance(16, 16, Image.SCALE_AREA_AVERAGING));
		icExam = new ImageIcon(new ImageIcon("images/exam.png").getImage().getScaledInstance(275, 150, Image.SCALE_AREA_AVERAGING));
		icClock = new ImageIcon(new ImageIcon("images/update.png").getImage().getScaledInstance(16, 16, Image.SCALE_AREA_AVERAGING));
		icSee = new ImageIcon(new ImageIcon("images/see.png").getImage().getScaledInstance(16, 16, Image.SCALE_AREA_AVERAGING));
		
		pLeft = new MyCustomPanel();
		pLeft.setRadius(30);
		pLeft.setAllBackgroundColor(Color.WHITE);
		pLeft.setPreferredSize(new Dimension(250, pLeft.getPreferredSize().height));
//		pLeft.setLayout(new BoxLayout(pLeft, BoxLayout.Y_AXIS));
		
		Box bLeft = Box.createVerticalBox();
		
		MyCustomPanel pFilter = new MyCustomPanel();
		pFilter.setRoundTopLeft(30);
		pFilter.setRoundTopRight(30);
		pFilter.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		pFilter.setLayout(new BorderLayout());
		JLabel lblFilter = new JLabel("Lọc dữ liệu");
		lblFilter.setIcon(icFilter);
		lblFilter.setFont(new Font("Leelawadee UI", Font.BOLD, 15));
		lblFilter.setForeground(Color.BLACK);
		lblFilter.setBorder(new EmptyBorder(10, 15, 15, 0));
		pFilter.add(lblFilter, BorderLayout.CENTER);
		pFilter.setPreferredSize(new Dimension(250, pFilter.getPreferredSize().height));
		
		MyCustomPanel pGrade = new MyCustomPanel();
		pGrade.setLayout(new BorderLayout());
		JLabel lblGrade = new JLabel("Lớp:");
		lblGrade.setForeground(new Color(31, 32, 29));
		lblGrade.setFont(new Font("Leelawadee UI", Font.PLAIN, 15));
		lblGrade.setBorder(new EmptyBorder(0, 15, 0, 0));
		pGrade.add(lblGrade, BorderLayout.CENTER);
		
		MyCustomPanel pCbbGrades = new MyCustomPanel();
		pCbbGrades.setLayout(new BorderLayout());
		cbbGrades = new ComboBoxSuggestion<Grade>();
		pCbbGrades.setBorder(new EmptyBorder(0, 15, 0, 15));
		pCbbGrades.add(cbbGrades, BorderLayout.CENTER);
		cbbGrades.setPreferredSize(cbbGrades.getPreferredSize());
		cbbGrades.setEditable(false);
		cbbGrades.setFocusable(false);
		cbbGrades.setForeground(new Color(31, 32, 29));
		cbbGrades.setFont(new Font("Leelawadee UI", Font.PLAIN, 15));
		gradesModel = new DefaultComboBoxModel<Grade>();
		cbbGrades.setModel(gradesModel);
		cbbGrades.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (cbbGrades.getSelectedIndex() == 0) {
					loadSubjects(null);
				}else {
					loadSubjects((Grade)cbbGrades.getSelectedItem());
				}
			}
		});
		
		MyCustomPanel pBorderGrade = new MyCustomPanel();
		MyCustomPanel pBorderGrade1 = new MyCustomPanel();
		pBorderGrade.setLayout(new BorderLayout());
		pBorderGrade.add(pBorderGrade1, BorderLayout.CENTER);
		pBorderGrade.setBorder(new EmptyBorder(0, 15, 0, 15));
		pBorderGrade1.setPreferredSize(new Dimension(pBorderGrade1.getPreferredSize().width, 1));
		pBorderGrade1.setAllBackgroundColor(Color.BLACK);
		
		MyCustomPanel pSubject = new MyCustomPanel();
		pSubject.setLayout(new BorderLayout());
		JLabel lblSubject = new JLabel("Môn học:");
		lblSubject.setFont(new Font("Leelawadee UI", Font.PLAIN, 15));
		lblSubject.setForeground(new Color(31, 32, 29));
		lblSubject.setBorder(new EmptyBorder(0, 15, 0, 0));
		pSubject.add(lblSubject, BorderLayout.CENTER);
		
		MyCustomPanel pCbbSubject = new MyCustomPanel();
		pCbbSubject.setLayout(new BorderLayout());
		cbbSubjects = new ComboBoxSuggestion<Subject>();
		pCbbSubject.setBorder(new EmptyBorder(0, 15, 0, 15));
		pCbbSubject.add(cbbSubjects, BorderLayout.CENTER);
		cbbSubjects.setPreferredSize(cbbGrades.getPreferredSize());
		cbbSubjects.setEditable(false);
		cbbSubjects.setFocusable(false);
		cbbSubjects.setForeground(new Color(31, 32, 29));
		cbbSubjects.setFont(new Font("Leelawadee UI", Font.PLAIN, 15));
		subjectsModel = new DefaultComboBoxModel<Subject>();
		cbbSubjects.setModel(subjectsModel);
		cbbSubjects.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (cbbSubjects.getSelectedIndex() == 0) {
					loadChapters(null);
				}else {
					loadChapters((Subject)cbbSubjects.getSelectedItem());
				}
			}
		});
		
		MyCustomPanel pBorderSubject = new MyCustomPanel();
		MyCustomPanel pBorderSubject1 = new MyCustomPanel();
		pBorderSubject.setLayout(new BorderLayout());
		pBorderSubject.add(pBorderSubject1, BorderLayout.CENTER);
		pBorderSubject.setBorder(new EmptyBorder(0, 15, 0, 15));
		pBorderSubject1.setPreferredSize(new Dimension(pBorderSubject1.getPreferredSize().width, 1));
		pBorderSubject1.setAllBackgroundColor(Color.BLACK);
		
		MyCustomPanel pChapter = new MyCustomPanel();
		pChapter.setLayout(new BorderLayout());
		JLabel lblChapter = new JLabel("Chương:");
		lblChapter.setFont(new Font("Leelawadee UI", Font.PLAIN, 15));
		lblChapter.setBorder(new EmptyBorder(0, 15, 0, 0));
		pChapter.add(lblChapter, BorderLayout.CENTER);
		
		MyCustomPanel pCbbChapter = new MyCustomPanel();
		pCbbChapter.setLayout(new BorderLayout());
		cbbChapters = new ComboBoxSuggestion<Chapter>();
		pCbbChapter.setBorder(new EmptyBorder(0, 15, 0, 15));
		pCbbChapter.add(cbbChapters, BorderLayout.CENTER);
		cbbChapters.setPreferredSize(cbbGrades.getPreferredSize());
		cbbChapters.setEditable(false);
		cbbChapters.setFocusable(false);
		cbbChapters.setForeground(new Color(31, 32, 29));
		cbbChapters.setFont(new Font("Leelawadee UI", Font.PLAIN, 15));
		chaptersModel = new DefaultComboBoxModel<Chapter>();
		cbbChapters.setModel(chaptersModel);
		cbbChapters.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (cbbChapters.getSelectedIndex() == 0) {
					loadLessons(null);;
				}else {
					loadLessons((Chapter)cbbChapters.getSelectedItem());
				}
			}
		});
		
		MyCustomPanel pBorderChapter = new MyCustomPanel();
		MyCustomPanel pBorderChapter1 = new MyCustomPanel();
		pBorderChapter.setLayout(new BorderLayout());
		pBorderChapter.add(pBorderChapter1, BorderLayout.CENTER);
		pBorderChapter.setBorder(new EmptyBorder(0, 15, 0, 15));
		pBorderChapter1.setPreferredSize(new Dimension(pBorderChapter1.getPreferredSize().width, 1));
		pBorderChapter1.setAllBackgroundColor(Color.BLACK);
		
		MyCustomPanel pLesson = new MyCustomPanel();
		pLesson.setLayout(new BorderLayout());
		JLabel lblLesson = new JLabel("Nội dung:");
		lblLesson.setFont(new Font("Leelawadee UI", Font.PLAIN, 15));
		lblLesson.setBorder(new EmptyBorder(0, 15, 0, 0));
		pLesson.add(lblLesson, BorderLayout.CENTER);
		
		MyCustomPanel pCbbLesson = new MyCustomPanel();
		pCbbLesson.setLayout(new BorderLayout());
		cbbLessons = new ComboBoxSuggestion<Lesson>();
		pCbbLesson.setBorder(new EmptyBorder(0, 15, 0, 15));
		pCbbLesson.add(cbbLessons, BorderLayout.CENTER);
		cbbLessons.setPreferredSize(cbbGrades.getPreferredSize());
		cbbLessons.setEditable(false);
		cbbLessons.setFocusable(false);
		cbbLessons.setForeground(new Color(31, 32, 29));
		cbbLessons.setFont(new Font("Leelawadee UI", Font.PLAIN, 15));
		lessonsModel = new DefaultComboBoxModel<Lesson>();
		cbbLessons.setModel(lessonsModel);
		cbbLessons.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (cbbGrades.getItemAt(0) != null && cbbSubjects.getItemAt(0) != null && cbbChapters.getItemAt(0) != null && cbbLessons.getItemAt(0) != null) {
					loadExams();
				}
			}
		});
		
		MyCustomPanel pBorderLesson = new MyCustomPanel();
		MyCustomPanel pBorderLesson1 = new MyCustomPanel();
		pBorderLesson.setLayout(new BorderLayout());
		pBorderLesson.add(pBorderLesson1, BorderLayout.CENTER);
		pBorderLesson.setBorder(new EmptyBorder(0, 15, 0, 15));
		pBorderLesson1.setPreferredSize(new Dimension(pBorderLesson1.getPreferredSize().width, 1));
		pBorderLesson1.setAllBackgroundColor(Color.BLACK);
		
		bLeft.add(pFilter);
		bLeft.add(Box.createVerticalGlue());
		bLeft.add(Box.createVerticalStrut(20));
		bLeft.add(pGrade);
		bLeft.add(Box.createVerticalStrut(5));
		bLeft.add(pCbbGrades);
		bLeft.add(Box.createVerticalStrut(20));
		bLeft.add(pBorderGrade);
		bLeft.add(Box.createVerticalStrut(20));
		bLeft.add(pSubject);
		bLeft.add(Box.createVerticalStrut(5));
		bLeft.add(pCbbSubject);
		bLeft.add(Box.createVerticalStrut(20));
		bLeft.add(pBorderSubject);
		bLeft.add(Box.createVerticalStrut(20));
		bLeft.add(pChapter);
		bLeft.add(Box.createVerticalStrut(5));
		bLeft.add(pCbbChapter);
		bLeft.add(Box.createVerticalStrut(20));
		bLeft.add(pBorderChapter);
		bLeft.add(Box.createVerticalStrut(20));
		bLeft.add(pLesson);
		bLeft.add(Box.createVerticalStrut(5));
		bLeft.add(pCbbLesson);
		bLeft.add(Box.createVerticalStrut(20));
		bLeft.add(pBorderLesson);
		bLeft.add(Box.createVerticalStrut(20));
		
		pLeft.add(bLeft);
	
		pCenter = new MyCustomPanel();
		pCenter.setRadius(30);
		pCenter.setBorder(new EmptyBorder(0, 15, 0, 15));
		pCenter.setLayout(new BorderLayout());
		
		pMain = new MyCustomPanel();
		pMain.setRadius(30);
		pMain.setAllBackgroundColor(Color.WHITE);
		pMain.setLayout(new BorderLayout());
		pMain.setBorder(new EmptyBorder(15, 15, 15, 0));
		
		pListExams = new MyCustomPanel();
		pListExams.setAllBackgroundColor(Color.WHITE);
		pListExams.setBorder(new EmptyBorder(0, 0, 0, 15));
		
		gridLayout = new GridLayout(0,3);
		
		pListExams.setLayout(gridLayout);
		
		scrollPane = new JScrollPane(pListExams, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setOpaque(true);
		
		pMain.add(scrollPane, BorderLayout.CENTER);
		
		pCenter.add(pMain, BorderLayout.CENTER);
		
		this.add(pLeft, BorderLayout.WEST);
		this.add(pCenter, BorderLayout.CENTER);
		
		LoadGrade();
		
//		loadExams();
	}
	
	private void LoadGrade() {
		gradesModel.removeAllElements();
		subjectsModel.removeAllElements();
		chaptersModel.removeAllElements();
		lessonsModel.removeAllElements();
		
		lstGrades = new ArrayList<Grade>();
		lstSubjects = new ArrayList<Subject>();
		lstChapters = new ArrayList<Chapter>();
		lstLessons = new ArrayList<Lesson>();
		
		lstGrades = gradeController.getAllGrades();
		
		gradesModel.addElement(new Grade("1", "Tất cả", ""));
		
		if (lstGrades != null && lstGrades.size() > 0) {
			for (Grade grade : lstGrades) {
				gradesModel.addElement(grade);
			}
		}
		
//		lstExams = examController.getAllExams();
//		loadExams();
	}
	
	private void loadSubjects(Grade grade) {
		subjectsModel.removeAllElements();
		chaptersModel.removeAllElements();
		lessonsModel.removeAllElements();

		lstSubjects = new ArrayList<Subject>();
		lstChapters = new ArrayList<Chapter>();
		lstLessons = new ArrayList<Lesson>();

		subjectsModel.addElement(new Subject("1", "Tất cả", "", new Grade("1")));
		
		if (grade != null) {
			lstSubjects = subjectsController.getSubjectsByGrade(grade.getId());
		}else {
			lstSubjects = subjectsController.getAllSubjects();
		}
		if (lstSubjects != null && lstSubjects.size() > 0) {
			for (Subject subject : lstSubjects) {
				subjectsModel.addElement(subject);
			}
		}

	}
	
	private void loadChapters(Subject subject) {
		chaptersModel.removeAllElements();
		lessonsModel.removeAllElements();

		lstChapters = new ArrayList<Chapter>();
		lstLessons = new ArrayList<Lesson>();
		
		chaptersModel.addElement(new Chapter("1", "Tất cả", "", new Subject("1")));
		
		if (subject != null) {
			lstChapters = chapterController.getChaptersBySubject(subject.getId());
		}else {
			lstChapters = chapterController.getAllChapters();
		}
		
		if (lstChapters != null && lstChapters.size() > 0) {
			for (Chapter chapter : lstChapters) {
				chaptersModel.addElement(chapter);
			}
		}
	}
	
	private void loadLessons(Chapter chapter) {
		lessonsModel.removeAllElements();

		lstLessons = new ArrayList<Lesson>();

		lessonsModel.addElement(new Lesson("1", "Tất cả", "", new Chapter("1")));
		
		if (chapter != null) {
			lstLessons = lessonController.getLessonByChapter(chapter.getId());
		}else {
			lstLessons = lessonController.getAllLesson();
		}
		if (lstLessons != null && lstLessons.size() > 0) {
			for (Lesson lesson : lstLessons) {
				lessonsModel.addElement(lesson);
			}
			cbbLessons.setSelectedIndex(0);
		}
	}
	
	private void loadExams() {
		
		System.out.println("Chạy Chạy Chạy Chạy Chạy");
		
		pListExams.setVisible(false);
		pListExams.removeAll();
		gridLayout.setVgap(15);
		gridLayout.setHgap(15);
		
		lstExams = new ArrayList<Exam>();
		
		if (cbbLessons.getSelectedIndex() == 0) {
			if (cbbChapters.getSelectedIndex() == 0) {
				if (cbbSubjects.getSelectedIndex() == 0) {
					if (cbbGrades.getSelectedIndex() == 0) {
						lstExams = examController.getAllExams();
					}else {
						Grade grade = (Grade) cbbGrades.getSelectedItem();
						lstExams = examController.getExamsByGrade(grade.getId());
					}
				}else {
					Subject subject = (Subject) cbbSubjects.getSelectedItem();
					lstExams = examController.getExamsBySubject(subject.getId());
				}
			}else {
				Chapter chapter = (Chapter) cbbChapters.getSelectedItem();
				lstExams = examController.getExamsByChapter(chapter.getId());
			}
		}else {
			Lesson lesson = (Lesson) cbbLessons.getSelectedItem();
			lstExams = examController.getExamsByLesson(lesson.getId());
		}
		
		if (lstExams != null && lstExams.size() > 0) {
			for (Exam e : lstExams) {
				final Exam exam = e;
				
				MyCustomPanel p0 = new MyCustomPanel();
				p0.setAllBackgroundColor(Color.WHITE);
				p0.setLayout(new BorderLayout());
				p0.setRadius(30);
				
				MyCustomPanel p1 = new MyCustomPanel();
				p1.setRadius(30);
				p1.setAllBackgroundColor(new Color(233, 233, 230));
				p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
				p1.setBorder(new EmptyBorder(15, 15, 15, 15));
				
				JLabel lblImage = new JLabel();
				if (exam.getImage() != null && !exam.getImage().trim().equals("")) {
					try {
						File file = new File(exam.getImage());
						Image image = ImageIO.read(file);
						image = image.getScaledInstance(275, 150, Image.SCALE_AREA_AVERAGING);
						lblImage.setIcon(new ImageIcon(image));
					} catch (IOException e1) {
						lblImage.setIcon(icExam);
						e1.printStackTrace();
					}
				}else
					lblImage.setIcon(icExam);
				
				Box bImage = Box.createHorizontalBox();
				bImage.add(Box.createHorizontalGlue());
				bImage.add(lblImage);
				bImage.add(Box.createHorizontalGlue());
				
				JTextArea lblName = new JTextArea(exam.getName());
				lblName.setWrapStyleWord(true);
				lblName.setLineWrap(true);
				lblName.setFont(new Font("Leelawadee UI", Font.BOLD, 15));
				lblName.setBackground(new Color(233, 233, 230));
				lblName.setOpaque(true);
				lblName.setEditable(false);
				lblName.setFocusable(false);
				Box bName = Box.createHorizontalBox();
				bName.add(lblName);
				bName.add(Box.createHorizontalGlue());
				
				MyCustomButton btnDetails = new MyCustomButton();
				btnDetails.setText("Xem chi tiết");
				btnDetails.setFont(new Font("Leelawadee UI", Font.BOLD, 15));
				btnDetails.setBackground(new Color(65, 145, 222));
				btnDetails.setModel(new FixedStateButtonModel());
				btnDetails.setBorder(new EmptyBorder(5,15,5,15));
				btnDetails.setRadius(10);
				btnDetails.setForeground(Color.WHITE);
				btnDetails.setFocusPainted(false);
				btnDetails.setIcon(icSee);
				btnDetails.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						super.mouseReleased(e);
						examListViewListener.ExamItemClick(exam);
					}
				});
				
				String date = exam.getUpdatedAt().getDate() + "/" + exam.getUpdatedAt().getMonth() + "/" + (exam.getUpdatedAt().getYear() + 1900);
				JLabel lblUpdateAt = new JLabel(date);
				lblUpdateAt.setFont(new Font("Leelawadee UI", Font.PLAIN, 15));
				lblUpdateAt.setIcon(icClock);
				
				Box bBtn = Box.createHorizontalBox();
				bBtn.add(btnDetails);
				bBtn.add(Box.createHorizontalGlue());
				bBtn.add(lblUpdateAt);
				
				p1.add(bImage);
				p1.add(Box.createVerticalStrut(10));
				p1.add(bName);
				p1.add(Box.createVerticalStrut(10));
				p1.add(bBtn);
				
				p0.add(p1, BorderLayout.NORTH);
				
				pListExams.add(p0);
			}
		}
		pListExams.setVisible(true);
	}
	
}
