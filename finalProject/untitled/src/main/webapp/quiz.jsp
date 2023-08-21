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
    int ID = Integer.parseInt(id);
    session.setAttribute("quizID",id);
    String buttonValue = request.getParameter("buttonValue");
    Quiz quiz = handler.getQuiz(ID);
    List<Question> questions = quiz.getQuestions();
    String startTime = request.getParameter("start_time");

    Integer multiID = (Integer)session.getAttribute("multiID");
    if(buttonValue == null) multiID = null;
    handler.debug("Start time in quiz.jsp: "+startTime);
%>
<form action="FinishOnePageQuizServlet" method="get">
<div id="entireDiv">
    <h1><%= quiz.getTitle() %></h1>
    <div id = "questionsAndButtonsDiv">
        <div id="questionsDiv">
        <div class="questions-container">
            <%
                int count = 0;
            %>
            <%
            if(quiz.areQuestionsOnSinglePage()){
                if(!quiz.isOrdered()){
                    Collections.shuffle(questions);
                }
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
            <input type="hidden" name="start_time" value="<%=startTime%>">
            <button class="custom-button">Finish Quiz</button>
        </div>
    </div>

    <% } else { // multiple %>
    <%
        session.setAttribute("Questions",questions);
        Question question = null;
        if(multiID == null){
            question = questions.get(0);
            session.setAttribute("multiID",0);
            multiID = 0;
        } else {
            if(buttonValue != null && buttonValue.equals("Next")){
                multiID++;
            }
            question = questions.get(multiID);
            session.setAttribute("multiID",multiID);
        }
    %>
    <div id = "questionAndButtonsDiv">
        <div id="questionDiv">
            <div class="questions-container">
                <%session.setAttribute("curIdx",(multiID+1));%>
                 <%if(question.getQuestionClass().equals("QuestionResponse")) {%>
                <div class="question">
                    <p class="question-text"><%=(multiID+1) + "." +question.getQuestionText() %></p>
                </div>
                <input type="text" name="<%=(multiID+1)%>" class="answer-field" placeholder="Enter your answer">
                <%}else if(question.getQuestionClass().equals("FillTheBlank")){ %>
                <div class="question">
                    <p class="question-text"><%=(multiID+1) + "." + question.getQuestionText() %></p>
                    <input type="text" name="<%=(multiID+1)%>" class="answer-field" placeholder="Enter your answer">
                </div>
                <%} else if (question.getQuestionClass().equals("PictureResponse")) { %>
                <%
                    PictureResponse pictureQuestion = (PictureResponse) question;
                %>
                <div class="question">
                    <p class="question-text"><%=(multiID+1) + "." + question.getQuestionText() %></p>
                    <div class="image-question-container">
                        <img src="<%= pictureQuestion.getImage() %>" alt="Question Image">
                    </div>
                    <input type="text" name="<%=(multiID+1)%>" class="answer-field" placeholder="Enter your answer">
                </div>
                <% } else if (question.getQuestionClass().equals("MultipleChoiceSingleAnswer")) { %>
                <div class="question">
                    <p class="question-text"><%=(multiID+1) + "." + question.getQuestionText() %></p>
                    <ul class="answer-options">
                        <%
                            MultipleChoiceSingleAnswer multipleChoiceQuestion = (MultipleChoiceSingleAnswer)question;
                            List<String> answers = multipleChoiceQuestion.getPossibleAnswers();
                        %>
                        <% for (int i = 0; i < answers.size(); i++) { %>
                        <li>
                            <label>
                                <input type="radio" name="<%=(multiID+1)%>" value="<%= answers.get(i) %>">
                                <%= answers.get(i) %>
                            </label>
                        </li>
                        <% } %>
                    </ul>
                </div>
                <% } %>
            </div>
        </div>
    </div>
    <div class="buttons-container">
        <%if(multiID != questions.size()-1){
            if(!quiz.immediateAnswerNeeded()){
        %>
        <div class = "centered-button">
        <div id="buttonsDiv2">
            <input type="hidden" name="buttonValue" value="Next">
            <input type="hidden" name="start_time" value="<%=startTime%>">
            <button class="custom-button">Next</button>
        </div>
        </div>
        <%
            } else {
        %>
        <div class = "centered-button">
            <div id="buttonsDiv4">
                <input type="hidden" name="buttonValue" value="Confirm">
                <input type="hidden" name="questionId" value="<%=(multiID)%>">
                <input type="hidden" name="start_time" value="<%=startTime%>">
                <button class="custom-button">Confirm</button>
            </div>
        </div>
        <%} } else {%>
        <div class = "centered-button">
        <div id="buttonsDiv3">
            <input type="hidden" name="buttonValue" value="Finish">
            <input type="hidden" name="start_time" value="<%=startTime%>">
            <button class="custom-button">Finish Quiz</button>
        </div>
        </div>
        <%}%>
    </div>
    <% } %>
</div>
</form>
</body>
</html>
