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
					<td rowspan="5" width="40%">
						<img src="${vo.goods_poster }" style="height: 250px">
					</td>
					<td colspan="2">
						<h3>${vo.goods_name }</h3>
					</td>
				</tr>
				<tr>
					<td width="10%" class="text-center success">가격</td>
					<td width="50%">${vo.goods_price }</td>
				</tr>
				<tr>
					<td width="10%" class="text-center success">할인율</td>
					<td width="50%">${vo.goods_discount }</td>
				</tr>
				<tr>
					<td width="10%" class="text-center success">할인가</td>
					<td width="50%">${vo.goods_first_price }</td>
				</tr>
				<tr>
					<td width="10%" class="text-center success">배달</td>
					<td width="50%">${vo.goods_delivery }</td>
				</tr>
				<tr>
					<td colspan="3">
						<h5>${vo.goods_sub }</h5>
					</td>
				</tr>
				<tr>
					<td colspan="3" class="text-right">
						<a href="../goods/list.do" class="btn btn-sm btn-danger">목록</a>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
</body>
</html>