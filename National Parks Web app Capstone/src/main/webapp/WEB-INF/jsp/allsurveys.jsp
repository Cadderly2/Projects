<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<h1>Favorite Parks Voted By Fellow Hikers</h1>
<c:forEach items="${surveys.keySet()}" var="park">
	<div class="surveyDisplay">
		<div class="surveyAndImage">
			<c:url var="surveyPicture" value="/img/parks/${park.parkCode}.jpg"/>
			<c:url var="surveyImageLink" value="/detail?parkId=${park.parkCode}"/>
			<a href="${surveyImageLink}">
				<img src="${surveyPicture}"/>
			</a>
			<div class="survey">
				<h3>${park.parkName}</h3>
				<p>Located in ${park.state}</p>
				<p>Votes: ${surveys[park]}</p>
			</div>
		</div>
		<p class="surveyDescription">${park.parkDescription}</p>
	</div>	
</c:forEach>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />