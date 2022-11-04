<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/header.jsp"%>

<link rel="stylesheet" type="text/css" href="${root}/css/marker.css">

 </head>

<body>
	<%@ include file="../common/nav.jsp"%>


	<input type="hidden" name="action" value="getDongDode">
	<select id="sido" onchange="getGugun(this.value)"
		style="background-color: gray">
		<option>시</option>
	</select>
	<select id="gugun" onchange="getDong(this.value)"
		style="background-color: gray">
		<option>구/군</option>
	</select>
	<select id="dong" onchange="getDongCode(this.value)"
		style="background-color: gray">
		<option>동</option>
	</select>



	<!-- 지도를 표시할 div 입니다 -->
	<div id="map" class="container" style="width: 100%; height: 550px;"></div>

	<br>
	<br>
	<br>

	<table class="table">
		<thead>
			<tr>
				<th>no</th>
				<th>daelAmount</th>
				<th>dealDate</th>
				<th>area</th>
				<th>floor</th>
				<th>cancelDealType</th>
				<th>aptCode</th>
				<th></th>
			</tr>
		</thead>
		<tbody id="dealTable">
		</tbody>
	</table>

	<%@ include file="../common/footer.jsp"%>

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8847514c621fc1830dd53ecf9f1b36f4"></script>
	<script>
	window.onload=function(){
		
		getSido();
	}

	async function getSido(){
		let result = await fetch("${root}/house/sido")
		let sidoList = await result.json()
		
		sidoList.forEach(el=>{
			sido.innerHTML += `<option value=${"${el.dongCode}"}>${"${el.sidoName}"}</option>`
		})
	}

	async function getGugun(value){
		let result = await fetch("${root}/house/gugun?sidoCode="+value)
		let sidoList = await result.json()
		gugun.innerHTML="";
		sidoList.forEach(el=>{
			gugun.innerHTML += `<option value=${"${el.dongCode}"}>${"${el.gugunName}"}</option>`
			
		})
	}
	
	async function getDong(value){
		let result = await fetch("${root}/house/dong?gugunCode="+value)
		let sidoList = await result.json()
		dong.innerHTML="";
		sidoList.forEach(el=>{
			dong.innerHTML += `<option value=${"${el.dongCode}"}>${"${el.dongName}"}</option>`
		})
	}
	
	async function getTradeCode(value){
		
		let result = await fetch("${root}/house/tradeCode?Code="+value)
		let sidoList = await result.json()
		dealTable.innerHTML="";
		
		console.log("sido", sidoList);
		
		sidoList.forEach(houseDeal=>{
			dealTable.innerHTML += `<tr><td>${"${houseDeal.no}"}</td>
				<td>${"${houseDeal.dealAmount}"}</td>
				<td>${"${houseDeal.dealYear}"}년${"${houseDeal.dealMonth}"}월${"${houseDeal.dealDay}"}일</td>
				<td>${"${houseDeal.area}"}</td>
				<td>${"${houseDeal.floor}"}</td>
				<td>${"${houseDeal.cancelDealType}"}</td>
				<td>${"${houseDeal.aptCode}"}</td>
				</tr>`
			 
		})
	 }
	
	async function getDongCode(value){
		console.log("?")
		let result = await fetch("${root}/house/dongCode?Code="+value);
		console.log("이건 result입니다",result);
		let data = await result.json();
		
		window.navigator.geolocation.getCurrentPosition(function(position) {
			makeMarker(position, data);
		});
		
		getTradeCode(value)
	 }
	
	//오버레이를 하나만 유지하기 위한 변수
	let singleOverlay = null;
	
	//내 위치 받아오기
	function makeMarker(position, data) {
		console.log(position)
		console.log(data);
		let mapContainer = document.getElementById("map"), // 지도의 중심좌표
		mapOption = {
			center : new kakao.maps.LatLng(position.coords.latitude, position.coords.longitude), // 지도의 중심좌표
			level : 5, // 지도의 확대 레벨
		};

		let map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성

		//우측 줌 컨트롤러
		var zoomControl = new kakao.maps.ZoomControl();

		//우측 내 위치 버튼
		let myLocation = document.createElement("button");
		myLocation.setAttribute("draggable", "false");
		myLocation.setAttribute("type", "button");
		myLocation.setAttribute("class", "myLocation");
		myLocation.onclick = function() {
			map.setCenter(new kakao.maps.LatLng(position.coords.latitude, position.coords.longitude));
			map.setLevel(5, {
				animate : true
			});
		};

		map.addControl(myLocation, kakao.maps.ControlPosition.RIGHT);
		map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
		
		//다중 마커
		let positions = [];
		let apts = data.apt;
		for(let i=0; i<apts.length; i++) {
			positions[i] = {title: apts[i].apartmentName, latlng: new kakao.maps.LatLng(apts[i].lat, apts[i].lng)};
		}
		
		if(positions.length > 0) {
			console.log("move");
			map.setCenter(new kakao.maps.LatLng(positions[0].latlng.Ma, positions[0].latlng.La));
			map.setLevel(5, {
				animate : true
			});
		}

		for (let i = 0; i < positions.length; i++) {
			let d = positions[i];
			displayMarker(d, map, data.address, data.apt[i], data.dongCode);
		}
	}

	// 지도에 마커를 표시하는 함수
	function displayMarker(data, map, add, apt, dCode) {
		console.log(add);
		console.log(apt);
		let marker = new kakao.maps.Marker({
			map : map,
			position : data.latlng,
		});

		//마커 클릭 시 나타날 오버레이
		let overlay = new kakao.maps.CustomOverlay({
			yAnchor : 3,
			position : marker.getPosition(),
		});
		
		//마커 클릭 시 오버레이 생성
		kakao.maps.event.addListener(marker, "click", function() {
			if (singleOverlay) {
				singleOverlay.setMap(null);
			}
			overlay.setMap(map);
			singleOverlay = overlay;
		});

		let content = document.createElement("div");
		content.setAttribute("class", "wrap");

		let info = document.createElement("div");
		info.setAttribute("class", "info");

		let title = document.createElement("div");
		title.setAttribute("class", "title");

		title.innerText = data.title;

		let close = document.createElement("div");
		close.setAttribute("class", "close");
		
		//오버레이 닫기
		close.addEventListener("click", function() {
			overlay.setMap(null);
		});

		let body = document.createElement("div");
		body.setAttribute("class", "body");

		let desc = document.createElement("div");
		desc.setAttribute("class", "desc");

		let ellipsis = document.createElement("div");
		ellipsis.setAttribute("class", "ellipsis");
		ellipsis.innerText = add + " " + apt.roadName;

		let jibun = document.createElement("div");
		jibun.setAttribute("class", "jibun ellipsis");
		jibun.innerText = "(지번) " + apt.jibun;
		
		let fv = document.createElement("button");
		fv.setAttribute("draggable", "false");
		fv.setAttribute("type", "button");
		fv.setAttribute("class", "myLocation");
		fv.onclick = function() {
			console.log("click");
		};

		desc.appendChild(jibun);
		desc.appendChild(ellipsis);

		body.appendChild(desc);
		body.appendChild(fv);
		title.appendChild(close);

		info.appendChild(title);
		info.appendChild(body);

		content.appendChild(info);

		overlay.setContent(content);
	}
	</script>
	
</body>

</html>
