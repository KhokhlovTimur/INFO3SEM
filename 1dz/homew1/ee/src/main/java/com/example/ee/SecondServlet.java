package com.example.ee;

import java.io.*;
import java.lang.annotation.Annotation;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/serv2")
public class SecondServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<title>Menu</title>");
        out.println("<body>");
        Path path = Paths.get(request.getServletContext().getRealPath("WEB-INF/classes/com/example/ee"));
        Files.walk(path).forEach(x -> {
            String[] files = x.toString().split("\\\\");
            String[] paths = x.toString().split("\\.");
                    if (paths[paths.length - 1].equals("class")) {
                        String[] localString = files[files.length - 1].split("\\.");
                        try {
                            Class cl1 = Class.forName("com.example.ee."+localString[localString.length - 2]);
                            Annotation[] annotationsClass = cl1.getAnnotations();
                            for (int i = 0; i < annotationsClass.length; i++) {
                                String[] annot = annotationsClass[i].toString().split("\\.");
                                if (annot[3].subSequence(0, 12).equals("ServletAnnot")) {
                                    ServletAnnot[] annotations = (ServletAnnot[]) cl1.getAnnotationsByType(ServletAnnot.class);
                                    for (ServletAnnot annotation : annotations) {
                                        if (!Objects.equals(annotation.pathToResources(), "")) {
                                            out.println(styleBorders() + "<p><div style=\"background-color: #00FFFF\" class=\"ramka-5\"> <form method=\"get\" action=" + annotation.servletURL() + ">\n" +
                                                    "            <button style=\"\n" +
                                                    "  position: relative;\n" +
                                                    "left: 50%;bottom: 0;\n" +
                                                    "transform: translate(-50%, 0);\"type=\"submit\">Перейти</button>\n" +
                                                    "        </form><img src=" + annotation.pathToResources() + " width=\"250\" height=\"110\" alt=\"\">" +
                                                    "<p align = \"center\"> " + annotation.someInfo()+"</p>" +
                                                    "</div></p>");
                                        } else {
                                            out.println(styleBorders() + "<p><div class=\"ramka-5\"> <form method=\"get\" action=" + annotation.servletURL() + ">\n" +
                                                    "<button style=\" position: relative;left: 50%;bottom: 0;transform: translate(-50%, 0);" +
                                                    "" +
                                                    "  type=\"submit\">Перейти</button>\n" +
                                                    "</form></div></p>");
                                        }
                                    }
                                }
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        out.println("<form action=\"/\">\n" +
                "    <button style=\"position: absolute;\n" +
                "left: 50%; bottom: 0; transform: translate(-50%, 0);\" type=\"submit\">Назад</button>\n" +
                "</form>");
        out.println("</body>");
        out.println("</html>");
    }
    public String styleBorders(){
        return "<style>\n" +
                "        .ramka-5 {\n" +
                "            position: relative;\n" +
                "            z-index: 0;\n" +
                "            float: left;\n" +
                "            width: 250px;\n" +
                "            height: 200px;\n" +
                "            border-radius: 3px;\n" +
                "            overflow: hidden;\n" +
                "            margin: 5px;\n" +
                "        }\n" +
                "        .ramka-5::before {\n" +
                "            content: '';\n" +
                "            position: absolute;\n" +
                "            z-index: -2;\n" +
                "            left: -50%;\n" +
                "            top: -50%;\n" +
                "            width: 200%;\n" +
                "            height: 200%;\n" +
                "            background-color: #BFE2FF;\n" +
                "            background-repeat: no-repeat;\n" +
                "            background-size: 50% 50%, 50% 50%;\n" +
                "            background-position: 0 0, 100% 0, 100% 100%, 0 100%;\n" +
                "            background-image: linear-gradient(#BFE2FF, #BFE2FF), linear-gradient(#337AB7, #337AB7), linear-gradient(#BFE2FF, #BFE2FF), linear-gradient(#337AB7, #337AB7);\n" +
                "            animation: anim-ramka-5 6s linear infinite;\n" +
                "        }\n" +
                "        .ramka-5::after {\n" +
                "            content: '';\n" +
                "            position: absolute;\n" +
                "            z-index: -1;\n" +
                "            left: 3px;\n" +
                "            top: 3px;\n" +
                "            width: calc(100% - 4px);\n" +
                "            height: calc(100% - 4px);\n" +
                "            background: white;\n" +
                "            border-radius: 2px;\n" +
                "        }" +
                "@keyframes anim-ramka-5 {\n" +
                "            100% {\n" +
                "                transform: rotate(1turn);\n" +
                "            }\n" +
                "        }</style>";
    }
}
