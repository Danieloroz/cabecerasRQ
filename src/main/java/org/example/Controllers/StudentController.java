package org.example.Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.mapping.dto.StudentDto;
import repository.repositoryImpl.StudentRepositoryImp;
import repository.repositoryImpl.StudentRespositoryLogicImpl;
import services.StudentService;
import services.servicesImpl.StudentServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "studentController", value = "/student-form")

public class StudentController extends HttpServlet {

    public StudentRespositoryLogicImpl studentRepository;
    public StudentService service;

    public StudentController() {
        studentRepository = new StudentRespositoryLogicImpl();
        service = new StudentServiceImpl(studentRepository);
    }

    private String message;

    public void init() {
        message = "Students";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Students</h1>");
        out.println(service.list());
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String semester = req.getParameter("semester");
        String career = req.getParameter("career");
        StudentDto student = new StudentDto(1L, name, email,semester,career);
        service.update(student);
        System.out.println(service.list());

        try (PrintWriter out = resp.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <head>");
            out.println("        <meta charset=\"UTF-8\">");
            out.println("        <title>Resultado form</title>");
            out.println("    </head>");
            out.println("    <body>");
            out.println("        <h1>Resultado form!</h1>");

            out.println("        <ul>");
            out.println("            <li>Name: " + name + "</li>");
            out.println("            <li>Email: " + email + "</li>");
            out.println("            <li>Semester: " + semester + "</li>");
            out.println("            <li>Career: " + career + "</li>");
            out.println("        </ul>");
            out.println("    </body>");
            out.println("</html>");
        }
    }

    public void destroy() {
    }
}