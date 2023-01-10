package Project;

public class Student extends Member {

	//학생 객체를 생성하는 메소드
	Student(String name){
		this.name = name;
	}
	
	//학생의 Lectures에 강의를 추가하는 메소드 ( 오버라이딩 )
	void AddLecture(Lecture L) {
		//먼저 해당 강의 정원확인 ( '해당 강의의 학생수 <= 최대정원' 조건 일때만 신청 )  
		if(L.sNames.size() <= L.maxSize) {
			//그다음 CheckTimeCollision메소드를 호출해 학생의 Lectures에 저장되어있는 강좌와 충돌하는지 파악
			if(CheckTimeCollision(L)) System.out.println("이미 있는 강좌와 충돌합니다.");
			else {
				Lectures.add(L);	// 학생의 Lectures에 강의 추가
				L.sNames.add(this.name);	//강의에 학생 정보 추가
			}
		}
		else System.out.println("수강인원이 꽉차 해당 강좌 신청 불가합니다.");
	}


	//학생이 확인할수있는 정보 출력하는 메소드 ( 강의이름, 강의시간, 강의학점, 교수, 강의실 ) ( 오버라이딩 )
	void View() {
		System.out.println();
		//반복문을통해 Lectures에 저장되어있는 강의탐방
		for(int i=0; i<Lectures.size(); i++) {
			//강의 객체에 저장되어있는 정보 출력
			System.out.printf("%d. %s ( %s요일, %.1f~%.1f교시, %d학점 ,%s교수, %s강의실 ) " ,i+1, Lectures.get(i).name, Lectures.get(i).Day, Lectures.get(i).sTime, Lectures.get(i).fTime, Lectures.get(i).Credit,
				Lectures.get(i).pName , Lectures.get(i).rName);
		}
		System.out.println();
	}

}
