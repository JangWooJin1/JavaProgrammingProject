package Project;

public class Room extends Member {
	int maxSize;	//���ǽ� �ִ� ����
	
	//���ǽ� ��ü�� �����ϴ� ������
	Room(String name, int maxSize){
		this.name = name;
		this.maxSize = maxSize;
	}
	
	//���ǽǿ��� Ȯ���Ҽ��ִ� ���� ����ϴ� �޼ҵ� ( �����̸�, ���ǽð�, ���� ) ( �������̵� )
	void View() {
		System.out.println();
		//�ݺ��������� Lectures�� ����Ǿ��ִ� ����Ž��
		for(int i=0; i<Lectures.size(); i++) {
			//���� ��ü�� ����Ǿ��ִ� ���� ���
			System.out.printf("%d. %s ( %s����, %.1f~%.1f���� ,%s���� ) " ,i+1, Lectures.get(i).name, Lectures.get(i).Day, Lectures.get(i).sTime, Lectures.get(i).fTime,
					  Lectures.get(i).pName );
		}
		System.out.println();
	}

}
