<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:forEach items="${parks}" var="park">
	<div class="homePagePark">
		<div class="container">
			<c:url value="/img/parks/${park.parkCode}.jpg" var="parkImage"/>
			<c:url value="/detail?parkId=${park.parkCode}" var="detailLink"/>
			<a href="${detailLink}">
				<img class="homePageImage" src="${parkImage}"/>
			</a>
			<div class="text-block">
				<h4 class="bold">Click Image For More Details</h4>
			</div>
		</div>
		<div class="rightSideHomePage">
			<div class="homePageName">
				<p class="bold">${park.parkName}</p>
				<p>Located in ${park.state}</p>
			</div>
			<p>${park.parkDescription}</p>
		</div>
	</div>
</c:forEach>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />