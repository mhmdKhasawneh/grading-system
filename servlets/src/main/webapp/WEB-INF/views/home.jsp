<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.List" %>
<%@ page import ="java.util.ArrayList" %>
<%@ page import ="models.StudentCourse" %>
<%@ page import ="models.CourseStatistics" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Grading System</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.19/tailwind.min.css">
</head>
<body class="bg-gray-900 text-white">
    <div class="container mx-auto px-4 py-8">
        <h1 class="text-4xl font-semibold mb-4">Welcome, <%= session.getAttribute("userName") %>!</h1>

        <%-- Admin view --%>
        <% if ("ADMIN".equals(session.getAttribute("userRole"))) { %>
            <div class="bg-gray-800 p-4 rounded-lg mb-4">
                <form action="/addUser" method="post">
                    <h2 class="text-xl font-semibold mb-2">Add user:</h2>

                    <label class="text-white">Name:</label>
                    <input type="text" id="name" name="name" required class="w-full bg-gray-700 border border-gray-600 rounded px-3 py-2 text-white mb-2">

                    <label class="text-white">Email:</label>
                    <input type="email" id="email" name="email" required class="w-full bg-gray-700 border border-gray-600 rounded px-3 py-2 text-white mb-2">

                    <label class="text-white">Password:</label>
                    <input type="password" id="password" name="password" required class="w-full bg-gray-700 border border-gray-600 rounded px-3 py-2 text-white mb-2">

                    <input type="submit" value="Add as student" name="student" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">
                    <input type="submit" value="Add as teacher" name="teacher" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">
                </form>
            </div>
            <div class="bg-gray-800 p-4 rounded-lg mb-4">
                <form action="/addCourse" method="post">
                     <h2 class="text-xl font-semibold mb-2">Add course:</h2>

                     <label class="text-white">Course Name:</label>
                     <input type="text" id="courseName" name="courseName" required class="w-full bg-gray-700 border border-gray-600 rounded px-3 py-2 text-white mb-2">

                     <label class="text-white">Course Teacher ID:</label>
                     <input type="text" id="courseTeacherId" name="courseTeacherId" required class="w-full bg-gray-700 border border-gray-600 rounded px-3 py-2 text-white mb-2">

                     <input type="submit" value="Add course" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">

                     <% if (request.getAttribute("addCourseError") != null) { %>
                         <p class="text-red-500 mb-4"><%= request.getAttribute("addCourseError") %></p>
                     <% } %>
                </form>
            </div>
        <% } %>

        <%-- Teacher view --%>
        <% if ("TEACHER".equals(session.getAttribute("userRole"))) { %>
            <div class="bg-gray-800 p-4 rounded-lg mb-4">
                <form action="/updateMark" method="post">
                    <h2 class="text-xl font-semibold mb-2">Enter mark</h2>
                    <label class="text-white">Student ID:</label>
                    <input type="text" id="studentId" name="studentId" required class="w-full bg-gray-700 border border-gray-600 rounded px-3 py-2 text-white mb-2">

                    <label class="text-white">Course Name:</label>
                    <input type="text" id="courseName" name="courseName" required class="w-full bg-gray-700 border border-gray-600 rounded px-3 py-2 text-white mb-2">

                    <label class="text-white">Mark:</label>
                    <input type="number" id="mark" name="mark" required class="w-full bg-gray-700 border border-gray-600 rounded px-3 py-2 text-white mb-2">

                    <input type="submit" value="Submit mark" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">
                    <% if (request.getAttribute("updateMarkError") != null) { %>
                       <p class="text-red-500 mb-4"><%= request.getAttribute("updateMarkError") %></p>
                    <% } %>
                </form>
            </div>
        <% } %>

        <%-- Student view --%>
        <% if ("STUDENT".equals(session.getAttribute("userRole"))) { %>
            <div class="bg-gray-800 p-4 rounded-lg mb-4">
                <h3 class="text-lg mb-2">Your marks are:</h3>
                 <table class="table-auto w-full">
                     <thead>
                         <tr>
                           <th class="px-4 py-2">Course Name</th>
                           <th class="px-4 py-2">Mark</th>
                           <th class="px-4 py-2">Class Maximum</th>
                           <th class="px-4 py-2">Class Minimum</th>
                           <th class="px-4 py-2">Class Average</th>
                         </tr>
                     </thead>
                     <tbody>
                         <% List<StudentCourse> studentCourses = (List<StudentCourse>) request.getAttribute("studentCourses"); %>
                             <% List<CourseStatistics> courseStatistics = (List<CourseStatistics>) request.getAttribute("stats"); %>
                                <% for (int i = 0; i < studentCourses.size(); i++) { %>
                                   <tr>
                                      <td class="border px-4 py-2"><%= studentCourses.get(i).getCourseName() %></td>
                                      <td class="border px-4 py-2"><%= (studentCourses.get(i).getMark() == 0) ? "N/A" : studentCourses.get(i).getMark() %></td>
                                      <% CourseStatistics stat = courseStatistics.get(i); %>
                                      <td class="border px-4 py-2"><%= (stat.getMax() == 0) ? "N/A" : stat.getMax() %></td>
                                      <td class="border px-4 py-2"><%= (stat.getMin() == 0) ? "N/A" : stat.getMin() %></td>
                                      <td class="border px-4 py-2"><%= (stat.getAvg() == 0) ? "N/A" : stat.getAvg() %></td>
                                   </tr>
                             <% } %>
                     </tbody>
                 </table>
            </div>
            <div class="bg-gray-800 p-4 rounded-lg mb-4">
                <form action="/enroll" method="post">
                    <h2 class="text-xl font-semibold mb-2">Enroll in course:</h2>

                    <label class="text-white">Course Name:</label>
                    <input type="text" id="courseName" name="courseName" required class="w-full bg-gray-700 border border-gray-600 rounded px-3 py-2 text-white mb-2">

                    <input type="submit" value="Enroll" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">
                </form>
            </div>
        <% } %>

        <a href="/logout" class="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600">Logout</a>
    </div>
</body>
</html>
