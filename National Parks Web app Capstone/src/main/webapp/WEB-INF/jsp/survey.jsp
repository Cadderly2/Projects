<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<h1>Park Survey</h1>

<div id="surveyDiv">
	<c:url var="surveyUrl" value="/survey"/>
	<div id="surveyForm">
	<form:form method="POST" action="${surveyUrl}" modelAttribute="surveyData">
		<div class="inputAndLabel">
			<label for="favoritePark" class="bold">Favorite National Park</label>
			<select id="favoritePark" name="favoritePark" class="form-control">
				<c:forEach items="${parks}" var="park">
					<option value="${park.parkCode}">${park.parkName}</option>
				</c:forEach>
			</select>
			<form:errors class="error" path="favoritePark"/>
		</div>
		<div class="inputAndLabel">
			<label for="userEmail" class="bold">Email</label>
			<form:input type="email" id="userEmail" required="required" name="email" path="email" class="form-control"/>
			<form:errors class="error" path="email"/>
		</div>
		<div class="inputAndLabel">
			<label for="stateOfResidence" class="bold">State Of Residence</label>
			<form:select id="stateOfResidence" name="stateOfResidence" class="form-control" path="stateOfResidence">
				<c:forEach items="${states}" var="state">
					<option>${state}</option>
				</c:forEach>
			</form:select>
			<form:errors path="stateValid" class="error"/>
		</div>
		<div class="inputAndLabel">
			<label for="activityLevel" class="bold">Activity Level</label>
			<form:select id="activityLevel" name="activityLevel" class="form-control" path="activityLevel">
				<c:forEach items="${activityLevels}" var="activity">
					<option>${activity}</option>
				</c:forEach>
			</form:select>
			<form:errors path="activityLevelValid" class="error" />
		</div>
		<button type="submit">Submit</button>
	</form:form>
	</div>
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />