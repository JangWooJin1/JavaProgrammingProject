package Project;
import java.util.ArrayList;
public class Lecture {
	String name, Day;	//�����̸�, ���ǿ���
	int Credit, maxSize;	//��������, �����ִ�����
	double sTime, fTime;	//���ǽ��۽ð�, ��������ð�
	
	String pName; //���� ����
	String rName; //���ǽ� ����
	ArrayList<String> sNames = new ArrayList<String>();	//�л��� ����
	
	//���� ��ü�� �����ϴ� ������
	public Lecture(String name, int Credit, String Day, double sTime, double fTime, int maxSize, String  p) {
		this.name = name;
		this.Credit = Credit;
		this.Day = Day;	
		this.sTime = sTime;
		this.fTime = fTime;
		this.maxSize = maxSize;
		this.pName = p;
	}
	
	// ���ǽ� ������ �߰��ϴ� �޼ҵ� 
	public void setRoom(Room r) {
		this.rName = r.name;
	}
	
	// �Ű������� ���� �л� ��ü�� ArrayList�迭 s���� �����ϴ� �޼ҵ�
	public void DeleteStudent(Student S) {
		this.sNames.remove(S.name);
	}
}
