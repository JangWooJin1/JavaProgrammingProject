package Project;

public class Professor extends Member {
	
	//���� ��ü�� �����ϴ� �޼ҵ�
	Professor(String name) {
		this.name = name;
	}
	
	//������ Ȯ���Ҽ��ִ� ���� ����ϴ� �޼ҵ� ( �����̸�, ���ǽð�, ��������, ���ǽ�, �������� ) ( �������̵� )
	void View() {
		System.out.println();
		//�ݺ��������� Lectures�� ����Ǿ��ִ� ����Ž��
		for(int i=0; i<Lectures.size(); i++) {
			//���� ��ü�� ����Ǿ��ִ� ���� ���
			System.out.printf("%d. %s ( %s����, %.1f~%.1f����, %d����, %s���ǽ� ) ( %d / %d �������� ) " ,i+1, Lectures.get(i).name, Lectures.get(i).Day, 
					Lectures.get(i).sTime, Lectures.get(i).fTime, Lectures.get(i).Credit, Lectures.get(i).rName, Lectures.get(i).sNames.size(),Lectures.get(i).maxSize);
		}
		System.out.println();
	}
	
	//�ش簭�Ǹ� ������û�� �л� ������ ����ϴ� �޼ҵ�
	void ViewStudent(Lecture L) {
		//���� �̸� ���
		System.out.printf("%s������ �����ϰ� �ִ� �л� : ",L.name);
		//�ݺ����� ���� ���ǿ� ����Ǿ��ִ� �л� �迭 Ž��
		for(int j=0; j<L.sNames.size(); j++) {
			//�л��� �й��� �̸��� ���
			System.out.printf(" %s ", L.sNames.get(j));
		}
		System.out.println();
	}
	
}
