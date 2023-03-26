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

import org.trananh3010.model.Chapter;
import org.trananh3010.model.Lesson;

import controller.LessonController;
import ultilities.customView.MyCustomPanel;

public class LessonsListView extends MyCustomPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2030286429073703584L;
	
	private MyCustomPanel pMain;
	private LessonController lessonController = new LessonController();
	private List<Lesson> lessons;
	private JScrollPane scrollPane;

	private LessonsListViewListener lessonListViewListener;

	public interface LessonsListViewListener {
		void ItemClick(Lesson lesson);
	}
	
	public LessonsListView(LessonsListViewListener listener) {
		this.lessonListViewListener = listener;
		Initialize();
	}
	
	private void Initialize() {
		lessonController = new LessonController();
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
	
	public void createListLesson(Chapter chapter) {
		pMain.removeAll();
		lessons = lessonController.getLessonByChapter(chapter.getId());
		
//		JOptionPane.showMessageDialog(LessonsListView.this,
//                lessons.size(),
//                "Title Example",
//                JOptionPane.INFORMATION_MESSAGE);
		
		if (lessons != null && lessons.size() > 0) {
			for (Lesson lesson : lessons) {
				final Lesson l = lesson;
				MyCustomPanel p = new MyCustomPanel();
				p.setAllBackgroundColor(new Color(233, 233, 230));
				p.setPreferredSize(new Dimension(p.getPreferredSize().width, 80));
				p.setMaximumSize(new Dimension(p.getMaximumSize().width, 80));
				p.setBorder(new EmptyBorder(0, 0, 15, 15));
				p.setRadius(30);
				p.setLayout(new BorderLayout());

				final MyCustomPanel p1 = new MyCustomPanel();

				p1.setAllBackgroundColor(Color.WHITE);
				p1.setRadius(30);
				p1.setBorder(new EmptyBorder(15, 15, 15, 15));
				p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));

				final JLabel l1 = new JLabel(lesson.getName());
				l1.setFont(new Font("Leelawadee UI", Font.BOLD, 16));
				l1.setForeground(Color.BLACK);
				l1.setBorder(new EmptyBorder(0, 0, 10, 0));

//				JLabel l2 = new JLabel(subject.getDescription());
//				l2.setFont(new Font("Time News Roman", Font.BOLD, 14));
//				l2.setForeground(new Color(130, 129, 130));

				p1.add(l1);
//				p1.add(l2);

				p.add(p1, BorderLayout.CENTER);
				p1.addMouseListener(new MouseListener() {
					public void mouseReleased(MouseEvent e) {
						lessonListViewListener.ItemClick(l);
					}

					public void mousePressed(MouseEvent e) {

					}

					public void mouseExited(MouseEvent e) {
						p1.setAllBackgroundColor(Color.WHITE);
						l1.setForeground(new Color(70, 70, 68));
						p1.repaint();
					}

					public void mouseEntered(MouseEvent e) {
						p1.setAllBackgroundColor(new Color(100, 201, 244));
						l1.setForeground(Color.WHITE);
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
