function displayName(){
    let btn = document.querySelector('#accountname')
    let title = document.querySelector('#title')
    console.log(btn.innerText)

    let xhr = new XMLHttpRequest()
    console.log(btn)
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 & xhr.status ===200){
            let employee = JSON.parse(xhr.response)
            console.log(employee)
            let firstName = employee.firstName
            let lastName = employee.lastName
            let type = employee.type
            console.log(type)
            btn.innerHTML = firstName + ' ' + lastName

            if(type==1){
                title.innerHTML = 'Manager'
            }

            if(type==2){
                title.innerHTML = 'Employee'
            }
        }
        
        
    }

    xhr.open('GET', 'http://localhost:8080/TRMS/LoginServlet')
    xhr.send()
}

window.onload = () =>{
     displayName()
 }