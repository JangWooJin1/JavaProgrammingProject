package Project;

public class Room extends Member {
	int maxSize;	//강의실 최대 정원
	
	//강의실 객체를 생성하는 생성자
	Room(String name, int maxSize){
		this.name = name;
		this.maxSize = maxSize;
	}
	
	//강의실에서 확인할수있는 정보 출력하는 메소드 ( 강의이름, 강의시간, 교수 ) ( 오버라이딩 )
	void View() {
		System.out.println();
		//반복문을통해 Lectures에 저장되어있는 강의탐방
		for(int i=0; i<Lectures.size(); i++) {
			//강의 객체에 저장되어있는 정보 출력
			System.out.printf("%d. %s ( %s요일, %.1f~%.1f교시 ,%s교수 ) " ,i+1, Lectures.get(i).name, Lectures.get(i).Day, Lectures.get(i).sTime, Lectures.get(i).fTime,
					  Lectures.get(i).pName );
		}
		System.out.println();
	}

}
