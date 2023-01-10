package Project;

import java.util.ArrayList;

public abstract class Member {
	String name;	//�̸�
	ArrayList<Lecture> Lectures=new ArrayList<Lecture>();	//���� ���� ����
	
	//Lectures�� ���Ǹ� �߰��Ҷ� �ð��밡 ��ġ���� �ľ����ִ� �޼ҵ�
	boolean CheckTimeCollision(Lecture L) {
		boolean collision = false;
		
		//�ݺ����� ���� Lectures�迭 Ž��
		for(int i=0; i<Lectures.size(); i++) {
			//���� ���� ������ �������� �ľ�
			if(L.Day.contentEquals(Lectures.get(i).Day)) {
				//���� ���ϰ� �����ϸ� �״����� ���ǽð��� ��ġ���� �ľ�
				if(L.sTime < Lectures.get(i).fTime && L.fTime > Lectures.get(i).sTime) {
					collision = true;
					break;
				}
			}
		}
		return collision;
	}

	//���ǽ�, ����, �л� Ŭ������ View()�޼ҵ�� �����ϱ� ���� �޼ҵ� ( �������̵� )
	void View() {}
	
	//�ش� ����� ���� ��Ͽ� ���� �߰��ϴ� �޼ҵ� ( �������̵� )
	void AddLecture(Lecture L) {
		//CheckTimeCollision�޼ҵ带 ���� ����� Lectures�� �浹�ϴ��� �ľ�
		if(CheckTimeCollision(L)) System.out.println("�̹� �ִ� ���¿� �浹�մϴ�.");
		else { Lectures.add(L); } //����� Lectures�� ���� �߰�
	}

	//����� Lectures���� ���Ǹ� �����ϴ� �޼ҵ�
	void DeleteLecture(Lecture L) {
		this.Lectures.remove(L);
	}


}
