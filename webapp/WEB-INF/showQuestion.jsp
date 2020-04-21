<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<title>Question Profile</title>
	</head>
	<body>
		<div class="container">
			<h1><c:out value="${q.question}"/></h1>
			<!-- TAGS -->
			<div class="col">
				<div class="row">
					<h5>Tags: </h5>
					<div>
						<c:forEach items="${q.tags}" var="tag">
							<span class="badge badge-dark">
								<c:out value="${tag.subject}"/>
							</span>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="row">
				<!-- LIST OF ANSWERS -->
				<div class="col">
					<table class="table">
						<thead>
							<tr>
								<th>Answers</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${q.answers}" var="a">
								<tr>
									<td><c:out value="${a.answer}"/></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- ADD ANSWER -->
				<div class="col">
					<h5>Add your answer:</h5>
					<form action="/questions/${q.id}" method="post">
						<p>
							<label>Answer:</label>
							<textarea name="answer"></textarea>
						</p>
						<p><c:out value="${error}"/></p>
						<input type="submit" value="Answer it!!" class="btn btn-dark" />
					</form>
				</div>
			</div>
		</div>
	</body>
</html>