package view.main.addQuestions;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.concurrent.Flow;
import java.util.stream.LongStream;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.trananh3010.model.Answer;
import org.trananh3010.model.Chapter;
import org.trananh3010.model.Grade;
import org.trananh3010.model.Lesson;
import org.trananh3010.model.Subject;

import controller.ChapterController;
import controller.GradeController;
import controller.LessonController;
import controller.SubjectsController;
import jaco.mp3.player.MP3Player;
import jnafilechooser.api.JnaFileChooser;
import ultilities.customView.FixedStateButtonModel;
import ultilities.customView.MyCustomButton;
import ultilities.customView.MyCustomJlabel;
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

	public AddQuestionView(MainFrame frame) {
		this.frame = frame;
		Initialize();
	}

	private void Initialize() {

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

		String city[] = { "Ha Noi", "Vinh Phuc", "Da Nang", "TP. Ho Chi Minh", "Nha Trang" };

		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(city);

		cbbGrades = new ComboBoxSuggestion<Grade>();
		cbbGrades.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
		cbbGrades.setEditable(false);
		Box b1 = Box.createHorizontalBox();
		JLabel lblGrades = new JLabel("Lớp:");
		lblGrades.setPreferredSize(new Dimension(100, lblGrades.getPreferredSize().height));
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
		lblSubjects.setPreferredSize(new Dimension(100, lblGrades.getPreferredSize().height));
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
		lblChapters.setPreferredSize(new Dimension(100, lblGrades.getPreferredSize().height));
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
		lblLessons.setPreferredSize(new Dimension(100, lblGrades.getPreferredSize().height));
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

		bCenter = Box.createHorizontalBox();
		bCenter.setBackground(new Color(255, 255, 255, 0));
		bCenter.setSize(WIDTH, 100);
		bCenter.setOpaque(true);

		Box bCenterL = Box.createVerticalBox();

		JLabel lblQuestion = new JLabel("Câu hỏi:");
		lblQuestion.setPreferredSize(new Dimension(100, lblQuestion.getPreferredSize().height));
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

		Box bAnswer = Box.createHorizontalBox();

		JLabel lblAnswer = new JLabel("Đáp án:");
		lblAnswer.setPreferredSize(new Dimension(100, lblAnswer.getPreferredSize().height));
		lblAnswer.setFont(new Font("Leelawadee UI", Font.BOLD, 16));

		JLabel lblAnswerQuantity = new JLabel();
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
		pAnswers.setAllBackgroundColor(Color.YELLOW);

		JButton btnbtn = new JButton("TEST");
		btnbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				for(Answer answer : lstAnswers) {
					System.out.println(answer);
				}
			}
		});
		
		bMain.add(bTop);
		bMain.add(bCenter);
		bMain.add(Box.createVerticalStrut(20));
		bMain.add(bAudio);
		bMain.add(Box.createVerticalStrut(20));
		bMain.add(bImage);
		bMain.add(Box.createVerticalStrut(20));
		bMain.add(bAnswer);
		bMain.add(Box.createVerticalStrut(20));
		bMain.add(pAnswers);
		bMain.add(Box.createVerticalStrut(20));
		bMain.add(btnbtn);

		bMain.add(new JLabel(" "));
		bMain.add(new JLabel(" "));
		bMain.add(new JLabel(" "));
		bMain.add(new JLabel(" "));
		bMain.add(new JLabel(" "));
		bMain.add(new JLabel(" "));
		bMain.add(new JLabel(" "));
		bMain.add(new JLabel(" "));
		bMain.add(new JLabel("  "));
		bMain.add(new JLabel(" "));
		bMain.add(new JLabel(" "));
		bMain.add(new JLabel(" "));
		bMain.add(new JLabel(" "));
		bMain.add(new JLabel(" "));
		bMain.add(new JLabel(" "));
		bMain.add(new JLabel(" "));
		bMain.add(new JLabel(" "));
		bMain.add(new JLabel(" "));
		bMain.add(new JLabel(" "));
		bMain.add(new JLabel(" "));
		bMain.add(new JLabel(" "));

		pMain.add(bMain);

		scrollPane = new JScrollPane(pMain, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setOpaque(true);

		this.add(scrollPane);

		LoadGrades();

	}

	private void addAnswer(final Answer answer) {
		if(answer != null) {
			lstAnswers.add(answer);
		}
		pAnswers.removeAll();
		pAnswers.setVisible(false);
		
		if (lstAnswers != null && lstAnswers.size() > 0) {
			
			for (int i = 0; i < lstAnswers.size(); i++) {
				
				final int index = i;
				
				String str = getAnswerChar(i);
				
				MyCustomPanel pAnswer = new MyCustomPanel();
				pAnswer.setLayout(new BoxLayout(pAnswer, BoxLayout.Y_AXIS));
				pAnswer.setAllBackgroundColor(Color.GREEN);
				pAnswer.setBorder(new EmptyBorder(15, 15, 15, 15));
				pAnswer.setRadius(20);
				
				//---------------------------------------------------------------------
				
				//Answer------------------------------------------------
				Box bAnswer1 = Box.createHorizontalBox();
				JLabel lblAnswer1 = new JLabel("Câu " + str + ":");
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
				
				MyCustomButton btnAnswerAudio = new MyCustomButton();
				btnAnswerAudio.setIcon(icVolume);
				
				bContent1L.add(btnAnswerImage);
				bContent1L.add(btnAnswerAudio);
				bContent1L.setPreferredSize(new Dimension(100, bContent1L.getPreferredSize().height));
				
				final JTextArea txtAnswerContent = new JTextArea();
				txtAnswerContent.setText(lstAnswers.get(index).getContent());
				
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
				//-------------------------------------------------------
				pAnswer.add(bAnswer1);
				pAnswer.add(Box.createVerticalStrut(10));
				pAnswer.add(bContent1);
				
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
			lblImage.setPreferredSize(new Dimension(100, lblImage.getPreferredSize().height));
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
			lblAudio.setPreferredSize(new Dimension(100, lblAudio.getPreferredSize().height));
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
					btnStart.setBackground(new Color(233, 233, 230));
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
					btnPause.setOpaque(true);
					btnPause.setBackground(new Color(233, 233, 230));
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
					btnStop.setOpaque(true);
					btnStop.setBackground(new Color(233, 233, 230));
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
