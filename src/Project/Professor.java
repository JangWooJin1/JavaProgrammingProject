package Project;

public class Professor extends Member {
	
	//교수 객체를 생성하는 메소드
	Professor(String name) {
		this.name = name;
	}
	
	//교수가 확인할수있는 정보 출력하는 메소드 ( 강의이름, 강의시간, 강의학점, 강의실, 수강정원 ) ( 오버라이딩 )
	void View() {
		System.out.println();
		//반복문을통해 Lectures에 저장되어있는 강의탐방
		for(int i=0; i<Lectures.size(); i++) {
			//강의 객체에 저장되어있는 정보 출력
			System.out.printf("%d. %s ( %s요일, %.1f~%.1f교시, %d학점, %s강의실 ) ( %d / %d 수강정원 ) " ,i+1, Lectures.get(i).name, Lectures.get(i).Day, 
					Lectures.get(i).sTime, Lectures.get(i).fTime, Lectures.get(i).Credit, Lectures.get(i).rName, Lectures.get(i).sNames.size(),Lectures.get(i).maxSize);
		}
		System.out.println();
	}
	
	//해당강의를 수강신청한 학생 정보를 출력하는 메소드
	void ViewStudent(Lecture L) {
		//강의 이름 출력
		System.out.printf("%s수업을 수강하고 있는 학생 : ",L.name);
		//반복문을 통해 강의에 저장되어있는 학생 배열 탐방
		for(int j=0; j<L.sNames.size(); j++) {
			//학생의 학번과 이름을 출력
			System.out.printf(" %s ", L.sNames.get(j));
		}
		System.out.println();
	}
	
}
