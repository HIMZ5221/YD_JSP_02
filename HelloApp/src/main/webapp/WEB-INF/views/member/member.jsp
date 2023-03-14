<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<h3>Tiles가 적용된 member 페이지</h3>
<div>
	<table class="table">
		<tbody id="list">
		
		</tbody>
	</table>
</div>
<script>

	fetch('memberListAjax.do') // json 포맷으로 데이터 정상
	.then(function(resolve){
		console.log(resolve); // body: readablestream
		return resolve.json(); // json포맷에 따라 javascript object변경
	})
	.then(function (result){
		console.log(result);
		for(let i=0; i < result.length; i++){
			let id = result[i].id;
			let tr = document.createElement('tr');
			for(let prop in result[i]){
				let td = document.createElement('td');
				td.innerText = result[i][prop];
				tr.append(td);
			}
			//삭제버튼
			let delBtn = document.createElement('button');
			delBtn.innerText = '삭제';
			delBtn.addEventListener('click', function(){
				console.log(this);
				console.log(this.parentElement.parentElement.children[0].innerText);
				let delId = this.parentElement.parentElement.children[0].innerText;

				fetch('memberRemoveAjax.do', {
					method: 'post',
					headers: {'Content-Type': 'application/x-www-form-urlencoded'}, //key=val&key=val
					body: 'id=' + delId
				})
				.then(resolve => resolve.json())
				.then(result => {
					console.log(result);
					if (result.retCode == 'Success'){
						alert('성공!');
						this.parentElement.parentElement.remove();
					}else if (result.retCode == 'Fail'){
						alert('실패!');
					}
				})
				.catch(reject => console.log(reject))

				//this.parentElement.parentElement.remove();
			});
			let td = document.createElement('td');
			td.append(delBtn);
			tr.append(td);
			document.getElementById('list').append(tr);
		}

		document.getElementById
		
	})
	
	.catch(function(reject){
		console.error(reject);
	})
</script>