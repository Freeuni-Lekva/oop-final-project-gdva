<%@ page import="quizpackage.model.DBHandler" %>
<%@ page import="java.util.List" %>
<%@ page import="quizpackage.model.quizzes.*" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="/CSS/quiz.css" rel="stylesheet" type="text/css">
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
<form action = "FinishOnePageQuizServlet" method = "get">
<div id="entireDiv">
    <h1><%= quiz.getTitle() %></h1>

    <div id = "questionsAndButtonsDiv">
        <div id="questionsDiv">
        <div class="questions-container">
            <% if(!quiz.isOrdered()){
                Collections.shuffle(questions);
            }
            if(quiz.areQuestionsOnSinglePage()){
                int count = 0;
                session.setAttribute("Questions",questions);
            %>

            <% for (Question question : questions) {
                count++;
            if(question.getQuestionClass().equals("QuestionResponse")) {%>
                <div class="question">
                    <p class="question-text"><%=count + "." +question.getQuestionText() %></p>
                </div>
            <input type="text" name="<%=count%>" class="answer-field" placeholder="Enter your answer">
            <%}else if(question.getQuestionClass().equals("FillTheBlank")){ %>
                <div class="question">
                    <p class="question-text"><%=count + "." + question.getQuestionText() %></p>
                    <input type="text" name="<%=count%>" class="answer-field" placeholder="Enter your answer">
                </div>
            <%} else if (question.getQuestionClass().equals("PictureResponse")) { %>
                <%
                    PictureResponse pictureQuestion = (PictureResponse) question;
                %>
            <div class="question">
                <p class="question-text"><%=count + "." + question.getQuestionText() %></p>
                <div class="image-question-container">
                    <img src="<%= pictureQuestion.getImage() %>" alt="Question Image">
                </div>
                <input type="text" name="<%=count%>" class="answer-field" placeholder="Enter your answer">
            </div>
            <% } else if (question.getQuestionClass().equals("MultipleChoiceSingleAnswer")) { %>
            <div class="question">
                <p class="question-text"><%=count + "." + question.getQuestionText() %></p>
                <ul class="answer-options">
                    <%
                       MultipleChoiceSingleAnswer multipleChoiceQuestion = (MultipleChoiceSingleAnswer)question;
                       List<String> answers = multipleChoiceQuestion.getPossibleAnswers();
                       handler.debug(answers.size()+"");
                    %>
                    <% for (int i = 0; i < answers.size(); i++) { %>
                    <li>
                        <label>
                            <input type="radio" name="<%=count%>" value="<%= answers.get(i) %>">
                            <%= answers.get(i) %>
                        </label>
                    </li>
                    <% } %>
                </ul>
            </div>
            <% } } %>
        </div>

    </div>
        <div id="buttonsDiv">
            <button class="custom-button">Finish Quiz</button>
        </div>
        </div>
    <% } else { // multiple %>


    <%}%>
</div>
</form>
</body>
</html>
