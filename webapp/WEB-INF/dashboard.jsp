<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<title>Overflow Dashboard</title>
	</head>
	<body>
		<div class="container">
			<h1>Questions Dashboard</h1>
			<table class="table">
				<thead>
					<tr>
						<th>Questions</th>
						<th>Tags</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${questions}" var="q">
						<tr>
							<td><a href="/questions/${q.id}"><c:out value="${q.question}"/></a></td>
							<td>
								<c:forEach items="${q.tags}" var="tag">
									<c:out value="${tag.subject}"/>,
								</c:forEach>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<a href="/questions/new">New Question</a>
		</div>
	</body>
</html>