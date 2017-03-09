import api.IAdmin;
import api.IStudent;
import api.core.impl.Admin;
import api.core.impl.Student;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by scifa on 3/8/2017.
 */
public class StudentTest {
    private IAdmin admin;
    private IStudent student;

    @Before
    public void setup() {
        this.admin = new Admin();
        this.student = new Student();
    }

    @Test
    public void testRegister(){
        this.admin.createClass("60", 2017,"Devanbu",50);
    }
}