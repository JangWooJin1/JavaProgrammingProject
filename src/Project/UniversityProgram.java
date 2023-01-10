package Project;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class UniversityProgram {
	ArrayList<Member> Members = new ArrayList<Member>();   //���ǽ�, ����, �л� ��ü�� �����ϴ� �迭 ( ��ĳ���� )

	Scanner input = new Scanner(System.in);    //Ű���� �Է��� ���� ��ü
	int fKey;  			//� �۾� ������ �Է¹޴� ����
	String strInput;   //���ڿ��� �Է¹��� ����
	int intInput;     //���ڸ� �Է¹��� ����

	//�ش� �̸��� ���� ����� ã�� ��ȯ���ִ� �޼ҵ�
	public Member FindMember(String name) {
		//����� ����Ű�� ���۷��� ���� null�� �ʱ�ȭ
		Member m = null;

		int i=0;   //�ݺ��� ���� i ���� �� �ʱ�ȭ
		//����� �̸�(name)�� ������ Users�迭���� ��� ã��
		for(; i<Members.size(); i++) {
			//����� �̸�(name)�� �߰ߵǸ� �ݺ��� ����
			if(Members.get(i).name.contentEquals(name)) {
				m = Members.get(i);
				break;
			}
		}

		return m;
	}

	//���� ����Ʈ���� �ش� �̸��� ���� ���Ǹ� ã�� ��ȯ���ִ� �޼ҵ�
	public Lecture FindLecture(ArrayList<Lecture> Ls, String name) {
		Lecture l = null;  //���� ��ü�� ����Ű�� ���۷��� ���� null������ �ʱ�ȭ

		//�ݺ����� ���� LecturesŽ��
		for(int i=0; i<Ls.size(); i++) {
			//���� �̸�(name)�� �߰ߵǸ� �ݺ��� ����
			if(Ls.get(i).name.contentEquals(name)) {
				l = Ls.get(i);
				break;
			}
		}

		return l;
	}

	//����� �߰��ϴ� �޼ҵ�
	public void MemberJoin() {
		System.out.println("\n������� ������");
		try {  //����ó�� ( �Է°��� �߸� �Է��� ��� )
			
			System.out.print("[1] ���ǽ�, [2] ����, [3] �л� ");
			fKey = Integer.parseInt(input.nextLine());
			if ( fKey < 1 || fKey > 3 ) return;

			//�̸� �Է�
			System.out.print("�̸��� �Է����ּ��� : ");
			strInput = input.nextLine();
			if(strInput.contentEquals("")) {   //����ó�� name�� �ƹ��͵� �Էµ������� ���
				System.out.println("�Է¿����Դϴ�.\n");
				return;
			}

			//FindUser�޼ҵ带 �̿��� �ش� �̸��� ���� ����� �����ϴ��� �ľ� ( �ߺ� ȸ������ ���� )
			Member u = FindMember(strInput);
			if(u != null) {    //u�� null�� �ƴ϶�� �ش� �̸��� ���� ����� �̹� �����Ѵٴ� ��
				System.out.println("�̹� �ش� ����� �����մϴ�.\n");
				return;
			}

			if(fKey == 1){ //���ǽ� �߰�
				System.out.print("���ǽ� �ִ� �ο��� �Է����ּ���. : ");
				intInput = Integer.parseInt(input.nextLine());

				Members.add(new Room(strInput,intInput));  //���ǽ� �߰� ( ��ĳ���� )
				System.out.printf("%s���ǽ��� �����Ǿ����ϴ�.\n\n", strInput);
			}
			else if(fKey == 2){    //���� �߰�
				Members.add(new Professor(strInput));  //���� �߰� ( ��ĳ���� )
				System.out.printf("%s������ �����Ǿ����ϴ�.\n\n", strInput);
			}
			else if(fKey == 3){    //�л� �߰�
				Members.add(new Student(strInput));    //�л� �߰� ( ��ĳ���� )
				System.out.printf("%s�л��� �����Ǿ����ϴ�.\n\n", strInput);
			}
		} catch(NumberFormatException ex) {
			System.out.println("�Է¿����Դϴ�.\n");
		}

	}

	//DB���� ������ �о�� �迭�� �߰��ϴ� �޼ҵ�
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
							L_info.get(0), //�����̸�
							Integer.parseInt(L_info.get(1)),   //����
							L_info.get(3), //����
							Double.parseDouble(L_info.get(4)), //���۽ð�
							Double.parseDouble(L_info.get(5)), //����ð�
							Integer.parseInt(L_info.get(2)),   //��������
							L_info.get(6)                 //����
					);
					L.rName = L_info.get(7);               //���ǽ�

					//System.out.println("�����1 : " + L_info.get(0) +"/"+ L_info.get(1) +"/"+ L_info.get(3) +"/"+ L_info.get(4) +"/"+ L_info.get(5) +"/"+ L_info.get(2) +"/"+ L_info.get(6));

					//System.out.print("�����2 : ");
					if(L_info.size() == 9) {
						ArrayList<String> S_info = new ArrayList<String>(Arrays.asList(L_info.get(8).split("/")));    //�л���
						for (int i = 0; i < S_info.size(); i++) {
							L.sNames.add(S_info.get(i));
							//System.out.print(S_info.get(i) + "/"); //�����2
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
							c_info.get(0), //���ǽ��̸�
							Integer.parseInt(c_info.get(1)) //��������
					);

					//System.out.println("�����3 : " + c_info.get(0) +"/"+ c_info.get(1));

					//System.out.print("�����4 : ");
					if(c_info.size() == 3) {
						ArrayList<String> L_info = new ArrayList<String>(Arrays.asList(c_info.get(2).split("/")));    //���ǵ�
						for (int i = 0; i < L_info.size(); i++) {
							Lecture L = FindLecture(Ls, L_info.get(i));
							if(L != null) C.Lectures.add(L);
							//System.out.print(L_info.get(i) + "/"); //�����2
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
							p_info.get(0)  //�����̸�
					);

					//System.out.println("�����5 : " + p_info.get(0));

					//System.out.print("�����6 : ");
					if(p_info.size() == 2) {
						ArrayList<String> L_info = new ArrayList<String>(Arrays.asList(p_info.get(1).split("/")));    //���ǵ�
						for (int i = 0; i < L_info.size(); i++) {
							Lecture L = FindLecture(Ls, L_info.get(i));
							if(L != null) P.Lectures.add(L);
							//System.out.print(L_info.get(i) + "/"); //�����2
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
							s_info.get(0)  //�л��̸�
					);

					//System.out.println("�����7 : " + s_info.get(0));

					//System.out.print("�����8 : ");
					if(s_info.size() == 2) {
						ArrayList<String> L_info = new ArrayList<String>(Arrays.asList(s_info.get(1).split("/")));    //���ǵ�
						for (int i = 0; i < L_info.size(); i++) {
							Lecture L = FindLecture(Ls, L_info.get(i));
							if(L != null) S.Lectures.add(L);
							//System.out.print(L_info.get(i) + "/"); //�����2
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

	//DB�� ������ �ۼ��ϴ� �޼ҵ�
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
			cfw.write("���ǽ��̸�,���ǽ��ִ��ο�,[���ǵ�]\n");
			FileWriter pfw = new FileWriter(PF);
			pfw.write("�����̸�,[���ǵ�]\n");
			FileWriter sfw = new FileWriter(SF);
			sfw.write("�л��̸�,[���ǵ�]\n");
			FileWriter lfw = new FileWriter(LF);
			lfw.write("�����̸�,����,�����ִ��ο�,���ǿ���,���۽ð�,����ð�,�����̸�,���ǽ��̸�,[�л��̸�]\n");

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

	//���α׷��� ���������� ������� �����Ǵ� ����
	public void startService() {
		//�ݺ����� ���� �������̽� �ݺ�
		while(true) {
			try { //����ó�� ( �Է°��� �߸� �Է��� ��� )
				System.out.print("[1] �������, [2] �α��� [3] ���α׷� ���� : ");
				fKey = Integer.parseInt(input.nextLine()) ;

				if(fKey == 1) MemberJoin(); //ȸ������ ����
				else if(fKey == 2) {   //�α��� ����
					System.out.println("\n�α��� ������");
					
					System.out.print("�̸��� �Է����ּ��� : ");
					strInput = input.nextLine();

					//�ش��̸��� ���� ����� ��ȯ
					Member m = FindMember(strInput);
					//�ش��ϴ� ����� ���ٸ� m�� null���� ����
					if(m == null) System.out.println("�ش��ϴ� ����� �����ϴ�.\n");
					//instanceof�����ڸ� �̿��Ͽ� �� Ÿ�Կ� �´� �������̽��� ȣ�� ( m�� �ٿ�ĳ���� ���� )
					else if(m instanceof Room) RoomService((Room) m);    //���ǽ� �������̽��� �̵�
					else if(m instanceof Professor) ProfessorService((Professor) m); //���� �������̽��� �̵�
					else if(m instanceof Student)  StudentService((Student) m); //�л� �������̽��� �̵�
				}
				else if(fKey == 3) {   //���α׷� ���� ����
					System.out.println("���α׷��� �����մϴ�.\n");
					break;
				}
				else System.out.println("�Է¿����Դϴ�.\n");

			} catch(NumberFormatException ex) {
				System.out.println("�Է¿����Դϴ�.\n");
			}
		}
	}

	//���ǽ� ����
	public void RoomService(Room r) {
		System.out.printf("\n%s���ǽ� �Դϴ�.\n", r.name);
		while(true) {
			try { //����ó�� ( �Է°��� �߸� �Է��� ��� )
				System.out.print("[1]���ǽ����� Ȯ�� [2] �α׾ƿ� : ");
				fKey =Integer.parseInt(input.nextLine()) ;

				if(fKey == 1) r.View();    //���ǽǿ��� Ȯ���� �� �ִ� ���� ���
				else if(fKey == 2) {   //���ǽ� �������̽� ����
					System.out.println("ó������ ���ư��ϴ�.\n");
					break;
				}
				else System.out.println("�Է¿����Դϴ�.\n");


			} catch(NumberFormatException ex) {
				System.out.println("�Է¿����Դϴ�.\n");
			}
		}

	}

	//���� ����
	public void ProfessorService(Professor p) {
		System.out.printf("\n%s������ ȯ���մϴ�!\n", p.name);
		while(true) {
			try {  //����ó�� ( �Է°��� �߸� �Է��� ��� )

				System.out.print("[1]��������Ȯ�� [2]������û�л�Ȯ�� [3]���ǻ��� [4]������ [5]�α׾ƿ� : ");
				fKey =Integer.parseInt(input.nextLine()) ;

				if(fKey == 1) p.View();    //������ Ȯ���� �� �ִ� ���� ���
				else if(fKey == 2) {   //������û�л� Ȯ�� ����
					//���� ������ ������ �ִ� ���� ���� ���
					p.View();
					System.out.print("� ������ �л����� Ȯ���Ͻðڽ��ϱ�? : ");

					//Ȯ���ϰ���� ���� �̸��� �Է�
					Lecture L = FindLecture(p.Lectures, input.nextLine());
					if(L!=null) p.ViewStudent(L);  //�ش��ϴ� ���ǰ� �ִٸ� Ȯ�� ����
					else System.out.println("�Է¿����Դϴ�.\n");
				}

				else if(fKey == 3) {   //���ǻ��� ����
					String Day; //���� ����
					int Credit, maxSize;   //���� ����, �ִ������û�ο�
					double sTime;  //���ǽ��۽ð�

					System.out.print("���� �̸� : ");
					strInput = input.nextLine();
					if(strInput.contentEquals("")) {   //����ó�� ( �����̸��� �ƹ� ���� �Էµ����ʾ����� )
						System.out.println("�Է¿����Դϴ�.\n");
						continue;
					}

					System.out.print("���� : ");
					Credit = Integer.parseInt(input.nextLine());
					if(Credit<=0) {    //����ó�� ( �������� �����϶� )
						System.out.println("�Է¿����Դϴ�.\n");
						continue;
					}

					System.out.print("���� ����(ex> ��) : ");
					Day = input.nextLine();
					if(Day.contentEquals("")) {    //����ó�� ( ���ǿ��Ͽ� �ƹ����� �Էµ����ʾ����� )
						System.out.println("�Է¿����Դϴ�.\n");
						continue;
					}

					System.out.print("���� ����(n����) : ");
					sTime = Double.parseDouble(input.nextLine());
					if(sTime<=0) { //����ó�� ( ���ǽ��۽ð��� �����϶� )
						System.out.println("�Է¿����Դϴ�.\n");
						continue;
					}

					System.out.print("���� ��û ���� : ");
					maxSize = Integer.parseInt(input.nextLine());
					if(maxSize<=0) {   //����ó�� ( ������ ���� �϶�)
						System.out.println("�Է¿����Դϴ�.\n");
						continue;
					}

					//������ �Է¹��� ������ ������ ���� ��ü ���� ( ���� ����ð��� ���۽ð��� + ���� - 1 )
					Lecture L = new Lecture(strInput, Credit, Day, sTime, sTime+Credit-1, maxSize, p.name);

					//��밡���� ���ǽ� ��� ���
					Room r;
					System.out.print("��� ���ǽ��� �����Ͻðڽ��ϱ�? : ");

					//�ݺ����� �̿��� Members���� ���ǽ� ��ü ã��
					for(int i=0; i<Members.size(); i++) {
						//instanceof �����ڸ� �̿��ؼ� Room�� Ÿ���� ���� ���ǽ� ��ü ã��
						if(Members.get(i) instanceof Room) {
							r = (Room) Members.get(i); //ã�� ��ü�� ���ǽ�(r) Ÿ������ ��ȯ ( �ٿ�ĳ���� )
							//���ǽ�(r)�� �ð���� �浹���� �ʰ� and ���������� �����Ҽ��ִ� ���ǽ� �̸� ���
							if(!r.CheckTimeCollision(L) && r.maxSize >= maxSize) System.out.printf("%s ", r.name);
						}
					}

					System.out.print("\n���ǽ� �̸� : ");
					//�߰��ϰ� ���ǽ� �̸��� �Է�
					r = (Room) FindMember(input.nextLine());
					if(r==null) {  // ����ó�� ( �ش��ϴ� �̸��� ���ǽ��� ������ )
						System.out.println("�ش��ϴ� ���ǽ��� �����ϴ�.\n");
						continue;
					}
					// ����ó�� ( �����Ҽ��ִ� ���ǽ��� �ƴҶ� )
					else if(r.CheckTimeCollision(L) || r.maxSize < maxSize) {
						System.out.println("�ش� ���ǽ� ������ �����մϴ�.\n");
					}
					else {
						L.setRoom(r); //���ǿ� ���ǽ� ���� �߰�
						r.AddLecture(L);
						p.AddLecture(L);   //������ Lectures�� ���� �߰�
					}

				}
				else if(fKey == 4) {   //�������� ����
					//���� ������ ������ �ִ� ���� ���� ���
					p.View();
					System.out.print("�����ϰ� ���� ���� �̸��� �Է����ּ��� : ");
					Lecture L = FindLecture(p.Lectures, input.nextLine());
					if(L!=null) {
						Room c = (Room) FindMember(L.rName);
						c.DeleteLecture(L);       //���ǽǿ��� ���� ����
						p.DeleteLecture(L);    		//���� ���� ����
						for(int i=0; i<L.sNames.size(); i++){
							Student s = (Student) FindMember(L.sNames.get(i));
							s.DeleteLecture(L);
						}
						System.out.println("���ǰ� ���������� ���ŵǾ����ϴ�.");
					}
					else System.out.println("�Է¿����Դϴ�.\n");

				}
				else if(fKey == 5) {   //���� ���� ����
					System.out.println("ó������ ���ư��ϴ�.\n");
					break;
				}
				else System.out.println("�Է¿����Դϴ�.\n");

				System.out.println();

			} catch(NumberFormatException ex) {
				System.out.println("�Է¿����Դϴ�.\n");
			}
		}


	}

	//�л� ����
	public void StudentService(Student s) {
		System.out.printf("\n%s�п�� ȯ���մϴ�!\n", s.name);
		while(true) {
			try {  //����ó�� ( �Է°��� �߸� �Է��� ��� )

				System.out.print("[1]�л�����Ȯ�� [2]������û [3]������û ��� [4]�α׾ƿ� : ");
				fKey =Integer.parseInt(input.nextLine()) ;

				if(fKey == 1) s.View();    //�л� Ȯ���� �� �ִ� ���� ���
				else if(fKey == 2) { 	   //������û ����

					//�ݺ����� �̿��� Members���� ���� ��ü ã��
					for(int i=0; i<Members.size(); i++) {
						//instanceof �����ڸ� �̿��ؼ� Professor�� Ÿ���� ���� ���� ��ü ã��
						if(Members.get(i) instanceof Professor) {
							System.out.printf("%s������ ���� : ", Members.get(i).name);
							Members.get(i).View(); // ���� ��ü�� ������ �ִ� ���� ��� ( �������̵� )
						}
					}

					System.out.print("��û�ϰ���� �������̸��� �Է��ϼ��� : ");
					strInput = input.nextLine();
					Professor p = (Professor) FindMember(strInput);
					if(p != null) {    //�ش��ϴ� ���� ��ü�� �ִٸ�

						//�߰��ϰ� ���� ���� �̸� �Է�
						System.out.print("��û�ϰ���� �����̸��� �Է��ϼ��� : ");
						Lecture L = FindLecture(p.Lectures, input.nextLine());

						if(L != null) s.AddLecture(L); //���ǰ� �ִٸ� �л� ��ü�� ���� �߰�
						else System.out.println("�����̸� �Է¿����Դϴ�.");
					}
					else System.out.println("�������̸� �Է¿����Դϴ�.");
				}
				else if(fKey == 3) { //������û��� ����
					//�л��� ������ �ִ� ���� ���
					s.View();
					System.out.print("������û����ϰ���� ���� �̸��� �Է��ϼ���. : ");
					Lecture L = FindLecture(s.Lectures, input.nextLine());

					if(L!=null) {
						L.DeleteStudent(s); 	//���ǿ��� �л� ����
						s.DeleteLecture(L);    //���ǰ� �ִٸ� �л� ��ü���� ���� ����  
						System.out.println("������û��Ұ� �Ϸ�Ǿ����ϴ�.");
					}
					else System.out.println("�Է¿����Դϴ�.\n");
				}
				else if(fKey == 4) {   //�л� ���� ����
					System.out.println("ó������ ���ư��ϴ�.\n");
					break;
				}
				else System.out.println("�Է¿����Դϴ�.\n");
				System.out.println();

			} catch(NumberFormatException ex) {
				System.out.println("�Է¿����Դϴ�.\n");
			}
		}

	}
	

	//���� �Լ�
	public static void main(String args[]) {
		//���α׷� ������ ���� UniversityProgram��ü ����
		UniversityProgram Program = new UniversityProgram();

		Program.readDB();
		Program.startService();
		Program.writeDB();
	}
}


