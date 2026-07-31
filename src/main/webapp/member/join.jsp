<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row {
  margin: 0px auto;
  width: 600px;
}
h3 {
  text-align: center;
}
</style>
</head>
<body>
<div class="container">
	<div class="row">
		<h3>회원가입</h3>
		<form method="post" action="../member/join_ok.do">
		<table class="table">
			<tbody>
				<tr>
					<th width="25%" class="text-center">ID</th>
					<td width="75%">
						<input type="text" class="input-sm" name="userid" size="20" required>
					</td>
				</tr>
				<tr>
					<th width="25%" class="text-center">PW</th>
					<td width="75%">
						<input type="password" class="input-sm" name="userpwd" size="20" required>
					</td>
				</tr>
				<tr>
					<th width="25%" class="text-center">이름</th>
					<td width="75%">
						<input type="text" class="input-sm" name="username" size="20" required>
					</td>
				</tr>
				<tr>
					<th width="25%" class="text-center">성별</th>
					<td width="75%">
						<input type="radio" name="sex" value="남자" checked> 남자
						<input type="radio" name="sex" value="여자"> 여자
					</td>
				</tr>
				
				<tr>
					<td colspan="2" class="text-center">
						<button type="submit" class="btn btn-sm btn-primary">회원가입</button>
						<button type="button" class="btn btn-sm btn-danger"
						onclick="javascript:history.back()"
						>취소</button>
					</td>
				</tr>
			</tbody>
		</table>
		</form>
	</div>
</div>
</body>
</html>