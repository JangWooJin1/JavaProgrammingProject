package Project;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class UniversityProgram {
	ArrayList<Member> Members = new ArrayList<Member>();   //강의실, 교수, 학생 객체를 저장하는 배열 ( 업캐스팅 )

	Scanner input = new Scanner(System.in);    //키보드 입력을 받을 객체
	int fKey;  			//어떤 작업 종류를 입력받는 변수
	String strInput;   //문자열을 입력받을 변수
	int intInput;     //숫자를 입력받을 변수

	//해당 이름을 가진 멤버를 찾아 반환해주는 메소드
	public Member FindMember(String name) {
		//멤버를 가르키는 레퍼런스 변수 null로 초기화
		Member m = null;

		int i=0;   //반복문 변수 i 선언 및 초기화
		//멤버의 이름(name)을 가지고 Users배열에서 멤버 찾기
		for(; i<Members.size(); i++) {
			//멤버의 이름(name)이 발견되면 반복문 종료
			if(Members.get(i).name.contentEquals(name)) {
				m = Members.get(i);
				break;
			}
		}

		return m;
	}

	//강의 리스트에서 해당 이름을 가진 강의를 찾아 반환해주는 메소드
	public Lecture FindLecture(ArrayList<Lecture> Ls, String name) {
		Lecture l = null;  //강의 객체를 가리키는 레퍼런스 변수 null값으로 초기화

		//반복문을 통해 Lectures탐방
		for(int i=0; i<Ls.size(); i++) {
			//강의 이름(name)이 발견되면 반복문 종료
			if(Ls.get(i).name.contentEquals(name)) {
				l = Ls.get(i);
				break;
			}
		}

		return l;
	}

	//멤버를 추가하는 메소드
	public void MemberJoin() {
		System.out.println("\n멤버생성 페이지");
		try {  //예외처리 ( 입력값을 잘못 입력할 경우 )
			
			System.out.print("[1] 강의실, [2] 교수, [3] 학생 ");
			fKey = Integer.parseInt(input.nextLine());
			if ( fKey < 1 || fKey > 3 ) return;

			//이름 입력
			System.out.print("이름을 입력해주세요 : ");
			strInput = input.nextLine();
			if(strInput.contentEquals("")) {   //예외처리 name이 아무것도 입력되지않을 경우
				System.out.println("입력오류입니다.\n");
				return;
			}

			//FindUser메소드를 이용해 해당 이름을 가진 멤버가 존재하는지 파악 ( 중복 회원가입 방지 )
			Member u = FindMember(strInput);
			if(u != null) {    //u가 null이 아니라면 해당 이름을 가진 멤버가 이미 존재한다는 뜻
				System.out.println("이미 해당 멤버가 존재합니다.\n");
				return;
			}

			if(fKey == 1){ //강의실 추가
				System.out.print("강의실 최대 인원을 입력해주세요. : ");
				intInput = Integer.parseInt(input.nextLine());

				Members.add(new Room(strInput,intInput));  //강의실 추가 ( 업캐스팅 )
				System.out.printf("%s강의실이 생성되었습니다.\n\n", strInput);
			}
			else if(fKey == 2){    //교수 추가
				Members.add(new Professor(strInput));  //교수 추가 ( 업캐스팅 )
				System.out.printf("%s교수가 생성되었습니다.\n\n", strInput);
			}
			else if(fKey == 3){    //학생 추가
				Members.add(new Student(strInput));    //학생 추가 ( 업캐스팅 )
				System.out.printf("%s학생이 생성되었습니다.\n\n", strInput);
			}
		} catch(NumberFormatException ex) {
			System.out.println("입력오류입니다.\n");
		}

	}

	//DB에서 정보를 읽어와 배열에 추가하는 메소드
	public void readDB(){
		try {
			File LF = new File("database/LectureList.csv");

			ArrayList<Lecture> Ls=new ArrayList<Lecture>();

			if (LF.exists()) {
				BufferedReader lfr = new BufferedReader(new FileReader(LF));
				String line = lfr.readLine();

				while((line = lfr.readLine()) != null){
					ArrayList<String> L_info = new ArrayList<String>(Arrays.asList(line.split(",")));
					Lecture L = new Lecture(
							L_info.get(0), //강의이름
							Integer.parseInt(L_info.get(1)),   //학점
							L_info.get(3), //요일
							Double.parseDouble(L_info.get(4)), //시작시간
							Double.parseDouble(L_info.get(5)), //종료시간
							Integer.parseInt(L_info.get(2)),   //수강정원
							L_info.get(6)                 //교수
					);
					L.rName = L_info.get(7);               //강의실

					//System.out.println("디버깅1 : " + L_info.get(0) +"/"+ L_info.get(1) +"/"+ L_info.get(3) +"/"+ L_info.get(4) +"/"+ L_info.get(5) +"/"+ L_info.get(2) +"/"+ L_info.get(6));

					//System.out.print("디버깅2 : ");
					if(L_info.size() == 9) {
						ArrayList<String> S_info = new ArrayList<String>(Arrays.asList(L_info.get(8).split("/")));    //학생들
						for (int i = 0; i < S_info.size(); i++) {
							L.sNames.add(S_info.get(i));
							//System.out.print(S_info.get(i) + "/"); //디버깅2
						}
					}
					//System.out.println();

					Ls.add(L);

				}

			}

			File CF = new File("database/ClassroomList.csv");
			if (CF.exists()) {
				BufferedReader cfr = new BufferedReader(new FileReader(CF));
				String line = cfr.readLine();

				while((line = cfr.readLine()) != null){
					ArrayList<String> c_info = new ArrayList<String>(Arrays.asList(line.split(",")));
					Room C = new Room(
							c_info.get(0), //강의실이름
							Integer.parseInt(c_info.get(1)) //수강정원
					);

					//System.out.println("디버깅3 : " + c_info.get(0) +"/"+ c_info.get(1));

					//System.out.print("디버깅4 : ");
					if(c_info.size() == 3) {
						ArrayList<String> L_info = new ArrayList<String>(Arrays.asList(c_info.get(2).split("/")));    //강의들
						for (int i = 0; i < L_info.size(); i++) {
							Lecture L = FindLecture(Ls, L_info.get(i));
							if(L != null) C.Lectures.add(L);
							//System.out.print(L_info.get(i) + "/"); //디버깅2
						}
					}
					//System.out.println();
					Members.add(C);
				}
			}

			File PF = new File("database/ProfessorList.csv");
			if (PF.exists()) {
				BufferedReader pfr = new BufferedReader(new FileReader(PF));
				String line = pfr.readLine();

				while((line = pfr.readLine()) != null){
					ArrayList<String> p_info = new ArrayList<String>(Arrays.asList(line.split(",")));
					Professor P = new Professor(
							p_info.get(0)  //교수이름
					);

					//System.out.println("디버깅5 : " + p_info.get(0));

					//System.out.print("디버깅6 : ");
					if(p_info.size() == 2) {
						ArrayList<String> L_info = new ArrayList<String>(Arrays.asList(p_info.get(1).split("/")));    //강의들
						for (int i = 0; i < L_info.size(); i++) {
							Lecture L = FindLecture(Ls, L_info.get(i));
							if(L != null) P.Lectures.add(L);
							//System.out.print(L_info.get(i) + "/"); //디버깅2
						}
					}
					//System.out.println();
					Members.add(P);
				}
			}

			File SF = new File("database/StudentList.csv");
			if (SF.exists()) {
				BufferedReader sfr = new BufferedReader(new FileReader(SF));
				String line = sfr.readLine();

				while((line = sfr.readLine()) != null){
					ArrayList<String> s_info = new ArrayList<String>(Arrays.asList(line.split(",")));
					Student S = new Student(
							s_info.get(0)  //학생이름
					);

					//System.out.println("디버깅7 : " + s_info.get(0));

					//System.out.print("디버깅8 : ");
					if(s_info.size() == 2) {
						ArrayList<String> L_info = new ArrayList<String>(Arrays.asList(s_info.get(1).split("/")));    //강의들
						for (int i = 0; i < L_info.size(); i++) {
							Lecture L = FindLecture(Ls, L_info.get(i));
							if(L != null) S.Lectures.add(L);
							//System.out.print(L_info.get(i) + "/"); //디버깅2
						}
					}
					//System.out.println();
					Members.add(S);
				}
			}


		} catch(IOException e){
			e.printStackTrace();
		}

	}

	//DB에 정보를 작성하는 메소드
	public void writeDB(){
		try {
			File CF = new File("database/ClassroomList.csv");
			if (!CF.exists()) {
				CF.createNewFile();
			}

			File PF = new File("database/ProfessorList.csv");
			if (!PF.exists()) {
				PF.createNewFile();
			}

			File SF = new File("database/StudentList.csv");
			if (!SF.exists()) {
				SF.createNewFile();
			}

			File LF = new File("database/LectureList.csv");
			if (!LF.exists()) {
				LF.createNewFile();
			}

			FileWriter cfw = new FileWriter(CF);
			cfw.write("강의실이름,강의실최대인원,[강의들]\n");
			FileWriter pfw = new FileWriter(PF);
			pfw.write("교수이름,[강의들]\n");
			FileWriter sfw = new FileWriter(SF);
			sfw.write("학생이름,[강의들]\n");
			FileWriter lfw = new FileWriter(LF);
			lfw.write("강의이름,학점,강의최대인원,강의요일,시작시간,종료시간,교수이름,강의실이름,[학생이름]\n");

			for(int i=0; i<Members.size(); i++) {
				Member u = Members.get(i);
				if(u instanceof Room){
					Room c = (Room) u;
					cfw.write(c.name + ",");
					cfw.write(c.maxSize + ",");

					for(int j=0; j<c.Lectures.size(); j++){
						Lecture l = c.Lectures.get(j);
						cfw.write(l.name+"/");
					}

					cfw.write("\n");
				}
				else if(u instanceof Professor){
					Professor p = (Professor) u;
					pfw.write(p.name+ ",");

					for(int j=0; j<p.Lectures.size(); j++){
						Lecture l = p.Lectures.get(j);
						pfw.write(l.name +"/");

						lfw.write(l.name + ",");
						lfw.write(l.Credit + ",");
						lfw.write(l.maxSize + ",");
						lfw.write(l.Day + ",");
						lfw.write(l.sTime + ",");
						lfw.write(l.fTime + ",");
						lfw.write(l.pName + ",");
						lfw.write(l.rName + ",");

						for(int k=0; k<l.sNames.size(); k++){
							lfw.write(l.sNames.get(k) +"/");
						}

						lfw.write("\n");


					}

					pfw.write("\n");
				}
				else if(u instanceof Student){
					Student s = (Student) u;
					sfw.write(s.name + "," );


					for(int j=0; j<s.Lectures.size(); j++){
						Lecture l = s.Lectures.get(j);
						sfw.write(l.name+"/");
					}

					sfw.write("\n");
				}
			}

			cfw.close();
			pfw.close();
			sfw.close();
			lfw.close();


		} catch(IOException e){
			e.printStackTrace();
		}


	}

	//프로그램을 실행했을때 가장먼저 제공되는 서비스
	public void startService() {
		//반복문을 통해 인터페이스 반복
		while(true) {
			try { //예외처리 ( 입력값을 잘못 입력할 경우 )
				System.out.print("[1] 멤버생성, [2] 로그인 [3] 프로그램 종료 : ");
				fKey = Integer.parseInt(input.nextLine()) ;

				if(fKey == 1) MemberJoin(); //회원가입 진행
				else if(fKey == 2) {   //로그인 진행
					System.out.println("\n로그인 페이지");
					
					System.out.print("이름을 입력해주세요 : ");
					strInput = input.nextLine();

					//해당이름을 가진 멤버를 반환
					Member m = FindMember(strInput);
					//해당하는 멤버가 없다면 m는 null값을 저장
					if(m == null) System.out.println("해당하는 멤버가 없습니다.\n");
					//instanceof연산자를 이용하여 각 타입에 맞는 인터페이스를 호출 ( m의 다운캐스팅 진행 )
					else if(m instanceof Room) RoomService((Room) m);    //강의실 인터페이스로 이동
					else if(m instanceof Professor) ProfessorService((Professor) m); //교수 인터페이스로 이동
					else if(m instanceof Student)  StudentService((Student) m); //학생 인터페이스로 이동
				}
				else if(fKey == 3) {   //프로그램 종료 진행
					System.out.println("프로그램을 종료합니다.\n");
					break;
				}
				else System.out.println("입력오류입니다.\n");

			} catch(NumberFormatException ex) {
				System.out.println("입력오류입니다.\n");
			}
		}
	}

	//강의실 서비스
	public void RoomService(Room r) {
		System.out.printf("\n%s강의실 입니다.\n", r.name);
		while(true) {
			try { //예외처리 ( 입력값을 잘못 입력할 경우 )
				System.out.print("[1]강의실정보 확인 [2] 로그아웃 : ");
				fKey =Integer.parseInt(input.nextLine()) ;

				if(fKey == 1) r.View();    //강의실에서 확인할 수 있는 정보 출력
				else if(fKey == 2) {   //강의실 인터페이스 종료
					System.out.println("처음으로 돌아갑니다.\n");
					break;
				}
				else System.out.println("입력오류입니다.\n");


			} catch(NumberFormatException ex) {
				System.out.println("입력오류입니다.\n");
			}
		}

	}

	//교수 서비스
	public void ProfessorService(Professor p) {
		System.out.printf("\n%s교수님 환영합니다!\n", p.name);
		while(true) {
			try {  //예외처리 ( 입력값을 잘못 입력할 경우 )

				System.out.print("[1]교수정보확인 [2]수강신청학생확인 [3]강의생성 [4]강의폐강 [5]로그아웃 : ");
				fKey =Integer.parseInt(input.nextLine()) ;

				if(fKey == 1) p.View();    //교수가 확인할 수 있는 정보 출력
				else if(fKey == 2) {   //수강신청학생 확인 진행
					//현재 교수가 가지고 있는 강의 정보 출력
					p.View();
					System.out.print("어떤 강의의 학생들을 확인하시겠습니까? : ");

					//확인하고싶은 강의 이름을 입력
					Lecture L = FindLecture(p.Lectures, input.nextLine());
					if(L!=null) p.ViewStudent(L);  //해당하는 강의가 있다면 확인 진행
					else System.out.println("입력오류입니다.\n");
				}

				else if(fKey == 3) {   //강의생성 진행
					String Day; //강의 요일
					int Credit, maxSize;   //강의 학점, 최대수강신청인원
					double sTime;  //강의시작시간

					System.out.print("강의 이름 : ");
					strInput = input.nextLine();
					if(strInput.contentEquals("")) {   //예외처리 ( 강의이름에 아무 값이 입력되지않았을때 )
						System.out.println("입력오류입니다.\n");
						continue;
					}

					System.out.print("학점 : ");
					Credit = Integer.parseInt(input.nextLine());
					if(Credit<=0) {    //예외처리 ( 학점수가 음수일때 )
						System.out.println("입력오류입니다.\n");
						continue;
					}

					System.out.print("강의 요일(ex> 월) : ");
					Day = input.nextLine();
					if(Day.contentEquals("")) {    //예외처리 ( 강의요일에 아무값이 입력되지않았을때 )
						System.out.println("입력오류입니다.\n");
						continue;
					}

					System.out.print("강의 시작(n교시) : ");
					sTime = Double.parseDouble(input.nextLine());
					if(sTime<=0) { //예외처리 ( 강의시작시간이 음수일때 )
						System.out.println("입력오류입니다.\n");
						continue;
					}

					System.out.print("수강 신청 정원 : ");
					maxSize = Integer.parseInt(input.nextLine());
					if(maxSize<=0) {   //예외처리 ( 정원이 음수 일때)
						System.out.println("입력오류입니다.\n");
						continue;
					}

					//위에서 입력받은 정보를 가지고 강의 객체 생성 ( 강의 종료시간은 시작시간의 + 학점 - 1 )
					Lecture L = new Lecture(strInput, Credit, Day, sTime, sTime+Credit-1, maxSize, p.name);

					//사용가능한 강의실 목록 출력
					Room r;
					System.out.print("어느 강의실을 선택하시겠습니까? : ");

					//반복문을 이용해 Members에서 강의실 객체 찾기
					for(int i=0; i<Members.size(); i++) {
						//instanceof 연산자를 이용해서 Room의 타입을 가진 강의실 객체 찾기
						if(Members.get(i) instanceof Room) {
							r = (Room) Members.get(i); //찾은 객체를 강의실(r) 타입으로 변환 ( 다운캐스팅 )
							//강의실(r)의 시간대와 충돌하지 않고 and 수강정원을 수용할수있는 강의실 이름 출력
							if(!r.CheckTimeCollision(L) && r.maxSize >= maxSize) System.out.printf("%s ", r.name);
						}
					}

					System.out.print("\n강의실 이름 : ");
					//추가하고 강의실 이름을 입력
					r = (Room) FindMember(input.nextLine());
					if(r==null) {  // 예외처리 ( 해당하는 이름의 강의실이 없을때 )
						System.out.println("해당하는 강의실이 없습니다.\n");
						continue;
					}
					// 예외처리 ( 수용할수있는 강의실이 아닐때 )
					else if(r.CheckTimeCollision(L) || r.maxSize < maxSize) {
						System.out.println("해당 강의실 정원이 부족합니다.\n");
					}
					else {
						L.setRoom(r); //강의에 강의실 정보 추가
						r.AddLecture(L);
						p.AddLecture(L);   //교수의 Lectures에 강의 추가
					}

				}
				else if(fKey == 4) {   //강의제거 진행
					//현재 교수가 가지고 있는 강의 정보 출력
					p.View();
					System.out.print("제거하고 싶은 강의 이름을 입력해주세요 : ");
					Lecture L = FindLecture(p.Lectures, input.nextLine());
					if(L!=null) {
						Room c = (Room) FindMember(L.rName);
						c.DeleteLecture(L);       //강의실에서 강의 제거
						p.DeleteLecture(L);    		//교수 강의 제거
						for(int i=0; i<L.sNames.size(); i++){
							Student s = (Student) FindMember(L.sNames.get(i));
							s.DeleteLecture(L);
						}
						System.out.println("강의가 성공적으로 제거되었습니다.");
					}
					else System.out.println("입력오류입니다.\n");

				}
				else if(fKey == 5) {   //교수 서비스 종료
					System.out.println("처음으로 돌아갑니다.\n");
					break;
				}
				else System.out.println("입력오류입니다.\n");

				System.out.println();

			} catch(NumberFormatException ex) {
				System.out.println("입력오류입니다.\n");
			}
		}


	}

	//학생 서비스
	public void StudentService(Student s) {
		System.out.printf("\n%s학우님 환영합니다!\n", s.name);
		while(true) {
			try {  //예외처리 ( 입력값을 잘못 입력할 경우 )

				System.out.print("[1]학생정보확인 [2]수강신청 [3]수강신청 취소 [4]로그아웃 : ");
				fKey =Integer.parseInt(input.nextLine()) ;

				if(fKey == 1) s.View();    //학생 확인할 수 있는 정보 출력
				else if(fKey == 2) { 	   //수강신청 진행

					//반복문을 이용해 Members에서 교수 객체 찾기
					for(int i=0; i<Members.size(); i++) {
						//instanceof 연산자를 이용해서 Professor의 타입을 가진 교수 객체 찾기
						if(Members.get(i) instanceof Professor) {
							System.out.printf("%s교수님 수업 : ", Members.get(i).name);
							Members.get(i).View(); // 교수 객체가 가지고 있는 정보 출력 ( 오버라이딩 )
						}
					}

					System.out.print("신청하고싶은 교수님이름을 입력하세요 : ");
					strInput = input.nextLine();
					Professor p = (Professor) FindMember(strInput);
					if(p != null) {    //해당하는 교수 객체가 있다면

						//추가하고 싶은 강의 이름 입력
						System.out.print("신청하고싶은 과목이름을 입력하세요 : ");
						Lecture L = FindLecture(p.Lectures, input.nextLine());

						if(L != null) s.AddLecture(L); //강의가 있다면 학생 객체에 강의 추가
						else System.out.println("과목이름 입력오류입니다.");
					}
					else System.out.println("교수님이름 입력오류입니다.");
				}
				else if(fKey == 3) { //수강신청취소 진행
					//학생이 가지고 있는 강의 출력
					s.View();
					System.out.print("수강신청취소하고싶은 강의 이름을 입력하세요. : ");
					Lecture L = FindLecture(s.Lectures, input.nextLine());

					if(L!=null) {
						L.DeleteStudent(s); 	//강의에서 학생 제거
						s.DeleteLecture(L);    //강의가 있다면 학생 객체에서 강의 제거  
						System.out.println("수강신청취소가 완료되었습니다.");
					}
					else System.out.println("입력오류입니다.\n");
				}
				else if(fKey == 4) {   //학생 서비스 종료
					System.out.println("처음으로 돌아갑니다.\n");
					break;
				}
				else System.out.println("입력오류입니다.\n");
				System.out.println();

			} catch(NumberFormatException ex) {
				System.out.println("입력오류입니다.\n");
			}
		}

	}
	

	//메인 함수
	public static void main(String args[]) {
		//프로그램 실행을 위해 UniversityProgram객체 생성
		UniversityProgram Program = new UniversityProgram();

		Program.readDB();
		Program.startService();
		Program.writeDB();
	}
}


