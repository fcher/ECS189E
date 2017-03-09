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
 * Created by Faraz on 3/8/2017.
 */
public class StudentTest {
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
    public void testRegister_1(){
        this.admin.createClass("60", 2017,"Devanbu",10);
        this.student.registerForClass("Tom","50", 2017);
        assertFalse(this.student.isRegisteredFor("Tom","50",2017));
    }

    @Test
    public void testRegister_2(){
        this.admin.createClass("60", 2017,"Devanbu",0);
        this.student.registerForClass("Tom","60", 2017);
        assertFalse(this.student.isRegisteredFor("Tom","60",2017));
    }

    @Test
    public void testRegister(){
        this.admin.createClass("60", 2017,"Devanbu",30);
        this.student.registerForClass("Tom","60", 2017);
        assertTrue(this.student.isRegisteredFor("Tom","60",2017));
    }

    @Test
    public void testDrop_1(){
        this.admin.createClass("Test", 2017, "Instructor", 20);
        this.student.dropClass("Tom","Test",2017);
        assertTrue(this.student.isRegisteredFor("Tom","Test",2017));
    }

    @Test
    public void testDrop_2(){
        this.admin.createClass("Test", 2016, "Instructor", 20);
        this.student.registerForClass("Tom","Test", 2016);
        this.student.dropClass("Tom","Test",2016);
        assertTrue(this.student.isRegisteredFor("Tom","Test",2016));
    }

    @Test
    public void testDrop(){
        this.admin.createClass("Test", 2017, "Instructor", 20);
        this.student.registerForClass("Tom","Test", 2017);
        this.student.dropClass("Tom","Test",2017);
        assertFalse(this.student.isRegisteredFor("Tom","Test",2017));
    }

    @Test
    public void testhw_1() {
        this.admin.createClass("Test", 2017,"Instructor", 30);
        this.prof.addHomework("Instructor","Test",2017,"hw1","descr");
        this.student.submitHomework("Tom","hw1","ans","Test", 2017);
        assertFalse(this.student.hasSubmitted("Tom","hw1","Test",2017));
    }

    @Test
    public void testhw_2() {
        this.admin.createClass("Test", 2016,"Instructor", 30);
        this.student.registerForClass("Tom","Test", 2016);
        this.prof.addHomework("Instructor","Test",2016,"hw1","descr");
        this.student.submitHomework("Tom","hw1","ans","Test", 2016);
        assertFalse(this.student.hasSubmitted("Tom","hw1","Test",2016));
    }

    @Test
    public void testhw_3() {
        this.admin.createClass("Test", 2017,"Instructor", 30);
        this.student.registerForClass("Tom","Test", 2017);
        this.student.submitHomework("Tom","hw1","ans","Test", 2017);
        assertFalse(this.student.hasSubmitted("Tom","hw1","Test",2017));
    }

    @Test
    public void testhw() {
        this.admin.createClass("Test", 2017,"Instructor", 30);
        this.student.registerForClass("Tom","Test", 2017);
        this.prof.addHomework("Instructor", "Test", 2017, "hw1", "descr");
        this.student.submitHomework("Tom","hw1","ans","Test", 2017);
        assertTrue(this.student.hasSubmitted("Tom","hw1","Test",2017));
    }

}