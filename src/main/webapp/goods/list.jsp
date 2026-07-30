<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row{
	width: 960px;
	margin: 0px auto;
}
p {
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}
</style>
</head>
<body>
<div class="container">
	<div class="row">
	  <c:forEach var="vo" items="${list }">
		<div class="col-md-3">
		    <div class="thumbnail">
		      <a href="../goods/detail_before.do?no=${vo.no }">
		        <img src="${vo.goods_poster }" style="width:250px; height: 140px; object-fit:cover">
		        <div class="caption">
		          <p>${vo.goods_name }</p>
		        </div>
		      </a>
		    </div>
		</div>
	  </c:forEach>
	</div>
	<div class="row text-center" style="margin-top: 10px">
		<ul class="pagination">
		  <c:if test="${startpage>1 }">
			<li><a href="../goods/list.do?page=${startpage-1 }">&laquo;</a></li>
		  </c:if>
		  <c:forEach var="i" begin="${startpage }" end="${endpage }">
			<li ${curpage==i?'class=active':'' }><a href="../goods/list.do?page=${i }">${i }</a></li>
		  </c:forEach>
		  <c:if test="${endpage<totalpage }">
			<li><a href="../goods/list.do?page=${endpage+1 }">&raquo;</a></li>
		  </c:if>
		</ul>
	</div>
	<div class="row" style="margin-top: 10px">
		<h3>최근 방문 목록</h3>
		<hr>
		<c:if test="${size<1 }">
			<h4>방문 기록이 없습니다...</h4>
		</c:if>
		<c:if test="${size>0 }">
		  <div class="text-right" style="margin-bottom: 20px">
		  	<a href="../goods/cookie_delete.do" class="btn btn-xs btn-danger">방문 기록 삭제</a>
		  </div>
		  <c:forEach var="cvo" items="${cList }" begin="0" end="4">
		  	<div style="width: 180px; height: 180px; margin-left: 5px; display: inline">
		  	  <a href="../goods/detail_before.do?no=${cvo.no }">
		  		<img src="${cvo.goods_poster }" style="width: 180px; height: 180px">
		      </a>
		  	</div>
		  </c:forEach>	
		</c:if>
	</div>
</div>
</body>
</html>