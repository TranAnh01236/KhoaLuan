package view.main.questionsList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import org.trananh3010.model.Answer;
import org.trananh3010.model.Lesson;
import org.trananh3010.model.Question;

import controller.QuestionController;
import jaco.mp3.player.MP3Player;
import jaco.mp3.player.MP3PlayerApplet;
import ultilities.customView.FixedStateButtonModel;
import ultilities.customView.MyCustomButton;
import ultilities.customView.MyCustomPanel;
import view.main.questionsList.LessonsListView.LessonsListViewListener;

public class QuestionsListView extends MyCustomPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8190560918091963409L;
	private MyCustomPanel pMain;
	private QuestionController questionController;
	private List<Question> questions;
	private JScrollPane scrollPane;

	private QuestionListViewListener questionListViewListener;
	
	private ImageIcon icNote, icStart, icPause, icStop;

	public interface QuestionListViewListener {
		void ItemClick(Question question);
	}

	public QuestionsListView(QuestionListViewListener listener) {
		this.questionListViewListener = listener;
		Initialize();
	}

	private void Initialize() {
		
		icNote = new ImageIcon(new ImageIcon("images/volume.png").getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING));
		icStart = new ImageIcon(new ImageIcon("images/start.png").getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING));
		icPause = new ImageIcon(new ImageIcon("images/pause.png").getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING));
		icStop = new ImageIcon(new ImageIcon("images/stop.png").getImage().getScaledInstance(20, 20, Image.SCALE_AREA_AVERAGING));
		
		questionController = new QuestionController();
		this.setRadius(30);
		this.setBorder(new EmptyBorder(15, 15, 15, 0));

		pMain = new MyCustomPanel();
		pMain.setAllBackgroundColor(new Color(233, 233, 230));
		pMain.setLayout(new BoxLayout(pMain, BoxLayout.Y_AXIS));
		pMain.setRadius(30);

		scrollPane = new JScrollPane(pMain, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setOpaque(true);

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(scrollPane);
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

	public void createListQuestions(Lesson lesson) {
		
//		pMain.setMaximumSize(new Dimension(Constants.appWidth - 300 - 15 - 15 - 15 - 15, pMain.getMaximumSize().height));
//		pMain.setMaximumSize(new Dimension(300, pMain.getMaximumSize().height));
		
		pMain.removeAll();
		int i = 1;
		questions = questionController.getQuestionsByLesson(lesson.getId());
		if (questions != null && questions.size() > 0) {
			for (Question question : questions) {
				final Question q = question;
				final MyCustomPanel p = new MyCustomPanel();
				p.setAlignmentX(Component.LEFT_ALIGNMENT);
				p.setBorder(new EmptyBorder(0, 0, 15, 15));
				p.setRadius(30);
				final MyCustomPanel p1 = new MyCustomPanel();
				p1.setAllBackgroundColor(Color.WHITE);
				p1.setRadius(30);
				p1.setBorder(new EmptyBorder(15, 15, 15, 15));
				p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
				String txtAswer = "";
				
				JTextArea lblNumber = new JTextArea("CÂU " + i + ":");
				lblNumber.setLineWrap(true);
				lblNumber.setWrapStyleWord(true);
				lblNumber.setForeground(new Color(166, 0, 0));
				lblNumber.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
				lblNumber.setBackground(new Color(255,255,255));
				lblNumber.setEditable(false);
				
				JTextArea lblContent = new JTextArea(q.getContent());
				lblContent.setLineWrap(true);
				lblContent.setWrapStyleWord(true);
				lblContent.setForeground(new Color(0, 0, 0));
				lblContent.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
				lblContent.setBackground(new Color(255,255,255));
				lblContent.setBorder(new EmptyBorder(0, 0, 10, 0));
				lblContent.setEditable(false);
				
				p1.add(lblNumber);
				p1.add(lblContent);
				
				if (q.getAudio() != null && !q.getAudio().trim().equals("")) {
					try {
						JPanel p2 = new JPanel();
						p2.setLayout(new FlowLayout(FlowLayout.LEFT));
						p2.setBackground(new Color(255,255,255,0));
						
						String url[] = q.getAudio().split(";");
						
						for (int j = 0; j < url.length; j++) {
							MyCustomPanel p3 = new MyCustomPanel();
							p3.setLayout(new FlowLayout(FlowLayout.LEFT));
							p3.setAllBackgroundColor(new Color(233, 233, 230));
							p3.setRadius(30);
							p3.setBorder(new EmptyBorder(2, 5, 2, 5));
							JLabel lbl = new JLabel();
							lbl.setIcon(icNote);
							lbl.setBorder(new EmptyBorder(0, 0, 0, 30));
							final MP3Player mp3Player = new MP3Player(new File(url[j]));
							JButton btnStart = new JButton();
							btnStart.setModel(new FixedStateButtonModel());
							btnStart.setIcon(icStart);
							btnStart.setFocusPainted(false);
							btnStart.setOpaque(true);
							btnStart.setBackground(new Color(233, 233, 230));
							btnStart.setBorder(new EmptyBorder(0,0,0,0));
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
							btnPause.setBorder(new EmptyBorder(0,0,0,0));
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
							btnStop.setBorder(new EmptyBorder(0,0,0,0));
							btnStop.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseReleased(MouseEvent e) {
									super.mouseReleased(e);
									mp3Player.stop();
								}
							});
							p3.add(lbl);
							p3.add(btnStart);
							p3.add(btnPause);
							p3.add(btnStop);
							p2.add(p3);
						}
						
						p1.add(p2);
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				if (q.getImage() != null && !q.getImage().trim().equals("")) {
					try {
						JPanel p2 = new JPanel();
						p2.setLayout(new FlowLayout(FlowLayout.LEFT));
						
						String url[] = q.getImage().split(";");
						
						for (int j = 0; j < url.length; j++) {
							JLabel lblImage = new JLabel();
							lblImage.setIcon(new ImageIcon(new ImageIcon(url[j]).getImage().getScaledInstance(500, 250, Image.SCALE_AREA_AVERAGING)));
							lblImage.setAlignmentX(JLabel.LEFT_ALIGNMENT);
							lblImage.setBorder(new EmptyBorder(0, 0, 10, 0));
							p2.add(lblImage);
						}
						
//						JLabel lblImage = new JLabel();
//						lblImage.setIcon(new ImageIcon(new ImageIcon(q.getImage()).getImage().getScaledInstance(400, 250, Image.SCALE_AREA_AVERAGING)));
//						lblImage.setAlignmentX(JLabel.LEFT_ALIGNMENT);
//						lblImage.setBorder(new EmptyBorder(0, 0, 10, 0));
//						p2.add(lblImage);
						
						p2.setBackground(new Color(255, 255, 255, 0));
						p1.add(p2);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				
				for (int j = 0; j < q.getAnswers().size(); j++) {
					Answer answer = q.getAnswers().get(j);
					JTextArea lblAnswer = new JTextArea(getAnswerChar(j) + ". " + answer.getContent());
					lblAnswer.setFont(new Font("Leelawadee UI", Font.PLAIN, 16));
					lblAnswer.setForeground(new Color(31, 32, 29));
					lblAnswer.setLineWrap(true);
					lblAnswer.setWrapStyleWord(true);
					lblAnswer.setBackground(new Color(255,255,255));
					lblAnswer.setEditable(false);
					p1.add(lblAnswer);
					if (answer.getRightt() == 1) {
						txtAswer += " " + getAnswerChar(j);
					}
					
					if (answer.getAudio() != null && !answer.getAudio().trim().equals("")) {
						try {
							JPanel p2 = new JPanel();
							p2.setLayout(new FlowLayout(FlowLayout.LEFT));
							p2.setBackground(new Color(255,255,255,0));
							
							String url[] = answer.getAudio().split(";");
							
							for (int k = 0; k < url.length; k++) {
								MyCustomPanel p3 = new MyCustomPanel();
								p3.setLayout(new FlowLayout(FlowLayout.LEFT));
								p3.setAllBackgroundColor(new Color(233, 233, 230));
								p3.setRadius(30);
								p3.setBorder(new EmptyBorder(2, 5, 2, 5));
								
								JLabel lbl = new JLabel();
								lbl.setIcon(icNote);
								lbl.setBorder(new EmptyBorder(0, 0, 0, 30));
								
								final MP3Player mp3Player = new MP3Player(new File(url[k]));
								
								JButton btnStart = new JButton();
								btnStart.setModel(new FixedStateButtonModel());
								btnStart.setIcon(icStart);
								btnStart.setFocusPainted(false);
								btnStart.setOpaque(true);
								btnStart.setBackground(new Color(233, 233, 230));
								btnStart.setBorder(new EmptyBorder(0,0,0,0));
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
								btnPause.setBorder(new EmptyBorder(0,0,0,0));
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
								btnStop.setBorder(new EmptyBorder(0,0,0,0));
								btnStop.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseReleased(MouseEvent e) {
										super.mouseReleased(e);
										mp3Player.stop();
									}
								});
								
								p3.add(lbl);
								p3.add(btnStart);
								p3.add(btnPause);
								p3.add(btnStop);
								
								p2.add(p3);
							}
							
							
							p1.add(p2);
						}catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					if (answer.getImage() != null && !answer.getImage().trim().equals("")) {
						JPanel p2 = new JPanel();
						p2.setLayout(new FlowLayout(FlowLayout.LEFT));
						
						String url[] = answer.getImage().split(";");
						
						for (int h = 0; h < url.length; h++) {
							JLabel lblImage = new JLabel();
							lblImage.setIcon(new ImageIcon(new ImageIcon(url[h]).getImage().getScaledInstance(400, 200, Image.SCALE_AREA_AVERAGING)));
							lblImage.setAlignmentX(JLabel.LEFT_ALIGNMENT);
							lblImage.setBorder(new EmptyBorder(0, 0, 10, 0));
							p2.add(lblImage);
						}
						
//						JLabel lblImage = new JLabel();
//						lblImage.setIcon(new ImageIcon(new ImageIcon(answer.getImage()).getImage().getScaledInstance(300, 200, Image.SCALE_AREA_AVERAGING)));
//						lblImage.setAlignmentX(JLabel.LEFT_ALIGNMENT);
//						lblImage.setBorder(new EmptyBorder(0, 0, 10, 0));
//						p2.add(lblImage);
						
						p2.setBackground(new Color(255, 255, 255, 0));
						p1.add(p2);
					}
					
				}
				
				JTextArea lblAnswer = new JTextArea("Đáp án đúng là: " + txtAswer);
				lblAnswer.setBorder(new EmptyBorder(10, 0, 0, 0));
				lblAnswer.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
				lblAnswer.setLineWrap(true);
				lblAnswer.setWrapStyleWord(true);
				lblAnswer.setBackground(new Color(255,255,255));
				lblAnswer.setForeground(new Color(0, 0, 0));
				lblAnswer.setEditable(false);
				p1.add(lblAnswer);
				
				
				JTextArea lblExplain = new JTextArea("Giải thích: " + q.getExplain());
				lblExplain.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
				lblExplain.setForeground(new Color(68, 165, 0));
				lblExplain.setLineWrap(true);
				lblExplain.setWrapStyleWord(true);
				lblExplain.setBackground(new Color(255,255,255));
				lblExplain.setEditable(false);
				p1.add(lblExplain);
				
				p.setLayout(new BorderLayout());
				p.add(p1, BorderLayout.CENTER);
				p1.setAlignmentX(Component.LEFT_ALIGNMENT);
				pMain.add(p);
				i++;
			}
		}
	}
}
