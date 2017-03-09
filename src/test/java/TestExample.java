import api.IAdmin;
import api.core.impl.Admin;
import api.core.impl.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Vincent on 23/2/2017.
 */
public class TestExample {

    private IAdmin admin;
    private Student student;

    @Before
    public void setup() {
        this.admin = new Admin();
        this.student = new Student();
    }

    @Test
    public void testMakeClass() {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        assertTrue(this.admin.classExists("Test", 2017));
    }

    @Test
    public void testMakeClass2() {
        this.admin.createClass("Test", 2016, "Instructor", 15);
        assertFalse(this.admin.classExists("Test", 2016));
    }

    @Test
    public void testCap() {
        this.admin.createClass("Test", 2017, "Instructor", -1);
        assertFalse(this.admin.getClassCapacity("Test", 2017) <= 0);
    }

    @Test
    public void testInst() {
        int no_of_courses = 0;
        this.admin.createClass("60", 2017, "Devanbu", 50);
        this.admin.createClass("50", 2017, "Devanbu", 50);
        this.admin.createClass("40", 2017, "Devanbu", 50);
        if (this.admin.getClassInstructor("40",2017).equals("Devanbu"))
            no_of_courses++;
        if (this.admin.getClassInstructor("50",2017).equals("Devanbu"))
            no_of_courses++;
        if (this.admin.getClassInstructor("60",2017).equals("Devanbu"))
            no_of_courses++;
        assertFalse(no_of_courses > 2);
    }

    @Test
    public void testUnique() {
        this.admin.createClass("Test", 2017, "Devanbu", 30);
        this.admin.createClass("Test", 2017, "Davis", 30);
        assertFalse(this.admin.getClassInstructor("Test", 2017).equals("Davis"));
    }

    @Test
    public void testChange() {
        this.admin.createClass("60", 2017, "Devanbu", 50);
        this.student.registerForClass("Tom", "60", 2017);
        this.student.registerForClass("Nick", "60", 2017);
        this.student.registerForClass("Tim", "60", 2017);
        this.student.registerForClass("Kevin", "60", 2017);
        this.admin.changeCapacity("60", 2017, 1);
        assertFalse(this.admin.classExists("60", 2017));
    }

}

