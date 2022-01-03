package lk.SipsewanaInstitute.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.util.List;

@Entity
public class Registration implements SuperEntity{
    @Id
    private int regNo;
    private Date regDate;
    private double regFee;

    @ManyToOne
    private Student student;

    @ManyToMany
    private List<Course> course;

    public Registration() {
    }

    public Registration(int regNo, Date regDate, double regFee) {
        this.regNo = regNo;
        this.regDate = regDate;
        this.regFee = regFee;
    }

    public Registration(int regNo, Date regDate, double regFee, Student student, List<Course> course) {
        this.regNo = regNo;
        this.regDate = regDate;
        this.regFee = regFee;
        this.student = student;
        this.course = course;
    }


    public int getRegNo() {
        return regNo;
    }

    public void setRegNo(int regNo) {
        this.regNo = regNo;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public double getRegFee() {
        return regFee;
    }

    public void setRegFee(double regFee) {
        this.regFee = regFee;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCourse(List<Course> course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "regNo=" + regNo +
                ", regDate=" + regDate +
                ", regFee=" + regFee +
                ", student=" + student +
                ", course=" + course +
                '}';
    }
}
