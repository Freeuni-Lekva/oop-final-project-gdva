<%@ page import="quizpackage.model.DBHandler" %>
<%@ page import="java.util.List" %>
<%@ page import="quizpackage.model.quizzes.*" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        #backgrVid {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%; /* Use viewport width */
            height: 100%; /* Use viewport height */
            z-index: -1;
        }

        /* Reset some default styles for consistency */
        body, h1, p, ul, li {
            margin: 0;
            padding: 0;
        }

        /* Center the content vertically and horizontally */
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f0f0f0;
        }

        /* Style the quiz container */
        #entireDiv {
            text-align: center;
            color: azure;
            width: 100%; /* Take up the entire viewport width */
        }

        /* Style the questions container */
        #questionsDiv {
            display: flex;
            justify-content: center;
            align-items: center;
            width: 100%;
            max-width: 800px; /* Adjust the maximum width as needed */
            padding: 20px;
            box-sizing: border-box;
        }

        /* Style the container for multiple questions */
        .questions-container {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
            gap: 20px;
        }

        /* Style the question text */
        .question-text {
            font-size: 18px;
            margin-bottom: 10px;
        }

        /* Style the answer field */
        .answer-field {
            border: 1px solid #ccc;
            border-radius: 6px;
            padding: 10px;
            font-size: 16px;
            width: 100%;
            box-sizing: border-box;
            margin-bottom: 15px;
            transition: border-color 0.3s ease;
        }

        .answer-field:focus {
            outline: none;
            border-color: #3498db;
        }

        /* Style the submit button */
        .submit-button {
            background-color: #3498db;
            color: #ffffff;
            border: none;
            border-radius: 6px;
            padding: 8px 16px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .submit-button:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>
<video autoplay muted loop id="backgrVid">
    <source src="/CSS/backgr.mp4" type="video/mp4">
</video>

<%
    DBHandler handler = (DBHandler)application.getAttribute("handler");
    String id = request.getParameter("id");
    Quiz quiz = handler.getQuiz(Integer.parseInt(id));
    List<Question> questions = quiz.getQuestions();
%>

<div id="entireDiv">
    <h1><%= quiz.getTitle() %></h1>
    <div id="questionsDiv">
        <div class="questions-container">
            <% for (Question question : questions) { %>
            <div class="question">
                <p class="question-text"><%= question.getQuestionText() %></p>
                <input type="text" class="answer-field" placeholder="Enter your answer">
            </div>
            <% } %>
        </div>
    </div>
</div>

</body>
</html>
