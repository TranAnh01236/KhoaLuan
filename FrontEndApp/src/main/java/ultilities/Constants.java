package ultilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.trananh3010.model.User;

public class Constants {
	public static final String host = "localhost";
	public static final String port = "9090";

	public static final String connectURL = "http://" + host + ":" + port;

	public static final String authsURL = connectURL + "/auths";
	public static final String loginURL = authsURL + "/login";

	public static final String usersURL = connectURL + "/users";
	public static final String getUserByIdURL = usersURL + "/";

	public static final String gradesURL = connectURL + "/grades";
	public static final String getAllGradesURL = gradesURL + "/";

	public static final String subjectsURL = connectURL + "/subjects";
	public static final String getAllSubjectsURL = subjectsURL + "/";
	public static final String getSubjectsByGradeURL = getAllSubjectsURL + "grade/";

	public static final String chaptersURL = connectURL + "/chapters";
	public static final String getAllChaptersURL = chaptersURL + "/";
	public static final String getChaptersBySubjectURL = getAllChaptersURL + "subject/";

	public static final String lessonsURL = connectURL + "/lessons";
	public static final String getAllLessonsURL = lessonsURL + "/";
	public static final String getLessonsByChapterURL = getAllLessonsURL + "chapter/";

	public static final String questionsURL = connectURL + "/questions";
	public static final String getAllQuestionURL = questionsURL + "/";
	public static final String getQuestionsByLessonURL = getAllQuestionURL + "lesson/";
	public static final String addQuestionURL = questionsURL + "/";
	
	public static final String examsURL = connectURL + "/exams";
	public static final String getAllExamsURL = examsURL + "/";
	public static final String getExamsByLesson = getAllExamsURL + "lesson/";
	public static final String getExamsByChapter = getAllExamsURL + "chapter/";
	public static final String getExamsBySubject = getAllExamsURL + "subject/";
	public static final String getExamsByGrade = getAllExamsURL + "grade/";

	public static int appHeight = 900;
	public static int appWidth = 1600;

	public static void saveUser(User user) {
		try {
			File f = new File("datas/user.txt");
			FileWriter fw = new FileWriter(f);
			fw.write(user.toJson());
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static User getUser() {
		try {
			File f = new File("datas/user.txt");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			User user = org.trananh3010.ultilities.Constants.gson.fromJson(line, User.class);
//			while ((line = br.readLine()) != null) {
//				System.out.println(line);
//			}
			fr.close();
			br.close();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
