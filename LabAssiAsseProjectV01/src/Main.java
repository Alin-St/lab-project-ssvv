

import console.*;
import domain.*;
import repository.*;
import service.*;
import validation.*;

public class Main {
    public static void main(String[] args) {
        // Disable to skip tests.
        doTests();

        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        UI consola = new UI(service);
        consola.run();

        //PENTRU GUI
        // de avut un check: daca profesorul introduce sau nu saptamana la timp
        // daca se introduce nota la timp, se preia saptamana din sistem
        // altfel, se introduce de la tastatura
    }

    static void doTests() {
        testAddStudentWithGreaterId();
        testAddStudentWithSameId();
    }

    static void testAddStudentWithGreaterId() {
        // Arrange
        Validator<Student> studentValidator = new StudentValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");

        var stud1 = new Student("100", "stud1", 936);
        studentValidator.validate(stud1);
        fileRepository1.save(stud1);

        // Act
        var stud2 = new Student("101", "stud1", 936);
        studentValidator.validate(stud2);
        fileRepository1.save(stud2);

        boolean success = true;
        try {
            fileRepository1.save(stud1);
        } catch (Exception ex) {
            success = false;
        }

        // Assert
        if (success)
            System.out.println("testAddStudentWithGreaterId passed");
        else
            System.out.println("testAddStudentWithGreaterId failed");
    }

    static void testAddStudentWithSameId() {
        // Arrange
        Validator<Student> studentValidator = new StudentValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");

        var stud1 = new Student("100", "stud1", 936);
        studentValidator.validate(stud1);
        fileRepository1.save(stud1);

        // Act
        var stud2 = new Student("100", "stud1", 936);
        studentValidator.validate(stud2);
        fileRepository1.save(stud2);

        boolean success = true;
        try {
            fileRepository1.save(stud1);
        } catch (Exception ex) {
            success = false;
        }

        // Assert
        if (success)
            System.out.println("testAddStudentWithSameId passed");
        else
            System.out.println("testAddStudentWithSameId failed");
    }
}
