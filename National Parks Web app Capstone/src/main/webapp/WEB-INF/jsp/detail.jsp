<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<div class="parkDetail">
		<div class="homePageName">
			<h1>${park.parkName}</h1>
			<p>Located in ${park.state}</p>
		</div>
		
		<c:url value="/img/parks/${park.parkCode}.jpg" var="parkImage" />
		<img class="homePageImage" src="${parkImage}" />
		
		<div class="detailQuoteAndDesc">
			<p id="em">${park.quote}-${park.quoteSource}</p>
			<p>${park.parkDescription}</p>
		</div>
	<div id="tableDiv">
		<h3 class="bold">Park Information</h3>
		<table class="table">
			<tr>
				<td class="bold">Acreage</td>
				<td>${park.acreage} acres</td>
			</tr>
			<tr>
				<td class="bold">Elevation In Feet</td>
				<td>${park.elevationInFeet} feet</td>
			</tr>
			<tr>
				<td class="bold">Miles Of Trail</td>
				<td>${park.milesOfTrail} miles</td>
			</tr>
			<tr>
				<td class="bold"># Of Campsites</td>
				<td>${park.numberOfCampsites}</td>
			</tr>
			<tr>
				<td class="bold">Climate</td>
				<td>${park.climate}</td>
			</tr>
			<tr>
				<td class="bold">Year Founded</td>
				<td>${park.yearFounded}</td>
			</tr>
			<tr>
				<td class="bold">Annual Visitors</td>
				<td>${park.visitorCount}</td>
			</tr>
			<tr>
				<td class="bold">Entry Fee</td>
				<td>$ ${park.entryFee}</td>
			</tr>
			<tr>
				<td class="bold"># Of Animal Species</td>
				<td>${park.numberOfAnimalSpecies} species</td>
			</tr>
		</table>
	</div>


<div class="temperatureForm">
	<c:url var="detailFormUrl" value="/detail" />
	<form method="POST" action="${detailFormUrl}">
		<label>Temperature Display Preference</label> <select name="temp">
			<option>F</option>
			<option>C</option>
		</select> <input type="hidden" name="parkId" value="${park.parkCode}" />
		<button type="submit">Change</button>
	</form>
</div>

	<div id="weather">
		
		<c:forEach items="${forecasts}" var="forecast" varStatus="status">
			<c:if test="${status.count <= 5}">
			<div class="weatherDay">
			<c:if test="${status.first}">
				<p id="todayLabel">
					<h4 class="bold">Today</h4>
				</p>
			</c:if>
			<c:url value="/img/weather/${forecast.forecastImage}.png" var="forecastImage" />
			<img src="${forecastImage}" />
			<span class="temperatures">
			<p>
				<span class="bold">High</span>
				<c:choose>
					<c:when test="${sessionScope.tempPreference == 'C'}">
						<span>${forecast.highInC} C</span>
					</c:when>
					<c:otherwise>
						<span>${forecast.highInF} F</span>
					</c:otherwise>
				</c:choose>
			</p>
			<p>
				<span class="bold">Low</span>
				<c:choose>
					<c:when test="${sessionScope.tempPreference == 'C'}">
						<span>${forecast.lowInC} C</span>
					</c:when>
					<c:otherwise>
						<span>${forecast.lowInF} F</span>
					</c:otherwise>
				</c:choose>
			</p>
			</span>
			<span class="recommendations">
				<c:if test="${forecast.messages.size() > 0}">
				<p class="bold">Recommendations: </p>
				<c:forEach var="message" items="${forecast.messages}">
					<p>${message}</p>
				</c:forEach>
				</c:if>
			</span>
		</div>
		</c:if>
	</c:forEach>
</div>


</div>



<c:import url="/WEB-INF/jsp/common/footer.jsp" />