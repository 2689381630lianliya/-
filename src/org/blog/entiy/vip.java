package org.blog.entiy;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class vip implements Serializable {
     private Integer sid;
     private Integer grade;
    private Integer course;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "vip{" +
                "sid=" + sid +
                ", grade=" + grade +
                ", course=" + course +
                '}';
    }
}
