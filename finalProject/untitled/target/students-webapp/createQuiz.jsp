<%@ page import="java.util.List" %>
<%@ page import="quizpackage.model.quizzes.*" %>
<%@ page import="quizpackage.model.DBHandler" %><%--
  Created by IntelliJ IDEA.
  User: giorgi kobakhia
  Date: 8/10/2023
  Time: 5:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="/CSS/createQuiz.css" rel="stylesheet" type="text/css">
</head>
<body>
<%--<video autoplay muted loop id = "backgrVid">--%>
<%--    <source src="/CSS/backgr.mp4" type="video/mp4">--%>
<%--</video>--%>
<div id = "entireDiv">
    <div id = "topDiv">
        <h1>Create Quiz</h1>
    </div>
    <div id ="bodyDiv">
        <div id = "leftDiv">
            <div id="leftFirst">
                <div id = "leftCenter">
                    <p>question type</p>

                    <form method = "Post" action = "addQuestionServlet">
                        <input style="display:none" id = "numberOfChoices" name = "numberOfChoices" value = "0">
                        <select id="mySelect" name = "mySelect">
                            <option value="1">question response</option>
                            <option value="2">fill the blank</option>
                            <option value="3">picture response</option>
                            <option value="4">multiple choice</option>
                        </select>

                        <%--                <details>--%>
                        <%--                    <summary>question1</summary>--%>
                        <%--&lt;%&ndash;                    <div>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;                        <p>nika nika</p>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;                        <img src="baro.jpg">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;                    </div>&ndash;%&gt;--%>
                        <%--                </details>--%>
                        <%--                <details>--%>
                        <%--                    <summary>question2</summary>--%>
                        <%--&lt;%&ndash;                    <div>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;                        <p>baro baro</p>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;                        <img src="beka.jpg">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;                    </div>&ndash;%&gt;--%>
                        <%--                </details>--%>

                        <div class= "questionCreate" id = "questionCreate">


                            <div id="div1" style="display: block;">
                                <label for="questionResponseText">question: </label>
                                <input type="text" id = "questionResponseText" name = "questionResponseText">
                                <label for="questionResponseAnswer">answer: </label>
                                <input type="text" id = "questionResponseAnswer" name = "questionResponseAnswer">
                                <label for="questionResponseGrade">grade: </label>
                                <input type="text" id = "questionResponseGrade" name = "questionResponseGrade">
                            </div>
                            <div id="div2" style="display: none;">
                                <p>question: </p>
                                <input type="text" id = "fillText" name = "fillText">
                                <p>answer: (separate answers with ~ and no spaces)</p>
                                <input type="text" id = "fillAnswer" name = "fillAnswer">
                                <p>grade: </p>
                                <input type="text" id = "fillGrade" name = "fillGrade">
                            </div>
                            <div id="div3" style="display: none;">
                                <p>question: </p>
                                <input type="text" id ="pictureText" name = "pictureText">
                                <p>image: </p>
                                <input type="text" id = "pictureURL" name = "pictureURL">
                                <p>answer: </p>
                                <input type = "text" id = "pictureAnswer" name = "pictureAnswer">
                                <p>grade: </p>
                                <input type="text" id = "pictureGrade" name = "pictureGrade">
                            </div>
                            <div id="div4" style="display: none;">
                                <p>question: </p>
                                <input type="text" id="multipleText" name = "multipleText">
                                <p>grade: </p>
                                <input type="text" id = "multipleGrade" name = "multipleGrade">
                                <p>number of choices: </p>
                                <input type="text" id="choicesNumber" name="choicesNumber">
                                <div id="choices"></div>
                                <p>answer: </p>
                                <input type="text" id="multipleAnswer" name = "multipleAnswer">
                                <script>
                                    const numInputs = document.querySelector('#choicesNumber');
                                    const inputsContainer = document.querySelector('#choices');
                                    let choicesCounter = 0;
                                    const inp = document.querySelector('#numberOfChoices');
                                    numInputs.addEventListener('input', (event) => {
                                        inp.value = choicesCounter.toString();
                                        const num = parseInt(event.target.value, 10);
                                        inputsContainer.innerHTML = '';

                                        for (let i = 0; i < num; i++) {
                                            const input = document.createElement('input');
                                            input.type = 'text';
                                            choicesCounter++;
                                            input.id = 'choice' + choicesCounter.toString();
                                            input.name = 'choice' + choicesCounter.toString();
                                            inputsContainer.appendChild(input);
                                        }
                                    });
                                </script>
                            </div>
                            <input type="submit" value = "add question" id = "addQuestionButton">
                            <script>
                                const mySelect = document.querySelector('#mySelect');
                                const div1 = document.querySelector('#div1');
                                const div2 = document.querySelector('#div2');
                                const div3 = document.querySelector('#div3');
                                const div4 = document.querySelector('#div4');
                                mySelect.addEventListener('change', (event) => {
                                    const selectedValue = event.target.value;

                                    if (selectedValue === '1') {
                                        div1.style.display = 'block';
                                        div2.style.display = 'none';
                                        div3.style.display = 'none';
                                        div4.style.display = 'none';
                                    } else if (selectedValue === '2') {
                                        div1.style.display = 'none';
                                        div2.style.display = 'block';
                                        div3.style.display = 'none';
                                        div4.style.display = 'none';
                                    } else if (selectedValue === '3') {
                                        div1.style.display = 'none';
                                        div2.style.display = 'none';
                                        div3.style.display = 'block';
                                        div4.style.display = 'none';
                                    }
                                    else if(selectedValue === '4'){
                                        div1.style.display = 'none';
                                        div2.style.display = 'none';
                                        div3.style.display = 'none';
                                        div4.style.display = 'block';
                                    }
                                });
                            </script>

                        </div>
                    </form>
                    <%--    <script>--%>
                    <%--        let questionsCounter = 0;--%>
                    <%--        let template = document.querySelector('#questionCreate');--%>
                    <%--        let leftCenter = document.querySelector('#leftCenter');--%>
                    <%--        let addButton = document.querySelector('#addQuestionButton');--%>
                    <%--        const selector = document.querySelector('#mySelect');--%>
                    <%--        let info = document.querySelector('#numberOfQuestions');--%>
                    <%--        addButton.addEventListener('click',function(){--%>
                    <%--            questionsCounter++;--%>
                    <%--            info.textContent = questionsCounter.toString();--%>
                    <%--            let newDetails = document.createElement('details');--%>


                    <%--            let newSummary = document.createElement('summary');--%>
                    <%--            let p = document.createElement("p");--%>
                    <%--            if(selector.value === "1"){--%>
                    <%--                let questionText = document.querySelector('#questionResponseText');--%>
                    <%--                newSummary.textContent = questionText.value;--%>
                    <%--                let text = document.createElement("p");--%>
                    <%--                text.textContent = "Question: " + questionText.value;--%>
                    <%--                let answer = document.createElement("");--%>
                    <%--                p.textContent = 'Question Type: Question Response';--%>
                    <%--            }--%>
                    <%--            if(selector.value === "2"){--%>
                    <%--                let questionText = document.querySelector('#fillText');--%>
                    <%--                newSummary.textContent = questionText.value;--%>
                    <%--                p.textContent = 'Question Type: Fill The Blank';--%>
                    <%--            }--%>
                    <%--            if(selector.value === "3"){--%>
                    <%--                let questionText = document.querySelector('#pictureText');--%>
                    <%--                newSummary.textContent = questionText.value;--%>
                    <%--                p.textContent = 'Question Type: Picture Response';--%>
                    <%--            }--%>
                    <%--            if(selector.value === "4"){--%>
                    <%--                let questionText = document.querySelector('#multipleText');--%>
                    <%--                newSummary.textContent = questionText.value;--%>
                    <%--                p.textContent = 'Question Type: Multiple Choice';--%>
                    <%--            }--%>
                    <%--            let newDiv = template.cloneNode(true);--%>


                    <%--            newDetails.appendChild(newSummary);--%>
                    <%--            newDetails.appendChild(p);--%>
                    <%--            newDetails.appendChild(newDiv);--%>


                    <%--            leftCenter.appendChild(newDetails);--%>
                    <%--        });--%>
                    <%--    </script>--%>
                </div>
            </div>

            <div id="leftEdge">
                <div id="leftEdgeCenter">
                    <%
                        DBHandler handler = (DBHandler) application.getAttribute("handler");
                        List<Question> questions = (List<Question>) session.getAttribute("questions");
                        int sz = questions == null ? 0 : questions.size();
                        for(int i = 0; i<sz;i++){
                            out.println("<details>");
                            out.print("<summary style=\"background-color:#EFF6E0;position:relative;width:100%;border-color:inherit;\">"+questions.get(i).getQuestionText()+"</summary>");
                            out.println("<p>Grade is: "+questions.get(i).getQuestionGrade()+"</p>");
                            if(questions.get(i).getClass().toString().equals("class quizpackage.model.quizzes.QuestionResponse")){
                                QuestionResponse qr = (QuestionResponse) questions.get(i);
                                out.println("<p>Answer is: "+qr.getQuestionAnswer()+"</p>");
                            }
                            else if(questions.get(i).getClass().toString().equals("class quizpackage.model.quizzes.FillTheBlank")){
                                FillTheBlank fb = (FillTheBlank) questions.get(i);
                                out.println("<p>Answer is: " + fb.getQuestionAnswer()+"</p>");
                            }
                            else if(questions.get(i).getClass().toString().equals("class quizpackage.model.quizzes.PictureResponse")){
                                PictureResponse pr = (PictureResponse) questions.get(i);
                                out.println("<img style=\"position:relative; width:100%;\" src = \""+ pr.getImage() +"\">");
                                out.println("<p>Answer is: " + pr.getQuestionAnswer()+"</p>");
                            }
                            else if(questions.get(i).getClass().toString().equals("class quizpackage.model.quizzes.MultipleChoiceSingleAnswer")){
                                MultipleChoiceSingleAnswer mc = (MultipleChoiceSingleAnswer) questions.get(i);

                                out.println("<p>"+mc.getQuestionText()+"</p>");
                                out.println("<p>"+mc.getQuestionAnswer()+"</p>");
                            }
                            out.println("</details>");
                        }
                    %>
                </div>

            </div>
        </div>
        <div id="rightHolder">
            <div id="rightDiv">
                <form action="createQuizServlet" method="post">
                    <p>Quiz Properties: </p>
                    <div id = "properties">
                        <p>Quiz Title: </p>
                        <input type="text" placeholder="enter quiz title" id="quizTitle" name="quizTitle">
                        <p>Quiz Description: </p>
                        <input type = "text" placeholder = "enter quiz description" id="quizDescription" name="quizDescription">
                        <p>Question order</p>
                        <select name="questionOrder">
                            <option>randomized</option>
                            <option>ordered</option>
                        </select>
                        <p>Question alignment</p>
                        <select name = "questionAlignment">
                            <option>one page</option>
                            <option>multiple pages</option>
                        </select>
                        <p>Answer type</p>
                        <select name="answerType">
                            <option>immediate</option>
                            <option>after submit</option>
                        </select>
                        <input type="submit" id="createQuizButton" name="createQuizButton" value="Create Quiz">
                    </div>
                </form>
            </div>
        </div>

    </div>

</div>

</body>
</html>
