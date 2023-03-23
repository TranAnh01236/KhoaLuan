package ultilities;

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
	
	public static int appHeight = 1366;
	public static int appWidth = 768;
}
