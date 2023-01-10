package Project;

import java.util.ArrayList;

public abstract class Member {
	String name;	//이름
	ArrayList<Lecture> Lectures=new ArrayList<Lecture>();	//강의 정보 저장
	
	//Lectures에 강의를 추가할때 시간대가 겹치는지 파악해주는 메소드
	boolean CheckTimeCollision(Lecture L) {
		boolean collision = false;
		
		//반복문을 통해 Lectures배열 탐방
		for(int i=0; i<Lectures.size(); i++) {
			//먼저 강의 요일이 동일한지 파악
			if(L.Day.contentEquals(Lectures.get(i).Day)) {
				//강의 요일가 동일하면 그다음에 강의시간이 겹치는지 파악
				if(L.sTime < Lectures.get(i).fTime && L.fTime > Lectures.get(i).sTime) {
					collision = true;
					break;
				}
			}
		}
		return collision;
	}

	//강의실, 교수, 학생 클래스의 View()메소드로 접근하기 위한 메소드 ( 오버라이딩 )
	void View() {}
	
	//해당 멤버의 강의 목록에 강의 추가하는 메소드 ( 오버라이딩 )
	void AddLecture(Lecture L) {
		//CheckTimeCollision메소드를 통해 멤버의 Lectures와 충돌하는지 파악
		if(CheckTimeCollision(L)) System.out.println("이미 있는 강좌와 충돌합니다.");
		else { Lectures.add(L); } //멤버의 Lectures에 강의 추가
	}

	//멤버의 Lectures에서 강의를 제거하는 메소드
	void DeleteLecture(Lecture L) {
		this.Lectures.remove(L);
	}


}
