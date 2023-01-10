package Project;

public class Student extends Member {

	//�л� ��ü�� �����ϴ� �޼ҵ�
	Student(String name){
		this.name = name;
	}
	
	//�л��� Lectures�� ���Ǹ� �߰��ϴ� �޼ҵ� ( �������̵� )
	void AddLecture(Lecture L) {
		//���� �ش� ���� ����Ȯ�� ( '�ش� ������ �л��� <= �ִ�����' ���� �϶��� ��û )  
		if(L.sNames.size() <= L.maxSize) {
			//�״��� CheckTimeCollision�޼ҵ带 ȣ���� �л��� Lectures�� ����Ǿ��ִ� ���¿� �浹�ϴ��� �ľ�
			if(CheckTimeCollision(L)) System.out.println("�̹� �ִ� ���¿� �浹�մϴ�.");
			else {
				Lectures.add(L);	// �л��� Lectures�� ���� �߰�
				L.sNames.add(this.name);	//���ǿ� �л� ���� �߰�
			}
		}
		else System.out.println("�����ο��� ���� �ش� ���� ��û �Ұ��մϴ�.");
	}


	//�л��� Ȯ���Ҽ��ִ� ���� ����ϴ� �޼ҵ� ( �����̸�, ���ǽð�, ��������, ����, ���ǽ� ) ( �������̵� )
	void View() {
		System.out.println();
		//�ݺ��������� Lectures�� ����Ǿ��ִ� ����Ž��
		for(int i=0; i<Lectures.size(); i++) {
			//���� ��ü�� ����Ǿ��ִ� ���� ���
			System.out.printf("%d. %s ( %s����, %.1f~%.1f����, %d���� ,%s����, %s���ǽ� ) " ,i+1, Lectures.get(i).name, Lectures.get(i).Day, Lectures.get(i).sTime, Lectures.get(i).fTime, Lectures.get(i).Credit,
				Lectures.get(i).pName , Lectures.get(i).rName);
		}
		System.out.println();
	}

}
