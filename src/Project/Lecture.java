package Project;
import java.util.ArrayList;
public class Lecture {
	String name, Day;	//강의이름, 강의요일
	int Credit, maxSize;	//강의학점, 수강최대정원
	double sTime, fTime;	//강의시작시간, 강의종료시간
	
	String pName; //교수 정보
	String rName; //강의실 정보
	ArrayList<String> sNames = new ArrayList<String>();	//학생들 정보
	
	//강의 객체를 생성하는 생성자
	public Lecture(String name, int Credit, String Day, double sTime, double fTime, int maxSize, String  p) {
		this.name = name;
		this.Credit = Credit;
		this.Day = Day;	
		this.sTime = sTime;
		this.fTime = fTime;
		this.maxSize = maxSize;
		this.pName = p;
	}
	
	// 강의실 정보를 추가하는 메소드 
	public void setRoom(Room r) {
		this.rName = r.name;
	}
	
	// 매개변수로 받은 학생 객체를 ArrayList배열 s에서 제거하는 메소드
	public void DeleteStudent(Student S) {
		this.sNames.remove(S.name);
	}
}
