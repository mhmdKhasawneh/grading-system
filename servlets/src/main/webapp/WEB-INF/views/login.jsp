<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Grading System</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-900 text-white">
    <div class="flex justify-center items-center h-screen">
        <div class="bg-gray-800 p-8 shadow-md rounded-lg">
            <h1 class="text-2xl font-semibold mb-4">Login</h1>
            <form action="login" method="post">
                <div class="mb-4">
                    <label for="email" class="block font-medium">Email:</label>
                    <input type="text" id="email" name="email" required class="w-full bg-gray-700 border border-gray-600 rounded px-3 py-2 text-white">
                </div>

                <div class="mb-4">
                    <label for="password" class="block font-medium">Password:</label>
                    <input type="password" id="password" name="password" required class="w-full bg-gray-700 border border-gray-600 rounded px-3 py-2 text-white">
                </div>

                <% if (request.getAttribute("Error") != null) { %>
                    <p class="text-red-500 mb-4"><%= request.getAttribute("Error") %></p>
                <% } %>

                <div>
                    <input type="submit" value="Login" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">
                </div>
            </form>
        </div>
    </div>
</body>
</html>
