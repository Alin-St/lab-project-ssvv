package ssvv.example.repository;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ssvv.example.domain.Student;
import ssvv.example.validation.Validator;

public class StudentXMLRepository extends AbstractXMLRepository<String, Student> {

    public StudentXMLRepository(Validator<Student> validator, String XMLfilename) {
        super(validator, XMLfilename);
        loadFromXmlFile();
    }

    protected Element getElementFromEntity(Student student, Document XMLdocument) {
        Element element = XMLdocument.createElement("student");
        element.setAttribute("ID", student.getID());

        element.appendChild(createElement(XMLdocument, "Nume", student.getNume()));
        element.appendChild(createElement(XMLdocument, "Grupa", String.valueOf(student.getGrupa())));

        return element;
    }

    protected Student getEntityFromNode(Element node) {
        String ID = node.getAttributeNode("ID").getValue();
        String nume = node.getElementsByTagName("Nume").item(0).getTextContent();
        int grupa = Integer.parseInt(node.getElementsByTagName("Grupa").item(0).getTextContent());

        return new Student(ID, nume, grupa);
    }

    public void clearAllStudents() {
        clearAllEntities();
    }
}
