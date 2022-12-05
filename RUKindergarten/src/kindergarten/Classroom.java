package kindergarten;

import javax.crypto.MacSpi;

/**
 * This class represents a Classroom, with:
 * - an SNode instance variable for students in line,
 * - an SNode instance variable for musical chairs, pointing to the last student in the list,
 * - a boolean array for seating availability (eg. can a student sit in a given seat), and
 * - a Student array parallel to seatingAvailability to show students filed into seats 
 * --- (more formally, seatingAvailability[i][j] also refers to the same seat in studentsSitting[i][j])
 * 
 * @author Ethan Chou
 * @author Kal Pandit
 * @author Maksims Kurjanovics Kravcenko
 */
public class Classroom {
    private SNode studentsInLine;             // when students are in line: references the FIRST student in the LL
    private SNode musicalChairs;              // when students are in musical chairs: references the LAST student in the CLL
    private boolean[][] seatingAvailability;  // represents the classroom seats that are available to students
    private Student[][] studentsSitting;      // when students are sitting in the classroom: contains the students

    /**
     * Constructor for classrooms. Do not edit.
     * @param l passes in students in line
     * @param m passes in musical chairs
     * @param a passes in availability
     * @param s passes in students sitting
     */
    public Classroom ( SNode l, SNode m, boolean[][] a, Student[][] s ) {
		studentsInLine      = l;
        musicalChairs       = m;
		seatingAvailability = a;
        studentsSitting     = s;
	}
    /**
     * Default constructor starts an empty classroom. Do not edit.
     */
    public Classroom() {
        this(null, null, null, null);
    }

    /**
     * This method simulates students coming into the classroom and standing in line.
     * 
     * Reads students from input file and inserts these students in alphabetical 
     * order to studentsInLine singly linked list.
     * 
     * Input file has:
     * 1) one line containing an integer representing the number of students in the file, say x
     * 2) x lines containing one student per line. Each line has the following student 
     * information separated by spaces: FirstName LastName Height
     * 
     * @param filename the student information input file
     */
    public void makeClassroom ( String filename ) {

        // WRITE YOUR CODE HERE
        StdIn.setFile(filename); // imports file
        
        int numStudents = StdIn.readInt(); // reads first int in file and sets that to
        Student[] studentArray = new Student[numStudents]; // creates an array of type Student of size 
        
        

        for (int i = 0; i < numStudents; i++){ // traverses through the file starting with the first line

            String first = StdIn.readString(); // reads first String that represents first Name
            String last = StdIn.readString(); // reads second String that represents last Name
            int height = StdIn.readInt(); // reads integer that represents height
            
            Student studentObject = new Student(first, last, height); // populates the student object with the variables we just declared
            
            studentArray[i] = studentObject; // stores that object in the student array

            
            
        }

        for (int i = 0; i < studentArray.length;  i++){
            for (int j = i + 1; j < studentArray.length; j++){
                if (studentArray[i].compareNameTo(studentArray[j]) > 0){
                    Student temp = studentArray[i];
                    studentArray[i] = studentArray[j];
                    studentArray[j] = temp;
                }
            }
        }

        for (int i = studentArray.length - 1; i >= 0; i--){
            studentsInLine = new SNode(studentArray[i],studentsInLine);
        }
        /*for (SNode ptr = studentsInLine; ptr.getNext() != null; ptr = ptr.getNext()){
            Student info1 = ptr.getStudent();
            Student info2 = ptr.getNext().getStudent();
            if (info1.compareNameTo(info2) > 0){
                Student temp = info1;
                info1 = info2;
                info2 = temp;
            }
        }*/
        

        /*for (SNode ptr = studentsInLine; ptr != null; ptr = ptr.getNext()){
            for (SNode ptr2 = studentsInLine.getNext(); ptr2 != null; ptr2.getNext()){

                if (ptr.getStudent().compareNameTo(ptr2.getStudent()) > 0){

                    Student temp = ptr.getStudent();
                    System.out.println(temp);
                    System.out.println();
                    //ptr.getStudent() = ptr2.getStudent();
                    //ptr2.getStudent() = temp;

                }
            }
        }*/
    }

    /**
     * 
     * This method creates and initializes the seatingAvailability (2D array) of 
     * available seats inside the classroom. Imagine that unavailable seats are broken and cannot be used.
     * 
     * Reads seating chart input file with the format:
     * An integer representing the number of rows in the classroom, say r
     * An integer representing the number of columns in the classroom, say c
     * Number of r lines, each containing c true or false values (true denotes an available seat)
     *  
     * This method also creates the studentsSitting array with the same number of
     * rows and columns as the seatingAvailability array
     * 
     * This method does not seat students on the seats.
     * 
     * @param seatingChart the seating chart input file
     */
    public void setupSeats(String seatingChart) {

	// WRITE YOUR CODE HERE
    StdIn.setFile(seatingChart);
    
    int row = StdIn.readInt();
    int col = StdIn.readInt();

    seatingAvailability = new boolean[row][col];

    for (int i = 0; i < row; i++){
        for (int j = 0; j < col; j++){
            boolean availableSeat = StdIn.readBoolean();
            
            if (availableSeat == true){
                seatingAvailability[i][j] = true;
            }
            if (availableSeat == false){
                seatingAvailability[i][j] = false;
            }
        }
    }
    studentsSitting = new Student[row][col];
}

    /**
     * 
     * This method simulates students taking their seats in the classroom.
     * 
     * 1. seats any remaining students from the musicalChairs starting from the front of the list
     * 2. starting from the front of the studentsInLine singly linked list
     * 3. removes one student at a time from the list and inserts them into studentsSitting according to
     *    seatingAvailability
     * 
     * studentsInLine will then be empty
     */
    public void seatStudents () {

	// WRITE YOUR CODE HERE

    /*for (int i = 0; i < seatingAvailability.length; i++){
        for (int j = 0; j < seatingAvailability[0].length; j++){
            if (seatingAvailability[i][j]){
                if (musicalChairs != null){
                    studentsSitting[i][j] = musicalChairs.getStudent();
                    musicalChairs = musicalChairs.getNext();
                }
                else if (studentsInLine != null){
                    studentsSitting[i][j] = studentsInLine.getStudent();
                    studentsInLine = studentsInLine.getNext();
                }
            }
        }
    }*/

    /*SNode temp = studentsInLine;

    for (int i = 0; i < seatingAvailability.length; i++){
        for (int j = 0; j < seatingAvailability[0].length; j++){
            if (seatingAvailability[i][j] == true){
                if (musicalChairs != null){
                
                    studentsSitting[i][j] = musicalChairs.getStudent();
                    musicalChairs = musicalChairs.getNext();
                }
            }
            if (temp != null){
            if (seatingAvailability[i][j] == true){
                studentsSitting[i][j] = temp.getStudent();
                temp = temp.getNext();
                seatingAvailability[i][j] = false;
                //System.out.println(studentsInLine.getNext().getStudent().getFullName());
                //studentsInLine = studentsInLine.getNext();
                //SNode temp = studentsInLine;
                //temp = temp.getNext();
                //temp.setNext(temp.getNext());
            }
        }
    }
    }
    studentsInLine = null;*/
    for (int i = 0; i < seatingAvailability.length; i++){
        for (int j = 0; j < seatingAvailability[0].length; j++){
            if (seatingAvailability[i][j] == true){
                if (musicalChairs != null){

                    //seatingAvailability[i][j] = false;


                    studentsSitting[i][j] = musicalChairs.getStudent();
                    musicalChairs = musicalChairs.getNext();
                    musicalChairs = null;
                }
                else if (studentsInLine != null){
                    studentsSitting[i][j] = studentsInLine.getStudent();
                    studentsInLine = studentsInLine.getNext();


                    
                    //seatingAvailability[i][j] = false;
                }
            }
        }
    }
    studentsInLine = null;
}

    /**
     * Traverses studentsSitting row-wise (starting at row 0) removing a seated
     * student and adding that student to the end of the musicalChairs list.
     * 
     * row-wise: starts at index [0][0] traverses the entire first row and then moves
     * into second row.
     */

    public void insertMusicalChairs () {
        
        // WRITE YOUR CODE HERE
        //int count = 1;
        SNode front = new SNode();
        for (int i = 0; i < studentsSitting.length; i++){
            for (int j = 0; j < studentsSitting[0].length; j++){
                if (studentsSitting[i][j] == null){
                    continue;
                }
                SNode s = new SNode();
                s.setStudent(studentsSitting[i][j]);
                
                
                
                if (musicalChairs != null){
                    s.setNext(musicalChairs.getNext());
                    musicalChairs.setNext(s);
                   
                }
                else{
                    s.setNext(s);
                    front = s;
                    
                    
                }
                musicalChairs = s;
                
                studentsSitting[i][j] = null;
            }
        }
        
        musicalChairs.setNext(front);
        

        /*for (int i = 0; i < studentsSitting.length; i++){
            for (int j = 0; j < studentsSitting[0].length; j++){
                if (studentsSitting[i][j] != null){
                    
                    Student s = studentsSitting[i][j];
                    SNode newNode = new SNode(s, studentsInLine);
                    if (musicalChairs == null){
                        newNode.setNext(newNode);
                        musicalChairs = newNode;
                    }
                    else{
                        newNode.setNext(musicalChairs.getNext());
                        musicalChairs.setNext(newNode);
                    }
                    studentsSitting[i][j] = null;
            }
        }
    }*/
    }
                    

    /**
     * 
     * This method repeatedly removes students from the musicalChairs until there is only one
     * student (the winner).
     * 
     * Choose a student to be elimnated from the musicalChairs using StdRandom.uniform(int b),
     * where b is the number of students in the musicalChairs. 0 is the first student in the 
     * list, b-1 is the last.
     * 
     * Removes eliminated student from the list and inserts students back in studentsInLine 
     * in ascending height order (shortest to tallest).
     * 
     * The last line of this method calls the seatStudents() method so that students can be seated.
     */
    public void playMusicalChairs() {

        // WRITE YOUR CODE HERE
        /*int count = 1;
        SNode current = musicalChairs;
        SNode prev = musicalChairs;

        while (musicalChairs != musicalChairs.getNext()){
            count++;
            current = current.getNext();
            int studentPos = StdRandom.uniform(count);
            if (studentPos == count){
                prev = current;
                prev.setNext(musicalChairs.getNext());
            }
            else{
                for (int i = 0; i < studentPos; i++){
                prev = current;
                prev = prev.getNext();
            }
            count--;
        }
    }*/
        int count = 1;
        SNode curr = musicalChairs;
        while (curr.getNext() != musicalChairs){
            count++;
            curr = curr.getNext();
        }
        for (int i = 0; i < count - 1; i++){
            int studentsRem = count - i;
            int studentPos = StdRandom.uniform(studentsRem);
            SNode head = musicalChairs.getNext();
            if (studentPos == 0){
                head = musicalChairs;
            }
                for(int j = 0; j < studentPos-1; j++){
                    head = head.getNext();
                }
                    SNode s = head.getNext();
                    head.setNext(s.getNext());
                    Student studentInfo = s.getStudent();
                        if (s == musicalChairs){
                            musicalChairs = head;
                        }
                            if (studentsInLine != null){
                                SNode temp = studentsInLine;
                                if ((temp.getStudent().getHeight() - studentInfo.getHeight()) >= 0){
                                    s.setNext(temp);
                                    studentsInLine = s;
                                }
                                    else {
                                        SNode current = temp;
                                            while(current.getNext() != null && (current.getNext().getStudent().getHeight() - studentInfo.getHeight() < 0)){
                                                current = current.getNext();
                                            }
                                        s.setNext(current.getNext()); 
                                        current.setNext(s);
                                    }
                            }
                                else {
                                    studentsInLine = s;
                                        s.setNext(null);
            }
        }
        seatStudents();
    }
     
    /**
     * Insert a student to wherever the students are at (ie. whatever activity is not empty)
     * Note: adds to the end of either linked list or the next available empty seat
     * @param firstName the first name
     * @param lastName the last name
     * @param height the height of the student
     */
    public void addLateStudent ( String firstName, String lastName, int height ) {
        
        // WRITE YOUR CODE HERE
        SNode studentNode = new SNode();
        Student s = new Student(firstName, lastName, height);
        studentNode.setStudent(s);
        if (studentsInLine != null){ // if students are in line
            SNode head = studentsInLine;
            //SNode last = studentsInLine;
            while (head.getNext() != null){
                head = head.getNext();
            }
            head.setNext(studentNode);
            studentNode.setNext(null);
            //last.setNext(newNode); works
        }
        else if (musicalChairs != null){ // if students are in musical chairs
            studentNode.setNext(musicalChairs.getNext());
            musicalChairs.setNext(studentNode);
            musicalChairs =  studentNode;
            /*SNode head = musicalChairs.getNext();
            SNode newNode = new SNode(s, musicalChairs);
            musicalChairs.setNext(newNode);
            musicalChairs = newNode;
            musicalChairs.setNext(head); // works*/
        }
        else if (musicalChairs == null && studentsInLine == null){ // if students are sitting
            for (int i = 0; i < studentsSitting.length; i++){
                for (int j = 0; j < studentsSitting[0].length; j++){
                    if (s != null && seatingAvailability[i][j] == true){
                        seatingAvailability[i][j] = false;
                        studentsSitting[i][j] = s;
                        s = null;
                    }
                }
            } // works
        }
    }

    /**
     * A student decides to leave early
     * This method deletes an early-leaving student from wherever the students 
     * are at (ie. whatever activity is not empty)
     * 
     * Assume the student's name is unique
     * 
     * @param firstName the student's first name
     * @param lastName the student's last name
     */
    public void deleteLeavingStudent ( String firstName, String lastName ) {

        // WRITE YOUR CODE HERE
        
        Student obj = new Student(firstName, lastName, 0);
        String fullName = obj.getFullName();
        if (musicalChairs != null){
            SNode head = musicalChairs;
            SNode prev = null;
            Student s = musicalChairs.getStudent();
            if (fullName.equals(s.getFullName())){
                if (musicalChairs.getNext() == musicalChairs){
                    musicalChairs = null;
                }
            else{
                SNode curr = musicalChairs.getNext();
                while (curr.getNext() != musicalChairs){
                    curr = curr.getNext();
                }
                curr.setNext(musicalChairs.getNext());
                musicalChairs = curr;
            }
        }
            else{
                while (head.getNext() != musicalChairs){
                    Student deleteLeavingS = head.getStudent();
                    if (fullName.equals(deleteLeavingS.getFullName())){
                        prev.setNext(head.getNext());
                        break;
                    }
                    prev = head;
                    head = head.getNext();
                }
            }
            }
            else if (studentsInLine != null){
                SNode prev = null;
                SNode head = studentsInLine;
                while (head != null){
                    Student deleteLeavingS = head.getStudent();
                    if (fullName.equals(deleteLeavingS.getFullName())){
                        if (prev != null){
                            prev.setNext(head.getNext());
                        }
                        else{
                            studentsInLine = head.getNext();
                        }
                        break;
                    }
                    prev = head;
                    head = head.getNext();
                }
            }
            else{
                for (int i = 0; i < studentsSitting.length; i++){
                    for (int j = 0; j < studentsSitting[0].length; j++){
                        Student student = studentsSitting[i][j];
                        if (student != null){
                            if (fullName.equals(student.getFullName())){
                                studentsSitting[i][j] = null;
                                seatingAvailability[i][j] = true;
                            }
                        }
                }
            }
        }
            
    }

    

    /**
     * Used by driver to display students in line
     * DO NOT edit.
     */
    public void printStudentsInLine () {

        //Print studentsInLine
        StdOut.println ( "Students in Line:" );
        if ( studentsInLine == null ) { StdOut.println("EMPTY"); }

        for ( SNode ptr = studentsInLine; ptr != null; ptr = ptr.getNext() ) {
            StdOut.print ( ptr.getStudent().print() );
            if ( ptr.getNext() != null ) { StdOut.print ( " -> " ); }
        }
        StdOut.println();
        StdOut.println();
    }

    /**
     * Prints the seated students; can use this method to debug.
     * DO NOT edit.
     */
    public void printSeatedStudents () {

        StdOut.println("Sitting Students:");

        if ( studentsSitting != null ) {
        
            for ( int i = 0; i < studentsSitting.length; i++ ) {
                for ( int j = 0; j < studentsSitting[i].length; j++ ) {

                    String stringToPrint = "";
                    if ( studentsSitting[i][j] == null ) {

                        if (seatingAvailability[i][j] == false) {stringToPrint = "X";}
                        else { stringToPrint = "EMPTY"; }

                    } else { stringToPrint = studentsSitting[i][j].print();}

                    StdOut.print ( stringToPrint );
                    
                    for ( int o = 0; o < (10 - stringToPrint.length()); o++ ) {
                        StdOut.print (" ");
                    }
                }
                StdOut.println();
            }
        } else {
            StdOut.println("EMPTY");
        }
        StdOut.println();
    }

    /**
     * Prints the musical chairs; can use this method to debug.
     * DO NOT edit.
     */
    public void printMusicalChairs () {
        StdOut.println ( "Students in Musical Chairs:" );

        if ( musicalChairs == null ) {
            StdOut.println("EMPTY");
            StdOut.println();
            return;
        }
        SNode ptr;
        for ( ptr = musicalChairs.getNext(); ptr != musicalChairs; ptr = ptr.getNext() ) {
            StdOut.print(ptr.getStudent().print() + " -> ");
        }
        if ( ptr == musicalChairs) {
            StdOut.print(musicalChairs.getStudent().print() + " - POINTS TO FRONT");
        }
        StdOut.println();
    }

    /**
     * Prints the state of the classroom; can use this method to debug.
     * DO NOT edit.
     */
    public void printClassroom() {
        printStudentsInLine();
        printSeatedStudents();
        printMusicalChairs();
    }

    /**
     * Used to get and set objects.
     * DO NOT edit.
     */

    public SNode getStudentsInLine() { return studentsInLine; }
    public void setStudentsInLine(SNode l) { studentsInLine = l; }

    public SNode getMusicalChairs() { return musicalChairs; }
    public void setMusicalChairs(SNode m) { musicalChairs = m; }

    public boolean[][] getSeatingAvailability() { return seatingAvailability; }
    public void setSeatingAvailability(boolean[][] a) { seatingAvailability = a; }

    public Student[][] getStudentsSitting() { return studentsSitting; }
    public void setStudentsSitting(Student[][] s) { studentsSitting = s; }

}
