<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<title>New Question</title>
	</head>
	<body>
		<div class="container">
			<h1>What is your question?</h1>
			<form action="/questions" method="post">
				<p>
					<label>Question:</label>
					<textarea name="question"></textarea>
				</p>
				<c:forEach items="${errors}" var="error">
					<c:if test="${error.key == 'question'}">
						<p><c:out value="${error.value}"/></p>
					</c:if>
				</c:forEach>
				<p>
					<label>Tags:</label>
					<input type="text" name="tagsStr"/>
				</p>
				<c:forEach items="${errors}" var="error">
					<c:if test="${fn:contains(error.key, 'tag')}">
						<p><c:out value="${error.value}"/></p>
					</c:if>
				</c:forEach>
				<input type="submit" value="Submit" class="btn btn-dark" />
			</form>
		</div>
	</body>
</html>