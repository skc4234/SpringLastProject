<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row {
	width: 800px;
	margin: 0px auto;
}
</style>
</head>
<body>
<div class="container">
	<div class="row">
		<table class="table">
			<tbody>
				<tr>
					<td rowspan="8" width="40%">
						<img src="${vo.poster }" style="height: 350px">
					</td>
					<td colspan="2">
						<h3>${vo.name }&nbsp;<span style="color: orange">${vo.score }</span></h3>
					</td>
				</tr>
				<tr>
					<td width="10%" class="text-center success">종류</td>
					<td width="50%">${vo.type }</td>
				</tr>
				<tr>
					<td width="10%" class="text-center success">주소</td>
					<td width="50%">${vo.address }</td>
				</tr>
				<tr>
					<td width="10%" class="text-center success">가격대</td>
					<td width="50%">${vo.price }</td>
				</tr>
				<tr>
					<td width="10%" class="text-center success">영업시간</td>
					<td width="50%">${vo.time }</td>
				</tr>
				<tr>
					<td width="10%" class="text-center success">전화번호</td>
					<td width="50%">${vo.phone }</td>
				</tr>
				<tr>
					<td width="10%" class="text-center success">주차</td>
					<td width="50%">${vo.parking }</td>
				</tr>
				<tr>
					<td width="10%" class="text-center success">테마</td>
					<td width="50%">${vo.theme }</td>
				</tr>
				<tr>
					<td colspan="3">
						${vo.content }
					</td>
				</tr>
				<tr>
					<td colspan="3" class="text-right">
						<a href="../main/main.do" class="btn btn-sm btn-danger">목록</a>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
</body>
</html>