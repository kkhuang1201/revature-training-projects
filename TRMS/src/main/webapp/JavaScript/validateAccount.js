let hasErrorMessage = true;
console.log('I am here hahahaha')
function DisplayErrorMessage(event){
	let p = document.querySelector('form')
	console.log(p)
	let xhr = new XMLHttpRequest()
	xhr.onreadystatechange = function(){
		if(xhr.readyState === 4 && xhr.status === 2000){
			console.log(resp);
			window.location = 'http://localhost:8080/TRMS/index.html'
		}
	}

xhr.open('GET', 'http://localhost:8080/TRMS/api/login')
xhr.send()
}

let button = document.querySelector('button')
button.addEventListener('onclick', DisplayErrorMessage)
