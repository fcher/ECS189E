import api.IAdmin;
import api.IInstructor;
import api.IStudent;
import api.core.impl.Admin;
import api.core.impl.Instructor;
import api.core.impl.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
 * Created by scifa on 3/8/2017.
 */
public class InstructorTest {
    private IAdmin admin;
    private IStudent student;
    private IInstructor prof;

    @Before
    public void setup() {
        this.admin = new Admin();
        this.student = new Student();
        this.prof = new Instructor();
    }

    @Test
    public void testAdd() {
        this.admin.createClass("60", 2017,"Sean", 30);
        this.admin.createClass("40", 2017, "Hellendoorn", 30);
        this.prof.addHomework("Sean","40", 2017, "hw1", "descr");
        assertFalse(this.prof.homeworkExists("40", 2017, "hw1"));
    }

    @Test
    public void testAssign_1() {
        this.admin.createClass("60", 2017,"Sean", 30);
        this.admin.createClass("40", 2017, "Hellendoorn", 30);
        this.student.registerForClass("Tim", "40", 2017);
        this.prof.addHomework("Hellendoorn","40", 2017, "hw1", "descr");
        this.student.submitHomework("Tim", "hw1", "ans", "40", 2017);
        this.prof.assignGrade("Sean", "40", 2017, "hw1", "Tim", 85);
        assertFalse(this.prof.getGrade("40", 2017, "hw1", "Tim") != null);
    }

    @Test
    public void testAssign_2() {
        this.admin.createClass("60", 2017,"Sean", 30);
        this.admin.createClass("40", 2017, "Hellendoorn", 30);
        this.student.registerForClass("Tim", "40", 2017);
        this.student.submitHomework("Tim", "hw1", "ans", "40", 2017);
        this.prof.assignGrade("Hellendoorn", "40", 2017, "hw1", "Tim", 85);
        assertFalse(this.prof.getGrade("40", 2017, "hw1", "Tim") != null);
    }

    @Test
    public void testAssign_3() {
        this.admin.createClass("60", 2017,"Sean", 30);
        this.admin.createClass("40", 2017, "Hellendoorn", 30);
        this.student.registerForClass("Tim", "40", 2017);
        this.prof.addHomework("Hellendoorn","40", 2017, "hw1", "descr");
        this.prof.assignGrade("Hellendoorn", "40", 2017, "hw1", "Tim", 85);
        assertFalse(this.prof.getGrade("40", 2017, "hw1", "Tim") != null);
    }

    @Test
    public void testAssign() {
        this.admin.createClass("60", 2017,"Sean", 30);
        this.admin.createClass("40", 2017, "Hellendoorn", 30);
        this.student.registerForClass("Tim", "40", 2017);
        this.prof.addHomework("Hellendoorn","40", 2017, "hw1", "descr");
        this.student.submitHomework("Tim", "hw1", "ans", "40", 2017);
        this.prof.assignGrade("Hellendoorn", "40", 2017, "hw1", "Tim", 85);
        assertTrue(this.prof.getGrade("40", 2017, "hw1", "Tim") != null);
    }
}

