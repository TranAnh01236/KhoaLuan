package view.main.addQuestions;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.trananh3010.model.Answer;
import org.trananh3010.model.Chapter;
import org.trananh3010.model.Grade;
import org.trananh3010.model.Lesson;
import org.trananh3010.model.Question;
import org.trananh3010.model.Subject;
import org.trananh3010.model.User;
import org.trananh3010.ultilities.MyHttpResponse;

import controller.ChapterController;
import controller.GradeController;
import controller.LessonController;
import controller.QuestionController;
import controller.SubjectsController;
import jaco.mp3.player.MP3Player;
import jnafilechooser.api.JnaFileChooser;
import ultilities.Constants;
import ultilities.customView.FixedStateButtonModel;
import ultilities.customView.MyCustomButton;
import ultilities.customView.MyCustomPanel;
import ultilities.customView.comboboxSuggesion.ComboBoxSuggestion;
import view.AppFrame.MainFrame;

public class AddQuestionView extends MyCustomPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8704573458541009928L;

	private MyCustomPanel pMain;

	private Box bTop, bCenter, bMain;

	private ComboBoxSuggestion<Grade> cbbGrades;
	private ComboBoxSuggestion<Subject> cbbSubjects;
	private ComboBoxSuggestion<Chapter> cbbChapters;
	private ComboBoxSuggestion<Lesson> cbbLessons;

	private JScrollPane scrollPane;

	private DefaultComboBoxModel<Grade> gradesModel;
	private DefaultComboBoxModel<Subject> subjectsModel;
	private DefaultComboBoxModel<Chapter> chaptersModel;
	private DefaultComboBoxModel<Lesson> lessonsModel;

	private List<Grade> lstGrades;
	private List<Subject> lstSubjects;
	private List<Chapter> lstChapters;
	private List<Lesson> lstLessons;

	private GradeController gradeController;
	private SubjectsController subjectsController;
	private ChapterController chapterController;
	private LessonController lessonController;

	private ImageIcon icVolume, icImage, icCross, icStart, icPause, icStop, icPlus;

	private Box bImage, bAudio;

	private MyCustomPanel pAnswers;

	private MainFrame frame;

	private List<String> imageURLs;

	private List<File> audioFiles;

	private List<Answer> lstAnswers;

	private JTextArea txtQuestion;
	
	private JLabel lblAnswerQuantity;
	
	private JRadioButton radOneAnswer, radMultiAnswers;
	private ButtonGroup questionTypeGroup;
	
	private ButtonGroup rightAnswerGroup;
	
	private JLabel lblError;
	
	private JTextArea txtExplain;
	
	private QuestionController questionController;

	public AddQuestionView(MainFrame frame) {
		this.frame = frame;
		Initialize();
	}

	private void Initialize() {

		rightAnswerGroup = new ButtonGroup();
		
		imageURLs = new ArrayList<String>();
		audioFiles = new ArrayList<File>();

		lstAnswers = new ArrayList<Answer>();

		icStart = new ImageIcon(
				new ImageIcon("images/start.png").getImage().getScaledInstance(18, 18, Image.SCALE_AREA_AVERAGING));
		icPause = new ImageIcon(
				new ImageIcon("images/pause.png").getImage().getScaledInstance(18, 18, Image.SCALE_AREA_AVERAGING));
		icStop = new ImageIcon(
				new ImageIcon("images/stop.png").getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING));
		icVolume = new ImageIcon(
				new ImageIcon("images/volume.png").getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING));
		icImage = new ImageIcon(
				new ImageIcon("images/image.png").getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING));
		icCross = new ImageIcon(
				new ImageIcon("images/cross.png").getImage().getScaledInstance(15, 15, Image.SCALE_AREA_AVERAGING));
		icPlus = new ImageIcon(
				new ImageIcon("images/plus.png").getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING));

		lstGrades = new ArrayList<Grade>();
		lstSubjects = new ArrayList<Subject>();
		lstChapters = new ArrayList<Chapter>();
		lstLessons = new ArrayList<Lesson>();

		gradeController = new GradeController();
		subjectsController = new SubjectsController();
		chapterController = new ChapterController();
		lessonController = new LessonController();

		this.setRadius(30);
		this.setLayout(new BorderLayout());

		pMain = new MyCustomPanel();
		pMain.setAllBackgroundColor(new Color(233, 233, 230));
		pMain.setLayout(new BorderLayout());
//		pMain.setLayout(new BoxLayout(pMain, BoxLayout.Y_AXIS));
		pMain.setRadius(30);
		pMain.setBorder(new EmptyBorder(15, 15, 15, 15));

		bMain = Box.createVerticalBox();

		bTop = Box.createVerticalBox();

		cbbGrades = new ComboBoxSuggestion<Grade>();
		cbbGrades.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
		cbbGrades.setEditable(false);
		Box b1 = Box.createHorizontalBox();
		JLabel lblGrades = new JLabel("Lớp:");
		lblGrades.setPreferredSize(new Dimension(120, lblGrades.getPreferredSize().height));
		lblGrades.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
		b1.add(lblGrades);
		b1.add(cbbGrades);
		gradesModel = new DefaultComboBoxModel<Grade>();
		cbbGrades.setModel(gradesModel);
		cbbGrades.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				loadSubjects((Grade) gradesModel.getSelectedItem());
			}
		});

		cbbSubjects = new ComboBoxSuggestion<Subject>();
		cbbSubjects.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
		cbbSubjects.setEditable(false);
		Box b2 = Box.createHorizontalBox();
		JLabel lblSubjects = new JLabel("Môn học:");
		lblSubjects.setPreferredSize(new Dimension(120, lblGrades.getPreferredSize().height));
		lblSubjects.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
		b2.add(lblSubjects);
		b2.add(cbbSubjects);
		subjectsModel = new DefaultComboBoxModel<Subject>();
		cbbSubjects.setModel(subjectsModel);
		cbbSubjects.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				loadChapters((Subject) cbbSubjects.getSelectedItem());
			}
		});

		cbbChapters = new ComboBoxSuggestion<Chapter>();
		cbbChapters.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
		cbbChapters.setEditable(false);
		Box b3 = Box.createHorizontalBox();
		JLabel lblChapters = new JLabel("Chương:");
		lblChapters.setPreferredSize(new Dimension(120, lblGrades.getPreferredSize().height));
		lblChapters.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
		b3.add(lblChapters);
		b3.add(cbbChapters);
		chaptersModel = new DefaultComboBoxModel<Chapter>();
		cbbChapters.setModel(chaptersModel);
		cbbChapters.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				loadLessons((Chapter) cbbChapters.getSelectedItem());
			}
		});

		cbbLessons = new ComboBoxSuggestion<Lesson>();
		cbbLessons.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
		cbbLessons.setEditable(false);
		Box b4 = Box.createHorizontalBox();
		JLabel lblLessons = new JLabel("Nội dung:");
		lblLessons.setPreferredSize(new Dimension(120, lblGrades.getPreferredSize().height));
		lblLessons.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
		b4.add(lblLessons);
		b4.add(cbbLessons);
		lessonsModel = new DefaultComboBoxModel<Lesson>();
		cbbLessons.setModel(lessonsModel);

		bTop.add(b1);
		bTop.add(Box.createVerticalStrut(10));
		bTop.add(b2);
		bTop.add(Box.createVerticalStrut(10));
		bTop.add(b3);
		bTop.add(Box.createVerticalStrut(10));
		bTop.add(b4);
		bTop.add(Box.createVerticalStrut(20));

		Box bBtnFile = Box.createHorizontalBox();
		MyCustomButton btnFile = new MyCustomButton();
		btnFile.setText("Thêm câu hỏi từ file");
		btnFile.setRadius(10);
		btnFile.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
		btnFile.setBackground(new Color(68, 165, 0));
		btnFile.setForeground(Color.WHITE);
		btnFile.setModel(new FixedStateButtonModel());
		btnFile.setBorder(new EmptyBorder(10, 30, 10, 30));
		btnFile.setFocusPainted(false);
		btnFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
			}
		});
		bBtnFile.add(Box.createHorizontalGlue());
		bBtnFile.add(btnFile);
		
		bCenter = Box.createHorizontalBox();
		bCenter.setBackground(new Color(255, 255, 255, 0));
		bCenter.setSize(WIDTH, 100);
		bCenter.setOpaque(true);

		Box bCenterL = Box.createVerticalBox();

		JLabel lblQuestion = new JLabel("Câu hỏi:");
		lblQuestion.setPreferredSize(new Dimension(120, lblQuestion.getPreferredSize().height));
		lblQuestion.setFont(new Font("Leelawadee UI", Font.BOLD, 16));

		final MyCustomButton btnImage = new MyCustomButton();
		btnImage.setIcon(icImage);
		btnImage.setModel(new FixedStateButtonModel());
		btnImage.setFocusPainted(false);
		btnImage.setBorder(new EmptyBorder(10, 10, 10, 10));
		btnImage.setBackground(Color.WHITE);
		btnImage.setRadius(10);
		btnImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				btnImage.setBackground(new Color(65, 145, 222));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				btnImage.setBackground(Color.WHITE);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				JnaFileChooser ch = new JnaFileChooser();
				ch.setMultiSelectionEnabled(false);
				ch.addFilter("Hình ảnh", "jpg", "png");
				boolean action = ch.showOpenDialog(frame);
				if (action) {
					loadImage("images/images/" + ch.getSelectedFile().getName());
				}
			}
		});

		final MyCustomButton btnAudio = new MyCustomButton();
		btnAudio.setIcon(icVolume);
		btnAudio.setModel(new FixedStateButtonModel());
		btnAudio.setFocusPainted(false);
		btnAudio.setBorder(new EmptyBorder(10, 10, 10, 10));
		btnAudio.setBackground(Color.WHITE);
		btnAudio.setRadius(10);
		btnAudio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				btnAudio.setBackground(new Color(65, 145, 222));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				btnAudio.setBackground(Color.WHITE);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				JnaFileChooser ch = new JnaFileChooser();
				ch.setMultiSelectionEnabled(false);
				ch.addFilter("Âm thanh", "mp3", "mov");
				boolean action = ch.showOpenDialog(frame);
				if (action) {
					loadAudio(ch.getSelectedFile());
				}
			}
		});

		bCenterL.add(lblQuestion);
		bCenterL.add(Box.createVerticalStrut(20));
		bCenterL.add(btnImage);
		bCenterL.add(Box.createVerticalStrut(20));
		bCenterL.add(btnAudio);
		bCenterL.add(Box.createVerticalGlue());

		Box bCenterR = Box.createVerticalBox();
		txtQuestion = new JTextArea();
		txtQuestion.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
		txtQuestion.setBorder(new EmptyBorder(10, 10, 10, 10));
//		txtQuestion.setMaximumSize(new Dimension(txtQuestion.getMaximumSize().width, 300));
		txtQuestion.setMinimumSize(new Dimension(bCenter.getPreferredSize().width, 200));
		txtQuestion.setLineWrap(true);
		txtQuestion.setWrapStyleWord(true);

		bCenterR.add(txtQuestion);
//		bCenterR.add(Box.createVerticalGlue());

		bCenter.add(bCenterL);
		bCenter.add(bCenterR);

		bAudio = Box.createHorizontalBox();
		bAudio.setVisible(false);

		bImage = Box.createHorizontalBox();
		bImage.setVisible(false);

		Box bType = Box.createHorizontalBox();
		JLabel lblType = new JLabel("Loại câu hỏi:");
		lblType.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
		lblType.setPreferredSize(new Dimension(120, lblType.getPreferredSize().height));
		
		radOneAnswer = new JRadioButton("Một đáp án");
		radOneAnswer.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
		radOneAnswer.setFocusPainted(false);
		radOneAnswer.setBackground(new Color(233, 233, 230));
		radOneAnswer.setSelected(true);
		radOneAnswer.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				pAnswers.removeAll();
				List<Answer> lstAnswers1 = new ArrayList<Answer>(lstAnswers);
				lstAnswers.removeAll(lstAnswers);
				if (lstAnswers1 != null && lstAnswers1.size() > 0) {
					for (int i = 0; i < lstAnswers1.size(); i++) {
						lstAnswers1.get(i).setRightt(0);
					}
				}
				for(Answer answer : lstAnswers1) {
					addAnswer(answer);
				}
			}
		});
		
		radMultiAnswers = new JRadioButton("Nhiều đáp án");
		radMultiAnswers.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
		radMultiAnswers.setFocusPainted(false);
		radMultiAnswers.setBackground(new Color(233, 233, 230));
		radMultiAnswers.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
			}
		});
		
		questionTypeGroup = new ButtonGroup();
		questionTypeGroup.add(radOneAnswer);
		questionTypeGroup.add(radMultiAnswers);
		bType.add(lblType);
		bType.add(radOneAnswer);
		bType.add(Box.createHorizontalStrut(20));
		bType.add(radMultiAnswers);
		bType.add(Box.createHorizontalGlue());
		
		Box bAnswer = Box.createHorizontalBox();

		JLabel lblAnswer = new JLabel("Đáp án:");
		lblAnswer.setPreferredSize(new Dimension(120, lblAnswer.getPreferredSize().height));
		lblAnswer.setFont(new Font("Leelawadee UI", Font.BOLD, 16));

		lblAnswerQuantity = new JLabel();
		lblAnswerQuantity.setText("Số lượng: 0");
		lblAnswerQuantity.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));

		final MyCustomButton btnAddAnswer = new MyCustomButton();
		btnAddAnswer.setIcon(icPlus);
		btnAddAnswer.setFocusPainted(false);
		btnAddAnswer.setRadius(10);
		btnAddAnswer.setBackground(Color.WHITE);
		btnAddAnswer.setModel(new FixedStateButtonModel());
		btnAddAnswer.setBorder(new EmptyBorder(5, 10, 5, 10));
		btnAddAnswer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				btnAddAnswer.setBackground(new Color(68, 165, 0));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				btnAddAnswer.setBackground(Color.WHITE);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				addAnswer(new Answer());
			}
		});

		bAnswer.add(lblAnswer);
		bAnswer.add(lblAnswerQuantity);
		bAnswer.add(Box.createHorizontalStrut(20));
		bAnswer.add(btnAddAnswer);
		bAnswer.add(Box.createHorizontalGlue());

		pAnswers = new MyCustomPanel();
		pAnswers.setLayout(new BoxLayout(pAnswers, BoxLayout.Y_AXIS));

		Box bExplain = Box.createHorizontalBox();
		JLabel lblExplain = new JLabel("Giải thích:");
		lblExplain.setPreferredSize(new Dimension(120, lblExplain.getPreferredSize().height));
		lblExplain.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
		
		txtExplain = new JTextArea();
		txtExplain.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
		txtExplain.setBorder(new EmptyBorder(10,10,10,10));
		
		bExplain.add(lblExplain);
		bExplain.add(txtExplain);
		
		Box bError = Box.createHorizontalBox();
		lblError = new JLabel();
		lblError.setFont(new Font("Leelawadee UI", Font.PLAIN, 14));
		lblError.setForeground(new Color(166, 0, 0));
		bError.add(lblError);
		bError.add(Box.createHorizontalGlue());
		
		Box bBtnAdd = Box.createHorizontalBox();
		MyCustomButton btnAdd = new MyCustomButton();
		btnAdd.setText("Thêm");
		btnAdd.setRadius(10);
		btnAdd.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
		btnAdd.setBackground(new Color(68, 165, 0));
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setModel(new FixedStateButtonModel());
		btnAdd.setBorder(new EmptyBorder(10, 30, 10, 30));
		btnAdd.setFocusPainted(false);
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				saveQuestion();
			}
		});
		
		bBtnAdd.add(btnAdd);
		bBtnAdd.add(Box.createHorizontalGlue());
		
//		JButton btnbtn = new JButton("TEST");
//		btnbtn.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				super.mouseReleased(e);
//				for(Answer answer : lstAnswers) {
//					System.out.println(answer);
//				}
//			}
//		});
//		
//		JButton btnbtn1 = new JButton("Test2");
//		btnbtn1.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				super.mouseReleased(e);
//				pAnswers.removeAll();
//				List<Answer> lstAnswers1 = new ArrayList<Answer>(lstAnswers);
//				lstAnswers.removeAll(lstAnswers);
//				for(Answer answer : lstAnswers1) {
//					addAnswer(answer);
//				}
//			}
//		});
		
		bMain.add(bTop);
		bMain.add(Box.createVerticalStrut(20));
		bMain.add(bBtnFile);
		bMain.add(Box.createVerticalStrut(20));
		bMain.add(bCenter);
		bMain.add(Box.createVerticalStrut(20));
		bMain.add(bAudio);
		bMain.add(Box.createVerticalStrut(20));
		bMain.add(bImage);
		bMain.add(Box.createVerticalStrut(20));
		bMain.add(bType);
		bMain.add(Box.createVerticalStrut(20));
		bMain.add(bAnswer);
		bMain.add(Box.createVerticalStrut(20));
		bMain.add(pAnswers);
		bMain.add(Box.createVerticalStrut(20));
		bMain.add(bExplain);
		bMain.add(Box.createVerticalStrut(20));
		bMain.add(bError);
		bMain.add(Box.createVerticalStrut(20));
		bMain.add(bBtnAdd);
		bMain.add(Box.createVerticalStrut(20));
//		bMain.add(btnbtn);
//		bMain.add(Box.createVerticalStrut(20));
//		bMain.add(btnbtn1);

//		bMain.add(new JLabel(" "));
//		bMain.add(new JLabel(" "));
//		bMain.add(new JLabel(" "));
//		bMain.add(new JLabel(" "));
//		bMain.add(new JLabel(" "));
//		bMain.add(new JLabel(" "));
//		bMain.add(new JLabel(" "));
//		bMain.add(new JLabel(" "));
//		bMain.add(new JLabel("  "));
//		bMain.add(new JLabel(" "));
//		bMain.add(new JLabel(" "));
//		bMain.add(new JLabel(" "));
//		bMain.add(new JLabel(" "));
//		bMain.add(new JLabel(" "));
//		bMain.add(new JLabel(" "));
//		bMain.add(new JLabel(" "));
//		bMain.add(new JLabel(" "));
//		bMain.add(new JLabel(" "));
//		bMain.add(new JLabel(" "));
//		bMain.add(new JLabel(" "));
//		bMain.add(new JLabel(" "));

		pMain.add(bMain);

		scrollPane = new JScrollPane(pMain, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setOpaque(true);

		this.add(scrollPane);
		this.setBorder(new EmptyBorder(15, 0, 15, 0));

		LoadGrades();

	}
	
	private void saveQuestion() {
		System.out.println("aaaaa");
		Lesson lesson = (Lesson) cbbLessons.getSelectedItem();
		if (lesson == null) {
			lblError.setText("Bạn chưa chọn nội dung muốn thêm");
			return;
		}
		String content = txtQuestion.getText().trim();
		if (content.equals("")) {
			lblError.setText("Bạn chưa nhập nội dung câu hỏi");
			txtQuestion.requestFocus();
			return;
		}
		String audio = "";
		for (File file : audioFiles) {
			if (audio.equals("")) {
				audio = "audios/" + file.getName();
			}else {
				audio += ";" + "audios/" + file.getName();
			}
		}
		
		String image = "";
		for (String url : imageURLs) {
			if (image.equals("")) {
				image = url;
			}else {
				image += ";" + url;
			}
		}
		
		int type = 0;
		if (radMultiAnswers.isSelected()) {
			type = 1;
		}
		
		String explain = txtExplain.getText().trim();
		
		if (lstAnswers.size() < 2) {
			lblError.setText("Câu hỏi hợp lệ phải chứa nhiều hơn 1 đáp án");
			return;
		}
		
		List<Answer> lstAnswers1 = new ArrayList<Answer>(lstAnswers);
		
		int right1 = 0;
		
		for (Answer answer : lstAnswers1) {
			if (answer.getContent() == null || answer.getContent().trim().equals("")) {
				if ( (answer.getImage() == null || answer.getImage().trim().equals("")) && (answer.getAudio() == null || answer.getAudio().trim().equals("")) ) {
					lblError.setText("Bạn chưa nhập nội dung đáp án");
					return;
				}
			}
			if (answer.getRightt() == 1) {
				right1++;
			}
		}
		
		if (right1 == 0) {
			lblError.setText("Bạn chưa chọn câu trả lời đúng");
			return;
		}
		
		User user = Constants.getUser();
		
		Question question = new Question(null, content, type, explain, image, audio, lesson, user);
		
		for (Answer answer : lstAnswers1) {
			question.addAnswer(answer);
		}
		lblError.setText("");
		
		String message = "";
		
		questionController = new QuestionController();
		try {
			MyHttpResponse myHttpResponse = questionController.saveQuestion(question);
			message = myHttpResponse.getMessage();
			if (myHttpResponse.getStatusCode() == 200) {
				this.removeAll();
				Initialize();
				this.setVisible(false);
				this.setVisible(true);
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
			lblError.setText(message);
		} catch (InterruptedException e) {
			e.printStackTrace();
			lblError.setText(message);
		}
	}

	private void addAnswer(Answer answer) {
		
		if(answer != null) {
			lstAnswers.add(answer);
		}
		
		lblAnswerQuantity.setText("Số lượng: " + lstAnswers.size() );
		
		pAnswers.removeAll();
		pAnswers.setVisible(false);
		
		if (lstAnswers != null && lstAnswers.size() > 0) {
			
			for (int i = 0; i < lstAnswers.size(); i++) {
				
				final int index = i;
				
				lstAnswers.get(index).setContent("");
				
				final List<String> imageFiles1 = new ArrayList<String>();
				final List<String> audioFiles1 = new ArrayList<String>();
				final Box bImage1 = Box.createHorizontalBox();
				final Box bAudio1 = Box.createHorizontalBox();
				
				String str = getAnswerChar(i);
				
				MyCustomPanel pAnswer = new MyCustomPanel();
				pAnswer.setLayout(new BoxLayout(pAnswer, BoxLayout.Y_AXIS));
				pAnswer.setAllBackgroundColor(new Color(68, 165, 0));
				pAnswer.setBorder(new EmptyBorder(15, 15, 15, 15));
				pAnswer.setRadius(20);
				
				//---------------------------------------------------------------------
				
				//Answer------------------------------------------------
				Box bAnswer1 = Box.createHorizontalBox();
				JLabel lblAnswer1 = new JLabel("Câu " + str + ":");
				lblAnswer1.setForeground(Color.WHITE);
				lblAnswer1.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
				MyCustomButton btnRemove = new MyCustomButton();
				btnRemove.setText("Xóa");
				btnRemove.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
				btnRemove.setBorder(new EmptyBorder(5, 10, 5, 10));
				btnRemove.setBackground(new Color(166, 0, 0));
				btnRemove.setForeground(Color.WHITE);
				btnRemove.setRadius(10);
				btnRemove.setModel(new FixedStateButtonModel());
				btnRemove.setFocusPainted(false);
				btnRemove.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						super.mouseReleased(e);
						lstAnswers.remove(index);
						addAnswer(null);
					}
				});
				bAnswer1.add(lblAnswer1);
				bAnswer1.add(Box.createHorizontalGlue());
				bAnswer1.add(btnRemove);
				
				//Content-----------------------------------------------
				Box bContent1 = Box.createHorizontalBox();
				Box bContent1L = Box.createVerticalBox();
				Box bContent1R = Box.createVerticalBox();
				
				MyCustomButton btnAnswerImage = new MyCustomButton();
				btnAnswerImage.setIcon(icImage);
				btnAnswerImage.setFocusPainted(false);
				btnAnswerImage.setBorder(new EmptyBorder(5, 5, 5, 5));
				btnAnswerImage.setRadius(10);
				btnAnswerImage.setBackground(Color.WHITE);
				btnAnswerImage.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						super.mouseReleased(e);
						JnaFileChooser ch = new JnaFileChooser();
						ch.setMultiSelectionEnabled(false);
						ch.addFilter("Hình ảnh", "jpg", "png");
						boolean action = ch.showOpenDialog(frame);
						if(action) {
							imageFiles1.add("images/images/" + ch.getSelectedFile().getName());
							lstAnswers.get(index).setImage("");
							for (String string : imageFiles1) {
								if(lstAnswers.get(index).getImage() == null || lstAnswers.get(index).getImage().trim().equals("")) {
									lstAnswers.get(index).setImage(string);
								}else {
									lstAnswers.get(index).setImage(lstAnswers.get(index).getImage() + ";" + string);
								}
							}
							
							System.out.println(lstAnswers.get(index).getImage());
							
							//------------------------------------------------------------------------------------
							
							bImage1.removeAll();
							bImage1.setVisible(false);
							
							JLabel lblImage1 = new JLabel("Hình ảnh:");
							lblImage1.setForeground(Color.WHITE);
							lblImage1.setPreferredSize(new Dimension(120, lblImage1.getPreferredSize().height));
							lblImage1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
							bImage1.add(lblImage1);
							bImage1.setVisible(false);
							
							if (lstAnswers.get(index).getImage() != null) {
								String imageURLs1[] = lstAnswers.get(index).getImage().split(";");
								imageFiles1.removeAll(imageFiles1);
								for (int j = 0; j < imageURLs1.length; j++) {
									imageFiles1.add(imageURLs1[j]);
								}
								for (int j = 0; j < imageFiles1.size(); j++) {
									final int index1 = j;
									if (imageFiles1.get(j) != null) {
										try {
											
											final MyCustomPanel pImage2 = new MyCustomPanel();
											pImage2.setRoundBottomRight(10);
											pImage2.setRoundTopRight(10);
											pImage2.setAllBackgroundColor(Color.WHITE);
											pImage2.setLayout(new BoxLayout(pImage2, BoxLayout.X_AXIS));
											
											Image image = ImageIO.read(new File(imageFiles1.get(index1)));
											image = image.getScaledInstance(200, 100, Image.SCALE_AREA_AVERAGING);
											
											JLabel lblImage2 = new JLabel();
											lblImage2.setIcon(new ImageIcon(image));
											JLabel lblRemove1 = new JLabel();
											lblRemove1.setBorder(new EmptyBorder(0, 5, 0, 5));
											lblRemove1.setIcon(icCross);
											lblRemove1.addMouseListener(new MouseAdapter() {
												@Override
												public void mouseReleased(MouseEvent e) {
													super.mouseReleased(e);
													imageFiles1.set(index1, null);
													lstAnswers.get(index).setImage("");
													for (String string : imageFiles1) {
														if(lstAnswers.get(index).getImage() == null || lstAnswers.get(index).getImage().trim().equals("")) {
															lstAnswers.get(index).setImage(string);
														}else {
															lstAnswers.get(index).setImage(lstAnswers.get(index).getImage() + ";" + string);
														}
													}
													pImage2.setVisible(false);
													
													if (lstAnswers.get(index).getImage() == null || lstAnswers.get(index).getImage().trim().equals("")) {
														bImage1.setVisible(false);
													}
												}
											});
											
											pImage2.add(lblImage2);
											pImage2.add(lblRemove1);
											
											bImage1.add(pImage2);
											bImage1.add(Box.createHorizontalStrut(10));
											
										} catch (IOException e1) {
											e1.printStackTrace();
										}
									}
								}
								bImage1.add(Box.createHorizontalGlue());
								bImage1.setVisible(true);	
							}
							
							//------------------------------------------------------------------------------------
							
						}
					}
				});
				
				MyCustomButton btnAnswerAudio = new MyCustomButton();
				btnAnswerAudio.setIcon(icVolume);
				btnAnswerAudio.setFocusPainted(false);
				btnAnswerAudio.setBorder(new EmptyBorder(5, 5, 5, 5));
				btnAnswerAudio.setRadius(10);
				btnAnswerAudio.setBackground(Color.WHITE);
				btnAnswerAudio.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						super.mouseReleased(e);
						JnaFileChooser ch = new JnaFileChooser();
						ch.setMultiSelectionEnabled(false);
						ch.addFilter("Âm thanh", "mp3", "mov");
						boolean action = ch.showOpenDialog(frame);
						if(action) {
							audioFiles1.add("audios/" + ch.getSelectedFile().getName());
							lstAnswers.get(index).setAudio("");
							for (String string2 : audioFiles1) {
								if (lstAnswers.get(index).getAudio() == null || lstAnswers.get(index).getAudio().trim().equals("")) {
									lstAnswers.get(index).setAudio(string2);
								}else {
									lstAnswers.get(index).setAudio(lstAnswers.get(index).getAudio() + ";" + string2);
								}
							}
							
							//------------------------------------------------------------------------------------
							
							bAudio1.removeAll();
							bAudio1.setVisible(false);
							
							JLabel lblAudio1 = new JLabel("Âm thanh:");
							lblAudio1.setForeground(Color.WHITE);
							lblAudio1.setPreferredSize(new Dimension(120, lblAudio1.getPreferredSize().height));
							lblAudio1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
							bAudio1.add(lblAudio1);
							bAudio1.setVisible(false);
							
							if (lstAnswers.get(index).getAudio() != null) {
								String audioURLs1[] = lstAnswers.get(index).getAudio().trim().split(";");
								audioFiles1.removeAll(audioFiles1);
								for (int j = 0; j < audioURLs1.length; j++) {
									audioFiles1.add(audioURLs1[j]);
								}
								
								for (int j = 0; j < audioFiles1.size(); j++) {
									final int index1 = j;
									if (audioFiles1.get(index1) != null) {
										try {
											
											final MyCustomPanel pAudio2 = new MyCustomPanel();
											pAudio2.setRadius(30);
											pAudio2.setAllBackgroundColor(Color.WHITE);
											pAudio2.setLayout(new BoxLayout(pAudio2, BoxLayout.X_AXIS));
											
											final MP3Player mp3Player = new MP3Player(new File(audioFiles1.get(index1)));
											
											String strr[] = audioFiles1.get(index1).trim().split("/");
											
											JLabel lblAudio2 = new JLabel(strr[1]);
											lblAudio2.setFont(new Font("Leelawadee UI", Font.BOLD, 12));
											
											JButton btnStart1 = new JButton();
											btnStart1.setBackground(Color.WHITE);
											btnStart1.setIcon(icStart);
											btnStart1.setModel(new FixedStateButtonModel());
											btnStart1.setFocusPainted(false);
											btnStart1.setBorder(new EmptyBorder(0, 0, 0, 0));
											btnStart1.addMouseListener(new MouseAdapter() {
												@Override
												public void mouseReleased(MouseEvent e) {
													super.mouseReleased(e);
													mp3Player.play();
												}
											});
											
											JButton btnPause1 = new JButton();
											btnPause1.setBackground(Color.WHITE);
											btnPause1.setIcon(icPause);
											btnPause1.setModel(new FixedStateButtonModel());
											btnPause1.setFocusPainted(false);
											btnPause1.setBorder(new EmptyBorder(0, 0, 0, 0));
											btnPause1.addMouseListener(new MouseAdapter() {
												@Override
												public void mouseReleased(MouseEvent e) {
													super.mouseReleased(e);
													mp3Player.pause();
												}
											});
											
											JButton btnStop1 = new JButton();
											btnStop1.setBackground(Color.WHITE);
											btnStop1.setIcon(icStop);
											btnStop1.setModel(new FixedStateButtonModel());
											btnStop1.setFocusPainted(false);
											btnStop1.setBorder(new EmptyBorder(0, 0, 0, 0));
											btnStop1.addMouseListener(new MouseAdapter() {
												@Override
												public void mouseReleased(MouseEvent e) {
													super.mouseReleased(e);
													mp3Player.stop();
												}
											});
											
											JButton btnRemove1 = new JButton();
											btnRemove1.setBackground(Color.WHITE);
											btnRemove1.setIcon(icCross);
											btnRemove1.setModel(new FixedStateButtonModel());
											btnRemove1.setFocusPainted(false);
											btnRemove1.setBorder(new EmptyBorder(0, 0, 0, 0));
											btnRemove1.addMouseListener(new MouseAdapter() {
												@Override
												public void mouseReleased(MouseEvent e) {
													super.mouseReleased(e);
													audioFiles1.set(index1, null);
													lstAnswers.get(index).setAudio("");
													for (String string : audioFiles1) {
														if (lstAnswers.get(index).getAudio() == null || lstAnswers.get(index).getAudio().trim().equals("")) {
															lstAnswers.get(index).setAudio(string);
														}else {
															lstAnswers.get(index).setAudio(lstAnswers.get(index).getAudio().trim() + ";" + string);
														}
													}
													pAudio2.setVisible(false);
													if (lstAnswers.get(index).getAudio() == null || lstAnswers.get(index).getAudio().trim().equals("")) {
														bAudio1.setVisible(false);
													}
												}
											});
											
											pAudio2.add(lblAudio2);
											pAudio2.add(Box.createHorizontalStrut(30));
											pAudio2.add(btnStart1);
											pAudio2.add(Box.createHorizontalStrut(10));
											pAudio2.add(btnPause1);
											pAudio2.add(Box.createHorizontalStrut(10));
											pAudio2.add(btnStop1);
											pAudio2.add(Box.createHorizontalStrut(10));
											pAudio2.add(btnRemove1);
											
											pAudio2.setBorder(new EmptyBorder(5, 5, 5, 5));
											
											bAudio1.add(pAudio2);
											bAudio1.add(Box.createHorizontalStrut(10));
											
										} catch (Exception e2) {
											e2.printStackTrace();
										}
									}
								}
								bAudio1.add(Box.createHorizontalGlue());
								bAudio1.setVisible(true);
								
							}
						}
					}
				});
				
				bContent1L.add(btnAnswerImage);
				bContent1L.add(Box.createVerticalStrut(10));
				bContent1L.add(btnAnswerAudio);
				bContent1L.setPreferredSize(new Dimension(120, bContent1L.getPreferredSize().height));
				
				final JTextArea txtAnswerContent = new JTextArea();
				txtAnswerContent.setText(lstAnswers.get(index).getContent());
				txtAnswerContent.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
				txtAnswerContent.setBorder(new EmptyBorder(5, 5, 5, 5));
				
				txtAnswerContent.getDocument().addDocumentListener(new DocumentListener() {
					public void removeUpdate(DocumentEvent e) {
						lstAnswers.get(index).setContent(txtAnswerContent.getText());
					}
					
					public void insertUpdate(DocumentEvent e) {
						lstAnswers.get(index).setContent(txtAnswerContent.getText());
					}
					
					public void changedUpdate(DocumentEvent e) {	
						System.out.println("3 " + txtAnswerContent.getText());
					}
				});
				bContent1R.add(txtAnswerContent);
				
				bContent1.add(bContent1L);
				bContent1.add(bContent1R);
				//Audio
				
				JLabel lblAudio1 = new JLabel("Âm thanh:");
				lblAudio1.setForeground(Color.WHITE);
				lblAudio1.setPreferredSize(new Dimension(120, lblAudio1.getPreferredSize().height));
				lblAudio1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
				bAudio1.add(lblAudio1);
				bAudio1.setVisible(false);
				
				if (lstAnswers.get(index).getAudio() != null) {
					
					String audioURLs1[] = lstAnswers.get(index).getAudio().trim().split(";");
					for (int j = 0; j < audioURLs1.length; j++) {
						audioFiles1.add(audioURLs1[j]);
					}
					
					for (int j = 0; j < audioFiles1.size(); j++) {
						final int index1 = j;
						if (audioFiles1.get(index1) != null && !audioFiles1.get(index1).trim().equals("")) {
							try {
								
								final MyCustomPanel pAudio2 = new MyCustomPanel();
								pAudio2.setRadius(30);
								pAudio2.setAllBackgroundColor(Color.WHITE);
								pAudio2.setLayout(new BoxLayout(pAudio2, BoxLayout.X_AXIS));
								
								final MP3Player mp3Player = new MP3Player(new File(audioFiles1.get(index1)));
								
								String strr[] = audioFiles1.get(index1).trim().split("/");
								
								JLabel lblAudio2 = new JLabel(strr[1]);
								lblAudio2.setFont(new Font("Leelawadee UI", Font.BOLD, 12));
								
								JButton btnStart1 = new JButton();
								btnStart1.setBackground(Color.WHITE);
								btnStart1.setIcon(icStart);
								btnStart1.setModel(new FixedStateButtonModel());
								btnStart1.setFocusPainted(false);
								btnStart1.setBorder(new EmptyBorder(0, 0, 0, 0));
								btnStart1.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseReleased(MouseEvent e) {
										super.mouseReleased(e);
										mp3Player.play();
									}
								});
								
								JButton btnPause1 = new JButton();
								btnPause1.setBackground(Color.WHITE);
								btnPause1.setIcon(icPause);
								btnPause1.setModel(new FixedStateButtonModel());
								btnPause1.setFocusPainted(false);
								btnPause1.setBorder(new EmptyBorder(0, 0, 0, 0));
								btnPause1.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseReleased(MouseEvent e) {
										super.mouseReleased(e);
										mp3Player.pause();
									}
								});
								
								JButton btnStop1 = new JButton();
								btnStop1.setBackground(Color.WHITE);
								btnStop1.setIcon(icStop);
								btnStop1.setModel(new FixedStateButtonModel());
								btnStop1.setFocusPainted(false);
								btnStop1.setBorder(new EmptyBorder(0, 0, 0, 0));
								btnStop1.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseReleased(MouseEvent e) {
										super.mouseReleased(e);
										mp3Player.stop();
									}
								});
								
								JButton btnRemove1 = new JButton();
								btnRemove1.setBackground(Color.WHITE);
								btnRemove1.setIcon(icCross);
								btnRemove1.setModel(new FixedStateButtonModel());
								btnRemove1.setFocusPainted(false);
								btnRemove1.setBorder(new EmptyBorder(0, 0, 0, 0));
								btnRemove1.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseReleased(MouseEvent e) {
										super.mouseReleased(e);
										audioFiles1.set(index1, null);
										lstAnswers.get(index).setAudio("");
										for (String string : audioFiles1) {
											if (lstAnswers.get(index).getAudio() == null || lstAnswers.get(index).getAudio().trim().equals("")) {
												lstAnswers.get(index).setAudio(string);
											}else {
												lstAnswers.get(index).setAudio(lstAnswers.get(index).getAudio().trim() + ";" + string);
											}
										}
										pAudio2.setVisible(false);
										if (lstAnswers.get(index).getAudio() == null || lstAnswers.get(index).getAudio().trim().equals("")) {
											bAudio1.setVisible(false);
										}
									}
								});
								
								pAudio2.add(lblAudio2);
								pAudio2.add(Box.createHorizontalStrut(30));
								pAudio2.add(btnStart1);
								pAudio2.add(Box.createHorizontalStrut(10));
								pAudio2.add(btnPause1);
								pAudio2.add(Box.createHorizontalStrut(10));
								pAudio2.add(btnStop1);
								pAudio2.add(Box.createHorizontalStrut(10));
								pAudio2.add(btnRemove1);
								
								pAudio2.setBorder(new EmptyBorder(5, 5, 5, 5));
								
								bAudio1.add(pAudio2);
								bAudio1.add(Box.createHorizontalStrut(10));
								
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
					bAudio1.add(Box.createHorizontalGlue());
					bAudio1.setVisible(true);
					
				}
				
				//Image
				
				JLabel lblImage1 = new JLabel("Hình ảnh:");
				lblImage1.setForeground(Color.WHITE);
				lblImage1.setPreferredSize(new Dimension(120, lblImage1.getPreferredSize().height));
				lblImage1.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
				bImage1.add(lblImage1);
				bImage1.setVisible(false);
				
				if (lstAnswers.get(index).getImage() != null) {
					String imageURLs1[] = lstAnswers.get(index).getImage().split(";");
					for (int j = 0; j < imageURLs1.length; j++) {
						imageFiles1.add(imageURLs1[j]);
					}
					for (int j = 0; j < imageFiles1.size(); j++) {
						final int index1 = j;
						if (imageFiles1.get(j) != null) {
							try {
								
								final MyCustomPanel pImage2 = new MyCustomPanel();
								pImage2.setRoundBottomRight(10);
								pImage2.setRoundTopRight(10);
								pImage2.setAllBackgroundColor(Color.WHITE);
								pImage2.setLayout(new BoxLayout(pImage2, BoxLayout.X_AXIS));
								
								Image image = ImageIO.read(new File(imageFiles1.get(index1)));
								image = image.getScaledInstance(200, 100, Image.SCALE_AREA_AVERAGING);
								
								JLabel lblImage2 = new JLabel();
								lblImage2.setIcon(new ImageIcon(image));
								JLabel lblRemove1 = new JLabel();
								lblRemove1.setBorder(new EmptyBorder(0, 5, 0, 5));
								lblRemove1.setIcon(icCross);
								lblRemove1.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseReleased(MouseEvent e) {
										super.mouseReleased(e);
										imageFiles1.set(index1, null);
										lstAnswers.get(index).setImage("");
										for (String string : imageFiles1) {
											if(lstAnswers.get(index).getImage().trim().equals("") || lstAnswers.get(index).getImage() == null) {
												lstAnswers.get(index).setImage(string);
											}else {
												lstAnswers.get(index).setImage(lstAnswers.get(index).getImage() + ";" + string);
											}
										}
										pImage2.setVisible(false);
										
										if (lstAnswers.get(index).getImage() == null || lstAnswers.get(index).getImage().trim().equals("")) {
											bImage1.setVisible(false);
										}
									}
								});
								
								pImage2.add(lblImage2);
								pImage2.add(lblRemove1);
								
								bImage1.add(pImage2);
								bImage1.add(Box.createHorizontalStrut(10));
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}
					bImage1.add(Box.createHorizontalGlue());
					bImage1.setVisible(true);	
				}
				
				//------------------------RIGHT-------------------------------
				
				Box bRight1 = Box.createHorizontalBox();
				JLabel lblRight1 = new JLabel("Đáp án đúng:");
				lblRight1.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
				lblRight1.setForeground(Color.WHITE);
				lblRight1.setPreferredSize(new Dimension(120, lblRight1.getPreferredSize().height));
				
				bRight1.add(lblRight1);
				
				if (radOneAnswer.isSelected()) {
					final JRadioButton radRight1 = new JRadioButton();
					rightAnswerGroup.add(radRight1);
					radRight1.setFocusPainted(false);
					radRight1.setBackground(new Color(68, 165, 0));
					radRight1.addItemListener(new ItemListener() {
						public void itemStateChanged(ItemEvent e) {
							if (radRight1.isSelected()) {
								lstAnswers.get(index).setRightt(1);
							}else {
								lstAnswers.get(index).setRightt(0);
							}
						}
					});
					if (lstAnswers.get(index).getRightt() == 1) {
						radRight1.setSelected(true);
					}else {
						radRight1.setSelected(false);
					}
					bRight1.add(radRight1);
				}else {
					final JCheckBox cbRight1 = new JCheckBox();
					cbRight1.setFocusPainted(false);
					cbRight1.setBackground(new Color(68, 165, 0));
					cbRight1.addItemListener(new ItemListener() {
						public void itemStateChanged(ItemEvent e) {
							if (cbRight1.isSelected()) {
								lstAnswers.get(index).setRightt(1);
							}else {
								lstAnswers.get(index).setRightt(0);
							}
						}
					});
					if (lstAnswers.get(index).getRightt() == 1) {
						cbRight1.setSelected(true);
					}else {
						cbRight1.setSelected(false);
					}
					bRight1.add(cbRight1);
				}
				
				bRight1.add(Box.createHorizontalGlue());
				
				//------------------------------------------------------------
				pAnswer.add(bAnswer1);
				pAnswer.add(Box.createVerticalStrut(10));
				pAnswer.add(bContent1);
				pAnswer.add(Box.createVerticalStrut(10));
				pAnswer.add(bAudio1);
				pAnswer.add(Box.createVerticalStrut(10));
				pAnswer.add(bImage1);
				pAnswer.add(Box.createVerticalStrut(10));
				pAnswer.add(bRight1);
				
				//---------------------------------------------------------------------
				
				if (i == 0) {
					pAnswers.add(Box.createHorizontalGlue());
				}
				pAnswers.add(pAnswer);
				pAnswers.add(Box.createVerticalStrut(10));
			}
			
		}
		
		pAnswers.setVisible(true);
	}

	private void loadImage(String url) {
		if (url != null) {
			imageURLs.add(url);
		}
		bImage.setVisible(false);
		bImage.removeAll();
		if (imageURLs.size() > 0 && imageURLs != null) {
			JLabel lblImage = new JLabel("Hình ảnh:");
			lblImage.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
			lblImage.setPreferredSize(new Dimension(120, lblImage.getPreferredSize().height));
			bImage.add(lblImage);
			try {
				for (final String imgURL : imageURLs) {
					Image image = ImageIO.read(new File(imgURL));
					image = image.getScaledInstance(300, 170, Image.SCALE_AREA_AVERAGING);
					MyCustomPanel pImage = new MyCustomPanel();
					pImage.setLayout(new BoxLayout(pImage, BoxLayout.X_AXIS));
					pImage.setRoundTopRight(20);
					pImage.setRoundBottomRight(20);
					pImage.setRadius(30);
					pImage.setAllBackgroundColor(Color.WHITE);
					JLabel lbl1 = new JLabel();
					lbl1.setIcon(new ImageIcon(image));
					JLabel lbl2 = new JLabel();
					lbl2.setIcon(icCross);
					lbl2.setBorder(new EmptyBorder(0, 5, 0, 5));
					lbl2.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							super.mouseReleased(e);
							imageURLs.remove(imgURL);
							loadImage(null);
						}
					});
					pImage.add(lbl1);
					pImage.add(lbl2);
					bImage.add(pImage);
					bImage.add(Box.createHorizontalStrut(10));
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			bImage.add(Box.createHorizontalGlue());
			bImage.setVisible(true);
		}
	}

	private void loadAudio(File file) {

		if (file != null) {
			audioFiles.add(file);
		}

		bAudio.setVisible(false);
		bAudio.removeAll();

		if (audioFiles.size() > 0 && audioFiles != null) {
			JLabel lblAudio = new JLabel("Âm thanh:");
			lblAudio.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
			lblAudio.setPreferredSize(new Dimension(120, lblAudio.getPreferredSize().height));
			bAudio.add(lblAudio);

			try {
				for (final File audFile : audioFiles) {
					final MP3Player mp3Player = new MP3Player(new File("audios/" + audFile.getName()));

					MyCustomPanel pAudio = new MyCustomPanel();
					pAudio.setLayout(new BoxLayout(pAudio, BoxLayout.X_AXIS));

					JLabel lblAudName = new JLabel(audFile.getName());
					lblAudName.setFont(new Font("Leelawadee UI", Font.BOLD, 12));

					JButton btnStart = new JButton();
					btnStart.setModel(new FixedStateButtonModel());
					btnStart.setIcon(icStart);
					btnStart.setFocusPainted(false);
					btnStart.setOpaque(true);
					btnStart.setBackground(Color.WHITE);
					btnStart.setBorder(new EmptyBorder(0, 0, 0, 0));
					btnStart.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							super.mouseReleased(e);
							mp3Player.play();
						}
					});
					JButton btnPause = new JButton();
					btnPause.setModel(new FixedStateButtonModel());
					btnPause.setIcon(icPause);
					btnPause.setFocusPainted(false);
					btnPause.setBackground(Color.WHITE);
					btnPause.setBorder(new EmptyBorder(0, 0, 0, 0));
					btnPause.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							super.mouseReleased(e);
							mp3Player.pause();
						}
					});
					JButton btnStop = new JButton();
					btnStop.setModel(new FixedStateButtonModel());
					btnStop.setIcon(icStop);
					btnStop.setFocusPainted(false);
					btnStop.setBackground(Color.WHITE);
					btnStop.setBorder(new EmptyBorder(0, 0, 0, 0));
					btnStop.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							super.mouseReleased(e);
							mp3Player.stop();
						}
					});

					JLabel lblDelete = new JLabel();
					lblDelete.setIcon(icCross);
					lblDelete.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							super.mouseReleased(e);
							audioFiles.remove(audFile);
							loadAudio(null);
						}
					});

					pAudio.add(lblAudName);
					pAudio.add(Box.createHorizontalStrut(20));
					pAudio.add(btnStart);
					pAudio.add(Box.createHorizontalStrut(10));
					pAudio.add(btnPause);
					pAudio.add(Box.createHorizontalStrut(10));
					pAudio.add(btnStop);
					pAudio.add(Box.createHorizontalStrut(20));
					pAudio.add(lblDelete);
					pAudio.setRadius(30);
					pAudio.setAllBackgroundColor(Color.WHITE);
					pAudio.setBorder(new EmptyBorder(5, 10, 5, 10));

					bAudio.add(pAudio);
					bAudio.add(Box.createHorizontalStrut(10));

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			bAudio.add(Box.createHorizontalGlue());
			bAudio.setVisible(true);
		}

	}

	private void LoadGrades() {
		gradesModel.removeAllElements();
		subjectsModel.removeAllElements();
		chaptersModel.removeAllElements();
		lessonsModel.removeAllElements();

		lstGrades = new ArrayList<Grade>();
		lstSubjects = new ArrayList<Subject>();
		lstChapters = new ArrayList<Chapter>();
		lstLessons = new ArrayList<Lesson>();

		lstGrades = gradeController.getAllGrades();
		if (lstGrades != null && lstGrades.size() > 0) {
			for (Grade grade : lstGrades) {
				gradesModel.addElement(grade);
			}
		}
	}

	private void loadSubjects(Grade grade) {
		subjectsModel.removeAllElements();
		chaptersModel.removeAllElements();
		lessonsModel.removeAllElements();

		lstSubjects = new ArrayList<Subject>();
		lstChapters = new ArrayList<Chapter>();
		lstLessons = new ArrayList<Lesson>();

		if (grade != null) {
			lstSubjects = subjectsController.getSubjectsByGrade(grade.getId());
			if (lstSubjects != null && lstSubjects.size() > 0) {
				for (Subject subject : lstSubjects) {
					subjectsModel.addElement(subject);
				}
			}
		}
	}

	private void loadChapters(Subject subject) {
		chaptersModel.removeAllElements();
		lessonsModel.removeAllElements();

		lstChapters = new ArrayList<Chapter>();
		lstLessons = new ArrayList<Lesson>();

		if (subject != null) {
			lstChapters = chapterController.getChaptersBySubject(subject.getId());
			if (lstChapters != null && lstChapters.size() > 0) {
				for (Chapter chapter : lstChapters) {
					chaptersModel.addElement(chapter);
				}
			}
		}
	}

	private void loadLessons(Chapter chapter) {
		lessonsModel.removeAllElements();

		lstLessons = new ArrayList<Lesson>();

		if (chapter != null) {
			lstLessons = lessonController.getLessonByChapter(chapter.getId());
			if (lstLessons != null && lstLessons.size() > 0) {
				for (Lesson lesson : lstLessons) {
					lessonsModel.addElement(lesson);
				}
			}
		}
	}

	private String getAnswerChar(int number) {
		switch (number) {
		case 0:
			return "A";
		case 1:
			return "B";
		case 2:
			return "C";
		case 3:
			return "D";
		case 4:
			return "E";
		case 5:
			return "F";
		default:
			return null;
		}
	}
}
