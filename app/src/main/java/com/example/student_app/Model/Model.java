package com.example.student_app.Model;
import java.util.LinkedList;
import java.util.List;

public class Model {
    public static final Model instance = new Model();

    private Model(){
        for(int i=0;i<10;i++){
           Student s = new Student("student #"+i, "address #"+i, ""+i, ""+i, false);
            data.add(s);
        }
    }

    List<Student> data = new LinkedList<Student>();

    public List<Student> getAllStudents(){
        return data;
    }

    public void addStudent(Student student){
        data.add(student);
    }

    public Student getStudentById(String studentId) {
        for(Student s : data)
        {
            if(s.getId().equals(studentId))
                return s;
        }

        return null;
    }

    public int getStudentByPosition(String studentId) throws Exception {
        for(int i = 0 ; i< data.size() ; i++)
        {
            if(data.get(i).getId().equals(studentId))
                return i;
        }
        throw new Exception("no id matched");
    }

    public void deleteStudentById(String studentId){
        for(int i = 0 ; i< data.size() ; i++)
        {
            if(data.get(i).getId().equals(studentId))
            {
                data.remove(i);
                return;
            }
        }
    }
}
